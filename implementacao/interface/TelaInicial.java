package classes.interfaceGrafica;

import classes.atributos.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaInicial {

    // --- Paleta de Cores ---
    private static final Color COR_FUNDO = new Color(244, 246, 249);
    private static final Color COR_BOTAO_PRIMARIO = new Color(13, 110, 253);
    private static final Color COR_BOTAO_PRIMARIO_HOVER = new Color(10, 88, 202); // Tom mais escuro
    private static final Color COR_TEXTO_BOTAO = Color.WHITE;
    private static final Color COR_TEXTO_TITULO = new Color(33, 37, 41);
    private static final Color COR_FUNDO_STATUS = new Color(233, 236, 239);

    private Discente discente;
    private JLabel lblStatusDiscente;
    private JButton btnAcompanhar;
    private JButton btnAdicionarHistorico;

    public void show() {
        JFrame frame = new JFrame("Sistema de Acompanhamento de Curso");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout(10, 10));
        frame.getContentPane().setBackground(COR_FUNDO);

        JLabel lblTitulo = new JLabel("Menu Principal", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitulo.setForeground(COR_TEXTO_TITULO);
        lblTitulo.setBorder(new EmptyBorder(20, 10, 10, 10));
        frame.add(lblTitulo, BorderLayout.NORTH);

        JPanel painelBotoes = new JPanel(new GridLayout(5, 1, 15, 15));
        painelBotoes.setBackground(COR_FUNDO);
        painelBotoes.setBorder(new EmptyBorder(15, 40, 15, 40));

        JButton btnBuscar = criarBotaoPrincipal("Buscar Discente", "icones/buscar.png");
        JButton btnCadastrar = criarBotaoPrincipal("Cadastrar Novo Discente", "icones/cadastrar.png");
        btnAcompanhar = criarBotaoPrincipal("Acompanhar Curso", "icones/acompanhar.png");
        btnAdicionarHistorico = criarBotaoPrincipal("Adicionar ao Histórico", "icones/historico.png");

        JButton btnSair = new JButton("Sair do Sistema");
        btnSair.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnSair.setBackground(Color.RED);
        btnSair.setForeground(Color.WHITE);
        btnSair.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSair.setFocusPainted(false);
        btnSair.setBorderPainted(false);
        btnSair.setIconTextGap(15);
        btnSair.setPreferredSize(new Dimension(200, 60));

        try {
            ImageIcon icon = new ImageIcon(new ImageIcon(getClass().getResource("icones/sair.png")).getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH));
            btnSair.setIcon(icon);
        } catch (Exception e) {
            System.err.println("Ícone não encontrado: icones/sair.png");
        }

        // Começam desabilitados
        btnAcompanhar.setEnabled(false);
        btnAcompanhar.setBackground(Color.LIGHT_GRAY);

        btnAdicionarHistorico.setEnabled(false);
        btnAdicionarHistorico.setBackground(Color.LIGHT_GRAY);

        painelBotoes.add(btnBuscar);
        painelBotoes.add(btnCadastrar);
        painelBotoes.add(btnAcompanhar);
        painelBotoes.add(btnAdicionarHistorico);
        painelBotoes.add(btnSair);

        frame.add(painelBotoes, BorderLayout.CENTER);

        JPanel painelStatus = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelStatus.setBackground(COR_FUNDO_STATUS);
        painelStatus.setBorder(new EmptyBorder(10, 10, 10, 10));

        lblStatusDiscente = new JLabel("Nenhum discente carregado.");
        lblStatusDiscente.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        lblStatusDiscente.setForeground(COR_TEXTO_TITULO);
        painelStatus.add(lblStatusDiscente);
        frame.add(painelStatus, BorderLayout.SOUTH);

        // --- AÇÕES DOS BOTÕES ---
        btnBuscar.addActionListener(e -> new TelaBuscarDiscente().show(d -> {
            if (d != null) {
                this.discente = d;
                lblStatusDiscente.setText("Discente carregado: " + d.getNome());

                btnAcompanhar.setEnabled(true);
                btnAcompanhar.setBackground(COR_BOTAO_PRIMARIO);

                btnAdicionarHistorico.setEnabled(true);
                btnAdicionarHistorico.setBackground(COR_BOTAO_PRIMARIO);

                JOptionPane.showMessageDialog(frame, "Discente " + d.getNome() + " carregado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            }
        }));

        btnCadastrar.addActionListener(e -> {
            new TelaCadastrarDiscente().show(d -> {
                if (d != null) {
                    this.discente = d;
                    lblStatusDiscente.setText("Discente carregado: " + d.getNome());

                    btnAcompanhar.setEnabled(true);
                    btnAcompanhar.setBackground(COR_BOTAO_PRIMARIO);

                    btnAdicionarHistorico.setEnabled(true);
                    btnAdicionarHistorico.setBackground(COR_BOTAO_PRIMARIO);

                    JOptionPane.showMessageDialog(frame, "Discente " + d.getNome() + " cadastrado e carregado!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                }
            });
        });

        btnAdicionarHistorico.addActionListener(e -> {
            if (discente != null) {
                new TelaCadastrarDiscente().showSomenteHistorico(discente);
            } else {
                JOptionPane.showMessageDialog(frame, "Nenhum discente carregado. Use o botão 'Buscar Discente' primeiro.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });

        btnAcompanhar.addActionListener(e -> {
            if (discente != null) {
                new TelaAcompanharCurso().show(discente);
            }
        });

        btnSair.addActionListener(e -> System.exit(0));

        frame.setVisible(true);
    }

    private JButton criarBotaoPrincipal(String texto, String caminhoIcone) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Segoe UI", Font.BOLD, 16));
        botao.setBackground(COR_BOTAO_PRIMARIO);
        botao.setForeground(COR_TEXTO_BOTAO);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botao.setFocusPainted(false);
        botao.setBorderPainted(false);
        botao.setIconTextGap(15);
        botao.setPreferredSize(new Dimension(200, 60));

        try {
            ImageIcon icon = new ImageIcon(new ImageIcon(getClass().getResource(caminhoIcone)).getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH));
            botao.setIcon(icon);
        } catch (Exception e) {
            System.err.println("Ícone não encontrado: " + caminhoIcone);
        }

        botao.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                if (botao.isEnabled()) {
                    botao.setBackground(COR_BOTAO_PRIMARIO_HOVER);
                }
            }

            public void mouseExited(MouseEvent evt) {
                if (botao.isEnabled()) {
                    botao.setBackground(COR_BOTAO_PRIMARIO);
                }
            }
        });

        return botao;
    }
}
