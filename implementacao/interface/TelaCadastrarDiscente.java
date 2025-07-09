// TelaCadastrarDiscente.java
package classes.interfaceGrafica;

import classes.atributos.*;
import classes.tools.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Consumer;
import java.util.List;
import java.util.ArrayList;

public class TelaCadastrarDiscente {

    private static final Color COR_FUNDO = new Color(244, 246, 249);
    private static final Color COR_TEXTO_NORMAL = new Color(33, 37, 41);
    private static final Color COR_BOTAO_PRIMARIO = new Color(13, 110, 253);
    private static final Color COR_BOTAO_PRIMARIO_HOVER = new Color(10, 88, 202);
    private static final Color COR_BOTAO_SECUNDARIO = new Color(108, 117, 125);
    private static final Color COR_BOTAO_SECUNDARIO_HOVER = new Color(84, 92, 98);
    private static final Color COR_TEXTO_BOTAO = Color.WHITE;

    private Discente discente;

    public void show(Consumer<Discente> onFinish, Runnable onClose) {
        JFrame frame = new JFrame("Cadastro de Discente - Etapa 1");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(450, 350);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(COR_FUNDO);

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent e) {
                if (onClose != null) onClose.run();
            }
        });

        JLabel lblTitulo = new JLabel("Dados Iniciais", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setBorder(new EmptyBorder(15, 10, 10, 10));
        frame.add(lblTitulo, BorderLayout.NORTH);

        JPanel painelFormulario = new JPanel(new GridBagLayout());
        painelFormulario.setBackground(COR_FUNDO);
        painelFormulario.setBorder(new EmptyBorder(10, 20, 10, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        JLabel lblNome = new JLabel("Nome completo:");
        lblNome.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridy = 0;
        painelFormulario.add(lblNome, gbc);

        JTextField campoNome = new JTextField(20);
        campoNome.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 5, 15, 5);
        painelFormulario.add(campoNome, gbc);

        JLabel lblMatricula = new JLabel("Matrícula (10 dígitos):");
        lblMatricula.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 5, 5, 5);
        painelFormulario.add(lblMatricula, gbc);

        JTextField campoMatricula = new JTextField(20);
        campoMatricula.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridy = 3;
        painelFormulario.add(campoMatricula, gbc);

        frame.add(painelFormulario, BorderLayout.CENTER);

        JPanel painelAcao = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelAcao.setBackground(COR_FUNDO);
        painelAcao.setBorder(new EmptyBorder(0, 20, 15, 20));
        JButton botaoContinuar = criarBotaoEstilizado("Continuar", COR_BOTAO_PRIMARIO, COR_BOTAO_PRIMARIO_HOVER);
        painelAcao.add(botaoContinuar);
        frame.add(painelAcao, BorderLayout.SOUTH);

        botaoContinuar.addActionListener((ActionEvent e) -> {
            String nome = campoNome.getText().trim();
            String matricula = campoMatricula.getText().trim();

            if (nome.isEmpty() || !matricula.matches("\\d{10}")) {
                JOptionPane.showMessageDialog(frame, "Preencha corretamente os dados.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
                return;
            }

            discente = new Discente(nome, matricula);
            frame.dispose();
            mostrarMenuCadastro(onClose);
            if (onFinish != null) {
                ArqDiscente.exportarHistoricoParaCSV(discente);
                onFinish.accept(discente);
            }
        });

        frame.setVisible(true);
    }



    private void mostrarMenuCadastro(Runnable onClose) {
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

        JPanel painelBotoes = new JPanel(new GridLayout(0, 2, 10, 10));
        painelBotoes.setBackground(COR_FUNDO);
        painelBotoes.setBorder(new EmptyBorder(15, 15, 15, 15));

        String[] opcoes = {
                "Cadastrar Artigo", "Cadastrar Atividade Complementar", "Cadastrar Componente Curricular Obrigatório",
                "Cadastrar Componente Curricular Não Obrigatório", "Cadastrar Estágio Obrigatório", "Cadastrar Estágio Não Obrigatório",
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

        menuFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent e) {
                if (onClose != null) onClose.run();
            }
        });

        menuFrame.setVisible(true);
    }




    private void executarCadastro(int opcao) {
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
                lerComponentesCurriculares componente = new lerComponentesCurriculares("Cursos/ComponentesCurriculares");
                List<String> nomesComponentes = componente.getComponentes();

                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                List<JCheckBox> checkBoxes = new ArrayList<>();

                for (String nome : nomesComponentes) {
                    JCheckBox checkBox = new JCheckBox(nome);
                    checkBoxes.add(checkBox);
                    panel.add(checkBox);
                }

                JScrollPane scrollPane = new JScrollPane(panel);
                scrollPane.setPreferredSize(new Dimension(400, 300));

                int result = JOptionPane.showConfirmDialog(null, scrollPane,
                        "Selecione os Componentes Curriculares Obrigatórios", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (result == JOptionPane.OK_OPTION) {
                    boolean selecionouAlgo = false;
                    for (int i = 0; i < checkBoxes.size(); i++) {
                        if (checkBoxes.get(i).isSelected()) {
                            int id = i + 1;
                            discente.getHistorico().cadastrarIDComponenteCurricularObrigatorio(id);
                            selecionouAlgo = true;
                        }
                    }
                    if (selecionouAlgo) {
                        JOptionPane.showMessageDialog(null, "Componentes cadastrados com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Nenhum componente foi selecionado.");
                    }
                }
            }
            case 4 -> {
                lerComponentesCurriculares componenteN = new lerComponentesCurriculares("Cursos/ComponentesCurricularesNaoObrigatorios");
                List<String> nomesComponentesN = componenteN.getComponentes();

                JPanel panelN = new JPanel();
                panelN.setLayout(new BoxLayout(panelN, BoxLayout.Y_AXIS));
                List<JCheckBox> checkBoxesN = new ArrayList<>();

                for (String nome : nomesComponentesN) {
                    JCheckBox checkBoxN = new JCheckBox(nome);
                    checkBoxesN.add(checkBoxN);
                    panelN.add(checkBoxN);
                }

                JScrollPane scrollPaneN = new JScrollPane(panelN);
                scrollPaneN.setPreferredSize(new Dimension(400, 300));

                int result = JOptionPane.showConfirmDialog(null, scrollPaneN,
                        "Selecione os Componentes Curriculares Não Obrigatórios", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (result == JOptionPane.OK_OPTION) {
                    boolean selecionouAlgo = false;
                    for (int i = 0; i < checkBoxesN.size(); i++) {
                        if (checkBoxesN.get(i).isSelected()) {
                            int idN = i + 1;
                            discente.getHistorico().cadastrarIDComponenteCurricularNaoObrigatorio(idN);
                            selecionouAlgo = true;
                        }
                    }
                    if (selecionouAlgo) {
                        JOptionPane.showMessageDialog(null, "Componentes cadastrados com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Nenhum componente foi selecionado.");
                    }
                }
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

        ArqDiscente.exportarHistoricoParaCSV(discente);
        JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
    }

    public void showSomenteHistorico(Discente discenteExistente, Runnable onClose) {
        if (discenteExistente == null) {
            JOptionPane.showMessageDialog(null, "Nenhum discente carregado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        this.discente = discenteExistente;
        mostrarMenuCadastro(onClose);
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
