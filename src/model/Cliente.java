package model;

import java.math.BigDecimal;
import java.time.LocalDate;

import reflexao.Obrigatorio;

public class Cliente {
	private Integer id;
	@Obrigatorio
	private String nome;
	@Obrigatorio
	private String cpf;
	private BigDecimal cashback;
	@Obrigatorio
	private String email;
	
	public Cliente(Integer id, String nome, String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
	}
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", cashback=" + cashback + ", data=" + data + "]";
	}
	public Cliente(String nome, String email) {
		super();
		this.nome = nome;
		this.email = email;
	}
	public Cliente() {
		super();
	}
	private LocalDate data;
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public BigDecimal getCashback() {
		return cashback;
	}
	public void setCashback(BigDecimal cashback) {
		this.cashback = cashback;
		
		
	}
}
