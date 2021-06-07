package br.com.EditoraPremium.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.EditoraPremiun.vo.FuncionarioVO;

public class FuncionarioDAO {

	public boolean inserir(Connection conn, FuncionarioVO funcionario) {

		String sql = "INSERT INTO funcionarios (idFunc, nome, nascimento, dataContrat, tipoSalario, salario) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";

		try {

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, funcionario.getIdFunc());
			stmt.setString(2, funcionario.getNome());
			stmt.setDate(3, new java.sql.Date(funcionario.getNascimento().getTime()));
			stmt.setDate(4, new java.sql.Date(funcionario.getDataContrat().getTime()));
			stmt.setString(5, funcionario.getTipoSalario());
			stmt.setFloat(6, funcionario.getSalario());
			stmt.executeUpdate();
			return true;

		} catch (SQLException ex) {

			Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, "Erro no INSERT", ex);
			return false;
		}
	}

	public boolean alterar (Connection conn, FuncionarioVO funcionario){
		
		String sql = "UPDATE funcionarios SET nome = ?, nascimento = ?, dataContrat = ?, "
				   + "tipoSalario = ? salario = ? WHERE idFunc = ?";
		
		try {
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, funcionario.getIdFunc());
	    	stmt.setString(2, funcionario.getNome());
	    	stmt.setDate(3, new java.sql.Date(funcionario.getNascimento().getTime()));
	    	stmt.setDate(4, new java.sql.Date(funcionario.getDataContrat().getTime()));
	    	stmt.setString(5, funcionario.getTipoSalario());
	    	stmt.setFloat(6, funcionario.getSalario());
	    	stmt.executeUpdate();
	    	return true;
			
			
		} catch (SQLException ex) {
			
			Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, "Erro no UPDATE", ex);
			return false;
		}
	}

	public boolean remover(Connection conn, FuncionarioVO funcionario){
		
		String sql = "DELETE FROM funcionarios WHERE idFunc = ?";
		
		try {
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, funcionario.getIdFunc());
			stmt.executeQuery();
			return true;
			
		} catch (SQLException ex) {
			
			Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, "Erro no DELETE", ex);
			return false;
		}
	}
	
	public List<FuncionarioVO> selecionarTodos (Connection conn){
		
		List<FuncionarioVO> lista = new LinkedList<>();
		
		String sql = "SELECT idFunc, nome, nascimento, dataContrat, "
				   + "tipoSalario, salario FROM funcionarios ORDER BY idFunc";
		
		try {
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()){
				
				FuncionarioVO funcionario = new FuncionarioVO();
				funcionario.setIdFunc(rs.getInt("idFunc"));
				funcionario.setNome(rs.getString("nome"));
				funcionario.setNascimento(rs.getDate("nascimento"));
				funcionario.setDataContrat(rs.getDate("dataContrat"));
				funcionario.setTipoSalario(rs.getString("tipoSalario"));
				funcionario.setSalario(rs.getFloat("salario"));
				lista.add(funcionario);
			}
			
		} catch (SQLException ex) {
			
			Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, "Erro no SELECT", ex);
		}
		
		return lista;
	}
}
