package app;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.reflections.Reflections;

import reflexao.ClasseTipo;
import reflexao.Comando;

public class MetodoClasse {

    public Map<Integer, Class<?>> carregarClasses() {
        Reflections reflections = new Reflections("service");

        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(ClasseTipo.class);

        Map<Integer, Class<?>> mapa = new HashMap<>();

        for (Class<?> classe : classes) {
        	ClasseTipo menu = classe.getAnnotation(ClasseTipo.class);
            mapa.put(menu.order(), classe);
        }

        return mapa;
    }

    public Map<Integer, Method> carregarMetodos(Class<?> classe) {
        Map<Integer, Method> mapa = new HashMap<>();

        for (Method metodo : classe.getDeclaredMethods()) {
            if (metodo.isAnnotationPresent(Comando.class)) {
                Comando comando = metodo.getAnnotation(Comando.class);
                mapa.put(comando.order(), metodo);
            }
        }

        return mapa;
    }

    public void mostrarClasses(Map<Integer, Class<?>> classes) {
        System.out.println("=== MENU PRINCIPAL ===");

        for (Integer ordem : classes.keySet()) {
            Class<?> classe = classes.get(ordem);
            ClasseTipo menu = classe.getAnnotation(ClasseTipo.class);

            System.out.println(menu.order() + " - " + menu.descricao());
        }

        System.out.println("0 - Sair");
    }

    public void mostrarMetodos(Map<Integer, Method> metodos) {
        System.out.println("=== AÇÕES ===");

        for (Integer ordem : metodos.keySet()) {
            Method metodo = metodos.get(ordem);
            Comando comando = metodo.getAnnotation(Comando.class);

            System.out.println(comando.order() + " - " + comando.descricao());
        }

        System.out.println("0 - Voltar");
    }
}
