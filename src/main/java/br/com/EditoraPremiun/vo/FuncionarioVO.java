package br.com.EditoraPremiun.vo;

import java.util.Date;

public class FuncionarioVO {

	private int idFunc;
	private String nome;
	private Date nascimento;
	private Date dataContrat;
	private String tipoSalario;
	private float salario;

	public float getSalario() {
		return salario;
	}

	public void setSalario(float salario) {
		this.salario = salario;
	}

	public int getIdFunc() {
		return idFunc;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getNascimento() {
		return nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	public void setIdFunc(int idFunc) {
		this.idFunc = idFunc;
	}

	public Date getDataContrat() {
		return dataContrat;
	}

	public void setDataContrat(Date dataContrat) {
		this.dataContrat = dataContrat;
	}

	public String getTipoSalario() {
		return tipoSalario;
	}

	public void setTipoSalario(String tipoSalario) {
		this.tipoSalario = tipoSalario;
	}

}
