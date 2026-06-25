package repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Item_Venda;
import model.Nota_Venda;
import reflexao.Repositorio;
@Repositorio(nome = "Venda")
public class NotaVendaRepository implements NotaRepository<Nota_Venda,Item_Venda,Integer> {
	private ConexaoBanco banco;
	
	
	

	public NotaVendaRepository(ConexaoBanco banco) {
		super();
		this.banco = banco;
	}

	@Override
	public Integer criarNota(Nota_Venda nota) {
		String sql="INSERT INTO nota_venda (id_cliente, data_venda, valor_total_venda, valor_cashback_gerado, valor_cashback_utilizado)	VALUES ( ?, ?, ?, ?, ?)";
		try {
			PreparedStatement preparar= banco.getConnection().prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
			preparar.setInt(1, nota.getCliente().getId());
			preparar.setDate(2, java.sql.Date.valueOf(nota.getData()));
			preparar.setBigDecimal(3, nota.getValorTotal());
			preparar.setBigDecimal(4, nota.getValor_cashbakc_gerado());
			preparar.setBigDecimal(5, nota.getValor_cashback_utilizado());
			preparar.executeUpdate();

	        ResultSet rs = preparar.getGeneratedKeys();

	        if (rs.next()) {
	            return rs.getInt(1);
	        }

	        preparar.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void adicionarItem(Integer notaId, Item_Venda item) {
		String sql="INSERT INTO public.item_venda(quantidade_produto_vendido, preco_unitario_venda, id_produto, id_nota_venda) VALUES ( ?, ?, ?, ?);";
		try(PreparedStatement preparar= banco.getConnection().prepareStatement(sql)) {
			
			preparar.setInt(1, item.getQuantidade());
			preparar.setBigDecimal(2, item.getPrecoUnitario());
			preparar.setInt(3, item.getProduto().getId());
			preparar.setInt(4, notaId);
			preparar.executeUpdate();
			preparar.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public Nota_Venda buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void finalizar(Integer notaId) {
		String sql = "UPDATE nota_venda SET id_status = 2 WHERE id_nota_venda = ?";

	    try (PreparedStatement preparar = banco.getConnection().prepareStatement(sql)) {
	    	preparar.setInt(1, notaId);
	    	preparar.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		
	}
	
	
}
