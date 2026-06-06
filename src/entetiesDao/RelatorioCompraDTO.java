package entetiesDao;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RelatorioCompraDTO {
	private String nomeFornecedor;
	private BigDecimal valorItem;
	private Integer quantidadeProdutoComprado;
	private String nomeProduto;
	private Integer idNota;
	private String nomeCategoria;
	private String status;
	private Integer idProduto;
	private LocalDate data;
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}
	public String getNomeFornecedor() {
		return nomeFornecedor;
	}
	public void setNomeFornecedor(String nomeFornecedor) {
		this.nomeFornecedor = nomeFornecedor;
	}
	public BigDecimal getValorItem() {
		return valorItem;
	}
	public void setValorItem(BigDecimal valorItem) {
		this.valorItem = valorItem;
	}
	public Integer getQuantidadeProdutoComprado() {
		return quantidadeProdutoComprado;
	}
	public void setQuantidadeProdutoComprado(Integer quantidadeProdutoComprado) {
		this.quantidadeProdutoComprado = quantidadeProdutoComprado;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	
	public Integer getIdNota() {
		return idNota;
	}
	public void setIdNota(Integer idNota) {
		this.idNota = idNota;
	}
	public String getNomeCategoria() {
		return nomeCategoria;
	}
	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}
	@Override
	public String toString() {
		return "Relatorio de Compra -> Fornecedor : " + nomeFornecedor + " | valor do Item : R$" + valorItem
				+ " | quantidade de Produto Comprado : " + quantidadeProdutoComprado +" | ID Produto : "+idProduto+ " | nome do Produto : " + nomeProduto
				+ " | ID da Nota : " + idNota + " | Status da Compra : " + status +" | Data Compra : " +data;
	}
}
