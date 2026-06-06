package app;

import java.util.Scanner;

import entetiesDao.Nota_VendaDTO;
import model.Cliente;
import model.Nota_Venda;
import service.ClienteService;
import service.ProdutoService;
import service.VendaServiceCliente;

public class MenuVenda {
	private VendaServiceCliente vendaServiceCliente;
	private ProdutoService produtoService;
	private ClienteService clienteService;

	

	public MenuVenda(VendaServiceCliente vendaServiceCliente, ProdutoService produtoService,
			ClienteService clienteService) {
		super();
		this.vendaServiceCliente = vendaServiceCliente;
		this.produtoService = produtoService;
		this.clienteService = clienteService;
	}

	public void exibir(Scanner sc) {
		int opcao;

		do {
			System.out.println("\n===== MENU VENDA =====");
			System.out.println("1 - Realizar venda");
			System.out.println("0 - Voltar");
			System.out.print("Escolha: ");

			opcao = sc.nextInt();
			sc.nextLine();

			switch (opcao) {
			case 1:
				realizarVenda(sc);
				break;
			case 0:
				break;
			default:
				System.out.println("Opção inválida.");
			}

		} while (opcao != 0);
	}

	private void realizarVenda(Scanner sc) {
		produtoService.listar();
		clienteService.listar();
		
		Nota_VendaDTO dto = new Nota_VendaDTO();

		Cliente cliente = new Cliente();

		System.out.print("ID do cliente: ");
		cliente.setId(sc.nextInt());
		sc.nextLine();

		dto.setCliente(cliente);

		String continuar;

		do {
			System.out.print("ID do produto: ");
			Integer produtoId = sc.nextInt();

			System.out.print("Quantidade: ");
			Integer quantidade = sc.nextInt();
			sc.nextLine();

			vendaServiceCliente.adicionarItem(dto, produtoId, quantidade);

			System.out.print("Adicionar outro produto? (s/n): ");
			continuar = sc.nextLine();

		} while (continuar.equalsIgnoreCase("s"));
		
		Nota_Venda nota = vendaServiceCliente.executarVenda(dto);

		if (nota == null) {
			System.out.println("Venda não realizada.");
		} else {
			System.out.println("Venda finalizada com sucesso.");
			System.out.println(nota.toString());
		}
	}
}
