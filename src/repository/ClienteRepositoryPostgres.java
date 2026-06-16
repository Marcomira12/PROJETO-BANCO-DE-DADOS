package repository;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;
import reflexao.Repositorio;
@Repositorio(nome = "Cliente" )
public class ClienteRepositoryPostgres implements RepositoryBancoCliente {
	private ConexaoBanco banco;

	public ClienteRepositoryPostgres(ConexaoBanco banco) {
		super();
		this.banco = banco;
	}

	@Override
	public void salvar(Cliente entidade) {
		String sql = "INSERT INTO public.cliente (nome_cliente, cpf_cliente, email_cliente, data_cadastro) VALUES (?, ?, ?, ?)";
		try (PreparedStatement preparar = banco.getConnection().prepareStatement(sql)) {
			preparar.setString(1, entidade.getNome());
			preparar.setString(2, entidade.getCpf());
			preparar.setString(3, entidade.getEmail());
			preparar.setDate(4, java.sql.Date.valueOf(LocalDate.now()));
			preparar.executeUpdate();
			preparar.close();

		} catch (SQLException e) {
			// TODO: handle exception
		}

	}

	@Override
	public Cliente buscarPorId(Integer id) {
		String sql = "SELECT * FROM cliente WHERE id_cliente = ?";

		try (

				PreparedStatement preparar = banco.getConnection().prepareStatement(sql);) {

			preparar.setInt(1, id);

			ResultSet rs = preparar.executeQuery();

			if (rs.next()) {

				Cliente cliente = new Cliente();
				cliente.setId(rs.getInt("id_cliente"));
				cliente.setNome(rs.getString("nome_cliente"));
				cliente.setCpf(rs.getString("cpf_cliente"));
				cliente.setEmail(rs.getString("email_cliente"));
				cliente.setData(rs.getDate("data_cadastro").toLocalDate());
				cliente.setCashback(rs.getBigDecimal("saldo_cashback"));

				return cliente;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Cliente> listar() {
		List<Cliente> lista = new ArrayList<>();
		String sql = "select id_cliente,nome_cliente,data_cadastro,saldo_cashback from cliente";
		try (PreparedStatement preparar = banco.getConnection().prepareStatement(sql);
				ResultSet rs = preparar.executeQuery()) {
			while (rs.next()) {
				Cliente entidade = new Cliente();
				entidade.setId(rs.getInt("id_cliente"));
				entidade.setNome(rs.getString("nome_cliente"));
				entidade.setData(rs.getDate("data_cadastro").toLocalDate());
				entidade.setCashback(rs.getBigDecimal("saldo_cashback"));
				lista.add(entidade);
			}
			return lista;
		} catch (SQLException e) {
			// TODO: handle exception
		}

		return new ArrayList<>();

	}

	@Override
	public void atualizar(Cliente entidade) {
		String sql = """
				CALL atualizar_cliente(?,?,?,?)
									""";

		try (CallableStatement preparar = banco.getConnection().prepareCall(sql)) {

			preparar.setString(2, entidade.getNome());
			preparar.setString(3, entidade.getEmail());
			preparar.setBigDecimal(4, entidade.getCashback());
			preparar.setInt(1, entidade.getId());
			preparar.executeUpdate();
			preparar.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	private void resertIncrement(Integer id) {
		
	}
	@Override
	public void deletar(Integer id) {
		String sql = "DELETE FROM public.cliente WHERE id_cliente = ?";

	    try (PreparedStatement preparar = banco.getConnection().prepareStatement(sql)) {

	        preparar.setInt(1, id);
	        preparar.executeUpdate();
	        resertIncrement(id);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	}

	@Override
	public Cliente buscarPorCPF(String cpf) {
		String sql = "SELECT id_cliente,nome_cliente,data_cadastro FROM cliente WHERE cpf_cliente = ?";

		try (

				PreparedStatement preparar = banco.getConnection().prepareStatement(sql);) {

			preparar.setString(1, cpf);

			ResultSet rs = preparar.executeQuery();

			if (rs.next()) {

				Cliente cliente = new Cliente();
				cliente.setId(rs.getInt("id_cliente"));
				cliente.setNome(rs.getString("nome_cliente"));
				cliente.setData(rs.getDate("data_cadastro").toLocalDate());

				return cliente;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
