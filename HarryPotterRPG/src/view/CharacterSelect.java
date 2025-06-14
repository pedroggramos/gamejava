package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CharacterSelect extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Image fundoImagem;

    private int numeroDeJogadores;
    private int jogadoresSelecionados = 0;
    private List<String> casasSelecionadas = new ArrayList<>();
    private JLabel backgroundLabel;

    public CharacterSelect(int numeroDeJogadores) {
        this.numeroDeJogadores = numeroDeJogadores;

        setTitle("JOGUE POR SUA CASA");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 768, 632);

        // Carrega imagem de fundo
        ImageIcon fundoIcon = new ImageIcon("assets/Casas.png");
        fundoImagem = fundoIcon.getImage().getScaledInstance(768, 632, Image.SCALE_SMOOTH);

        contentPane = new JPanel(null);
        setContentPane(contentPane);

        // Título
        JLabel lblTitulo = new JLabel("Escolha sua casa:");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);
        lblTitulo.setBounds(254, 5, 209, 28);
        contentPane.add(lblTitulo);

        // Botões das casas
        adicionarBotaoCasa("GRIFINÓRIA", "grifinoria.png", 500, 60);
        adicionarBotaoCasa("SONSERINA", "sonserina.png", 500, 350);
        adicionarBotaoCasa("CORVINAL", "corvinal.png", 86, 350);
        adicionarBotaoCasa("LUFA-LUFA", "lufalufa.png", 86, 60);

        // Botão Voltar
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(10, 13, 85, 21);
        btnVoltar.addActionListener(e -> {
            MainMenu menu = new MainMenu();
            setVisible(false);
            menu.setVisible(true);
        });
        contentPane.add(btnVoltar);

        // Fundo como JLabel
        backgroundLabel = new JLabel(new ImageIcon(fundoImagem));
        backgroundLabel.setBounds(0, 0, 768, 632);
        contentPane.add(backgroundLabel);
        contentPane.setComponentZOrder(backgroundLabel, contentPane.getComponentCount() - 1);
    }

    private void adicionarBotaoCasa(String casa, String imagem, int x, int y) {
        ImageIcon icon = new ImageIcon("assets/" + imagem);
        Image img = icon.getImage().getScaledInstance(174, 196, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(img);

        JButton btn = new JButton(resizedIcon);
        btn.setBounds(x, y, 174, 196);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
        btn.setOpaque(false);

        btn.addActionListener(e -> iniciarJogo(casa, btn));
        contentPane.add(btn);
    }

    private void iniciarJogo(String casaEscolhida, JButton btn) {
        if (casasSelecionadas.contains(casaEscolhida)) {
            System.out.println("Essa casa já foi escolhida.");
            return;
        }

        jogadoresSelecionados++;
        casasSelecionadas.add(casaEscolhida);
        btn.setEnabled(false); // Desativa o botão da casa

        System.out.println("Casa escolhida: " + casaEscolhida);

        if (jogadoresSelecionados >= numeroDeJogadores) {
            abrirMapa();
            dispose(); // Fecha essa tela
        }
    }

    private void abrirMapa() {
        System.out.println("Todas as casas selecionadas:");
        for (String casa : casasSelecionadas) {
            System.out.println("- " + casa);
        }

        MapaFrame mapa = new MapaFrame(casasSelecionadas);
        mapa.setVisible(true);
    }
}
