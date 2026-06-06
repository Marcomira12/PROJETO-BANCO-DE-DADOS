package app;

import java.util.Scanner;

import model.Categoria;
import service.CategoriaService;

public class MenuCategoria {
	private CategoriaService categoriaService;

    public MenuCategoria(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    public void exibir(Scanner sc) {
        int opcao;

        do {
            System.out.println("\n===== MENU CATEGORIA =====");
            System.out.println("1 - Cadastrar categoria");
            System.out.println("2 - Listar categoria ");
            System.out.println("3 - Deletar Categoria");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    cadastrar(sc);
                    break;
                case 2:
                	categoriaService.listar();
                	break;
                case 3:
                	deletarCategoria(sc);
                	break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    private void deletarCategoria(Scanner sc) {
    	categoriaService.listar();
		System.out.println("Digite o id da categoria para deletar :");
		Integer id=sc.nextInt();
		sc.nextLine();
		Categoria cat=categoriaService.buscarCategoria(id);
		if(cat.equals(null)) {
			System.out.println("Categoria não encontrada");
			return;
		}
		System.out.println("Deseja deletar a categoria s/n? "+ cat.toString());
		String valor=sc.nextLine();
		if(valor.equals("s")) {
			categoriaService.deletar(id);
			System.out.println("Deletado com sucesso ");
		}else {
			System.out.println("Delete interrompido ");
		}
	}

	private void cadastrar(Scanner sc) {
        System.out.print("Nome da categoria: ");
        String nome = sc.nextLine();

        System.out.print("Descrição: ");
        String descricao = sc.nextLine();

        Categoria categoria = new Categoria(nome, descricao);

        categoriaService.salvar(categoria);

        System.out.println("Categoria cadastrada com sucesso.");
    }
}
