	package service;

import java.math.BigDecimal;
import java.util.List;

import model.Cliente;
import reflexao.ClasseTipo;
import reflexao.Comando;
import reflexao.Repositorio;
import reflexao.Service;
import repository.RepositoryBancoCliente;
@ClasseTipo(
		descricao = "Cliente",
		order = 2
		)
@Service(nome = "Cliente" )
public class ClienteService {
	private RepositoryBancoCliente banco;

	public ClienteService(RepositoryBancoCliente banco) {
		this.banco = banco;
	}
	@Comando(descricao = "Adicionar Cliente",order = 1)
	public Cliente salvar(Cliente cliente) {
		 Cliente clienteExistente = buscarClienteCPF(cliente.getCpf());

		    if (clienteExistente != null) {
		    	System.out.println("Cliente já cadastrando "+ clienteExistente.toString());
		        return clienteExistente;
		    }

		    banco.salvar(cliente);
		    cliente=buscarClienteCPF(cliente.getCpf());
		    return cliente;
		
	}
	@Comando(descricao = "Buscar Cliente CPF", order = 2)
	public Cliente buscarClienteCPF(String cpf) {
		return banco.buscarPorCPF(cpf);
	}
	@Comando(descricao = "Buscar Cliente ID", order = 3)
	public Cliente buscarClienteId(Integer id) {
		Cliente cliente=banco.buscarPorId(id);
		return cliente;
	}
	@Comando(descricao = "Atualizar Cliente",order = 4)
	public void atualizarDadosCliente(Cliente cliente) {
		Cliente entidade=buscarClienteId(cliente.getId());
		if(entidade == null) {
			System.out.println("Cliente não registrado");
			return;
		}
		cliente.setCashback(entidade.getCashback());
		banco.atualizar(cliente);
	}
	
	public void utualizarCliente(Cliente cliente) {
		if(buscarClienteId(cliente.getId())==null) {
			System.out.println("Cliente não registrado");
		}else {
			banco.atualizar(cliente);
		}
	}
	@Comando(descricao = "Listar Cliente",order = 5)
	public void listar(){
		List<Cliente> cliente=banco.listar();
		

	    for (Cliente clientes : cliente) {

	        System.out.println(clientes.toString());
	    }
	}
	public void updateClienteCashback(Cliente id, BigDecimal valorCashback) {
		Cliente cliente= buscarClienteId(id.getId());
		cliente.setCashback(valorCashback);
		banco.atualizar(cliente);
	}
	@Comando(descricao = "Deletar Cliente",order = 6)
	public void deletar(Integer id) {
		listar();
		banco.deletar(id);
	}
	
}
