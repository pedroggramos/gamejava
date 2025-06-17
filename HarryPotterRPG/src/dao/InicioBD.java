package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InicioBD {

	private static final String URL1 = "jdbc:mysql://localhost:3306";
	private static final String URL2 = "jdbc:mysql://localhost:3306/game";
	
	public InicioBD() {
		String sql1 = "CREATE DATABASE IF NOT EXISTS game";
		String sql2 = "CREATE TABLE IF NOT EXISTS perguntas(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, pergunta VARCHAR(300), alt_a VARCHAR(200), alt_b VARCHAR(200), alt_c VARCHAR(200), alt_d VARCHAR(200), resposta_correta VARCHAR(1))";
		
		try {
			Connection conexao = DriverManager.getConnection(URL1, "root", "");
			PreparedStatement operacao = conexao.prepareStatement(sql1);
			operacao.execute();
			conexao.close();
			
			conexao = DriverManager.getConnection(URL2, "root", "");
			operacao = conexao.prepareStatement(sql2);
			operacao.execute();
			conexao.close();
			
			System.out.println("BD e tabela criada com sucesso!");			
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}