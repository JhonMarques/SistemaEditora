package br.com.EditoraPremiun.vo;

public class CursoVO {
	private int idCurso;
	private String descricao;
	private int material;
	private float valor;

	public int getIdCurso() {
		return idCurso;
	}
	
	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}


	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getMaterial() {
		return material;
	}

	public void setMaterial(int material) {
		this.material = material;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

}
