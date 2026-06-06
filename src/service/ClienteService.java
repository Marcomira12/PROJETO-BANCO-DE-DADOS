package service;

import java.math.BigDecimal;
import java.util.List;

import model.Cliente;
import repository.RepositoryBancoCliente;

public class ClienteService {
	private RepositoryBancoCliente banco;

	public ClienteService(RepositoryBancoCliente banco) {
		this.banco = banco;
	}

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
	
	public Cliente buscarClienteCPF(String cpf) {
		return banco.buscarPorCPF(cpf);
	}
	
	public Cliente buscarClienteId(Integer id) {
		Cliente cliente=banco.buscarPorId(id);
		return cliente;
	}
	
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
	public void deletar(Integer id) {
		banco.deletar(id);
	}
	
}
