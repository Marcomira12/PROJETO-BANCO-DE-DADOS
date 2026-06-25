package repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Fornecedor;
import reflexao.Repositorio;
@Repositorio(nome = "Fornecedor")
public class FornecedorRepositoryPostegres implements RepositoryBancoFornecedor {
	private ConexaoBanco banco;

	public FornecedorRepositoryPostegres(ConexaoBanco banco) {
		super();
		this.banco = banco;
	}

	@Override
	public void salvar(Fornecedor entidade) {
		String sql = "INSERT INTO public.fornecedor( nome_fornecedor, cnpj_forncedor, email_fornecedor, telefone_fornecedor,data_cadastro_fornecedor)VALUES ( ?, ?, ?, ?, ?)";
		try (PreparedStatement preparar = banco.getConnection().prepareStatement(sql)) {
			preparar.setString(1, entidade.getNome());
			preparar.setString(2, entidade.getCnpj());
			preparar.setString(3, entidade.getEmail());
			preparar.setString(4, entidade.getTelefone());
			preparar.setDate(5, java.sql.Date.valueOf(LocalDate.now()));
			preparar.executeUpdate();
			preparar.close();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Override
	public Fornecedor buscarPorId(Integer id) {
		String sql = "SELECT * FROM fornecedor WHERE id_fornecedor = ?";

		try (

				PreparedStatement preparar = banco.getConnection().prepareStatement(sql);) {

			preparar.setInt(1, id);

			ResultSet rs = preparar.executeQuery();

			if (rs.next()) {

				Fornecedor fornecedor = new Fornecedor();
				fornecedor.setId(rs.getInt("id_fornecedor"));
				fornecedor.setNome(rs.getString("nome_fornecedor"));
				fornecedor.setCnpj(rs.getString("cnpj_forncedor"));
				fornecedor.setEmail(rs.getString("email_fornecedor"));
				fornecedor.setData_cadastro(rs.getDate("data_cadastro_fornecedor").toLocalDate());
				fornecedor.setTelefone(rs.getString("telefone_fornecedor"));

				return fornecedor;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Fornecedor> listar() {
		List<Fornecedor> lista = new ArrayList<>();
		String sql = "select * from listar_fornecedor";
		try (PreparedStatement preparar = banco.getConnection().prepareStatement(sql);
				ResultSet rs = preparar.executeQuery()) {
			while (rs.next()) {
				Fornecedor entidade = new Fornecedor();
				entidade.setId(rs.getInt("id_fornecedor"));
				entidade.setEmail(rs.getString("email"));
				entidade.setTelefone(rs.getString("telefone_fornecedor"));
				entidade.setNome(rs.getString("nome_fornecedor"));
				entidade.setData_cadastro(rs.getDate("data_cadastro_fornecedor").toLocalDate());
				lista.add(entidade);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	@Override
	public void atualizar(Fornecedor entidade) {
		String sql="UPDATE public.fornecedor SET nome_fornecedor=?, email_fornecedor=?, telefone_fornecedor=?	WHERE id_fornecedor=?";
		try(PreparedStatement preparar=banco.getConnection().prepareStatement(sql)) {
			preparar.setString(1, entidade.getNome());
			preparar.setString(2, entidade.getEmail());
			preparar.setString(3, entidade.getTelefone());
			preparar.setInt(4, entidade.getId());
			preparar.executeUpdate();
			preparar.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deletar(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Fornecedor buscarPorCNPJ(String cpf) {
		String sql = "SELECT id_fornecedor,nome_fornecedor,data_cadastro_fornecedor FROM fornecedor WHERE cnpj_forncedor = ?";

		try (

				PreparedStatement preparar = banco.getConnection().prepareStatement(sql)) {

			preparar.setString(1, cpf);

			ResultSet rs = preparar.executeQuery();

			if (rs.next()) {

				Fornecedor fornecedor = new Fornecedor();
				fornecedor.setId(rs.getInt("id_fornecedor"));
				fornecedor.setNome(rs.getString("nome_fornecedor"));
				fornecedor.setData_cadastro(rs.getDate("data_cadastro_fornecedor").toLocalDate());

				return fornecedor;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
