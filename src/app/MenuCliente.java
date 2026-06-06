package app;

import java.util.Scanner;

import model.Cliente;
import service.ClienteService;

public class MenuCliente {
	private ClienteService clienteService;

	public MenuCliente(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	public void exibir(Scanner sc) {
		int opcao;

		do {
			System.out.println("\n===== MENU CLIENTE =====");
			System.out.println("1 - Cadastrar cliente");
			System.out.println("2 - Buscar cliente por ID");
			System.out.println("3 - Buscar cliente por CPF");
			System.out.println("4 - Listar clientes ");
			System.out.println("5 - Atualizar cliente ");
			System.out.println("6 - Detelar Cliente ");
			System.out.println("0 - Voltar");
			System.out.print("Escolha: ");

			opcao = sc.nextInt();
			sc.nextLine();

			switch (opcao) {
			case 1:
				cadastrar(sc);
				break;
			case 2:
				buscarPorId(sc);
				break;
			case 3:
				buscarPorCpf(sc);
				break;
			case 4:
				clienteService.listar();
				break;
			case 5:
				atualizarDadosCliente(sc);
				break;
			case 6:
				realizarDelete(sc);
				break;
			case 0:
				return;
			default:
				System.out.println("Opção inválida.");
			}

		} while (opcao != 0);
	}

	private void realizarDelete(Scanner sc) {
		clienteService.listar();
		System.out.println("Digite id do cliente para delete");
		Integer id = sc.nextInt();
		sc.nextLine();
		System.out.println("Deseja Realmente deletar ? " + clienteService.buscarClienteId(id));
		System.out.println("Digite sim/nao ");
		String valor= sc.nextLine();
		
		if (valor.equals("sim")) {
			clienteService.deletar(id);
			
		} else {
			System.out.println("Delete Interrompido ");
		}
		
	}

	private void cadastrar(Scanner sc) {
		Cliente cliente = new Cliente();

		System.out.print("Nome: ");
		cliente.setNome(sc.nextLine());

		System.out.print("CPF: ");
		cliente.setCpf(sc.nextLine());

		System.out.print("Email: ");
		cliente.setEmail(sc.nextLine());

		Cliente salvo = clienteService.salvar(cliente);

		System.out.println("Cliente processado:");
		System.out.println(salvo);
	}

	private void atualizarDadosCliente(Scanner sc) {
		System.out.println("Clientes disponíveis:");
		clienteService.listar();

		try {

			System.out.println("\nDigite o ID do cliente:");
			int id = sc.nextInt();
			sc.nextLine();
			if(clienteService.buscarClienteId(id).equals(null)) {
				return;
			}

			System.out.println("Digite o novo nome:");
			String nome = sc.nextLine();

			System.out.println("Digite o novo email:");
			String email = sc.nextLine();

			Cliente cliente = new Cliente();
			cliente.setId(id);
			cliente.setNome(nome);
			cliente.setEmail(email);

			clienteService.atualizarDadosCliente(cliente);

			System.out.println("Cliente atualizado com sucesso!");

		} catch (Exception e) {

			System.out.println("Cliente não encontrado.");
			sc.nextLine();
		}
	}

	private void buscarPorId(Scanner sc) {
		System.out.print("ID: ");
		Integer id = sc.nextInt();
		sc.nextLine();

		Cliente cliente = clienteService.buscarClienteId(id);

		if (cliente == null) {
			System.out.println("Cliente não encontrado.");
		} else {
			System.out.println(cliente);
		}
	}

	private void buscarPorCpf(Scanner sc) {
		System.out.print("CPF: ");
		String cpf = sc.nextLine();

		Cliente cliente = clienteService.buscarClienteCPF(cpf);

		if (cliente == null) {
			System.out.println("Cliente não encontrado.");
		} else {
			System.out.println(cliente);
		}
	}
}
