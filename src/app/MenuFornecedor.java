package app;

import java.util.Scanner;

import model.Fornecedor;
import service.FornecedorService;

public class MenuFornecedor {
    private FornecedorService fornecedorService;

    public MenuFornecedor(FornecedorService fornecedorService) {
        this.fornecedorService = fornecedorService;
    }

    public void exibir(Scanner sc) {
        int opcao;

        do {
            System.out.println("\n===== MENU FORNECEDOR =====");
            System.out.println("1 - Cadastrar fornecedor");
            System.out.println("2 - Buscar fornecedor por ID");
            System.out.println("3 - Buscar fornecedor por CNPJ");
            System.out.println("4 - Listar fornecedores por nome,id e data de Cadastro");
            System.out.println("5 - Atualizar Fornecedor");
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
                buscarPorCnpj(sc);
                break;
            case 4:
            	listarFornecedores();
            	break;
            case 5:
            	atualizarFornecedor(sc);
            	break;
            case 0:
                break;
            default:
                System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    private void atualizarFornecedor(Scanner sc) {
    	listarFornecedores();
    	System.out.println("Digite o ID para atualizar :");
    	Integer id=sc.nextInt();
    	sc.nextLine();
    	Fornecedor fornecedor=fornecedorService.buscarFornecedorID(id);
    	if(fornecedor.equals(null)) {
    		System.out.println("Fornecedor não encontrado");
    		return;
    	}else {
    		System.out.println("Digite novo nome :");
    		fornecedor.setNome(sc.nextLine());
    		System.out.println("Digite novo e-mail :");
    		fornecedor.setEmail(sc.nextLine());
    		System.out.println("Digite o novo telefone");
    		fornecedor.setTelefone(sc.nextLine());
    		fornecedorService.atualizarFornecedor(fornecedor);
    		System.out.println("Atualizado com sucesso");
    	}
	}

	private void listarFornecedores() {
    	fornecedorService.listarFornecedores();
	}

	private void cadastrar(Scanner sc) {
        Fornecedor fornecedor = new Fornecedor();

        System.out.print("Nome: ");
        fornecedor.setNome(sc.nextLine());

        System.out.print("CNPJ: ");
        fornecedor.setCnpj(sc.nextLine());

        System.out.print("Email: ");
        fornecedor.setEmail(sc.nextLine());

        System.out.print("Telefone: ");
        fornecedor.setTelefone(sc.nextLine());

        Fornecedor salvo = fornecedorService.salvar(fornecedor);

        System.out.println("Fornecedor processado: "+salvo.toString());
        System.out.println(salvo);
    }

    private void buscarPorId(Scanner sc) {
        System.out.print("ID do fornecedor: ");
        Integer id = sc.nextInt();
        sc.nextLine();

        Fornecedor fornecedor = fornecedorService.buscarFornecedorID(id);

        if (fornecedor == null) {
            System.out.println("Fornecedor não encontrado.");
        } else {
            System.out.println(fornecedor.toString());
        }
    }

    private void buscarPorCnpj(Scanner sc) {
        System.out.print("CNPJ: ");
        String cnpj = sc.nextLine();

        Fornecedor fornecedor = fornecedorService.buscarFornecedorCNPJ(cnpj);

        if (fornecedor == null) {
            System.out.println("Fornecedor não encontrado.");
        } else {
            System.out.println(fornecedor);
        }
    }
}
