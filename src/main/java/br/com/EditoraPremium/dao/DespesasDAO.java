package br.com.EditoraPremium.dao;

import java.sql.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.EditoraPremiun.vo.DespesasVO;

public class DespesasDAO {

	public boolean inserir(Connection conn, DespesasVO despesa) {

		String sql = "INSERT INTO despesas (idDespesa, descricao, valor, dataDespesa) "
		           + "VALUES (?, ?, ?, ?)";

		try {

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, despesa.getIdDespesa());
			stmt.setString(2, despesa.getDescricao());
			stmt.setFloat(3, despesa.getValor());
			stmt.setDate(4, new java.sql.Date(despesa.getDataDespesa().getTime()));
			stmt.executeUpdate();
			return true;

		} catch (SQLException ex) {
			Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, "Erro no insert", ex);
			return false;
		}
	}

	public boolean alterar(Connection conn, DespesasVO despesa) {

		String sql = "UPDATE despesas SET descricao = ?, valor = ?, "
				   + "dataDespesa = ? WHERE idDespesa = ?";

		try {

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, despesa.getIdDespesa());
			stmt.setString(2, despesa.getDescricao());
			stmt.setFloat(3, despesa.getValor());
			stmt.setDate(4, new java.sql.Date(despesa.getDataDespesa().getTime()));
			stmt.executeUpdate();
			return true;

		} catch (SQLException ex) {
			Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, "Erro no insert", ex);
			return false;
		}

	}

	public boolean remover(Connection conn, DespesasVO despesa) {

		String sql = "DELETE FORM despesas WHERE idDespesa = ?";

		try {

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, despesa.getIdDespesa());
			stmt.executeUpdate();
			return true;

		} catch (SQLException ex) {

			Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, "Erro no Delete", ex);
			return false;
		}
	}

	public List<DespesasVO> selecionarTodos(Connection conn) {

		List<DespesasVO> despesas = new LinkedList<DespesasVO>();

		String sql = "SELECT idDespesa, descricao, valor, dataDespesa ORDER BY idDespesa";

		try {

			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			DespesasVO despesa = new DespesasVO();

			while (rs.next()) {
				despesa.setIdDespesa(rs.getInt("idCliente"));
				despesa.setDescricao(rs.getString("descricao"));
				despesa.setValor(rs.getFloat("valor"));
				despesa.setDataDespesa(new Date(rs.getDate("dataDespesa").getTime()));

				despesas.add(despesa);
			}
		} catch (SQLException ex) {

			Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, "Erro no Selec all", ex);
		}

		return despesas;
	}

	public DespesasVO selecionarPeloId(Connection conn, int idDespesa) {

		DespesasVO despesa = null;

		String sql = "SELECT idDespesa, descricao, valor, dataDespesa WHERE idDespesa = ?";

		try {

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, 0);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {

				despesa = new DespesasVO();
				despesa.setIdDespesa(rs.getInt("idCliente"));
				despesa.setDescricao(rs.getString("descricao"));
				despesa.setValor(rs.getFloat("valor"));
				despesa.setDataDespesa(new Date(rs.getDate("dataDespesa").getTime()));

			}
		} catch (SQLException ex) {
			Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, "Erro no Selec for ID", ex);
		}
		
		return despesa;
	}

}
