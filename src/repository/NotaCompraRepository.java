package repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Item_Compra;
import model.Nota_Compra;

public class NotaCompraRepository implements NotaRepository<Nota_Compra, Item_Compra, Integer> {
	private ConexaoBanco banco;

	public NotaCompraRepository(ConexaoBanco banco) {
		super();
		this.banco = banco;
	}

	@Override
	public Integer criarNota(Nota_Compra nota) {
		String sql = "INSERT INTO public.nota_compra( id_fornecedor, data_compra, valor_compra) VALUES (?, ?, ?)";
		try (PreparedStatement preparar = banco.getConnection().prepareStatement(sql,
				PreparedStatement.RETURN_GENERATED_KEYS)) {
			preparar.setInt(1, nota.getFornecedor().getId());
			preparar.setDate(2, java.sql.Date.valueOf(nota.getData()));
			preparar.setBigDecimal(3, nota.getValorTotal());
			preparar.executeUpdate();
			ResultSet resultado = preparar.getGeneratedKeys();

			if (resultado.next()) {
				return resultado.getInt(1);
			}
			preparar.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public void adicionarItem(Integer notaId, Item_Compra item) {
		String sql="INSERT INTO public.item_compra( quantidade_produto_comprado, valor_unitario_item, id_nota_compra, id_produto)VALUES (?, ?, ?, ?)";
		try(PreparedStatement preparar= banco.getConnection().prepareStatement(sql)) {
			
			preparar.setInt(1, item.getQuantidade());
			preparar.setBigDecimal(2, item.getPrecoCusto());
			preparar.setInt(3, notaId);
			preparar.setInt(4, item.getProduto().getId());
			preparar.executeUpdate();
			preparar.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public Nota_Compra buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void finalizar(Integer notaId) {
		String sql = "UPDATE nota_compra SET id_status = 2 WHERE id_nota_compra = ?";

	    try (PreparedStatement preparar = banco.getConnection().prepareStatement(sql)) {
	    	preparar.setInt(1, notaId);
	    	preparar.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	}

}
