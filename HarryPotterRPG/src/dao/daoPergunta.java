package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.Pergunta;

public class daoPergunta {

    private static final String URL = "jdbc:mysql://localhost:3306/game";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static List<Integer> perguntasUsadas = new ArrayList<>();

    public Pergunta buscarPerguntaAleatoria() {
        Pergunta p = null;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {

            int total = contarTotalPerguntas(conn);

            if (perguntasUsadas.size() >= total) {
                perguntasUsadas.clear();
            }

            String sql;
            if (perguntasUsadas.isEmpty()) {
                sql = "SELECT * FROM perguntas ORDER BY RAND() LIMIT 1";
            } else {
                String usados = perguntasUsadas.stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(","));
                sql = "SELECT * FROM perguntas WHERE id NOT IN (" + usados + ") ORDER BY RAND() LIMIT 1";
            }

            try (PreparedStatement op = conn.prepareStatement(sql);
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

                    perguntasUsadas.add(p.getId());
                }

            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar pergunta: " + e.getMessage());
        }

        return p;
    }

    
    private int contarTotalPerguntas(Connection conn) throws SQLException {
        String sql = "SELECT COUNT(*) FROM perguntas";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }
}
