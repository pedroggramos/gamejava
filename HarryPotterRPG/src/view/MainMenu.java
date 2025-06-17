package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Image fundoImagemMenu;

	public MainMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 705, 675);
		setTitle("Harry Potter - RPG");

		ImageIcon fundoIcon = new ImageIcon("assets/fundoMenu.jpg");
		fundoImagemMenu = fundoIcon.getImage().getScaledInstance(782, 782, Image.SCALE_SMOOTH);

		contentPane = new JPanel(null) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(fundoImagemMenu, 0, 0, this);
			}
		};
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTituloMenu = new JLabel("HARRY POTTER - RPG") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				g.setFont(getFont());
				g.setColor(new Color(0, 0, 0, 150));
				g.drawString(getText(), 2, getHeight() / 2 + getFont().getSize() / 2);
				g.setColor(Color.WHITE);
				g.drawString(getText(), 0, getHeight() / 2 + getFont().getSize() / 2);
			}
		};
		lblTituloMenu.setLabelFor(contentPane);
		lblTituloMenu.setFont(new Font("Arial", Font.BOLD, 28));
		lblTituloMenu.setHorizontalAlignment(JLabel.CENTER);
		lblTituloMenu.setBounds(194, 88, 400, 50); 
		contentPane.add(lblTituloMenu);

		JLabel lblQtdJogadores = new JLabel("NÚMERO DE BRUXOS");
		lblQtdJogadores.setBounds(262, 203, 183, 19);
		lblQtdJogadores.setFont(new Font("Georgia", Font.PLAIN, 16));
		lblQtdJogadores.setForeground(Color.WHITE);
		contentPane.add(lblQtdJogadores);

		String[] opcoes = { "1", "2", "3", "4" };
		JComboBox<String> comboJogadores = new JComboBox<>(opcoes);
		comboJogadores.setBounds(323, 232, 43, 19);
		contentPane.add(comboJogadores);

		JButton btnJogar = new JButton("JOGAR");
		btnJogar.setBounds(262, 148, 159, 45);
		btnJogar.setForeground(new Color(218, 165, 32));
		btnJogar.setFont(new Font("Georgia", Font.BOLD, 18));
		btnJogar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

		btnJogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int numJogadores = Integer.parseInt((String) comboJogadores.getSelectedItem());
				CharacterSelect select = new CharacterSelect(numJogadores);
				select.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnJogar);

		JButton btnSair = new JButton("Sair");
		btnSair.setBounds(311, 277, 66, 35);
		btnSair.setForeground(new Color(218, 165, 32));
		btnSair.setFont(new Font("Georgia", Font.BOLD, 18));
		btnSair.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		btnSair.addActionListener(e -> System.exit(0));
		contentPane.add(btnSair);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				MainMenu frame = new MainMenu();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
