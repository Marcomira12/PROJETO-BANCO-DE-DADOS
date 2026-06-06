package app;

import java.math.BigDecimal;
import java.util.Scanner;

import model.Produto;
import service.CategoriaService;
import service.ProdutoService;

public class MenuProduto {
	private ProdutoService produtoService;
	private CategoriaService categoriaService;

	public MenuProduto(ProdutoService produtoService, CategoriaService categoriaService) {
		this.produtoService = produtoService;
		this.categoriaService = categoriaService;
	}

	public void exibir(Scanner sc) {
		int opcao;

		do {
			System.out.println("\n===== MENU PRODUTO =====");
			System.out.println("1 - Cadastrar produto");
			System.out.println("2 - Buscar produto por ID");
			System.out.println("3 - Listar Produtos ");
			System.out.println("4 - Atualizar Produto ");
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
				produtoService.listar();
				break;
			case 4:
				updateProduto(sc);
				break;
			case 0:
				break;
			default:
				System.out.println("Opção inválida.");
			}

		} while (opcao != 0);
	}

	private void updateProduto(Scanner sc) {
		System.out.println("Produtos disponiveis");
		produtoService.listar();

		System.out.println("Categorias disponiveis");
		categoriaService.listar();

		try {
			System.out.println("Selecione o ID do produto para alteração: ");
			int id = sc.nextInt();
			sc.nextLine();

			System.out.println("Digite o novo nome: ");
			String nome = sc.nextLine();

			System.out.println("Novo valor do produto: ");
			BigDecimal preco = sc.nextBigDecimal();

			System.out.println("ID da nova categoria: ");
			int idCategoria = sc.nextInt();

			Produto produto = new Produto(id, nome, preco, idCategoria);

			produtoService.atualizarProduto(produto);

			System.out.println("Produto atualizado com sucesso!");

		} catch (Exception e) {
			System.out.println("Valores inválidos");
			sc.nextLine();
		}

	}

	private void cadastrar(Scanner sc) {
		Produto produto = new Produto();

		System.out.print("Nome: ");
		produto.setNome(sc.nextLine());

		System.out.print("Preço: ");
		produto.setValorAtualProduto(sc.nextBigDecimal());

		System.out.print("ID da categoria: ");
		Integer categoriaId = sc.nextInt();
		sc.nextLine();

		if (categoriaService.buscarCategoria(categoriaId) == null) {
			System.out.println("Categoria não existente");
			return;
		}
		produto.setIdCategoria(categoriaId);

		produtoService.salvar(produto);

		System.out.println("Produto processado com sucesso.");
	}

	private void buscarPorId(Scanner sc) {
		System.out.print("ID do produto: ");
		Integer id = sc.nextInt();
		sc.nextLine();

		Produto produto = produtoService.buscarPorId(id);

		if (produto == null) {
			System.out.println("Produto não encontrado.");
		} else {
			System.out.println("Produto: " + produto.getNome());
			System.out.println("Estoque: " + produto.getEstoque());
			System.out.println("Preço: " + produto.getValorAtualProduto());
		}
	}
}
