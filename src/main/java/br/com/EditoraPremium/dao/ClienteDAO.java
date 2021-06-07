package br.com.EditoraPremium.dao;

import java.util.List;
import java.sql.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import br.com.EditoraPremiun.vo.ClienteVO;

public class ClienteDAO {

	public boolean inserir(Connection conn, ClienteVO cliente) {

		String sql = "INSERT INTO cliente (idCliente, nome, fone, foneRecado, email, profissao, cpf,"
				+ " endereco, cidadeFK, escolaridadeFK, nasCli, cep)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, cliente.getIdCliente());
			stmt.setString(2, cliente.getNome());
			stmt.setString(3, cliente.getFone());
			stmt.setString(4, cliente.getFoneRecado());
			stmt.setString(5, cliente.getFone());
			stmt.setString(6, cliente.getEmail());
			stmt.setString(7, cliente.getProfissao());
			stmt.setString(8, cliente.getCpf());
			stmt.setString(9, cliente.getEndereco());
			stmt.setInt(10, cliente.getCidadeFK());
			stmt.setDate(11, new java.sql.Date(cliente.getNasCli().getTime()));
			stmt.setInt(12, cliente.getEscolaridadeFK());
			stmt.executeUpdate();
			return true;

		} catch (SQLException ex) {
			Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, "Erro no insert", ex);
			return false;
		}
	}

	public boolean alterar(Connection conn, ClienteVO cliente) {

		String sql = "UPDATE cliente SET nome = ?, fone = ?, foneRecado = ?, email = ?, profissao = ?,"
				+ " cpf = ?, endereco = ?, cidadeFK = ?, escolaridadeFK = ?, nasCli = ?, cep = ? WHERE idCliente = ?";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, cliente.getIdCliente());
			stmt.setString(2, cliente.getNome());
			stmt.setString(3, cliente.getFone());
			stmt.setString(4, cliente.getFoneRecado());
			stmt.setString(5, cliente.getFone());
			stmt.setString(6, cliente.getEmail());
			stmt.setString(7, cliente.getProfissao());
			stmt.setString(8, cliente.getCpf());
			stmt.setString(9, cliente.getEndereco());
			stmt.setInt(10, cliente.getCidadeFK());
			stmt.setInt(11, cliente.getEscolaridadeFK());
			stmt.executeUpdate();
			return true;

		} catch (SQLException ex) {
			Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, "Erro no UPDATE", ex);
			return false;
		}
	}

	public boolean remover(Connection conn, ClienteVO cliente) {

		String sql = "DELETE FROM cliente WHERE idCliente = ?";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, cliente.getIdCliente());
			stmt.executeUpdate();
			return true;

		} catch (SQLException ex) {
			Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, "Erro no DELETE", ex);
			return false;
		}
	}

	public List<ClienteVO> selecionarTodos(Connection conn) {

		List<ClienteVO> clientes = new LinkedList<>();

		String sql = "SELECT c.idCliente, c.nome, c.fone, c.foneRecado, c.email, "
				+ "c.profissao, c.cpf, c.endereco, d.descricao, f.sigla, e.descricao, "
				+ "c.nasCli, c.cep FROM cliente c JOIN cidade d ON c.cidadeFK = d.idCidade JOIN estado f "
				+ "ON d.estadoFK = f.idEstado JOIN escolaridade e ON c.escolaridadeFK = e.idEscola "
				+ "ORDER BY idCliente";

		try {

			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				ClienteVO cliente = new ClienteVO();
				cliente.setIdCliente(rs.getInt("idCliente"));
				cliente.setNome(rs.getString("nome"));
				cliente.setFone(rs.getString("fone"));
				cliente.setFoneRecado(rs.getString("foneRecado"));
				cliente.setEmail(rs.getString("email"));
				cliente.setProfissao(rs.getString("profissao"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setEndereco(rs.getString("endereco"));
				cliente.setCidadeFK(rs.getInt("cidadeFK"));
				cliente.setEscolaridadeFK(rs.getInt("escolaridadeFK"));
				cliente.setNasCli(new Date(rs.getDate("nasCli").getTime()));
				cliente.setCep(rs.getString("cep"));
				clientes.add(cliente);

			}

		} catch (SQLException ex) {
			Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, "Erro no SELECET * FROM", ex);
		}

		return clientes;

	}

	public ClienteVO buscarPeloId(Connection conn, int id) {

		ClienteVO cliente = null;

		String sql = "SELECT c.idCliente, c.nome, c.fone, c.foneRecado, c.email, "
				+ "c.profissao, c.cpf, c.endereco, d.descricao, f.sigla, e.descricao, "
				+ "c.nasCli, c.cep FROM cliente c JOIN cidade d ON c.cidadeFK = d.idCidade JOIN estado f "
				+ "ON d.estadoFK = f.idEstado JOIN escolaridade e ON c.escolaridadeFK = e.idEscola "
				+ "WHERE idCliente = ?";

		try {

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, 0);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {

				cliente = new ClienteVO();
				cliente.setIdCliente(rs.getInt("idCliente"));
				cliente.setNome(rs.getString("nome"));
				cliente.setFone(rs.getString("fone"));
				cliente.setFoneRecado(rs.getString("foneRecado"));
				cliente.setEmail(rs.getString("email"));
				cliente.setProfissao(rs.getString("profissao"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setEndereco(rs.getString("endereco"));
				cliente.setCidadeFK(rs.getInt("cidadeFK"));
				cliente.setEscolaridadeFK(rs.getInt("escolaridadeFK"));
				cliente.setNasCli(new Date(rs.getDate("nasCli").getTime()));
				cliente.setCep(rs.getString("cep"));
			}

		} catch (Exception ex) {
			Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, "Erro no Busca por ID", ex);
		}

		return cliente;

	}

}
