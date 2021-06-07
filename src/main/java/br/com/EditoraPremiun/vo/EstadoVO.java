package br.com.EditoraPremiun.vo;

public class EstadoVO {

	private int idEstado;
	private String sigla;
	private String descricao;


	public int getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado ;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	@Override
	public String toString(){
		
		return this.sigla;
	}
}
