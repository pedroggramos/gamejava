package view;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dao.daoPergunta;
import dominio.Pergunta;

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
    private int[] pontuacaoPorJogador;
    private JLabel labelSorteio;
    private boolean ordemSorteada = false;

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
        this.pontuacaoPorJogador = new int[casasSelecionadas.size()];
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

        labelSorteio = new JLabel(" ");
        labelSorteio.setFont(new Font("Arial", Font.BOLD, 12));
        labelSorteio.setForeground(Color.BLACK);
        labelSorteio.setOpaque(true); 
        labelSorteio.setBackground(new Color(250, 250, 210)); 
        labelSorteio.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        labelSorteio.setBounds(825, 11, 151, 29);
        layeredPane.add(labelSorteio, Integer.valueOf(3));

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
            if (!ordemSorteada) {
                
                javax.swing.Timer animacaoTimer = new javax.swing.Timer(100, null);
                
                animacaoTimer.addActionListener(ev -> {
                    Collections.shuffle(casasSelecionadas);
                    labelSorteio.setText("Sorteando: " + casasSelecionadas.get(0));
                });

                animacaoTimer.start();

         
                new javax.swing.Timer(3000, ev -> {
                    animacaoTimer.stop();

                    
                    Collections.shuffle(casasSelecionadas);
                    ordemSorteada = true;
                    jogadorAtual = 0;

                    
                    StringBuilder ordem = new StringBuilder("🎲 Ordem dos turnos:\n\n");
                    StringBuilder ordemHTML = new StringBuilder("<html>");
                    for (int i = 0; i < casasSelecionadas.size(); i++) {
                        String linha = (i + 1) + "º - " + casasSelecionadas.get(i);
                        ordem.append(linha).append("\n");
                        ordemHTML.append(linha).append("<br>");
                    }
                    ordemHTML.append("</html>");
                    labelSorteio.setText("Vez de: " + casasSelecionadas.get(jogadorAtual));
                    JOptionPane.showMessageDialog(this, ordem.toString());

                    turnoAtivo = true;
                    atualizarBrasoes();

                    ((javax.swing.Timer) ev.getSource()).stop(); 
                }).start();

            } else {
                JOptionPane.showMessageDialog(this, "Turno do jogador: " + casasSelecionadas.get(jogadorAtual));
                labelSorteio.setText(" Vez de: " + casasSelecionadas.get(jogadorAtual));
                turnoAtivo = true;
                atualizarBrasoes();
            }
        });


        layeredPane.add(btnComecarTurno, Integer.valueOf(2));

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBackground(Color.DARK_GRAY);
        btnVoltar.setFont(new Font("Times New Roman", Font.BOLD, 18));
        btnVoltar.setBounds(697, 623, 104, 30);
        btnVoltar.addActionListener(e -> {
            int resposta = JOptionPane.showConfirmDialog(
                this,
                "Tem certeza que deseja voltar?\nO progresso atual será perdido.",
                "Confirmar saída",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
            );

            if (resposta == JOptionPane.YES_OPTION) {
                CharacterSelect select = new CharacterSelect(casasSelecionadas.size());
                setVisible(false);
                select.setVisible(true);
            }
        });

        layeredPane.add(btnVoltar, Integer.valueOf(2));

        
        criarFase(1, 50, 390);
        criarFase(2, 70, 120);
        criarFase(3, 360, 250);
        criarFase(4, 520, 150);
        criarFase(5, 800, 160);
        criarFase(6, 800, 340);
        criarFase(7, 800, 520);
        criarFase(8, 600, 480);
        criarFase(9, 260, 520);
    }
    


    private void colocarBrasaoNoPainel(JPanel painel, int jogador) {
        
        painel.removeAll();

        
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

    private void atualizarBrasoes() {
        for (int i = 0; i < casasSelecionadas.size(); i++) {
            String casa = casasSelecionadas.get(i);
            String arquivo = getNomeArquivoImagem(casa);
            ImageIcon icon = new ImageIcon("assets/" + arquivo);
            Image img = icon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);

            if (i != jogadorAtual) {
           
                img = aplicarTransparencia(img, 0.3f);
            }
           

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

        
        String[] nomesFases = {
            "Fase 1 – Cabana de Hagrid",
            "Fase 2 – Hogsmeade",
            "Fase 3 – Campo de Quadribol",
            "Fase 4 – West Tower",
            "Fase 5 – Floresta Proibida",
            "Fase 6 – Salgueiro Lutador",
            "Fase 7 – Estação de Trem",
            "Fase 8 – Castelo de Hogwarts",
            "Fase 9 – Lago Negro"
        };

        for (int i = 0; i < 3; i++) {
            JPanel painel = new JPanel(null);
            painel.setBackground(new Color(200, 200, 255, 150));
            painel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
            painel.setBounds(startX + i * espacamento, startY, largura, altura);

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

                    if (painel.getComponentCount() > 0) {
                        JOptionPane.showMessageDialog(MapaFrame.this,
                                "Esse quadradinho já foi completado.");
                        return;
                    }

                    perguntaDoBanco(acertou -> {
                        if (acertou) {
                            colocarBrasaoNoPainel(painel, jogadorAtual);
                            caixasCompletadasNaFase++;
                            pontuacaoPorJogador[jogadorAtual]++;
                            JOptionPane.showMessageDialog(MapaFrame.this, "Resposta correta! Brasão colocado.");

                            if (caixasCompletadasNaFase >= 3) {
                                faseAtual++;
                                if (faseAtual > 9) {
                                    mostrarRankingFinal();
                                    return;
                                }

                                caixasCompletadasNaFase = 0;
                                atualizarFaseAtualNaTela();
                                JOptionPane.showMessageDialog(MapaFrame.this,
                                        "Fase " + (faseAtual - 1) + " concluída! Próxima fase liberada.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(MapaFrame.this, "Resposta errada! Próximo jogador.");
                        }

                        jogadorAtual = (jogadorAtual + 1) % casasSelecionadas.size();
                        atualizarBrasoes();
                        turnoAtivo = false;
                    });
                }
            });

            layeredPane.add(painel, Integer.valueOf(2));
            painéisCasas.add(painel);
        }

        
        JLabel labelFase = new JLabel(nomesFases[numeroFase - 1]);
        labelFase.setFont(new Font("Arial", Font.BOLD, 12));
        labelFase.setForeground(Color.WHITE);
        labelFase.setBounds(startX, startY + 45, 200, 20); // Ajuste Y para aparecer abaixo
        layeredPane.add(labelFase, Integer.valueOf(3));
    }


    
    private void perguntaDoBanco(Consumer<Boolean> callback) {
        daoPergunta dao = new daoPergunta();
        Pergunta pergunta = dao.buscarPerguntaAleatoria();

        if (pergunta == null) {
            JOptionPane.showMessageDialog(this, "Erro ao buscar pergunta do banco.");
            callback.accept(false);
            return;
        }

        PerguntaFrame perguntaFrame = new PerguntaFrame(pergunta, callback);
        perguntaFrame.setLocationRelativeTo(this);
        perguntaFrame.setVisible(true);
    }
    
    private void mostrarRankingFinal() {
       
        List<String> ranking = new ArrayList<>();
        for (int i = 0; i < casasSelecionadas.size(); i++) {
            ranking.add(casasSelecionadas.get(i) + " - " + pontuacaoPorJogador[i] + " acertos");
        }

   
        ranking.sort((a, b) -> {
            int pontosA = Integer.parseInt(a.replaceAll("\\D+", ""));
            int pontosB = Integer.parseInt(b.replaceAll("\\D+", ""));
            return Integer.compare(pontosB, pontosA);
        });

        StringBuilder mensagem = new StringBuilder("🏆 Fim de jogo!\n\nRanking final:\n");
        for (int i = 0; i < ranking.size(); i++) {
            mensagem.append((i + 1)).append("º - ").append(ranking.get(i)).append("\n");
        }

        JOptionPane.showMessageDialog(this, mensagem.toString(), "Ranking Final", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0); 
    }



}
