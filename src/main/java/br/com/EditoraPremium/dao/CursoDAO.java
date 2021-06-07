package br.com.EditoraPremium.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.EditoraPremiun.vo.CursoVO;

public class CursoDAO {

	public boolean inserir(Connection conn, CursoVO curso) {

		String sql = "INSERT INTO curso (idCurso, descricao, materialFK, valor) VALUES (?, ?, ?, ?, ?)";

		try {

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, curso.getIdCurso());
			stmt.setString(2, curso.getDescricao());
			stmt.setInt(3, curso.getMaterial());
			stmt.setFloat(4, curso.getValor());
			stmt.executeUpdate();
			return true;

		} catch (SQLException ex) {

			Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, "Erro no insert", ex);
			return false;
		}

	}

	public boolean alterar(Connection conn, CursoVO curso) {

		String sql = "UPDATE curso SET descricao = ?, materialFK = ?, valor =? WHERE idCurso = ?";

		try {

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, curso.getIdCurso());
			stmt.setString(2, curso.getDescricao());
			stmt.setInt(3, curso.getMaterial());
			stmt.setFloat(4, curso.getValor());
			stmt.executeUpdate();
			return true;

		} catch (SQLException ex) {

			Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, "Erro no insert", ex);
			return false;

		}
	}

	public boolean remover(Connection conn, CursoVO curso) {

		String sql = "DELETE FROM curso WHERE idCurso = ?";

		try {

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, curso.getIdCurso());
			stmt.executeUpdate();
			return true;

		} catch (SQLException ex) {

			Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, "Erro no DELETE", ex);
			return false;
		}

	}

	public List<CursoVO> selecionarTodos(Connection conn) {

		List<CursoVO> cursos = new LinkedList<>();

		String sql = "SELECT idCurso, descricao, valor FROM curso";

		try {

			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				CursoVO curso = new CursoVO();
				curso.setIdCurso(rs.getInt("idCurso"));
				curso.setDescricao(rs.getString("descricao"));
				curso.setValor(rs.getFloat("valor"));
				cursos.add(curso);
			}

		} catch (SQLException ex) {

			Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, "Erro no DELETE", ex);

		}

		return cursos;

	}

}
