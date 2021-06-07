package br.com.EditoraPremium.framework.db;

import java.sql.*;




public class DatabaseConnectionFactory {

	private static final String USER = "root";
	private static final String PASS = "mysql";
	private static final String DRIVER = "jdbc:mysql://localhost/editora?useTimezone=true&serverTimezone=UTC";
   
	
public Connection getConnection(){
	
	Connection conn = null;
	
	try {
		conn = DriverManager.getConnection(DRIVER, USER, PASS);
		
	} catch (SQLException e) {
		throw new RuntimeException(e);
	}
	return conn;
}

}