package service;

import java.util.List;

import model.Categoria;
import repository.RepositoryBanco;

public class CategoriaService {
	private RepositoryBanco<Categoria, Integer> banco;

	public CategoriaService(RepositoryBanco<Categoria, Integer> banco) {
		super();
		this.banco = banco;
	}

	public void salvar(Categoria cat) {

		banco.salvar(cat);
	}

	public Categoria buscarCategoria(Integer cat) {
		Categoria categoria = banco.buscarPorId(cat);
		return categoria;
	}

	public void listar() {
		List<Categoria> categorias = banco.listar();

		for (Categoria categoria : categorias) {

			System.out.println(categoria.toString());
		}
	}

	public void atualizar(Categoria cat) {
		if(buscarCategoria(cat.getId())==null) {
			System.out.println("ID da categoria não existente");
			return;
		}
		banco.atualizar(cat);
	}
	
	public void deletar(Integer id) {
		if(buscarCategoria(id)==null) {
			System.out.println("ID da categoria não existente");
			return;
		}
		banco.deletar(id);
	}
}
