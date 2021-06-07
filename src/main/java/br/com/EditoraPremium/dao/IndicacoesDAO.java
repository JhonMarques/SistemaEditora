package br.com.EditoraPremium.dao;

import br.com.EditoraPremium.framework.db.DatabaseConnectionFactory;
import br.com.EditoraPremiun.vo.IndicacoesVO;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IndicacoesDAO {


	private Connection conn;
	
	
	public IndicacoesDAO() {

		this.conn = new DatabaseConnectionFactory().getConnection();
	};

	public void setConnection(Connection conn){
		this.conn = conn;
	}

	
	public boolean inserir(IndicacoesVO indicacoes) {

		String sql = "INSERT INTO indicacoes (idIndica, nome, fone, " + "idCliente, descricao) VALUES (?, ?, ?, ?, ?)";

		try {

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, indicacoes.getIdIndica());
			stmt.setString(2, indicacoes.getNome());
			stmt.setString(3, indicacoes.getFone());
			stmt.setInt(4, indicacoes.getClienteFK());
			stmt.setString(5, indicacoes.getDescricao());
			stmt.executeUpdate();
			return true;

		} catch (SQLException ex) {

			Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, "Erro no insert", ex);
			return false;
		}
	}

	public boolean alterar(IndicacoesVO indica) {

		String sql = "UPDATE indicacoes SET nome = ?, fone = ?, " 
		           + "idCliente = ?, descricao = ? WHERE idIndica = ?";

		try {

			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, indica.getNome());
			stmt.setString(2, indica.getFone());
			stmt.setInt(3, indica.getClienteFK());
			stmt.setString(4, indica.getDescricao());
			stmt.setInt(5, indica.getIdIndica());
			stmt.execute();
			return true;

		} catch (SQLException ex) {

			Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, "Erro no insert", ex);
			return false;
		}
	}

	public boolean remover(IndicacoesVO indica) {

		String sql = "DELETE FROM indicacoes WHERE idIndica = ?";

		try {

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, indica.getIdIndica());
			stmt.executeUpdate();
			return true;

		} catch (Exception ex) {

			Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, "Erro no Delete", ex);
			return false;
		}
	}

	public List<IndicacoesVO> selecionarTodos() {

		List<IndicacoesVO> lista = new LinkedList<>();

		String sql = "SELECT idIndica, nome, fone, idCliente, descricao FROM indicacoes";

		try {

			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				IndicacoesVO indica = new IndicacoesVO();
				indica.setIdIndica(rs.getInt("idIndica"));
				indica.setNome(rs.getString("nome"));
				indica.setFone(rs.getString("fone"));
				indica.setClienteFK(rs.getInt("idCliente"));
				indica.setDescricao(rs.getString("descricao"));
				lista.add(indica);

			}

		} catch (Exception ex) {
			Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, "Erro no SELECT", ex);
		}
		
		return lista;
	}

}
