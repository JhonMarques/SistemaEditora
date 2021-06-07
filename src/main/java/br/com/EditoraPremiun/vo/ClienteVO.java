package br.com.EditoraPremiun.vo;

import java.util.Date;

public class ClienteVO {

	private int idCliente;
	private String nome;
	private String fone;
	private String foneRecado;
	private String email;
	private String profissao;
	private String cpf;
	private String endereco;
	private int cidadeFK;
	private int escolaridadeFK;
	private Date nasCli;
	private String cep;

	public int getEscolaridadeFK() {
		return escolaridadeFK;
	}

	public void setEscolaridadeFK(int escolaridadeFK) {
		this.escolaridadeFK = escolaridadeFK;
	}

	public Date getNasCli() {
		return nasCli;
	}

	public void setNasCli(Date date) {
		this.nasCli = date;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public String getFoneRecado() {
		return foneRecado;
	}

	public void setFoneRecado(String foneRecado) {
		this.foneRecado = foneRecado;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public int getCidadeFK() {
		return cidadeFK;
	}

	public void setCidadeFK(int cidadeFK) {
		this.cidadeFK = cidadeFK;
	}

}
