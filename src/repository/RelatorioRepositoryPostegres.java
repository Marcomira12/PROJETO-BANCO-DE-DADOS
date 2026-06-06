package repository;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entetiesDao.RelatorioCompraDTO;
import entetiesDao.RelatorioVendaDTO;

public class RelatorioRepositoryPostegres implements RepositoryBancoRelatorio<RelatorioCompraDTO , RelatorioVendaDTO> {
	private ConexaoBanco banco;
	public RelatorioRepositoryPostegres(ConexaoBanco banco) {
		super();
		this.banco = banco;
	}

	@Override
	public List<RelatorioCompraDTO> listarCompra() {

	    List<RelatorioCompraDTO> lista = new ArrayList<>();

	    String sql = "SELECT * FROM itens_comprado_estoque";

	    try (
	        PreparedStatement ps = banco.getConnection().prepareStatement(sql);
	        ResultSet rs = ps.executeQuery()
	    ) {

	        while (rs.next()) {

	            RelatorioCompraDTO dto = new RelatorioCompraDTO();

	            dto.setNomeFornecedor(rs.getString("nome_fornecedor"));
	            dto.setValorItem(rs.getBigDecimal("valor_unitario_item"));
	            dto.setQuantidadeProdutoComprado(rs.getInt("quantidade_produto_comprado"));
	            dto.setNomeProduto(rs.getString("nome_produto"));
	            dto.setIdNota(rs.getInt("id_nota_compra"));
	            dto.setIdProduto(rs.getInt("id_produto"));
	            dto.setData(rs.getDate("data_compra").toLocalDate());
	            dto.setStatus(rs.getString("descricao"));
	            dto.setNomeCategoria(rs.getString("nome_categoria"));

	            lista.add(dto);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return lista;
	}

	@Override
	public List<RelatorioVendaDTO> listarVenda() {

	    List<RelatorioVendaDTO> lista = new ArrayList<>();

	    String sql = "SELECT * FROM itens_vendidos";

	    try (
	        PreparedStatement ps = banco.getConnection().prepareStatement(sql);
	        ResultSet rs = ps.executeQuery()
	    ) {

	        while (rs.next()) {

	            RelatorioVendaDTO dto = new RelatorioVendaDTO();

	            dto.setNomeProduto(rs.getString("produto"));
	            dto.setIdNota(rs.getInt("id_nota"));
	            dto.setIdProduto(rs.getInt("id_produto"));
	            dto.setCashbackGerado(rs.getBigDecimal("valor_cashback_gerado"));
	            dto.setCashbackUtilizado(rs.getBigDecimal("valor_cashback_utilizado"));
	            dto.setValorItem(rs.getBigDecimal("valor_Item"));
	            dto.setProdutosVendidos(rs.getInt("produtos_vendidos"));
	            dto.setData(rs.getDate("data").toLocalDate());
	            dto.setValor(rs.getBigDecimal("valor"));
	            dto.setNomeCliente(rs.getString("cliente"));
	            dto.setSaldoCashback(rs.getBigDecimal("saldo_cashback"));

	            lista.add(dto);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return lista;
	}

	@Override
	public BigDecimal listarValorEstoque() {
		 String sql = "SELECT calcular_valor_total_estoque()";

		    try (PreparedStatement ps = banco.getConnection().prepareStatement(sql);
		         ResultSet rs = ps.executeQuery()) {

		        if (rs.next()) {
		            return rs.getBigDecimal(1);
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return BigDecimal.ZERO;
	}

	@Override
	public BigDecimal listarFaturamento() {
		 String sql = "SELECT calcular_faturamento_total()";

		    try (PreparedStatement ps = banco.getConnection().prepareStatement(sql);
		         ResultSet rs = ps.executeQuery()) {

		        if (rs.next()) {
		            return rs.getBigDecimal(1);
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return BigDecimal.ZERO;
	}

}
