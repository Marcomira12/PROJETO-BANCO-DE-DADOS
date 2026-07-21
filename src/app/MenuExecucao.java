package app;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Scanner;

public class MenuExecucao {

	private final Scanner scanner ;
	private final MetodoClasse menuClasse ;
	private final ServiceFactory factory ;
	private final ParametroResolver resolver;
	

	
	
	public MenuExecucao(Scanner scanner, MetodoClasse menuClasse, ServiceFactory factory, ParametroResolver resolver) {
		super();
		this.scanner = scanner;
		this.menuClasse = menuClasse;
		this.factory = factory;
		this.resolver = resolver;
	}

	public void iniciar() {

        while (true) {
            Map<Integer, Class<?>> classes = menuClasse.carregarClasses();

            menuClasse.mostrarClasses(classes);

            System.out.print("Escolha: ");
            int opcaoClasse = Integer.parseInt(scanner.nextLine());

            if (opcaoClasse == 0) {
                System.out.println("Sistema encerrado.");
                break;
            }

            Class<?> classeEscolhida = classes.get(opcaoClasse);

            if (classeEscolhida == null) {
                System.out.println("Opção inválida.");
                continue;
            }

            abrirSubMenu(classeEscolhida);
        }
    }

    private void abrirSubMenu(Class<?> classeEscolhida) {
        try {
        	Object service = factory.criar(classeEscolhida);

            while (true) {
                Map<Integer, Method> metodos = menuClasse.carregarMetodos(classeEscolhida);

                menuClasse.mostrarMetodos(metodos);

                System.out.print("Escolha a ação: ");
                int opcaoMetodo = Integer.parseInt(scanner.nextLine());

                if (opcaoMetodo == 0) {
                    break;
                }

                Method metodoEscolhido = metodos.get(opcaoMetodo);

                if (metodoEscolhido == null) {
                    System.out.println("Ação inválida.");
                    continue;
                }

                MenuCommand command = new MenuCommand(service, metodoEscolhido,scanner,resolver);
                command.executar();
            }

        } catch (Exception e) {
            System.out.println("Erro ao abrir menu.");
            e.printStackTrace();
        }
    }

}
