package service;

import java.util.List;

import model.Fornecedor;
import repository.RepositoryBancoFornecedor;

public class FornecedorService {
	private RepositoryBancoFornecedor banco;

	public FornecedorService(RepositoryBancoFornecedor banco) {
		super();
		this.banco = banco;
	}
	
	public Fornecedor salvar(Fornecedor fornecedor) {
		Fornecedor fornecedorExistente = buscarFornecedorCNPJ(fornecedor.getCnpj());

		    if (fornecedorExistente != null) {
		    	System.out.println("Fornecedor já cadastrando ");
		        return fornecedorExistente;
		    }

		    banco.salvar(fornecedor);
		    fornecedor=buscarFornecedorCNPJ(fornecedor.getCnpj());
		    return fornecedor;
		
	}

	public Fornecedor buscarFornecedorCNPJ(String cnpj) {
		// TODO Auto-generated method stub
		return banco.buscarPorCNPJ(cnpj);
	}
	
	public Fornecedor buscarFornecedorID(Integer id) {
		Fornecedor fornecedor= banco.buscarPorId(id);
		return fornecedor;
	}
	public void listarFornecedores() {
		
		 List<Fornecedor> fornecedores = banco.listar();

		    if (fornecedores.isEmpty()) {
		        System.out.println("Nenhum fornecedor encontrado.");
		        return;
		    }

		    for (Fornecedor fornecedor : fornecedores) {

		        System.out.println(fornecedor.toString());
		    }
	}
	public void atualizarFornecedor(Fornecedor fornecedor) {
		banco.atualizar(fornecedor);
	}
	
}
