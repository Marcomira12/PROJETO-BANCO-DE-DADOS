package service;

import java.util.List;

import entetiesDao.ListarProdutos;
import model.Fornecedor;
import model.Produto;
import reflexao.ClasseTipo;
import reflexao.Service;
import repository.RepositoryBanco;
import repository.RepositoryBancoProduto;
@ClasseTipo(descricao = "Produto",order = 5)
@Service(nome = "Produto")
public class ProdutoService {
	
	private RepositoryBancoProduto banco;

	public ProdutoService(RepositoryBancoProduto banco) {
		this.banco = banco;
	}
	public void salvar(Produto entidade) {
		banco.salvar(entidade);
	}
	public Produto buscarPorId(Integer produtoId) {
		// TODO Auto-generated method stub
		return banco.buscarPorId(produtoId);
	}
	
	public void listar() {
		List<ListarProdutos> produtos= (List) banco.listar();

	    for (ListarProdutos produto : produtos) {

	        System.out.println(produto.toString());
	    }
	}
	public void baixarEstoque(Produto entidade) {
		banco.baixarEstoque(entidade);
	}
	
	public void aumentarEstoque(Produto entidade) {
		banco.aumentarEstoque(entidade);
	}
	
	public void atualizarProduto(Produto entidade) {
		// TODO Auto-generated method stub
		if(buscarPorId(entidade.getId())==null) {
			System.out.println("Produto não encontrado");
		}else {
			banco.atualizar(entidade);
		}
		
	}

	public void deletar(Integer id) {
		// TODO Auto-generated method stub
		
	}
}
