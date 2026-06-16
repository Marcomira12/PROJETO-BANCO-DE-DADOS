package app;

import java.lang.reflect.Method;

public class MenuCommand {
	private final Object objeto;
    private final Method metodo;

    public MenuCommand(Object objeto, Method metodo) {
        this.objeto = objeto;
        this.metodo = metodo;
    }

    public void executar() {
        try {
            metodo.invoke(objeto);
        } catch (Exception e) {
            System.out.println("Erro ao executar comando.");
            e.printStackTrace();
        }
    }
}
