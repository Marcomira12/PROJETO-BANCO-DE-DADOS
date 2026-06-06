package app;

import java.util.Scanner;

import service.RelatorioService;

public class MenuRelatorio {
	private RelatorioService relatorioService;

	public MenuRelatorio(RelatorioService relatorioService) {
		super();
		this.relatorioService = relatorioService;
	}

	public void exibir(Scanner sc) {
    	int opcao;

		do {
			System.out.println("\n===== MENU Relatorio =====");
			System.out.println("1 - Listar Compras");
			System.out.println("2 - Listar Vendas");
			System.out.println("3 - Faturamento ");
			System.out.println("4 - Valor do Estoque ");
			System.out.println("0 - Voltar");
			System.out.print("Escolha: ");

			opcao = sc.nextInt();
			sc.nextLine();

			switch (opcao) {
			case 1:
				relatorioService.listarCompras();
				break;
			case 2:
				relatorioService.listarVendas();
				break;
			case 3:
				relatorioService.faturamento();
				break;
			case 4:
				relatorioService.valorEstoque();
				break;
			case 0:
				break;
			default:
				System.out.println("Opção inválida.");
			}

		} while (opcao != 0);
	}

}
