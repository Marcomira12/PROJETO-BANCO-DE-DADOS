package repository;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entetiesDao.ListarProdutos;
import model.Produto;

public class ProdutoRepositoryPostegres implements RepositoryBancoProduto {
	private ConexaoBanco banco;

	public ProdutoRepositoryPostegres(ConexaoBanco banco) {
		super();
		this.banco = banco;
	}

	@Override
	public void salvar(Produto entidade) {

		inserirProduto(entidade);

	}

	@Override
	public Produto buscarPorId(Integer id) {

		String sql = "SELECT id_produto, nome_produto, preco_unitario, estoque_atual FROM produto WHERE id_produto = ?";

		try (

				PreparedStatement preparar = banco.getConnection().prepareStatement(sql);) {

			preparar.setInt(1, id);

			ResultSet rs = preparar.executeQuery();

			if (rs.next()) {

				Produto produto = new Produto();

				produto.setId(rs.getInt("id_produto"));
				produto.setNome(rs.getString("nome_produto"));
				produto.setValorAtualProduto(rs.getBigDecimal("preco_unitario"));
				produto.setEstoque(rs.getInt("estoque_atual"));

				preparar.close();
				return produto;
			}
			preparar.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List listar() {
		List<ListarProdutos> lista = new ArrayList<>();
		String sql = "select * from produtos";
		try (PreparedStatement preparar = banco.getConnection().prepareStatement(sql);
				ResultSet rs = preparar.executeQuery()) {
			while (rs.next()) {
				ListarProdutos entidade = new ListarProdutos();
				entidade.setNome_categoria(rs.getString("categoria"));
				entidade.setPreco(rs.getBigDecimal("preco_unitario"));
				entidade.setNome_produto(rs.getString("produto"));
				entidade.setEstoque(rs.getInt("estoque_atual"));
				entidade.setId_produto(rs.getInt("codigo"));
				lista.add(entidade);
			}
			return lista;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return new ArrayList<>();
	}

	private void inserirProduto(Produto item) {

		String sql = "INSERT INTO public.produto( id_categoria, nome_produto, preco_unitario) VALUES ( ?, ?, ?);";

		try (PreparedStatement preparar = banco.getConnection().prepareStatement(sql)) {

			preparar.setInt(1, item.getIdCategoria());
			preparar.setString(2, item.getNome());
			preparar.setBigDecimal(3, item.getValorAtualProduto());
			preparar.executeUpdate();
			preparar.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void baixarEstoque(Produto item) {
		String sql = "CALL baixar_estoque(?, ?)";

		try (CallableStatement preparar = banco.getConnection().prepareCall(sql)) {

			preparar.setInt(2, item.getEstoque());
			preparar.setInt(1, item.getId());
			preparar.execute();
			preparar.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// não esquece que quando for adicionar o metodo de compra para inserir no banco
	// o objeto
	// com atributo estoque tem que ser maior que o atula, ou seja adicionar ao
	// valor retornando
	@Override
	public void aumentarEstoque(Produto item) {
		String sql = "CALL aumentar_estoque(?, ?)";

		try (CallableStatement preparar = banco.getConnection().prepareCall(sql)) {

			preparar.setInt(2, item.getEstoque());
			preparar.setInt(1, item.getId());
			preparar.execute();
			preparar.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// incluir valor a ser atualizado
	private void atualizarDadosProduto(Produto item) {
		String sql = "UPDATE public.produto SET id_categoria=?, nome_produto=?,preco_unitario=?	WHERE id_produto=?";
		try (PreparedStatement preparar = banco.getConnection().prepareStatement(sql)) {

			preparar.setInt(1, item.getIdCategoria());
			preparar.setString(2, item.getNome());
			preparar.setBigDecimal(3, item.getValorAtualProduto());
			preparar.setInt(4, item.getId());
			preparar.executeUpdate();
			preparar.close();

		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	// modificar e alterar a implementação de produto e respeitar Liskov
	@Override
	public void atualizar(Produto entidade) {

		atualizarDadosProduto(entidade);

	}

	@Override
	public void deletar(Integer id) {
		// TODO Auto-generated method stub

	}

}
