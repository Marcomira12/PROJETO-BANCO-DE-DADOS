package model;

import java.time.LocalDate;

import reflexao.Obrigatorio;

public class Fornecedor {
	private Integer id;
	@Obrigatorio
    private String nome;
	@Obrigatorio
    private String cnpj;
	@Obrigatorio
    private String email;
	@Obrigatorio
    private String telefone;
    private LocalDate data_cadastro;
	public LocalDate getData_cadastro() {
		return data_cadastro;
	}
	public void setData_cadastro(LocalDate data_cadastro) {
		this.data_cadastro = data_cadastro;
	}
	
	@Override
	public String toString() {
		return "Fornecedor ID : " + id + " | nome : " + nome + " | email : " + email + " | telefone : " + telefone
				+ " | data_cadastro : " + data_cadastro ;
	}
	public Fornecedor() {
		super();
	}
	public Fornecedor(String nome, String cnpj, String email, String telefone) {
		super();
		this.nome = nome;
		this.cnpj = cnpj;
		this.email = email;
		this.telefone = telefone;
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
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
}
