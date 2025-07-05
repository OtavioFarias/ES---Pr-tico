// TelaBuscarDiscente.java
package classes.interfaceGrafica;

import classes.tools.ArqDiscente;
import classes.atributos.Discente;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;
import java.awt.event.*;
public class TelaBuscarDiscente {

    // --- Paleta de Cores ---
    private static final Color COR_FUNDO = new Color(244, 246, 249);
    private static final Color COR_TEXTO_NORMAL = new Color(33, 37, 41);
    private static final Color COR_BOTAO_ACAO = new Color(25, 135, 84); // Verde para sucesso/busca
    private static final Color COR_BOTAO_ACAO_HOVER = new Color(21, 115, 71);
    private static final Color COR_BOTAO_VOLTAR = new Color(108, 117, 125); // Cinza para secundário
    private static final Color COR_BOTAO_VOLTAR_HOVER = new Color(84, 92, 98);
    private static final Color COR_TEXTO_BOTAO = Color.WHITE;
    private static final Color COR_SUCESSO = new Color(25, 135, 84);
    private static final Color COR_ERRO = new Color(220, 53, 69);

    private JTextField campoMatricula;
    private JLabel lblResultado;
    private JFrame frame;

    public void show(Consumer<Discente> callback) {
        frame = new JFrame("Buscar Discente por Matrícula");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(450, 350);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout(10, 10));
        frame.getContentPane().setBackground(COR_FUNDO);

        JPanel painelTitulo = new JPanel();
        painelTitulo.setLayout(new BoxLayout(painelTitulo, BoxLayout.Y_AXIS));
        painelTitulo.setBackground(COR_FUNDO);
        painelTitulo.setBorder(new EmptyBorder(20, 20, 10, 20));

        JLabel lblTitulo = new JLabel("Buscar Discente");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitulo.setForeground(COR_TEXTO_NORMAL);
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelTitulo.add(lblTitulo);

        JLabel lblSubtitulo = new JLabel("Digite a matrícula de 10 dígitos do discente.");
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSubtitulo.setForeground(COR_TEXTO_NORMAL);
        lblSubtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelTitulo.add(Box.createRigidArea(new Dimension(0, 5)));
        painelTitulo.add(lblSubtitulo);
        frame.add(painelTitulo, BorderLayout.NORTH);

        JPanel painelEntrada = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        painelEntrada.setBackground(COR_FUNDO);
        painelEntrada.setBorder(new EmptyBorder(10, 20, 10, 20));

        JLabel lblMatricula = new JLabel("Matrícula:");
        lblMatricula.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblMatricula.setForeground(COR_TEXTO_NORMAL);

        campoMatricula = new JTextField(15);
        campoMatricula.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        campoMatricula.setPreferredSize(new Dimension(campoMatricula.getPreferredSize().width, 30));
        painelEntrada.add(lblMatricula);
        painelEntrada.add(campoMatricula);
        frame.add(painelEntrada, BorderLayout.CENTER);

        JPanel painelAcoes = new JPanel(new BorderLayout(10, 10));
        painelAcoes.setBackground(COR_FUNDO);
        painelAcoes.setBorder(new EmptyBorder(10, 20, 20, 20));

        lblResultado = new JLabel(" ", SwingConstants.CENTER);
        lblResultado.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        painelAcoes.add(lblResultado, BorderLayout.NORTH);

        JPanel painelBotoes = new JPanel(new GridLayout(1, 2, 10, 0));
        painelBotoes.setBackground(COR_FUNDO);

        JButton btnBuscar = criarBotaoAcao("Buscar", COR_BOTAO_ACAO, COR_BOTAO_ACAO_HOVER);
        JButton btnVoltar = criarBotaoAcao("Voltar", COR_BOTAO_VOLTAR, COR_BOTAO_VOLTAR_HOVER);
        painelBotoes.add(btnBuscar);
        painelBotoes.add(btnVoltar);
        painelAcoes.add(painelBotoes, BorderLayout.CENTER);
        frame.add(painelAcoes, BorderLayout.SOUTH);

        // --- AÇÃO DE BUSCA ---
        Action verificarAcao = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String matricula = campoMatricula.getText().trim();
                if (matricula.length() != 10 || !matricula.matches("\\d+")) {
                    lblResultado.setText("Matrícula inválida. Digite 10 números.");
                    lblResultado.setForeground(COR_ERRO);
                    return;
                }

                Discente discente = ArqDiscente.importarDiscenteDeCSV(matricula);

                if (discente != null) {
                    lblResultado.setText("Discente encontrado! Fechando...");
                    lblResultado.setForeground(COR_SUCESSO);

                    Timer timer = new Timer(1500, event -> {
                        callback.accept(discente);
                        frame.dispose();
                    });
                    timer.setRepeats(false);
                    timer.start();
                } else {
                    lblResultado.setText("Discente com matrícula " + matricula + " não foi encontrado.");
                    lblResultado.setForeground(COR_ERRO);
                    callback.accept(null);
                }
            }
        };

        btnBuscar.addActionListener(verificarAcao);
        campoMatricula.addActionListener(verificarAcao);
        frame.getRootPane().setDefaultButton(btnBuscar);
        btnVoltar.addActionListener(e -> frame.dispose());

        frame.setVisible(true);
    }

    private JButton criarBotaoAcao(String texto, Color corFundo, Color corHover) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Segoe UI", Font.BOLD, 14));
        botao.setBackground(corFundo);
        botao.setForeground(COR_TEXTO_BOTAO);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botao.setFocusPainted(false);
        botao.setBorderPainted(false);
        botao.setPreferredSize(new Dimension(100, 40));

        botao.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                botao.setBackground(corHover);
            }
            public void mouseExited(MouseEvent evt) {
                botao.setBackground(corFundo);
            }
        });
        return botao;
    }
}