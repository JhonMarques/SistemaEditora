package br.com.EditoraPremium.dao;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.EditoraPremiun.vo.EscolaridadeVO;

public class EscolaridadeDAO {

	public boolean inserir(Connection conn, EscolaridadeVO escolaridade) {

		String sql = "INSERT INTO escolaridade (idEscola, descricao) VALUES(?, ?)";

		try {

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, escolaridade.getIdEscola());
			stmt.setString(2, escolaridade.getDescricao());
			stmt.executeUpdate();
			return true;

		} catch (SQLException ex) {
			Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, "Erro no insert", ex);
			return false;
		}
	}

	public boolean alterar(Connection conn, EscolaridadeVO escolaridade) {

		String sql = "UPDATE escolaridade SET descricao = ? WHERE idEscola = ?";

		try {

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, escolaridade.getIdEscola());
			stmt.setString(2, escolaridade.getDescricao());
			stmt.executeQuery();
			return true;

		} catch (SQLException ex) {
			Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, "Erro no UPDATE", ex);
			return false;
		}
	}

	public boolean remover(Connection conn, EscolaridadeVO escolaridade) {

		String sql = "DELETE FROM escolaridade WHERE idEscola = ?";

		try {
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, escolaridade.getIdEscola());
			stmt.execute();
			return true;

		} catch (SQLException ex) {
			Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, "Erro no DELETE", ex);
			return false;
		}
	}

	public List<EscolaridadeVO> selecionarTodos(Connection conn) {

		List<EscolaridadeVO> selecao = new LinkedList<>();
		String sql = "SELECT idEscola, descricao FROM escolaridade ORDER BY idEscola";

		try {

			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				EscolaridadeVO escolaridade = new EscolaridadeVO();
				escolaridade.setIdEscola(rs.getInt("idEscola"));
				escolaridade.setDescricao(rs.getString("descricao"));
				selecao.add(escolaridade);
			}

		} catch (SQLException ex) {
			Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, "Erro no Select", ex);
		}

		return selecao;
	}

	public EscolaridadeVO selecionarPeloId(Connection conn, int idEscola) {

		EscolaridadeVO escola = null;
		String sql = "SELECT idEscola, descricao FROM escolaridade WHERE idEscola = ?";

		try {

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, 1);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {

				escola = new EscolaridadeVO();
				escola.setDescricao(rs.getString("descricao"));

			}

		} catch (SQLException ex) {

			Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, "Erro no Select ID", ex);
		}
		return escola;
	}

}
