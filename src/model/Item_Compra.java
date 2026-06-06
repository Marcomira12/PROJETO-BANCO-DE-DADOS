package model;

import java.math.BigDecimal;

public class Item_Compra {
	private Produto produto;
    private Integer quantidade;
    private BigDecimal precoCusto;
    
    
	@Override
	public String toString() {
		return "ID do Produto : " + produto.getId() + " | Quantidade Adicionada : " + quantidade + " | Preco do Item =" + precoCusto ;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public BigDecimal getPrecoCusto() {
		return precoCusto;
	}
	public void setPrecoCusto(BigDecimal precoCusto) {
		this.precoCusto = precoCusto;
	}
}
