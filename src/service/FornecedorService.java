package service;

import java.util.List;

import model.Fornecedor;
import reflexao.ClasseTipo;
import reflexao.Comando;
import reflexao.Service;
import repository.RepositoryBancoFornecedor;
@ClasseTipo(descricao = "Fornecedor",order = 3)
@Service(nome = "Fornecedor")
public class FornecedorService {
	private RepositoryBancoFornecedor banco;

	public FornecedorService(RepositoryBancoFornecedor banco) {
		super();
		this.banco = banco;
	}
	@Comando(descricao = "Cadastrar Fornecedor",order = 1)
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
	@Comando(descricao = "Buscar Fornecedor CNPJ",order = 2)
	public Fornecedor buscarFornecedorCNPJ(String cnpj) {
		// TODO Auto-generated method stub
		return banco.buscarPorCNPJ(cnpj);
	}
	@Comando(descricao = "Buscar Fornecedor pelo ID",order = 3)
	public Fornecedor buscarFornecedorID(Integer id) {
		Fornecedor fornecedor= banco.buscarPorId(id);
		return fornecedor;
	}
	@Comando(descricao = "Listar Fornecedores",order =4 )
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
	@Comando(descricao = "Atualizar Fornecedor",order = 5)
	public void atualizarFornecedor(Fornecedor fornecedor) {
		banco.atualizar(fornecedor);
	}
	
}
