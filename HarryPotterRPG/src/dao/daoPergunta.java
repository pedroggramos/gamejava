package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Pergunta;

public class daoPergunta {

    private static final String URL = "jdbc:mysql://localhost:3306/game";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public Pergunta buscarPerguntaAleatoria() {
        Pergunta p = null;
        String sql = "SELECT * FROM perguntas ORDER BY RAND() LIMIT 1";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement op = conn.prepareStatement(sql);
             ResultSet result = op.executeQuery()) {

            if (result.next()) {
                p = new Pergunta();
                p.setId(result.getInt("id"));
                p.setPergunta(result.getString("pergunta"));
                p.setAlt_a(result.getString("alt_a"));
                p.setAlt_b(result.getString("alt_b"));
                p.setAlt_c(result.getString("alt_c"));
                p.setAlt_d(result.getString("alt_d"));
                p.setResposta_correta(result.getString("resposta_correta"));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar pergunta: " + e.getMessage());
        }

        return p;
    }
}
