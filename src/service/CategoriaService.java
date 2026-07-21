package service;

import java.util.List;

import entetiesDao.CategoriaDTO;
import model.Categoria;
import reflexao.ClasseTipo;
import reflexao.Comando;
import reflexao.Service;
import repository.RepositoryBanco;
@ClasseTipo(
		descricao = "Categoria",
		order = 1
		)
@Service(
		nome = "Categoria"
		)
public class CategoriaService{
	
	private RepositoryBanco<Categoria, Integer> banco;

	public CategoriaService(RepositoryBanco<Categoria, Integer> banco) {
		super();
		this.banco = banco;
	}
	
	@Comando(
			descricao = "Adicionar Categoria",
			order = 1
			)
	public void salvar(Categoria cat) {

		banco.salvar(cat);
	}
	
	public Categoria buscarCategoria(Integer cat) {
		Categoria categoria = banco.buscarPorId(cat);
		return categoria;
	}
	@Comando(
			descricao = "Listar Categoria",
			order = 2
			)
	public void listar() {
		List<Categoria> categorias = banco.listar();

		for (Categoria categoria : categorias) {

			System.out.println(categoria.toString());
		}
	}
	@Comando(
			descricao = "Atualizar Categoria",
			order = 3
			)
	public void atualizar(CategoriaDTO cat) {
		if(buscarCategoria(cat.getId())==null) {
			System.out.println("ID da categoria não existente");
			return;
		}
		
		banco.atualizar(cat.convertDTO(cat));
	}
	@Comando(
			descricao = "Deletar Categoria",
			order = 4
			)	
	public void deletar(Integer id) {
		if(buscarCategoria(id)==null) {
			System.out.println("ID da categoria não existente");
			return;
		}
		banco.deletar(id);
	}
	
}
