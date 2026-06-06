package entetiesDao;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RelatorioVendaDTO {
	private String nomeProduto;
	private Integer produtosVendidos;
	private LocalDate data;
	private BigDecimal valor;
	private String nomeCliente;
	private BigDecimal saldoCashback;
	private Integer idProduto;
	private Integer idNota;
	private BigDecimal valorItem;
	private BigDecimal cashbackUtilizado;
	private BigDecimal cashbackGerado;
	
	public BigDecimal getCashbackUtilizado() {
		return cashbackUtilizado;
	}
	public void setCashbackUtilizado(BigDecimal cashbackUtilizado) {
		this.cashbackUtilizado = cashbackUtilizado;
	}
	public BigDecimal getCashbackGerado() {
		return cashbackGerado;
	}
	public void setCashbackGerado(BigDecimal cashbackGerado) {
		this.cashbackGerado = cashbackGerado;
	}
	public BigDecimal getValorItem() {
		return valorItem;
	}
	public void setValorItem(BigDecimal valorItem) {
		this.valorItem = valorItem;
	}
	@Override
	public String toString() {
		return "Relatorio de Venda -> " +" ID Nota : "+ idNota + " | data da Venda : " + data + " | Valor Total da Venda : " + valor
		+" | Valor CashBack Utilizado : "+ cashbackUtilizado+" | Valor CashBack Gerado : " +cashbackGerado + " | Nome do Cliente : " + nomeCliente +" | Saldo CashBack : "+ saldoCashback + " | Nome do Produto " +nomeProduto
		+" | ID Produto : "+ idProduto + " | Valor do Item Vendido : R$"+valorItem + " | Quantidade Vendida do Item : "+ produtosVendidos;
	}
	public Integer getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}
	public Integer getIdNota() {
		return idNota;
	}
	public void setIdNota(Integer idNota) {
		this.idNota = idNota;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public Integer getProdutosVendidos() {
		return produtosVendidos;
	}
	public void setProdutosVendidos(Integer produtosVendidos) {
		this.produtosVendidos = produtosVendidos;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public BigDecimal getSaldoCashback() {
		return saldoCashback;
	}
	public void setSaldoCashback(BigDecimal saldoCashback) {
		this.saldoCashback = saldoCashback;
	}
}
