package model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Nota_Venda {
	private Integer id;
    private Cliente cliente;
    private LocalDate data;
    private BigDecimal valor_cashbakc_gerado;
    private List<Item_Venda> itens;
    private BigDecimal valor_cashback_utilizado;
    
    @Override
	public String toString() {
		return "Nota da Venda=  ID Nota : " + id + " | data : " + data + " | CashBack Gerado : " + valor_cashbakc_gerado
				+ " | Itens : " + listar() + " | CashBack Utilizado : " + valor_cashback_utilizado + " | Valor Total : "
				+ valorTotal ;
	}
    private String listar() {
    	StringBuilder sb = new StringBuilder();

        for (Item_Venda item : itens) {
            sb.append("\n - ").append(item.toString());
        }

        return sb.toString();
    }
	public BigDecimal getValor_cashbakc_gerado() {
		return valor_cashbakc_gerado;
	}

	public void setValor_cashbakc_gerado(BigDecimal valor_cashbakc_gerado) {
		this.valor_cashbakc_gerado = valor_cashbakc_gerado;
	}

	public BigDecimal getValor_cashback_utilizado() {
		return valor_cashback_utilizado;
	}

	public void setValor_cashback_utilizado(BigDecimal valor_cashback_utilizado) {
		this.valor_cashback_utilizado = valor_cashback_utilizado;
	}

	private BigDecimal valorTotal;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public List<Item_Venda> getItens() {
		return itens;
	}

	public void setItens(List<Item_Venda> itens) {
		this.itens = itens;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
}
