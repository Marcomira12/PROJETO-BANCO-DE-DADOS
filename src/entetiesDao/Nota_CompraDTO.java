package entetiesDao;

import java.util.ArrayList;
import java.util.List;

import model.Fornecedor;
import model.Item_Compra;

public class Nota_CompraDTO {
	private Fornecedor fornecedor;
	
	private List<Item_Compra> itens = new ArrayList<>();
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Fornecedor cliente) {
		this.fornecedor = cliente;
	}
	public List<Item_Compra> getItens() {
		return itens;
	}
}
