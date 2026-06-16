package app;

import reflexao.ClasseTipo;
import repository.ConexaoBanco;

public class ServiceFactory {
	private ConexaoBanco conexaoBanco;

	

	public ServiceFactory(ConexaoBanco conexaoBanco) {
		this.conexaoBanco = conexaoBanco;
	}



	public Object criar(Class<?> serviceClass) {
		try {
			ClasseTipo usarRepository = serviceClass.getAnnotation(ClasseTipo.class);

			Class<?> repositoryClass = usarRepository.classe();

			Object repository = repositoryClass.getDeclaredConstructor(ConexaoBanco.class).newInstance(conexaoBanco);

			Object service = serviceClass.getDeclaredConstructor(repositoryClass).newInstance(repository);

			return service;

		} catch (Exception e) {
			throw new RuntimeException("Erro ao criar service", e);
		}
	}
}
