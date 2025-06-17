package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InserirPerguntas {

    private static final String URL = "jdbc:mysql://localhost:3306/game";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public InserirPerguntas() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {

            
            String checkSql = "SELECT COUNT(*) FROM perguntas";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            int total = rs.getInt(1);

            if (total > 0) {
                System.out.println("Perguntas já existem. Nenhuma inserção feita.");
                return;
            }

            
            String insertSql = "INSERT INTO perguntas(pergunta, alt_a, alt_b, alt_c, alt_d, resposta_correta) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement insertStmt = conn.prepareStatement(insertSql);

         
            adicionar(insertStmt, "Qual é a casa de Harry Potter?", "Grifinória", "Sonserina", "Lufa-Lufa", "Corvinal", "A");
            adicionar(insertStmt, "Quem é o diretor de Hogwarts?", "Snape", "Dumbledore", "Moody", "Voldemort", "B");
            adicionar(insertStmt, "Qual é o animal símbolo da Sonserina?", "Leão", "Corvo", "Cobra", "Texugo", "C");
            adicionar(insertStmt, "Qual é o nome do castelo onde se passa a história?", "Durmstrang", "Hogwarts", "Beauxbatons", "Ilvermorny", "B");
            adicionar(insertStmt, "Quem é o melhor amigo de Harry?", "Draco", "Neville", "Rony", "Cedrico", "C");
            adicionar(insertStmt, "Qual é o patrono de Harry?", "Cervo", "Cão", "Gato", "Coruja", "A");
            adicionar(insertStmt, "Quem é o inimigo principal de Harry?", "Lucius", "Sirius", "Voldemort", "Dumbledore", "C");
            adicionar(insertStmt, "Qual é o esporte jogado em vassouras?", "Quadribol", "Polo", "Futebol", "Basquete", "A");
            adicionar(insertStmt, "Quem é o guardião da chave em Hogwarts?", "Filch", "Hagrid", "Dobby", "Sirius", "B");
            adicionar(insertStmt, "Qual é a plataforma do trem para Hogwarts?", "9 ½", "10", "9 ¾", "8 ¾", "C");
            adicionar(insertStmt, "Quem é o professor de Poções no primeiro ano?", "Quirrell", "Dumbledore", "Snape", "Lockhart", "C");
            adicionar(insertStmt, "Qual o nome completo de Dumbledore?", "Alvo Brian Dumbledore", "Alvo Percival Wulfric Brian Dumbledore", "Alvo Severus Dumbledore", "Alvo Wulfric Grindelwald", "B");
            adicionar(insertStmt, "Qual o nome do banco dos bruxos?", "Diagon", "Gringotes", "Nimbus", "Galeões", "B");
            adicionar(insertStmt, "Quem é o elfo doméstico que tenta salvar Harry no segundo ano?", "Dobby", "Kreacher", "Winky", "Hokey", "A");
            adicionar(insertStmt, "Qual a profissão dos pais de Hermione?", "Dentistas", "Bruxos", "Aurores", "Professores", "A");
            adicionar(insertStmt, "Quem é o lobisomem que ensina Defesa Contra as Artes das Trevas no terceiro ano?", "Snape", "Karkaroff", "Lupin", "Quirrell", "C");
            adicionar(insertStmt, "O que representa o símbolo das Relíquias da Morte?", "Varinha, pedra e capa", "Vassoura, varinha e chapéu", "Três feitiços", "Três irmãos", "A");
            adicionar(insertStmt, "Qual é a varinha de Harry feita de quê?", "Carvalho e pena de fênix", "Azevinho e pena de fênix", "Teixo e pelo de unicórnio", "Videira e cabelo de veela", "B");
            adicionar(insertStmt, "Quem fundou a casa Lufa-Lufa?", "Helga", "Helena", "Godrico", "Rowena", "A");
            adicionar(insertStmt, "Quem é a mãe de Draco Malfoy?", "Bellatrix", "Andrômeda", "Narcisa", "Merope", "C");
            adicionar(insertStmt, "Qual animal representa a Corvinal?", "Águia", "Coruja", "Falcão", "Corvo", "A");
            adicionar(insertStmt, "Como se chama o feitiço que desarma?", "Alohomora", "Expelliarmus", "Lumos", "Expecto", "B");
            adicionar(insertStmt, "Qual é o nome da vassoura de Harry?", "Nimbus 2001", "Nimbus 2000", "Firebolt", "Comet 260", "C");
            adicionar(insertStmt, "O que Harry ganha de presente no primeiro Natal em Hogwarts?", "Varinha", "Manto da Invisibilidade", "Firebolt", "Coruja", "B");
            adicionar(insertStmt, "Quem matou Dumbledore?", "Voldemort", "Draco", "Snape", "Bellatrix", "C");
            adicionar(insertStmt, "O que são os Comensais da Morte?", "Aurores", "Seguidores de Dumbledore", "Seguidores de Voldemort", "Criaturas mágicas", "C");
            adicionar(insertStmt, "O que há dentro do armário que mostra o seu maior medo?", "Espelho", "Bicho-papão", "Dementador", "Inferi", "B");
            adicionar(insertStmt, "Qual feitiço é usado para patrono?", "Accio", "Lumos", "Expecto Patronum", "Expelliarmus", "C");
            adicionar(insertStmt, "Quem é o pai de Harry?", "Remo Lupin", "Sirius Black", "Tiago Potter", "Severo Snape", "C");
            adicionar(insertStmt, "Qual é o nome do irmão de Dumbledore?", "Aberforth", "Alastor", "Percival", "Grindelwald", "A");
            adicionar(insertStmt, "Quem é a mulher que dirige Beauxbatons?", "Bellatrix", "Fleur", "Madame Maxime", "Narcisa", "C");
            adicionar(insertStmt, "Qual animal é o patrono de Hermione?", "Cervo", "Coelho", "Lontra", "Coruja", "C");
            adicionar(insertStmt, "Quem é a autora de \"Hogwarts: Uma História\"?", "Rita Skeeter", "Bathilda Bagshot", "Minerva McGonagall", "Helena Ravenclaw", "B");
            adicionar(insertStmt, "Qual é o nome verdadeiro de Voldemort?", "Tom Riddle", "James Riddle", "Severus Riddle", "Thomas Potter", "A");
            adicionar(insertStmt, "Quem é o campeão de Hogwarts no Torneio Tribruxo?", "Harry", "Rony", "Cedrico", "Draco", "C");
            adicionar(insertStmt, "O que é um trasgo montanhês?", "Animal mágico", "Feitiço", "Criatura gigante", "Tipo de poção", "C");
            adicionar(insertStmt, "Qual é o nome do jornal dos bruxos?", "Correio Mágico", "O Profeta Diário", "Gazeta dos Magos", "Revista Corvinal", "B");
            adicionar(insertStmt, "O que significa ser um nascido-trouxa?", "Filho de bruxo e trouxa", "Bruxo criado por trouxas", "Filho de não bruxos", "Sem poderes mágicos", "C");
            adicionar(insertStmt, "Quem foi o primeiro amor de Snape?", "Lily Potter", "Narcisa", "Bellatrix", "Minerva", "A");
            adicionar(insertStmt, "Quem é o dono da Varinha das Varinhas?", "Harry", "Dumbledore", "Draco", "Tom Riddle", "A");
            adicionar(insertStmt, "Quantos jogadores cada time tem no quadribol?", "5", "6", "7", "11", "C");
            adicionar(insertStmt, "Qual a função do apanhador?", "Marcar gols", "Proteger o time", "Pegar o pomo de ouro", "Bater nos balaços", "C");
            adicionar(insertStmt, "Qual é o nome da bola usada para marcar gols?", "Pomo de Ouro", "Balaço", "Goles", "Quaffle", "D");
            adicionar(insertStmt, "Qual a função dos batedores no quadribol?", "Fazer gols", "Proteger o apanhador", "Desviar balaços", "Chutar a goles", "C");
            adicionar(insertStmt, "Qual dessas casas venceu mais a Taça de Quadribol em Hogwarts?", "Grifinória", "Sonserina", "Corvinal", "Lufa-Lufa", "B");
            adicionar(insertStmt, "O pomo de ouro vale quantos pontos?", "100", "50", "200", "150", "D");
            adicionar(insertStmt, "Quantas são as Relíquias da Morte?", "2", "3", "4", "5", "B");
            adicionar(insertStmt, "Qual das relíquias é usada para evitar a morte?", "Varinha", "Capa da Invisibilidade", "Pedra da Ressurreição", "Pomo de Ouro", "B");
            adicionar(insertStmt, "Quem foi o último dono legítimo da Varinha das Varinhas?", "Dumbledore", "Snape", "Draco", "Harry", "D");
            adicionar(insertStmt, "O que a Pedra da Ressurreição faz?", "Ressuscita os mortos", "Cria zumbis", "Invoca fantasmas", "Permite falar com mortos", "D");
            adicionar(insertStmt, "Quem possuía a capa da invisibilidade original?", "Tiago Potter", "Harry Potter", "Dumbledore", "Sirius Black", "B");
            adicionar(insertStmt, "De quem Harry herdou a capa da invisibilidade?", "Dumbledore", "Severo", "Tiago", "Aberforth", "C");
            adicionar(insertStmt, "Quem contou a história das Relíquias da Morte?", "Dumbledore", "Xenofílio Lovegood", "Harry", "Rony", "B");
            adicionar(insertStmt, "Quem matou Sirius Black?", "Bellatrix Lestrange", "Lucius Malfoy", "Severo Snape", "Dolores Umbridge", "A");
            adicionar(insertStmt, "Quem matou Cedrico Diggory?", "Voldemort", "Colaço", "Pedro Pettigrew", "Bellatrix", "C");
            adicionar(insertStmt, "Quem matou Dumbledore?", "Draco Malfoy", "Bellatrix Lestrange", "Snape", "Rony", "C");
            adicionar(insertStmt, "Quem matou Fred Weasley?", "Voldemort", "Bellatrix", "Explosão na batalha final", "Snape", "C");
            adicionar(insertStmt, "Quem matou Belatriz Lestrange?", "Molly Weasley", "Ginny Weasley", "Ninfadora Tonks", "Harry Potter", "A");
            adicionar(insertStmt, "Quem matou Remo Lupin?", "Greyback", "Voldemort", "Bellatrix", "Antonin Dolohov", "D");
            adicionar(insertStmt, "Qual é o verdadeiro nome de Lord Voldemort?", "Tom Marvolo Riddle", "Severo Prince", "Gellert Grindelwald", "Salazar Slytherin", "A");

            

            insertStmt.close();
            System.out.println("Perguntas inseridas com sucesso.");

        } catch (SQLException e) {
            System.out.println("Erro ao inserir perguntas: " + e.getMessage());
        }
    }

    private void adicionar(PreparedStatement stmt, String pergunta, String a, String b, String c, String d, String correta) throws SQLException {
        stmt.setString(1, pergunta);
        stmt.setString(2, a);
        stmt.setString(3, b);
        stmt.setString(4, c);
        stmt.setString(5, d);
        stmt.setString(6, correta);
        stmt.executeUpdate();
    }
}
