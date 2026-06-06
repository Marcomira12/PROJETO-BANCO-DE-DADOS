package repository;

import model.Fornecedor;

public interface RepositoryBancoFornecedor extends RepositoryBanco<Fornecedor, Integer> {
	Fornecedor buscarPorCNPJ(String cpf);

}
