package model;

import reflexao.Obrigatorio;

public class Categoria {
	private Integer id;
	@Obrigatorio
    private String nome;
	@Obrigatorio
    private String descricao;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nome=" + nome + ", descricao=" + descricao + "]";
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Categoria() {
		super();
	}
	public Categoria( String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}
    
    
}
