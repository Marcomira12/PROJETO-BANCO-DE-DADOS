package app;

import java.util.Scanner;

import service.CategoriaService;
import service.ClienteService;
import service.CompraServiceFornecedor;
import service.FornecedorService;
import service.ProdutoService;
import service.RelatorioService;
import service.VendaServiceCliente;

public class MenuPrincipal {
    private ClienteService clienteService;
    private ProdutoService produtoService;
    private CategoriaService categoriaService;
    private FornecedorService fornecedorService;
    private CompraServiceFornecedor compraService;
    private VendaServiceCliente vendaServiceCliente;
    private RelatorioService relatorioService;
    private Scanner sc = new Scanner(System.in);

   
    public MenuPrincipal(ClienteService clienteService, ProdutoService produtoService,
			CategoriaService categoriaService, FornecedorService fornecedorService,
			CompraServiceFornecedor compraService, VendaServiceCliente vendaServiceCliente,
			RelatorioService relatorioService) {
		super();
		this.clienteService = clienteService;
		this.produtoService = produtoService;
		this.categoriaService = categoriaService;
		this.fornecedorService = fornecedorService;
		this.compraService = compraService;
		this.vendaServiceCliente = vendaServiceCliente;
		this.relatorioService = relatorioService;
	}


	public void exibir() {
        int opcao;

        do {
            System.out.println("===== SISTEMA VAREJO =====");
            System.out.println("1 - Clientes");
            System.out.println("2 - Produtos");
            System.out.println("3 - Categorias");
            System.out.println("4 - Fornecedores");
            System.out.println("5 - Compras / Entrada de estoque");
            System.out.println("6 - Vendas / Saida de estoque");
            System.out.println("7 - Relatorio do sistema ");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
            case 1:
                new MenuCliente(clienteService).exibir(sc);
                break;
            case 2:
                new MenuProduto(produtoService, categoriaService).exibir(sc);
                break;
            case 3:
                new MenuCategoria(categoriaService).exibir(sc);
                break;
            case 4:
                new MenuFornecedor(fornecedorService).exibir(sc);
                break;
            case 5:
                new MenuCompra(compraService,fornecedorService,produtoService).exibir(sc);
                break;
            case 6:
                new MenuVenda(vendaServiceCliente,produtoService,clienteService).exibir(sc);
                break;
            case 7:
            	new MenuRelatorio(relatorioService).exibir(sc);
            	break;
            case 0:
                System.out.println("Sistema encerrado.");
                break;
            default:
                System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }
}
