package app;

import java.lang.reflect.Constructor;
import java.util.Set;

import org.reflections.Reflections;

import reflexao.Repositorio;
import reflexao.Service;
import repository.ConexaoBanco;
import repository.RepositoryBanco;

public class ServiceFactory {
	private ConexaoBanco conexaoBanco;

	public ServiceFactory(ConexaoBanco conexaoBanco) {
		this.conexaoBanco = conexaoBanco;
	}

	public Object criar(Class<?> serviceClass) {
		try {
			Service service = serviceClass.getAnnotation(Service.class);
			
			
			if (service == null) {
				throw new RuntimeException("Service sem @ServiceTag: " + serviceClass.getName());
			}
			String tag= service.nome();
			for (Constructor<?> construtor : serviceClass.getConstructors()) {

				Class<?>[] tiposParametros = construtor.getParameterTypes();
				Object[] dependencias = new Object[tiposParametros.length];

				for (int i = 0; i < tiposParametros.length; i++) {
					dependencias[i] = resolverDependencia(tiposParametros[i],tag);
				}

				return construtor.newInstance(dependencias);
			}
			throw new RuntimeException("Nenhum construtor compatível encontrado para " + serviceClass.getSimpleName());
		} catch (Exception e) {
			throw new RuntimeException("Erro ao criar service", e);
		}
	}

	private Object resolverDependencia(Class<?> tipo, String tag) {
		try {
			if (tipo.isAnnotationPresent(Service.class)) {
				return criar(tipo);
			}

			Class<?> repositoryClass = buscarRepositoryCompativel(tipo);
			if (RepositoryBanco.class.equals(tipo)) {
	            repositoryClass = buscarRepositoryPorTag(tag);
	        } else {
	            repositoryClass = buscarRepositoryCompativel(tipo);
	        }

			if (repositoryClass != null) {
				return repositoryClass.getDeclaredConstructor(ConexaoBanco.class).newInstance(conexaoBanco);
			}

			throw new RuntimeException("Dependência não encontrada: " + tipo.getName());

		} catch (Exception e) {
			throw new RuntimeException("Erro ao resolver dependência: " + tipo.getName(), e);
		}
	}

	private Class<?> buscarRepositoryPorTag(String tag) {
	    Reflections reflections = new Reflections("repository");

	    Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Repositorio.class);

	    for (Class<?> classe : classes) {
	        Repositorio repositorio = classe.getAnnotation(Repositorio.class);

	        if (repositorio.nome().equals(tag)) {
	            return classe;
	        }
	    }

	    throw new RuntimeException("Nenhum repository encontrado com a tag: " + tag);
	}

	private Class<?> buscarRepositoryCompativel(Class<?> tipoEsperado) {
		Reflections reflections = new Reflections("repository");

		Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Repositorio.class);

		for (Class<?> classe : classes) {
			if (tipoEsperado.isAssignableFrom(classe)) {
				return classe;
			}
		}

		return null;
	}

}
