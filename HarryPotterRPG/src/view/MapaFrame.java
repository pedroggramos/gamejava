package view;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MapaFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private JLayeredPane layeredPane;
    private JLabel labelFundo;
    private List<String> casasSelecionadas;
    private List<JLabel> iconesCasas = new ArrayList<>();
    private int jogadorAtual = 0;
    private List<JPanel> painéisCasas = new ArrayList<>();
    private boolean turnoAtivo = false;
    private int faseAtual = 1;
    private int caixasCompletadasNaFase = 0;
    private JLabel labelFaseAtual;

    public MapaFrame(List<String> casasSelecionadas) {
        if (casasSelecionadas == null || casasSelecionadas.isEmpty()) {
            throw new IllegalArgumentException("A lista de casas não pode ser nula ou vazia.");
        }

        this.casasSelecionadas = new ArrayList<>(casasSelecionadas);
        configurarJanela();
        inicializarPainelComFundo();
        adicionarBrasoesJogadores();
        adicionarBotoes();
        atualizarBrasoes();
    }

    private void configurarJanela() {
        setTitle("Mapa do Jogo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 700);
    }

    private void inicializarPainelComFundo() {
        layeredPane = new JLayeredPane();
        layeredPane.setLayout(null);
        setContentPane(layeredPane);

        ImageIcon fundoIcon = new ImageIcon("assets/MapaHP.png");
        Image imgRedimensionada = fundoIcon.getImage().getScaledInstance(1000, 660, Image.SCALE_SMOOTH);
        labelFundo = new JLabel(new ImageIcon(imgRedimensionada));
        labelFundo.setBounds(0, 0, 1000, 660);
        layeredPane.add(labelFundo, Integer.valueOf(0));
        
        labelFaseAtual = new JLabel("Fase atual: 1");
        labelFaseAtual.setFont(new Font("Arial", Font.BOLD, 20));
        labelFaseAtual.setForeground(Color.WHITE);
        labelFaseAtual.setBounds(10, 10, 200, 30);
        layeredPane.add(labelFaseAtual, Integer.valueOf(3));
    }
    
    private void atualizarFaseAtualNaTela() {
        labelFaseAtual.setText("Fase atual: " + faseAtual);
    }

    private void adicionarBrasoesJogadores() {
        int x = 100;
        for (int i = 0; i < casasSelecionadas.size(); i++) {
            JLabel iconeCasa = new JLabel();
            iconeCasa.setBounds(x, 580, 70, 70);
            iconesCasas.add(iconeCasa);
            layeredPane.add(iconeCasa, Integer.valueOf(1));
            x += 120;
        }
    }

    private void adicionarBotoes() {
        JButton btnComecarTurno = new JButton("Começar");
        btnComecarTurno.setFont(new Font("Times New Roman", Font.BOLD, 15));
        btnComecarTurno.setBackground(new Color(0, 128, 64));
        btnComecarTurno.setBounds(825, 623, 135, 30);
        btnComecarTurno.addActionListener(e -> {
            turnoAtivo = true;
            JOptionPane.showMessageDialog(this, "Turno do jogador: " + casasSelecionadas.get(jogadorAtual));
        });
        layeredPane.add(btnComecarTurno, Integer.valueOf(2));

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBackground(Color.DARK_GRAY);
        btnVoltar.setFont(new Font("Times New Roman", Font.BOLD, 18));
        btnVoltar.setBounds(697, 623, 104, 30);
        btnVoltar.addActionListener(e -> {
            // Ao voltar, passa o total de jogadores para o CharacterSelect
            CharacterSelect select = new CharacterSelect(casasSelecionadas.size());
            setVisible(false);
            select.setVisible(true);
        });
        layeredPane.add(btnVoltar, Integer.valueOf(2));

        // Cria as fases no mapa com 3 quadradinhos cada, em posições diferentes
        criarFase(1, 50, 280);
        criarFase(2, 360, 250);
        criarFase(3, 520, 150);
        criarFase(4, 800, 160);
        criarFase(5, 800, 340);
        criarFase(6, 260, 520);
        criarFase(7, 800, 520);
        criarFase(8, 600, 480);
    }

    private void colocarBrasaoNoPainel(JPanel painel, int jogador) {
        // Remove brasão anterior se houver
        painel.removeAll();

        // Pega o nome da casa do jogador atual e imagem
        String casa = casasSelecionadas.get(jogador);
        String arquivo = getNomeArquivoImagem(casa);
        ImageIcon icon = new ImageIcon("assets/" + arquivo);
        Image img = icon.getImage().getScaledInstance(painel.getWidth() - 8, painel.getHeight() - 8, Image.SCALE_SMOOTH);
        JLabel brasao = new JLabel(new ImageIcon(img));
        brasao.setBounds(4, 4, painel.getWidth() - 8, painel.getHeight() - 8);

        painel.add(brasao);
        painel.revalidate();
        painel.repaint();
    }

    private void proximoTurno() {
        jogadorAtual = (jogadorAtual + 1) % casasSelecionadas.size();
        atualizarBrasoes();
    }

    private void atualizarBrasoes() {
        for (int i = 0; i < casasSelecionadas.size(); i++) {
            String casa = casasSelecionadas.get(i);
            String arquivo = getNomeArquivoImagem(casa);
            ImageIcon icon = new ImageIcon("assets/" + arquivo);
            Image img = icon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);

            if (i != jogadorAtual) {
                // Brasão dos outros jogadores fica com transparência (esbranquiçado)
                img = aplicarTransparencia(img, 0.3f);
            }
            // Brasão do jogador atual fica normal (100% opaco)

            iconesCasas.get(i).setIcon(new ImageIcon(img));
        }
    }

    private String getNomeArquivoImagem(String casa) {
        switch (casa.toUpperCase()) {
            case "GRIFINÓRIA": return "grifinoria.png";
            case "SONSERINA": return "sonserina.png";
            case "CORVINAL":  return "corvinal.png";
            case "LUFA-LUFA": return "lufalufa.png";
            default: return "default.png";
        }
    }

    private Image aplicarTransparencia(Image img, float alpha) {
        int width = img.getWidth(null);
        int height = img.getHeight(null);

        if (width <= 0 || height <= 0) {
            System.err.println("Erro ao aplicar transparência: imagem inválida.");
            return img;
        }

        BufferedImage buffered = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = buffered.createGraphics();
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.drawImage(img, 0, 0, null);
        g2.dispose();
        return buffered;
    }
    
    private void criarFase(int numeroFase, int startX, int startY) {
        int largura = 40, altura = 40, espacamento = 50;

        for (int i = 0; i < 3; i++) {
            JPanel painel = new JPanel(null);
            painel.setBackground(new Color(200, 200, 255, 150)); // fundo esbranquiçado
            painel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
            painel.setBounds(startX + i * espacamento, startY, largura, altura);

            // Armazenar qual fase esse painel pertence (usando putClientProperty)
            painel.putClientProperty("fase", numeroFase);

            painel.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    if (!turnoAtivo) {
                        JOptionPane.showMessageDialog(MapaFrame.this, "Clique em 'Começar' para iniciar o turno.");
                        return;
                    }

                    int faseDoPainel = (int) painel.getClientProperty("fase");
                    if (faseDoPainel != faseAtual) {
                        JOptionPane.showMessageDialog(MapaFrame.this, 
                            "Você só pode jogar a fase atual: " + faseAtual);
                        return;
                    }

                    // Evita clicar em painel já preenchido (com brasão)
                    if (painel.getComponentCount() > 0) {
                        JOptionPane.showMessageDialog(MapaFrame.this, 
                            "Esse quadradinho já foi completado.");
                        return;
                    }

                    boolean acertou = perguntaDoBanco();

                    if (acertou) {
                        colocarBrasaoNoPainel(painel, jogadorAtual);
                        caixasCompletadasNaFase++;
                        JOptionPane.showMessageDialog(MapaFrame.this, "Resposta correta! Brasão colocado.");

                        // Se completou todos da fase (3 caixas), libera próxima fase
                        if (caixasCompletadasNaFase >= 3) {
                            faseAtual++;
                            caixasCompletadasNaFase = 0;
                            atualizarFaseAtualNaTela();
                            JOptionPane.showMessageDialog(MapaFrame.this, 
                                "Fase " + (faseAtual - 1) + " concluída! Próxima fase liberada.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(MapaFrame.this, "Resposta errada! Próximo jogador.");
                    }

                    // Passa para o próximo jogador e desativa turno
                    jogadorAtual = (jogadorAtual + 1) % casasSelecionadas.size();
                    atualizarBrasoes();
                    turnoAtivo = false;
                }
            });

            layeredPane.add(painel, Integer.valueOf(2));
            painéisCasas.add(painel);
        }
    }
    
    // Método simulado para testar
    private boolean perguntaDoBanco() {
        // Aqui você implementa sua lógica real com banco, pergunta, resposta, etc.
        // Por enquanto retorna aleatório para testar
        return Math.random() > 0.5;
    }
}
