package entetiesDao;

import java.math.BigDecimal;

public class ListarProdutos {
	private int id_produto;
	private String nome_produto;
	private BigDecimal preco;
	private int estoque;
	private String nome_categoria;
	
	
	@Override
	public String toString() {
		return "Produtos [id_produto= " + id_produto + ", nome_produto=" + nome_produto + ", preço=" + preco
				+ ", estoque=" + estoque + ", nome_categoria=" + nome_categoria + "]";
	}
	public int getId_produto() {
		return id_produto;
	}
	public void setId_produto(int id_produto) {
		this.id_produto = id_produto;
	}
	public String getNome_produto() {
		return nome_produto;
	}
	public void setNome_produto(String nome_produto) {
		this.nome_produto = nome_produto;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	public int getEstoque() {
		return estoque;
	}
	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}
	public String getNome_categoria() {
		return nome_categoria;
	}
	public void setNome_categoria(String nome_categoria) {
		this.nome_categoria = nome_categoria;
	}
}
