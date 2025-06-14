package view;

import java.awt.event.ActionEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

public class PerguntaFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JLabel lblPerguntaN = new JLabel("Pergunta N° ?");

	public PerguntaFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 206);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("...............Pergunta.................");
		lblNewLabel.setBounds(151, 10, 200, 13);
		contentPane.add(lblNewLabel);
		lblPerguntaN.setBounds(10, 0, 82, 13);
		contentPane.add(lblPerguntaN);

		JRadioButton opcaoA = new JRadioButton("A) Resposta A");
		opcaoA.setBounds(10, 71, 150, 21);
		contentPane.add(opcaoA);

		JRadioButton opcaoB = new JRadioButton("B) Resposta B");
		opcaoB.setBounds(10, 110, 150, 21);
		contentPane.add(opcaoB);

		JRadioButton opcaoC = new JRadioButton("C) Resposta C");
		opcaoC.setBounds(250, 71, 150, 21);
		contentPane.add(opcaoC);

		JRadioButton opcaoD = new JRadioButton("D) Resposta D");
		opcaoD.setBounds(250, 110, 150, 21);
		contentPane.add(opcaoD);

		ButtonGroup grupo = new ButtonGroup();
		grupo.add(opcaoA);
		grupo.add(opcaoB);
		grupo.add(opcaoC);
		grupo.add(opcaoD);

		JButton respostaBtn = new JButton("Responder");
		respostaBtn.setBounds(151, 138, 126, 21);
		contentPane.add(respostaBtn);

		respostaBtn.addActionListener((ActionEvent e) -> {
			String respostaSelecionada = "";

			if (opcaoA.isSelected()) respostaSelecionada = "A";
			else if (opcaoB.isSelected()) respostaSelecionada = "B";
			else if (opcaoC.isSelected()) respostaSelecionada = "C";
			else if (opcaoD.isSelected()) respostaSelecionada = "D";

			if (respostaSelecionada.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Você precisa selecionar uma alternativa!");
				return;
			}

			String respostaCorreta = "C";

			if (respostaSelecionada.equals(respostaCorreta)) {
				JOptionPane.showMessageDialog(null, "✅ Resposta Correta! Parabéns");
				dispose();

			} else {
				JOptionPane.showMessageDialog(null, "❌ Você errou!");
				dispose();
			}
		});
	}
	

}
