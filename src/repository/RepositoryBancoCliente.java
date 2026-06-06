package repository;

import model.Cliente;

public interface RepositoryBancoCliente extends RepositoryBanco<Cliente, Integer> {
	Cliente buscarPorCPF(String cpf);
}
