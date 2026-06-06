package app;

import java.util.Scanner;

import entetiesDao.Nota_CompraDTO;
import model.Fornecedor;
import model.Nota_Compra;
import service.CompraServiceFornecedor;
import service.FornecedorService;
import service.ProdutoService;

public class MenuCompra {
    private CompraServiceFornecedor compraService;
    private FornecedorService serviceFornecedor;
    private ProdutoService produtoService;

 
    public MenuCompra(CompraServiceFornecedor compraService, FornecedorService serviceFornecedor,
			ProdutoService produtoService) {
		super();
		this.compraService = compraService;
		this.serviceFornecedor = serviceFornecedor;
		this.produtoService = produtoService;
	}

	public void exibir(Scanner sc) {
        int opcao;

        do {
            System.out.println("\n===== MENU COMPRA / ENTRADA DE ESTOQUE =====");
            System.out.println("1 - Registrar compra de fornecedor");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
            case 1:
                registrarCompra(sc);
                break;
            case 0:
                break;
            default:
                System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    private void registrarCompra(Scanner sc) {
        Nota_CompraDTO dto = new Nota_CompraDTO();
        Fornecedor fornecedor = new Fornecedor();
        serviceFornecedor.listarFornecedores();
        System.out.print("ID do fornecedor: ");
        fornecedor.setId(sc.nextInt());
        sc.nextLine();

        dto.setFornecedor(fornecedor);

        String continuar;
        
        do {
        	produtoService.listar();
            System.out.print("ID do produto comprado: ");
            Integer produtoId = sc.nextInt();

            System.out.print("Quantidade comprada: ");
            Integer quantidade = sc.nextInt();
            sc.nextLine();

            compraService.adicionarItemAoLote(dto, produtoId, quantidade);

            System.out.print("Adicionar outro produto nesta nota? (s/n): ");
            continuar = sc.nextLine();

        } while (continuar.equalsIgnoreCase("s"));

        Nota_Compra nota = compraService.executarCompraParaEstoque(dto);

        if (nota == null) {
            System.out.println("Compra não realizada.");
        } else {
            System.out.println("Compra finalizada com sucesso.");
            System.out.println(nota.toString());
        }
    }
}
