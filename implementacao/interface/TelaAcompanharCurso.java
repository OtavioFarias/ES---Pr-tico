// TelaAcompanharCurso.java
package classes.interfaceGrafica;

import classes.atributos.*;
import classes.tools.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class TelaAcompanharCurso {

    // --- Paleta de Cores Profissional (Mantida) ---
    private static final Color COR_FUNDO_PAINEL_NAVEGACAO = new Color(233, 236, 239);
    private static final Color COR_FUNDO_JANELA = new Color(248, 249, 250);
    private static final Color COR_AZUL_DESTAQUE = new Color(13, 110, 253);
    private static final Color COR_AZUL_HOVER_FUNDO = new Color(222, 235, 255);
    private static final Color COR_TEXTO_NORMAL = new Color(33, 37, 41);
    private static final Color COR_TEXTO_DESTAQUE = new Color(13, 110, 253);
    private static final Color COR_BORDA_SUTIL = new Color(222, 226, 230);

    private CardLayout cardLayout;
    private JPanel painelConteudoCentral;
    private Discente discente;
    private final List<BotaoNavegacao> botoesDeNavegacao = new ArrayList<>();

    public void show(Discente discente, Runnable onClose) {
        if (discente == null) {
            JOptionPane.showMessageDialog(null, "Discente não pode ser nulo.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        this.discente = discente;

        JFrame frame = new JFrame("Acompanhamento do Curso de " + discente.getNome().toUpperCase());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(COR_FUNDO_JANELA);

        JPanel painelNavegacao = new JPanel();
        painelNavegacao.setLayout(new BoxLayout(painelNavegacao, BoxLayout.Y_AXIS));
        painelNavegacao.setBorder(new EmptyBorder(10, 5, 10, 5));
        painelNavegacao.setBackground(COR_FUNDO_PAINEL_NAVEGACAO);

        cardLayout = new CardLayout();
        painelConteudoCentral = new JPanel(cardLayout);
        painelConteudoCentral.setBackground(COR_FUNDO_JANELA);
        painelConteudoCentral.setBorder(new EmptyBorder(10, 25, 20, 25));

        String[] titulos = {
                "Resumo Completo", "Ver Artigos", "Ver Atividades Complementares", "Ver Componentes Curriculares Obrigatórios", "Ver Componentes Curriculares Opcionais",
                "Ver Estágios Obrigatórios", "Ver Estágios Não Obrigatórios", "Ver Práticas Extensionistas",
                "Ver Resumos Expandidos", "Ver Serviços Comunitários", "Ver Situação ENADE"
        };
        String[] cardNames = {
                "RESUMO", "ARTIGOS", "ATIVIDADES", "CADEIRAS_OBR", "CADEIRAS_OPC", "ESTAGIOS_OBR",
                "ESTAGIOS_NAO_OBR", "PRATICAS", "RESUMOS", "SERVICOS", "ENADE"
        };

        for (int i = 0; i < titulos.length; i++) {
            BotaoNavegacao botao = new BotaoNavegacao(titulos[i]);
            final String cardName = cardNames[i];

            botao.addActionListener(e -> {
                cardLayout.show(painelConteudoCentral, cardName);
                selecionarBotao((BotaoNavegacao) e.getSource());
            });

            painelNavegacao.add(botao);
            painelNavegacao.add(Box.createRigidArea(new Dimension(0, 10)));
            botoesDeNavegacao.add(botao);

            JPanel painelCategoria = criarPainelConteudo(titulos[i], i + 1);
            painelConteudoCentral.add(painelCategoria, cardName);
        }

        painelNavegacao.add(Box.createVerticalGlue());

        JButton btnVoltar = new JButton("Voltar ao Menu Inicial");
        btnVoltar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnVoltar.addActionListener(e -> {
            frame.dispose();
            if (onClose != null) onClose.run();
        });
        painelNavegacao.add(btnVoltar);

        frame.add(painelNavegacao, BorderLayout.WEST);
        frame.add(painelConteudoCentral, BorderLayout.CENTER);

        if (!botoesDeNavegacao.isEmpty()) {
            selecionarBotao(botoesDeNavegacao.get(0));
            cardLayout.show(painelConteudoCentral, cardNames[0]);
        }

        // Chamada do callback ao fechar a janela de qualquer forma
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                if (onClose != null) onClose.run();
            }
        });

        frame.setVisible(true);
    }


    private void selecionarBotao(BotaoNavegacao botaoSelecionado) {
        for (BotaoNavegacao btn : botoesDeNavegacao) {
            btn.setSelecionado(false);
        }
        botaoSelecionado.setSelecionado(true);
    }

    private JPanel criarPainelConteudo(String titulo, int opcao) {
        JPanel painel = new JPanel(new BorderLayout(10, 10));
        painel.setBackground(COR_FUNDO_JANELA);

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setForeground(COR_TEXTO_NORMAL);
        lblTitulo.setBorder(new EmptyBorder(5, 5, 15, 5));
        painel.add(lblTitulo, BorderLayout.NORTH);

        JTextArea areaDados = new JTextArea();
        areaDados.setEditable(false);
        areaDados.setFont(new Font("Consolas", Font.PLAIN, 14));
        areaDados.setText(obterDadosFormatados(opcao));
        areaDados.setMargin(new Insets(10, 10, 10, 10));
        areaDados.setBackground(Color.WHITE);
        areaDados.setForeground(COR_TEXTO_NORMAL);
        areaDados.setLineWrap(true);
        areaDados.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(areaDados);
        scrollPane.setBorder(BorderFactory.createLineBorder(COR_BORDA_SUTIL));

        painel.add(scrollPane, BorderLayout.CENTER);

        return painel;
    }

    private String obterDadosFormatados(int opcao) {
    		StringBuilder mensagem = new StringBuilder();
				Historico historico = discente.getHistorico();
				AcompanhamentoCurso acompanhamento = new AcompanhamentoCurso(historico);
				double total = 0.0;


				switch (opcao) {
				    case 2 -> {
				        historico.getArtigo().forEach(a -> mensagem.append(a.toString()).append("\n\n"));
				        total = acompanhamento.acompanharArtigoCientífico();
				        mensagem.append("Percentual de Conclusão: ").append(total).append("%\n");
				    }
				    case 3 -> {
				        historico.getAtividadesComplementares().forEach(a -> mensagem.append(a.toString()).append("\n\n"));
				        total = acompanhamento.acompanharAtividadesComplementares();
				   			mensagem.append("Percentual de Conclusão: ").append(total).append("%\n");
				    }
				    case 4 -> {

				        lerComponentesCurriculares componente = new lerComponentesCurriculares("Cursos/ComponentesCurriculares");
								List<String> nomesComponentes = componente.getComponentes();

								List<Integer> ids = historico.getIDComponentesCurricularesObrigatorios();

								for (Integer id : ids) {
										// IDs começam em 1, lista usa índice 0
										if (id > 0 && id <= nomesComponentes.size()) {
												String nomeComponente = nomesComponentes.get(id - 1);
												mensagem.append(nomeComponente).append("\n");
										} else {
												mensagem.append("ID: ").append(id).append(" - [ID inválido]\n");
										}
								}

								total = acompanhamento.acompanharComponenteCurricularObrigatorio();
				        mensagem.append("Percentual de Conclusão: ").append(total).append("%\n");

				    }
				    case 5 -> {
				        lerComponentesCurriculares componenteN = new lerComponentesCurriculares("Cursos/ComponentesCurricularesNaoObrigatorios");
								List<String> nomesComponentesN = componenteN.getComponentes();

								List<Integer> idsN = historico.getIDComponentesCurricularesNaoObrigatorios();

								for (Integer idN : idsN) {
										// IDs começam em 1, lista usa índice 0
										if (idN > 0 && idN <= nomesComponentesN.size()) {
												String nomeComponenteN = nomesComponentesN.get(idN - 1);
												mensagem.append(nomeComponenteN).append("\n");
										} else {
												mensagem.append("ID: ").append(idN).append(" - [ID inválido]\n");
										}
								}
								total = acompanhamento.acompanharComponentesCurricularesComplementares();
				        mensagem.append("Percentual de Conclusão: ").append(total).append("%\n");
				    }
				    case 6 -> {
				        historico.getEstagioObrigatorio().forEach(e -> mensagem.append(e.toString()).append("\n\n"));
				        total = acompanhamento.acompanharEstagioObrigatorio();
				        mensagem.append("Percentual de Conclusão: ").append(total).append("%\n");
				    }
				    case 7 -> {
				        historico.getEstagioNaoObrigatorio().forEach(e -> mensagem.append(e.toString()).append("\n\n"));
				        //double total = AcompanhamentoCurso.acompanharEstagiosNaoObrigatorios();

				    }
				    case 8 -> {
				        historico.getPraticasExtensionistas().forEach(p -> mensagem.append(p.toString()).append("\n\n"));
				        total = acompanhamento.acompanharPraticasExtensionistas();
				        mensagem.append("Percentual de Conclusão: ").append(total).append("%\n");
				    }
				    case 9 -> {
				        historico.getResumos().forEach(r -> mensagem.append(r.toString()).append("\n\n"));
				        total = acompanhamento.acompanharResumoExpandido();
				        mensagem.append("Percentual de Conclusão: ").append(total).append("%\n");
				    }
				    case 10 -> {
				        historico.getServicoComunitario().forEach(s -> mensagem.append(s.toString()).append("\n\n"));
				        total = acompanhamento.acompanharUnipampaCidada();
				        mensagem.append("Percentual de Conclusão: ").append(total).append("%\n");
				    }
				    case 11 -> mensagem.append("Situação: ").append(historico.getSituacaoENADE() ? "Regular" : "Irregular");
				    // Novo case para exibir todos os acompanhamentos e o total geral
				    case 1 -> {

				        double totalCurso = acompanhamento.acompanharCurso();
								mensagem.append("Percentual de Conclusão do Curso: ").append(String.format("%.2f", totalCurso)).append("%\n\n");

				        double totalArtigos = acompanhamento.acompanharArtigoCientífico();
				        mensagem.append("Artigos Científicos: ").append(String.format("%.2f", totalArtigos)).append("%\n");

				        double totalAtividades = acompanhamento.acompanharAtividadesComplementares();
				        mensagem.append("Atividades Complementares: ").append(String.format("%.2f", totalAtividades)).append("%\n");

				        // Supondo que tenha métodos para obrigatórios e opcionais (se não tiver, pode remover)
				        // double totalObrigatorios = acompanhamento.acompanharCadeirasObrigatorias();
				        // mensagem.append("Cadeiras Obrigatórias: ").append(String.format("%.2f", totalObrigatorios)).append("%\n");

				        // double totalOpcionais = acompanhamento.acompanharCadeirasOpcionais();
				        // mensagem.append("Cadeiras Opcionais: ").append(String.format("%.2f", totalOpcionais)).append("%\n");

				        double totalEstagiosObr = acompanhamento.acompanharEstagioObrigatorio();
				        mensagem.append("Estágios Obrigatórios: ").append(String.format("%.2f", totalEstagiosObr)).append("%\n");

				        // Se tiver método para estágios não obrigatórios:
				        // double totalEstagiosNaoObr = acompanhamento.acompanharEstagiosNaoObrigatorios();
				        // mensagem.append("Estágios Não Obrigatórios: ").append(String.format("%.2f", totalEstagiosNaoObr)).append("%\n");

				        double totalPraticas = acompanhamento.acompanharPraticasExtensionistas();
				        mensagem.append("Práticas Extensionistas: ").append(String.format("%.2f", totalPraticas)).append("%\n");

				        double totalResumos = acompanhamento.acompanharResumoExpandido();
				        mensagem.append("Resumos Expandidos: ").append(String.format("%.2f", totalResumos)).append("%\n");

				        double totalServicos = acompanhamento.acompanharUnipampaCidada();
				        mensagem.append("Serviços Comunitários: ").append(String.format("%.2f", totalServicos)).append("%\n");

				    }
				}

				if (mensagem.length() == 0) {
				    return "Nenhum item cadastrado nesta categoria.";
				}
				return mensagem.toString();
		}


    class BotaoNavegacao extends JButton {
        private boolean selecionado;
        private boolean mouseOver;

        public BotaoNavegacao(String texto) {
            super(texto);
            setSelecionado(false);

            setFont(new Font("Segoe UI", Font.BOLD, 14));
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            setHorizontalAlignment(SwingConstants.LEFT);

            setBorder(new EmptyBorder(12, 25, 12, 25));
            setFocusPainted(false);
            setContentAreaFilled(false);
            setOpaque(true);

            setAlignmentX(Component.CENTER_ALIGNMENT);
            setMaximumSize(new Dimension(Integer.MAX_VALUE, getPreferredSize().height + 15));

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    mouseOver = true;
                    repaint();
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    mouseOver = false;
                    repaint();
                }
            });
        }

        public void setSelecionado(boolean selecionado) {
            this.selecionado = selecionado;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            if (selecionado) {
                g2.setColor(COR_AZUL_HOVER_FUNDO);
                setForeground(COR_TEXTO_DESTAQUE);
            } else if (mouseOver) {
                g2.setColor(COR_AZUL_HOVER_FUNDO);
                setForeground(COR_TEXTO_NORMAL);
            } else {
                g2.setColor(COR_FUNDO_PAINEL_NAVEGACAO);
                setForeground(COR_TEXTO_NORMAL);
            }

            g2.fillRect(0, 0, getWidth(), getHeight());

            if (selecionado || mouseOver) {
                g2.setColor(COR_AZUL_DESTAQUE);
                g2.fillRect(0, 0, 4, getHeight());
            }

            g2.setColor(Color.BLACK);
            g2.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

            g2.dispose();
            super.paintComponent(g);
        }
    }
}