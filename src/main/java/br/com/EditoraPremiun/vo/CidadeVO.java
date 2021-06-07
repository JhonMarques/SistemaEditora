package br.com.EditoraPremiun.vo;

public class CidadeVO {
	private int idCidade;
	private String descricao;
	private int estadoFK;
	private String estadJoin;
	
	public String getEstadJoin() {
		return estadJoin;
	}

	public void setEstadJoin(String estadJoin) {
		this.estadJoin = estadJoin;
	}

	public void setIdCidade(int idCidade) {
		this.idCidade = idCidade;
	}

	public int getIdCidade() {
		return idCidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getEstadoFK() {
		return estadoFK;
	}

	public void setEstadoFK(int estadoFK) {
		this.estadoFK = estadoFK;
	}

	@Override
	public String toString(){
		
		return this.descricao;
	}
	
}
