package repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Categoria;

public class CategoriaRepositoryPostgres implements RepositoryBanco<Categoria, Integer> {
	private ConexaoBanco banco;

	public CategoriaRepositoryPostgres(ConexaoBanco banco) {
		this.banco = banco;
	}

	@Override
	public void salvar(Categoria entidade) {
		String sql = "INSERT INTO public.categoria( nome_categoria, descricao) VALUES ( ?, ?)";
		try {
			PreparedStatement ps = banco.getConnection().prepareStatement(sql);
			ps.setString(1, entidade.getNome());
			ps.setString(2, entidade.getDescricao());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Categoria buscarPorId(Integer id) {
		String sql = "SELECT cod_categoria, nome_categoria, descricao FROM public.categoria WHERE cod_categoria = ?";

		try (

				PreparedStatement preparar = banco.getConnection().prepareStatement(sql);) {

			preparar.setInt(1, id);

			ResultSet rs = preparar.executeQuery();

			if (rs.next()) {

				Categoria categoria = new Categoria();

				categoria.setId(rs.getInt("cod_categoria"));
				categoria.setNome(rs.getString("nome_categoria"));
				categoria.setDescricao(rs.getString("descricao"));
				preparar.close();
				return categoria;
			}
			preparar.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Categoria> listar() {
		List<Categoria> lista = new ArrayList<>();
		String sql = "select * from categoria";
		try (PreparedStatement preparar = banco.getConnection().prepareStatement(sql);
				ResultSet rs = preparar.executeQuery()) {
			while (rs.next()) {
				Categoria entidade = new Categoria();
				entidade.setId(rs.getInt("cod_categoria"));
				entidade.setNome(rs.getString("nome_categoria"));
				entidade.setDescricao(rs.getString("descricao"));
				lista.add(entidade);
			}
			return lista;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return new ArrayList<>();
	}

	@Override
	public void atualizar(Categoria entidade) {
		String sql = """
						    UPDATE public.categoria
				SET nome_categoria=?, descricao=?
				WHERE ?
						""";

		try (PreparedStatement preparar = banco.getConnection().prepareStatement(sql)) {

			preparar.setString(1, entidade.getNome());
			preparar.setString(2, entidade.getDescricao());
			preparar.setInt(3, entidade.getId());
			preparar.executeUpdate();
			preparar.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deletar(Integer id) {
		String sql="DELETE FROM public.categoria WHERE cod_categoria= ?";
		try(PreparedStatement preparar=banco.getConnection().prepareStatement(sql)) {
			preparar.setInt(1, id);
	        preparar.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
