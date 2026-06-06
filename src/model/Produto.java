package model;

import java.math.BigDecimal;

public class Produto {
	private Integer id;
    private String nome;
    private Integer estoque;
    private BigDecimal valorAtualProduto;
    private Integer idCategoria;
    
    

	public Produto() {
		super();
	}
	

	public Produto(Integer id, String nome, BigDecimal valorAtualProduto, Integer idCategoria) {
		super();
		this.id = id;
		this.nome = nome;
		this.valorAtualProduto = valorAtualProduto;
		this.idCategoria = idCategoria;
	}


	public Produto(String nome, Integer estoque, BigDecimal valorAtualProduto, Integer idCategoria) {
		super();
		this.nome = nome;
		this.estoque = estoque;
		this.valorAtualProduto = valorAtualProduto;
		this.idCategoria = idCategoria;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getEstoque() {
		return estoque;
	}

	public void setEstoque(Integer estoque) {
		this.estoque = estoque;
	}

	public BigDecimal getValorAtualProduto() {
		return valorAtualProduto;
	}

	public void setValorAtualProduto(BigDecimal valorAtualProduto) {
		this.valorAtualProduto = valorAtualProduto;
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	
}
