package view;

import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import dominio.Pergunta;

import java.util.function.Consumer;

public class PerguntaFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public PerguntaFrame(Pergunta pergunta, Consumer<Boolean> callback) {
        setTitle("Pergunta");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 500, 240);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblPergunta = new JLabel("<html>" + pergunta.getPergunta() + "</html>");
        lblPergunta.setBounds(10, 10, 460, 40);
        contentPane.add(lblPergunta);

        JRadioButton opcaoA = new JRadioButton("A) " + pergunta.getAlt_a());
        JRadioButton opcaoB = new JRadioButton("B) " + pergunta.getAlt_b());
        JRadioButton opcaoC = new JRadioButton("C) " + pergunta.getAlt_c());
        JRadioButton opcaoD = new JRadioButton("D) " + pergunta.getAlt_d());

        opcaoA.setBounds(10, 60, 534, 21);
        opcaoB.setBounds(10, 90, 534, 21);
        opcaoC.setBounds(10, 120, 534, 21);
        opcaoD.setBounds(10, 150, 534, 21);

        contentPane.add(opcaoA);
        contentPane.add(opcaoB);
        contentPane.add(opcaoC);
        contentPane.add(opcaoD);

        ButtonGroup grupo = new ButtonGroup();
        grupo.add(opcaoA);
        grupo.add(opcaoB);
        grupo.add(opcaoC);
        grupo.add(opcaoD);

        JButton respostaBtn = new JButton("Responder");
        respostaBtn.setBounds(180, 180, 120, 25);
        contentPane.add(respostaBtn);

        respostaBtn.addActionListener((ActionEvent e) -> {
            String respostaSelecionada = "";

            if (opcaoA.isSelected()) respostaSelecionada = "A";
            else if (opcaoB.isSelected()) respostaSelecionada = "B";
            else if (opcaoC.isSelected()) respostaSelecionada = "C";
            else if (opcaoD.isSelected()) respostaSelecionada = "D";

            if (respostaSelecionada.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Você precisa selecionar uma alternativa!");
                return;
            }

            boolean acertou = respostaSelecionada.equalsIgnoreCase(pergunta.getResposta_correta());

            JOptionPane.showMessageDialog(this,
                    acertou ? "✅ Resposta Correta!" : "❌ Você errou!");

            dispose();
            callback.accept(acertou);
        });
    }
}
