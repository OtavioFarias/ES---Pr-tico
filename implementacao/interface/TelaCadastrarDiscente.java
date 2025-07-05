// TelaCadastrarDiscente.java
package interfaceGrafica;

import classes.*;
import tools.ArqDiscente;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Consumer;

public class TelaCadastrarDiscente {

    // --- Paleta de Cores do Design (sem alterações) ---
    private static final Color COR_FUNDO = new Color(244, 246, 249);
    private static final Color COR_TEXTO_NORMAL = new Color(33, 37, 41);
    private static final Color COR_BOTAO_PRIMARIO = new Color(13, 110, 253);
    private static final Color COR_BOTAO_PRIMARIO_HOVER = new Color(10, 88, 202);
    private static final Color COR_BOTAO_SECUNDARIO = new Color(108, 117, 125);
    private static final Color COR_BOTAO_SECUNDARIO_HOVER = new Color(84, 92, 98);
    private static final Color COR_TEXTO_BOTAO = Color.WHITE;

    private Discente discente;

    /**
     * ETAPA 1: Mostra a primeira janela para coletar nome e matrícula.
     * O layout foi ajustado para colocar os labels ACIMA dos campos de texto.
     */
    public void show(Consumer<Discente> onFinish) {
        JFrame frame = new JFrame("Cadastro de Discente - Etapa 1");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // Aumentei um pouco a altura para acomodar o novo layout vertical
        frame.setSize(450, 350);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(COR_FUNDO);

        JLabel lblTitulo = new JLabel("Dados Iniciais", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setBorder(new EmptyBorder(15, 10, 10, 10));
        frame.add(lblTitulo, BorderLayout.NORTH);

        JPanel painelFormulario = new JPanel(new GridBagLayout());
        painelFormulario.setBackground(COR_FUNDO);
        painelFormulario.setBorder(new EmptyBorder(10, 20, 10, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 5, 5, 5); // Espaçamento entre linhas
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; // Todos os componentes estarão na mesma coluna (x=0)

        // --- INÍCIO DA MUDANÇA DE LAYOUT ---

        // Nome - Label
        JLabel lblNome = new JLabel("Nome completo:");
        lblNome.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.anchor = GridBagConstraints.WEST; // Alinha o texto à esquerda
        gbc.gridy = 0; // Linha 0
        painelFormulario.add(lblNome, gbc);

        // Nome - Campo de Texto
        JTextField campoNome = new JTextField(20);
        campoNome.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridy = 1; // Linha 1 (abaixo do label)
        gbc.insets = new Insets(0, 5, 15, 5); // Adiciona espaço extra abaixo do campo
        painelFormulario.add(campoNome, gbc);

        // Matrícula - Label
        JLabel lblMatricula = new JLabel("Matrícula (10 dígitos):");
        lblMatricula.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridy = 2; // Linha 2
        gbc.insets = new Insets(0, 5, 5, 5); // Reseta o espaço
        painelFormulario.add(lblMatricula, gbc);

        // Matrícula - Campo de Texto
        JTextField campoMatricula = new JTextField(20);
        campoMatricula.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridy = 3; // Linha 3 (abaixo do label)
        painelFormulario.add(campoMatricula, gbc);

        // --- FIM DA MUDANÇA DE LAYOUT ---

        frame.add(painelFormulario, BorderLayout.CENTER);

        JPanel painelAcao = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelAcao.setBackground(COR_FUNDO);
        painelAcao.setBorder(new EmptyBorder(0, 20, 15, 20));
        JButton botaoContinuar = criarBotaoEstilizado("Continuar", COR_BOTAO_PRIMARIO, COR_BOTAO_PRIMARIO_HOVER);
        painelAcao.add(botaoContinuar);
        frame.add(painelAcao, BorderLayout.SOUTH);

        // --- AÇÃO ORIGINAL MANTIDA ---
        botaoContinuar.addActionListener((ActionEvent e) -> {
            String nome = campoNome.getText().trim();
            String matricula = campoMatricula.getText().trim();

            if (nome.isEmpty() || !matricula.matches("\\d{10}")) {
                JOptionPane.showMessageDialog(frame, "Preencha corretamente os dados.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
                return;
            }

            discente = new Discente(nome, matricula);
            frame.dispose();
            mostrarMenuCadastro();
            if (onFinish != null) {
                onFinish.accept(discente);
            }
            ArqDiscente.exportarHistoricoParaCSV(discente);
        });

        frame.setVisible(true);
    }

    /**
     * ETAPA 2: Mostra a segunda janela com as opções de cadastro de histórico.
     * (Este método não foi alterado)
     */
    private void mostrarMenuCadastro() {
        JFrame menuFrame = new JFrame("Cadastrar Informações - Etapa 2");
        menuFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        menuFrame.setSize(600, 500);
        menuFrame.setLocationRelativeTo(null);
        menuFrame.setLayout(new BorderLayout());
        menuFrame.getContentPane().setBackground(COR_FUNDO);

        JLabel lblTitulo = new JLabel("Adicionar Informações ao Histórico", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setBorder(new EmptyBorder(15, 10, 10, 10));
        menuFrame.add(lblTitulo, BorderLayout.NORTH);

        JPanel painelBotoes = new JPanel(new GridLayout(0, 2, 10, 10)); // 2 colunas
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
            botao.addActionListener(e -> executarCadastro(opcao)); // Ação original mantida
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

    /**
     * FUNCIONALIDADE ORIGINAL MANTIDA: Usa JOptionPanes para os cadastros.
     * (Este método não foi alterado)
     */
    private void executarCadastro(int opcao) {
        switch (opcao) {
            case 1 -> {
                String titulo = JOptionPane.showInputDialog("Título do artigo:");
                String veiculo = JOptionPane.showInputDialog("Veículo de publicação:");
                if (titulo == null || veiculo == null) return; // Checagem se o usuário cancelou
                discente.getHistorico().cadastrarArtigo(titulo, veiculo);
            }
            // Adicione checagens de 'null' para os outros JOptionPanes também se desejar
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

        // A checagem de nulo no primeiro case evita que esta mensagem apareça se o usuário cancelar.
        JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
    }

    /**
     * Método auxiliar para criar botões estilizados.
     * (Este método não foi alterado)
     */
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