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
import br.com.EditoraPremiun.vo.EstadoVO;

public class EstadoDAO {

	private Connection conn;
	
	
	public EstadoDAO() {

		this.conn = new DatabaseConnectionFactory().getConnection();
	};

	public void setConnection(Connection conn){
		this.conn = conn;
	}
	
	public void inserir(EstadoVO estado) {

		String sql = "INSERT INTO estado (idEstado, sigla, descricao) VALUES (?, ?, ?)";

		try {

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, estado.getIdEstado());
			stmt.setString(2, estado.getSigla());
			stmt.setString(3, estado.getDescricao());
			stmt.execute();
		} catch (SQLException ex) {
			Logger.getLogger(EstadoDAO.class.getName()).log(Level.SEVERE, "Insira um Estado Válido", ex);
		}
	}

	public boolean alterar(EstadoVO estado) {

		String sql = "UPDATE estado SET  descricao = ?, sigla = ? WHERE idEstado = ?";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, estado.getIdEstado());
			stmt.setString(2, estado.getSigla());
			stmt.setString(3, estado.getDescricao());
			stmt.executeUpdate();
			return true;
		} catch (SQLException ex) {
			Logger.getLogger(EstadoDAO.class.getName()).log(Level.SEVERE, "Erro na Alteração", ex);
			return false;
		}

	}

	public boolean remover(EstadoVO estado) {

		String sql = "DELETE FROM estado WHERE idEstado = ? ";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, estado.getIdEstado());
			stmt.execute();
			return true;
		} catch (SQLException ex) {
			Logger.getLogger(EstadoDAO.class.getName()).log(Level.SEVERE, "Erro na Exclusão", ex);
			return false;
		}
	}

	public List<EstadoVO> selecionarTodos() {
		List<EstadoVO> estados = new LinkedList<>();

		String sql = "SELECT * FROM estado ORDER BY sigla";

		try {

			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				EstadoVO estado = new EstadoVO();
				estado.setIdEstado(rs.getInt("idEstado"));
				estado.setSigla(rs.getString("sigla"));
				estado.setDescricao(rs.getString("descricao"));
				estados.add(estado);
			}
		} catch (SQLException ex) {
			Logger.getLogger(EstadoDAO.class.getName()).log(Level.SEVERE, "Erro ao Selecionar", ex);
		}

		return estados;
	}

	public EstadoVO buscarPeloId(int id) {

		EstadoVO estado = null;
		String sql = "SELECT idEstado, sigla, desricao FROM estado WHERE idEstado = ?";

		try {

			PreparedStatement stmt = conn.prepareStatement(sql);

			// Atribuicao de valores nos marcadores/bind (? da SQL)
			stmt.setInt(1, 1);

			// Execucao da instrucao SQL (retorno de uma colecao de registros do
			// banco de dados)
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				estado = new EstadoVO();
				estado.setSigla(rs.getString("sigla"));
				estado.setDescricao(rs.getString("descricao"));

			}
		} catch (Exception ex) {
			Logger.getLogger(EstadoDAO.class.getName()).log(Level.SEVERE, "Erro na busca pelo ID", ex);
		}
		return estado;
	}

	public EstadoVO buscarDescricao(EstadoVO estado) {

		EstadoVO retorno = new EstadoVO();
		String sql = "SELECT * FROM estado WHERE descricao = ?";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(2, estado.getDescricao());
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				estado.setDescricao("descricao");
				retorno = estado;
			}

		} catch (SQLException ex) {
			Logger.getLogger(EstadoDAO.class.getName()).log(Level.SEVERE, "Erro na busca pela descricao", ex);
		}
		return retorno;
	}
}
