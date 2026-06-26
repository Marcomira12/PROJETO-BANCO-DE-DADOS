package entetiesDao;

import java.util.ArrayList;
import java.util.List;

import model.Cliente;
import model.Item_Venda;
import reflexao.Obrigatorio;

public class Nota_VendaDTO {
	@Obrigatorio
	private Integer Id;
	private Cliente cliente;
	private List<Item_Venda> itens = new ArrayList<>();
	public Cliente getCliente() {
		return cliente;
	}
	


	public Nota_VendaDTO() {
		super();
	}



	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public List<Item_Venda> getItens() {
		return itens;
	}
	
	
}
