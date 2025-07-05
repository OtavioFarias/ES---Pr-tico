package interfaceGrafica;

import classes.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Consumer;

public class TelaAdicionarInformacoes {

    private static final Color COR_FUNDO = new Color(244, 246, 249);
    private static final Color COR_TEXTO_NORMAL = new Color(33, 37, 41);
    private static final Color COR_BOTAO_PRIMARIO = new Color(13, 110, 253);
    private static final Color COR_BOTAO_PRIMARIO_HOVER = new Color(10, 88, 202);
    private static final Color COR_BOTAO_SECUNDARIO = new Color(108, 117, 125);
    private static final Color COR_BOTAO_SECUNDARIO_HOVER = new Color(84, 92, 98);
    private static final Color COR_TEXTO_BOTAO = Color.WHITE;

    private Discente discente;

    public void show(Consumer<Discente> onFinish) {

    		if (discente == null) {
            JOptionPane.showMessageDialog(null, "Discente não pode ser nulo.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        this.discente = discente;

        mostrarMenuCadastro();
    }

    private void mostrarMenuCadastro() {
        JFrame menuFrame = new JFrame("Cadastrar Informações");
        menuFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        menuFrame.setSize(600, 500);
        menuFrame.setLocationRelativeTo(null);
        menuFrame.setLayout(new BorderLayout());
        menuFrame.getContentPane().setBackground(COR_FUNDO);

        JLabel lblTitulo = new JLabel("Adicionar Informações ao Histórico", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setBorder(new EmptyBorder(15, 10, 10, 10));
        menuFrame.add(lblTitulo, BorderLayout.NORTH);

        JPanel painelBotoes = new JPanel(new GridLayout(0, 2, 10, 10));
        painelBotoes.setBackground(COR_FUNDO);
        painelBotoes.setBorder(new EmptyBorder(15, 15, 15, 15));

        String[] opcoes = {
                "Cadastrar Artigo", "Cadastrar Atividade Complementar", "Cadastrar Cadeira Obrigatória",
                "Cadastrar Cadeira Opcional", "Cadastrar Estágio Obrigatório", "Cadastrar Estágio Não Obrigatório",
                "Cadastrar Prática Extensionista", "Cadastrar Resumo Expandido", "Cadastrar Serviço Comunitário",
                "Cadastrar ENADE"
        };

        for (int i = 0; i < opcoes.length; i++) {
            int opcao = i + 1;
            JButton botao = criarBotaoEstilizado(opcoes[i], COR_BOTAO_SECUNDARIO, COR_BOTAO_SECUNDARIO_HOVER);
            botao.addActionListener(e -> executarCadastro(opcao));
            painelBotoes.add(botao);
        }

        menuFrame.add(painelBotoes, BorderLayout.CENTER);

        JPanel painelVoltar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelVoltar.setBackground(COR_FUNDO);
        painelVoltar.setBorder(new EmptyBorder(0, 15, 15, 15));
        JButton botaoVoltar = new JButton("Fechar");
        painelVoltar.add(botaoVoltar);

        botaoVoltar.addActionListener(e -> menuFrame.dispose());
        menuFrame.add(painelVoltar, BorderLayout.SOUTH);

        menuFrame.setVisible(true);
    }

    private void executarCadastro(int opcao) {
        try {
            switch (opcao) {
                case 1 -> {
                    String titulo = JOptionPane.showInputDialog("Título do artigo:");
                    String veiculo = JOptionPane.showInputDialog("Veículo de publicação:");
                    if (titulo == null || veiculo == null) return;
                    discente.getHistorico().cadastrarArtigo(titulo, veiculo);
                }
                case 2 -> {
                    String desc = JOptionPane.showInputDialog("Descrição da atividade:");
                    int horas = Integer.parseInt(JOptionPane.showInputDialog("Horas:"));
                    discente.getHistorico().cadastrarAtividadeComplementar(desc, horas);
                }
                case 3 -> {
                    int id = Integer.parseInt(JOptionPane.showInputDialog("ID da cadeira obrigatória:"));
                    discente.getHistorico().cadastrarIDComponenteCurricularObrigatorio(id);
                }
                case 4 -> {
                    int id = Integer.parseInt(JOptionPane.showInputDialog("ID da cadeira opcional:"));
                    discente.getHistorico().cadastrarIDComponenteCurricularObrigatorio(id);
                }
                case 5 -> {
                    String empresa = JOptionPane.showInputDialog("Empresa:");
                    int horas = Integer.parseInt(JOptionPane.showInputDialog("Horas:"));
                    String tipo = JOptionPane.showInputDialog("Tipo:");
                    discente.getHistorico().cadastrarEstagioObrigatorio(empresa, horas, tipo);
                }
                case 6 -> {
                    String empresa = JOptionPane.showInputDialog("Empresa:");
                    int horas = Integer.parseInt(JOptionPane.showInputDialog("Horas:"));
                    String tipo = JOptionPane.showInputDialog("Tipo:");
                    discente.getHistorico().cadastrarEstagioNaoObrigatorio(empresa, horas, tipo);
                }
                case 7 -> {
                    String atividade = JOptionPane.showInputDialog("Atividade:");
                    int horas = Integer.parseInt(JOptionPane.showInputDialog("Horas:"));
                    discente.getHistorico().cadastrarPraticaExtensionista(atividade, horas);
                }
                case 8 -> {
                    String projeto = JOptionPane.showInputDialog("Projeto:");
                    String papel = JOptionPane.showInputDialog("Papel:");
                    int creditos = Integer.parseInt(JOptionPane.showInputDialog("Créditos:"));
                    discente.getHistorico().cadastrarResumoExpandido(projeto, papel, creditos);
                }
                case 9 -> {
                    String desc = JOptionPane.showInputDialog("Descrição do serviço:");
                    int horas = Integer.parseInt(JOptionPane.showInputDialog("Horas:"));
                    discente.getHistorico().cadastrarServicoComunitario(desc, horas);
                }
                case 10 -> {
                    int confirm = JOptionPane.showConfirmDialog(null, "Situação ENADE regular?");
                    discente.getHistorico().cadastrarENADE(confirm == JOptionPane.YES_OPTION);
                }
                default -> JOptionPane.showMessageDialog(null, "Opção inválida.");
            }

            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar: " + ex.getMessage());
        }
    }

    private JButton criarBotaoEstilizado(String texto, Color corFundo, Color corHover) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Segoe UI", Font.BOLD, 14));
        botao.setBackground(corFundo);
        botao.setForeground(COR_TEXTO_BOTAO);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botao.setFocusPainted(false);
        botao.setBorderPainted(false);

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
