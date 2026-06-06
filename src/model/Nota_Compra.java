package model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Nota_Compra {
	private Integer id;
    private Fornecedor fornecedor;
    private LocalDate data;

    private List<Item_Compra> itens;

    
    @Override
	public String toString() {
		return "Nota da Compra -> ID : " + id + " | Nome do Forncedor : " + fornecedor.getNome() + " | Data da Compra : " + data + " | Itens :" + listar()
				+ " | valorTotal : " + valorTotal ;
	}
	private String listar() {
    	StringBuilder sb = new StringBuilder();

        for (Item_Compra item : itens) {
            sb.append("\n - ").append(item.toString());
        }

        return sb.toString();
    }
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public List<Item_Compra> getItens() {
		return itens;
	}

	public void setItens(List<Item_Compra> itens) {
		this.itens = itens;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	private BigDecimal valorTotal;
}
