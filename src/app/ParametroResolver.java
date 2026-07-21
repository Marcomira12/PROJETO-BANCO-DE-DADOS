package app;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

import reflexao.Obrigatorio;

public class ParametroResolver {
	private Scanner scanner;

	public ParametroResolver(Scanner scanner) {
		super();
		this.scanner = scanner;
	}

	public Object[] resolverParametros(Method metodo) {
		try {
			Class<?>[] tipos = metodo.getParameterTypes();
			Object[] args = new Object[tipos.length];
			for (int i = 0; i < args.length; i++) {
				args[i] = criarObjeto(tipos[i]);
			}
			return args;
		} catch (Exception e) {
			System.out.println("Erro ao executar comando.");
			e.printStackTrace();
		}
		return null;
	}

	private Object criarObjeto(Class<?> tipo) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		if (tipo == Scanner.class) {
			return new Scanner(System.in);
		}
		if (tipo == String.class) {
			System.out.println("Digite o Valor: ");
			return scanner.nextLine();
		}
		if (tipo == Integer.class || tipo == int.class) {
			System.out.println("Digite o número: ");
			return Integer.parseInt(scanner.nextLine());
		}
		if (tipo == Long.class || tipo == long.class) {
			System.out.println("Digite o número: ");
			return Long.parseLong(scanner.nextLine());
		}
		Object obj = tipo.getDeclaredConstructor().newInstance();
		preencherCampos(obj);
		return obj;
	}

	private void preencherCampos(Object obj) throws IllegalArgumentException, IllegalAccessException {
		Class<?> clazz = obj.getClass();
		for (Field field : clazz.getDeclaredFields()) {
			if (!field.isAnnotationPresent(Obrigatorio.class)) {
				continue;
			}

			field.setAccessible(true);
			System.out.println("Digite " + field.getName() + ": ");
			String valor = scanner.nextLine();
			Object convertido = converter(valor, field.getType());
			field.set(obj, convertido);
		}

	}

	private Object converter(String valor, Class<?> type) {
		if (type == String.class) {
			return valor;
		}
		if (type == Integer.class) {
			return Integer.parseInt(valor);
		}
		if (type == Long.class || type == long.class) {
			return Long.parseLong(valor);
		}
		return null;
	}
}
