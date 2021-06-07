package br.com.EditoraPremiun.vo;

import java.util.Date;

public class VendaVO {

	private int idVenda;
	private Date dataVenda;
	private String statusVenda;
	private int funcFK;
	private int clienteFK;
	private int cursoFK;

	public int getIdVenda() {
		return idVenda;
	}

	public void setIdVenda(int idVenda) {
		this.idVenda = idVenda;
	}

	public Date getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}

	public String getStatusVenda() {
		return statusVenda;
	}

	public void setStatusVenda(String statusVenda) {
		this.statusVenda = statusVenda;
	}

	public int getFuncFK() {
		return funcFK;
	}

	public void setFuncFK(int funcFK) {
		this.funcFK = funcFK;
	}

	public int getClienteFK() {
		return clienteFK;
	}

	public void setClienteFK(int clienteFK) {
		this.clienteFK = clienteFK;
	}

	public int getCursoFK() {
		return cursoFK;
	}

	public void setCursoFK(int cursoFK) {
		this.cursoFK = cursoFK;
	}

}
