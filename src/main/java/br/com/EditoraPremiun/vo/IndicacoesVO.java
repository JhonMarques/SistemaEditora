package br.com.EditoraPremiun.vo;

public class IndicacoesVO {
	private int idIndica;
	private String nome;
	private String fone;
	private int clienteFK;
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getIdIndica() {
		return idIndica;
	}

	public void setIdIndica(int idIndica) {
		this.idIndica = idIndica;
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

	public int getClienteFK() {
		return clienteFK;
	}

	public void setClienteFK(int clienteFK) {
		this.clienteFK = clienteFK;
	}

}
