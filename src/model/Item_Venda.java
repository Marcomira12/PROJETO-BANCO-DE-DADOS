package model;

import java.math.BigDecimal;

public class Item_Venda {
	@Override
	public String toString() {
		return "ID Produto : " + produto.getId() + " | Nome Produto : " +produto.getNome()+ " | Quantidade : " + quantidade + " | Preço Unitário : " + precoUnitario;
	}
	private Produto produto;
    private Integer quantidade;
    private BigDecimal precoUnitario;
    
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
	public BigDecimal getPrecoUnitario() {
		return precoUnitario;
	}
	public void setPrecoUnitario(BigDecimal precoUnitario) {
		this.precoUnitario = precoUnitario;
	}


}
