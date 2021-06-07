package br.com.EditoraPremium.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.EditoraPremiun.vo.ListaVO;

public class ListaDAO {

	public boolean inserir(Connection conn, ListaVO lista) {

		String sql = "INSERT INTO lista (idLista, nome, profissao, escolaridade, telefone) VALUES (?, ?, ?, ?, ?)";

		try {

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, lista.getIdLista());
			stmt.setString(2, lista.getNome());
			stmt.setString(3, lista.getProfissao());
			stmt.setString(4, lista.getEscolaridade());
			stmt.setString(5, lista.getTelefone());
			stmt.executeUpdate();
			return true;

		} catch (SQLException ex) {
			Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, "Erro no INSERT", ex);
			return false;
		}
	}

	public boolean alterar(Connection conn, ListaVO lista) {

		String sql = "UPDATE lista SET idLista = ?, nome = ?, profissao = ?, "
				+ "escolaridade = ?, telefone = ? WHERE idLista = ?";

		try {

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, lista.getIdLista());
			stmt.setString(2, lista.getNome());
			stmt.setString(3, lista.getProfissao());
			stmt.setString(4, lista.getEscolaridade());
			stmt.setString(5, lista.getTelefone());
			stmt.executeUpdate();
			return true;

		} catch (SQLException ex) {
			Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, "Erro no UPDATE", ex);
			return false;
		}
	}

	public boolean remover(Connection conn, ListaVO lista) {

		String sql = "DELETE FROM lista WHERE idLista = ?";

		try {

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, lista.getIdLista());
			stmt.executeUpdate();
			return true;

		} catch (SQLException ex) {

			Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, "Erro no DELETE", ex);
			return false;
		}
	}

	public List<ListaVO> selecionarTodos(Connection conn) {

		List<ListaVO> listas = new LinkedList<>();

		String sql = "SELECT idLista, nome, profissao, escolaridade, telefone FROM lista";

		try {

			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				ListaVO lista = new ListaVO();
				lista.setIdLista(rs.getInt("idLista"));
				lista.setNome(rs.getString("nome"));
				lista.setProfissao(rs.getString("profissao"));
				lista.setEscolaridade(rs.getString("escolaridade"));
				lista.setTelefone(rs.getString("telefone"));
				listas.add(lista);
			}

		} catch (SQLException ex) {
			Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, "Erro no Select", ex);
		}

		return listas;
	}

}
