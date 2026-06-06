package repository;

import model.Produto;

public interface RepositoryBancoProduto extends RepositoryBanco<Produto, Integer>{
	void baixarEstoque(Produto item);
	void aumentarEstoque(Produto item);

}
