package br.com.EditoraPremium.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.EditoraPremium.framework.db.DatabaseConnectionFactory;
import br.com.EditoraPremiun.vo.CidadeVO;



public class CidadeDAO {
	
	private Connection conn;

	public CidadeDAO() {
		this.conn = new DatabaseConnectionFactory().getConnection();
	}
	
	public void setConnection(Connection conn){
		this.conn = conn;
	}

	public boolean inserir(CidadeVO cidade) {

		String sql = ("INSERT INTO cidade (descricao, estadoFK) VALUES (?, ?)");

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, cidade.getDescricao());
			stmt.setInt(2, cidade.getEstadoFK());
			stmt.execute();
			return true;
		} catch (SQLException ex) {
			Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, "Erro no insert", ex);
			return false;
		}
	}

	public boolean alterar(CidadeVO cidade) {

		String sql = "UPDATE cidade SET descricao = ?, estadoFK = ? WHERE idCidade = ?";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);		
			stmt.setString(1, cidade.getDescricao());
			stmt.setLong(2, cidade.getEstadoFK());
			stmt.setLong(3, cidade.getIdCidade());
			stmt.executeUpdate();
			return true;
		} catch (SQLException ex) {
			Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, "Erro no Update", ex);
			return false;
		}

	}

	public boolean remover(CidadeVO idCidade) {

		String sql = "DELETE FROM cidade WHERE idCidade = ?";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, idCidade.getIdCidade());
			stmt.executeUpdate();
			return true;
		} catch (SQLException ex) {
			Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, "Erro na Remoção", ex);
			return false;
		}
	}

	public List<CidadeVO> SelecionarTodos() {

		List<CidadeVO> cidades = new LinkedList<>();
		String sql = "SELECT c.idCidade, c.descricao, e.sigla "
			       + "FROM cidade c JOIN estado e ON estadoFK = idEstado ORDER BY idCidade";

		try {

			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				CidadeVO cidade = new CidadeVO();
				cidade.setIdCidade(rs.getInt("c.idCidade"));
				cidade.setDescricao(rs.getString("c.descricao"));
				cidade.setEstadJoin(rs.getString("e.sigla"));
				cidades.add(cidade);
			}
		} catch (SQLException ex) {
			Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, "Erro na Seleção", ex);

		}
		return cidades;

	}

	public CidadeVO buscarDescricao(String cidade) throws Exception {
		CidadeVO cidades = null;
		String sql = "SELECT idCidade, descricao, sigla FROM cidade ";

		try {

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(2, cidade);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {

				cidades = new CidadeVO();
				cidades.setDescricao(rs.getString("descricao"));

			}
		} catch (SQLException ex) {
			Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, "Erro Seleção Pelo ID", ex);
		}

		return cidades;

	}

	public static void main(String[] args) {
		try {
		

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
