package app;

import entetiesDao.RelatorioCompraDTO;
import entetiesDao.RelatorioVendaDTO;
import model.Categoria;
import model.Item_Compra;
import model.Item_Venda;
import model.Nota_Compra;
import model.Nota_Venda;
import repository.CategoriaRepositoryPostgres;
import repository.ClienteRepositoryPostgres;
import repository.ConexaoBanco;
import repository.FornecedorRepositoryPostegres;
import repository.NotaCompraRepository;
import repository.NotaRepository;
import repository.NotaVendaRepository;
import repository.PostegresSQL;
import repository.ProdutoRepositoryPostegres;
import repository.RelatorioRepositoryPostegres;
import repository.RepositoryBanco;
import repository.RepositoryBancoCliente;
import repository.RepositoryBancoFornecedor;
import repository.RepositoryBancoProduto;
import repository.RepositoryBancoRelatorio;
import service.CategoriaService;
import service.ClienteService;
import service.CompraServiceFornecedor;
import service.FornecedorService;
import service.ProdutoService;
import service.RelatorioService;
import service.VendaServiceCliente;

public class Menu {
	public static void main(String[] args) {
		ConexaoBanco conexao = new PostegresSQL();

		RepositoryBancoCliente clienteRepo = new ClienteRepositoryPostgres(conexao);
		RepositoryBancoProduto produtoRepo = new ProdutoRepositoryPostegres(conexao);
		RepositoryBanco<Categoria, Integer> categoriaRepo = new CategoriaRepositoryPostgres(conexao);
		NotaRepository<Nota_Venda, Item_Venda, Integer> notaVendaRepo = new NotaVendaRepository(conexao);
		ClienteService clienteService = new ClienteService(clienteRepo);
		ProdutoService produtoService = new ProdutoService(produtoRepo);
		CategoriaService categoriaService = new CategoriaService(categoriaRepo);
		NotaRepository<Nota_Compra, Item_Compra, Integer> repositoryCompra = new NotaCompraRepository(conexao);
		RepositoryBancoFornecedor fornecedorBanco = new FornecedorRepositoryPostegres(conexao);
		FornecedorService fornecedorService = new FornecedorService(fornecedorBanco);
		RepositoryBancoRelatorio<RelatorioCompraDTO , RelatorioVendaDTO> repositoryRelatorio= new RelatorioRepositoryPostegres(conexao);
		RelatorioService relatorio=new RelatorioService(repositoryRelatorio);
		
		
		CompraServiceFornecedor compraService = new CompraServiceFornecedor(repositoryCompra, produtoService,
				fornecedorService);

		VendaServiceCliente vendaServiceCliente = new VendaServiceCliente(notaVendaRepo, produtoService,
				clienteService);

		MenuPrincipal menu = new MenuPrincipal(clienteService, produtoService, categoriaService, fornecedorService,
				compraService, vendaServiceCliente,relatorio);

		menu.exibir();
	}
}