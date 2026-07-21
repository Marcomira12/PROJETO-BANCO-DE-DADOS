package app;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class MenuCommand {
	private final Object objeto;
	private final Method metodo;
	private final ParametroResolver resolver;

	public MenuCommand(Object objeto, Method metodo, ParametroResolver resolver) {
		super();
		this.objeto = objeto;
		this.metodo = metodo;
		this.resolver = resolver;
	}

	public void executar() {

		Object[] parametros = resolver.resolverParametros(metodo);
		try {
			metodo.invoke(objeto, parametros);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}