package entetiesDao;

import model.Categoria;
import reflexao.Obrigatorio;

public class CategoriaDTO {
	@Obrigatorio
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
	public Categoria convertDTO(CategoriaDTO cat) {
		Categoria categoria=new Categoria();
		categoria.setId(cat.id);
		categoria.setDescricao(cat.descricao);
		categoria.setNome(cat.nome);
		return categoria;
		
	}
}
