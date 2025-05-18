import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class NavegacaoApp {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(NavegacaoApp::tela1);
    }

    static void tela1() {

        JFrame frame = new JFrame("Verificar Discente");
        JTextField campoMatricula = new JTextField(10);
        JButton botao = new JButton("Verificar");

        JPanel painel = new JPanel();
        painel.add(new JLabel("Digite a matrícula:"));
        painel.add(campoMatricula);
        painel.add(botao);


        // Ação que será usada tanto no botão quanto ao pressionar Enter
        Action verificarAcao = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String matricula = campoMatricula.getText();
                if (matricula.length() != 10 || !matricula.matches("\\d+")) {
                    JOptionPane.showMessageDialog(frame, "Matrícula inválida. Digite 10 números.");
                    return;
                }

                frame.dispose();
                Aluno aluno = Aluno.verificarDiscente(matricula);

                if (aluno != null) {
                    tela2(aluno);
                } else {
                    tela3();
                }
            }
        };

        // Associa o botão à ação
        botao.addActionListener(verificarAcao);
        // Pressionar Enter no campo também chama a ação
        campoMatricula.addActionListener(verificarAcao);
        // Define o botão como padrão (ativa com Enter)
        frame.getRootPane().setDefaultButton(botao);

        frame.setContentPane(painel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 100);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    // Tela 2: Encontrou o aluno
    static void tela2(Aluno aluno) {
        JFrame frame = new JFrame("Aluno Encontrado");
        JLabel label = new JLabel("Bem-vindo, " + aluno.getMatricula() + "!");
        JButton sair = new JButton("Sair");

        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.addActionListener(e -> {
            frame.dispose();  // Fecha a tela atual
            tela1();          // Chama a tela1
        });

        JPanel painel = new JPanel(new BorderLayout());
        painel.add(label, BorderLayout.CENTER);
        painel.add(sair, BorderLayout.SOUTH);
        painel.add(botaoVoltar);

        frame.setContentPane(painel);
        frame.setSize(300, 100);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        sair.addActionListener(e -> frame.dispose());
    }

    static void tela3() {
        JFrame frame = new JFrame("Aluno Não Encontrado");

        JLabel label = new JLabel("Escolha uma opção:");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton botao1 = new JButton("Cadastrar manualmente");
        botao1.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton botao2 = new JButton("Cadastrar por arquivo");
        botao2.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.setAlignmentX(Component.CENTER_ALIGNMENT);

        botaoVoltar.addActionListener(e -> {
            frame.dispose();  // Fecha a tela atual
            tela1();          // Chama a tela1
        });

        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        painel.add(label);
        painel.add(Box.createRigidArea(new Dimension(0, 10)));
        painel.add(botao1);
        painel.add(Box.createRigidArea(new Dimension(0, 10)));
        painel.add(botao2);
        painel.add(Box.createRigidArea(new Dimension(0, 10)));
        painel.add(botaoVoltar);

        frame.setContentPane(painel);
        frame.setSize(300, 220);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        botao1.addActionListener(e -> {
            frame.dispose();
            tela4();
        });

        botao2.addActionListener(e -> {
            frame.dispose();
            tela5();
        });
    }


    static void tela4() {

        Map<String, RequisitosCurso> cursos = LerCursos.carregarCursos("Cursos");

        JFrame frame = new JFrame("Selecione seu curso");

        JComboBox<String> comboCursos = new JComboBox<>(cursos.keySet().toArray(new String[0]));
        JButton botaoSelecionar = new JButton("Selecionar");

        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.addActionListener(e -> {
            frame.dispose();  // Fecha a tela atual
            tela3();          // Chama a tela1
        });

        JPanel painel = new JPanel();
        painel.add(new JLabel("Curso:"));
        painel.add(comboCursos);
        painel.add(botaoSelecionar);
        painel.add(botaoVoltar);

        frame.setContentPane(painel);
        frame.setSize(400, 100);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        botaoSelecionar.addActionListener((ActionEvent e) -> {
            String cursoSelecionado = (String) comboCursos.getSelectedItem();
            RequisitosCurso curso = cursos.get(cursoSelecionado);
            frame.dispose();
            List<String> materias = List.of(
                "Cálculo I",
                "Programação Orientada a Objetos",
                "Estruturas de Dados",
                "Sistemas Operacionais",
                "Inteligência Artificial"
                );
            telaCadastro1(materias);
        });
    }


    static void tela5() {
        JFrame frame = new JFrame("Cadastrar por arquivo");
        JLabel label = new JLabel("Digite o nome do arquivo");

        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.addActionListener(e -> {
            frame.dispose();  // Fecha a tela atual
            tela3();          // Chama a tela1
        });

        JTextField campoNomeArquivo = new JTextField(30);
        JButton botaoBuscar = new JButton("Buscar dados");

        // Ação que será usada tanto no botão quanto ao pressionar Enter
        Action buscarAcao = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeArquivo = campoNomeArquivo.getText();
                frame.dispose();

                // Simulação: se nome do arquivo for "erro", vai para tela de cadastro
                if (Cadastro.cadastrarDiscenteArq(nomeArquivo) == 1) {
                    tela3();
                } else {//arrumar aqui
                    // Exemplo: cria um Aluno fictício
                    Aluno aluno = new Aluno(); // substitua conforme a lógica real
                    tela2(aluno);
                }
            }
        };

        campoNomeArquivo.addActionListener(buscarAcao);
        botaoBuscar.addActionListener(buscarAcao);

        JPanel painelCentro = new JPanel();
        painelCentro.add(label);
        painelCentro.add(campoNomeArquivo);
        painelCentro.add(botaoBuscar);
        painelCentro.add(botaoVoltar);

        JPanel painel = new JPanel(new BorderLayout());
        painel.add(painelCentro, BorderLayout.CENTER);

        frame.setContentPane(painel);
        frame.setSize(400, 125);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

   static void telaCadastro2() {
        JFrame frame = new JFrame("Cadastrar Artigo Científico");

        JLabel label = new JLabel("Digite o título do artigo:");
        JTextField campoNomeArtigo = new JTextField(20);

        JLabel label1 = new JLabel("Digite onde foi realizada a publicação:");
        JTextField campoNomeVeiculo = new JTextField(20);

        JButton botaoSalvar = new JButton("Salvar");
        JButton botaoVoltar = new JButton("Voltar");

        Action salvarAcao = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                telaCadastro3();
            }
        };

        campoNomeArtigo.addActionListener(salvarAcao);
        campoNomeVeiculo.addActionListener(salvarAcao);
        botaoSalvar.addActionListener(salvarAcao);

        botaoVoltar.addActionListener(e -> {
            frame.dispose();
            tela4();
        });

        // Cada campo e label em um painel separado para centralizar
        JPanel linha1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        linha1.add(label);

        JPanel linha2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        linha2.add(campoNomeArtigo);

        JPanel linha3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        linha3.add(label1);

        JPanel linha4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        linha4.add(campoNomeVeiculo);

        JPanel linhaBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        linhaBotoes.add(botaoSalvar);
        linhaBotoes.add(botaoVoltar);

        // Painel principal com layout vertical
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        painelPrincipal.add(linha1);
        painelPrincipal.add(linha2);
        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 10)));
        painelPrincipal.add(linha3);
        painelPrincipal.add(linha4);
        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 15)));
        painelPrincipal.add(linhaBotoes);

        frame.setContentPane(painelPrincipal);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }



    static void telaCadastro3() {
        JFrame frame = new JFrame("Cadastrar Atividade Complementar");

        JLabel label = new JLabel("Digite a descrição da atividade:");
        JTextField campoDescAtiv = new JTextField(20);

        JLabel label1 = new JLabel("Digite as horas da atividade:");
        JTextField campoTempo = new JTextField(5);

        JButton botaoSalvar = new JButton("Salvar");
        JButton botaoVoltar = new JButton("Voltar");

        Action salvarAcao = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                telaCadastro4();
            }
        };

        campoDescAtiv.addActionListener(salvarAcao);
        campoTempo.addActionListener(salvarAcao);
        botaoSalvar.addActionListener(salvarAcao);

        botaoVoltar.addActionListener(e -> {
            frame.dispose();
            telaCadastro2();
        });

        // Linha para descrição da atividade
        JPanel linha1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        linha1.add(label);

        JPanel linha2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        linha2.add(campoDescAtiv);

        // Linha para horas da atividade
        JPanel linha3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        linha3.add(label1);

        JPanel linha4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        linha4.add(campoTempo);

        // Linha dos botões
        JPanel linhaBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        linhaBotoes.add(botaoSalvar);
        linhaBotoes.add(botaoVoltar);

        // Painel principal com layout vertical
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        painelPrincipal.add(linha1);
        painelPrincipal.add(linha2);
        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 10)));
        painelPrincipal.add(linha3);
        painelPrincipal.add(linha4);
        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 15)));
        painelPrincipal.add(linhaBotoes);

        frame.setContentPane(painelPrincipal);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    static void telaCadastro4() {
        JFrame frame = new JFrame("Cadastrar estágio");

        JLabel label = new JLabel("Digite as horas:");
        JTextField campoTempo = new JTextField(10);

        JLabel label1 = new JLabel("Digite onde foi realizado:");
        JTextField campoEmpresa = new JTextField(20);

        JLabel label2 = new JLabel("Digite o tipo de estágio:");
        // Exemplo de opções para tipo de estágio
        String[] tiposEstagio = { "Obrigatório", "Não obrigatório", "Voluntário", "Outro" };
        JComboBox<String> comboTipo = new JComboBox<>(tiposEstagio);

        JButton botaoSalvar = new JButton("Salvar");
        JButton botaoVoltar = new JButton("Voltar");

        Action salvarAcao = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                telaCadastro5();
            }
        };

        campoTempo.addActionListener(salvarAcao);
        campoEmpresa.addActionListener(salvarAcao);
        comboTipo.addActionListener(salvarAcao);
        botaoSalvar.addActionListener(salvarAcao);

        botaoVoltar.addActionListener(e -> {
            frame.dispose();
            telaCadastro3();
        });

        // Linha horas
        JPanel linha1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        linha1.add(label);

        JPanel linha2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        linha2.add(campoTempo);

        // Linha empresa
        JPanel linha3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        linha3.add(label1);

        JPanel linha4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        linha4.add(campoEmpresa);

        // Linha tipo estágio
        JPanel linha5 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        linha5.add(label2);

        JPanel linha6 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        linha6.add(comboTipo);

        // Linha botões
        JPanel linhaBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        linhaBotoes.add(botaoSalvar);
        linhaBotoes.add(botaoVoltar);

        // Painel principal vertical
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        painelPrincipal.add(linha1);
        painelPrincipal.add(linha2);
        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 10)));

        painelPrincipal.add(linha3);
        painelPrincipal.add(linha4);
        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 10)));

        painelPrincipal.add(linha5);
        painelPrincipal.add(linha6);
        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 15)));

        painelPrincipal.add(linhaBotoes);

        frame.setContentPane(painelPrincipal);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


   static void telaCadastro5() {
        JFrame frame = new JFrame("Cadastrar Serviço Comunitário");

        JLabel label = new JLabel("Digite a atividade:");
        JTextField campoAtividade = new JTextField(30);

        JLabel label1 = new JLabel("Digite as horas:");
        JTextField campoHoras = new JTextField(10);

        JButton botaoSalvar = new JButton("Salvar");
        JButton botaoVoltar = new JButton("Voltar");

        Action salvarAcao = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                telaCadastro6();
            }
        };

        campoAtividade.addActionListener(salvarAcao);
        campoHoras.addActionListener(salvarAcao);
        botaoSalvar.addActionListener(salvarAcao);

        botaoVoltar.addActionListener(e -> {
            frame.dispose();
            telaCadastro4();
        });

        // Linhas separadas para labels e campos, centralizados
        JPanel linha1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        linha1.add(label);

        JPanel linha2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        linha2.add(campoAtividade);

        JPanel linha3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        linha3.add(label1);

        JPanel linha4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        linha4.add(campoHoras);

        JPanel linhaBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        linhaBotoes.add(botaoSalvar);
        linhaBotoes.add(botaoVoltar);

        // Painel principal com BoxLayout vertical
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        painelPrincipal.add(linha1);
        painelPrincipal.add(linha2);
        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 10)));

        painelPrincipal.add(linha3);
        painelPrincipal.add(linha4);
        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 15)));

        painelPrincipal.add(linhaBotoes);

        frame.setContentPane(painelPrincipal);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    static void telaCadastro6() {
        JFrame frame = new JFrame("Prática Extensionista");

        JLabel label = new JLabel("Digite o nome do projeto:");
        JTextField campoNome = new JTextField(30);

        JLabel label1 = new JLabel("Digite o papel:");
        JTextField campoPapel = new JTextField(30);

        JLabel label2 = new JLabel("Digite os créditos:");
        JTextField campoCreditos = new JTextField(10);

        JButton botaoSalvar = new JButton("Salvar");
        JButton botaoVoltar = new JButton("Voltar");

        Action salvarAcao = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                telaCadastro7();
            }
        };

        campoNome.addActionListener(salvarAcao);
        campoPapel.addActionListener(salvarAcao);
        campoCreditos.addActionListener(salvarAcao);
        botaoSalvar.addActionListener(salvarAcao);

        botaoVoltar.addActionListener(e -> {
            frame.dispose();
            telaCadastro5();
        });

        // Linhas para labels e campos
        JPanel linha1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        linha1.add(label);

        JPanel linha2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        linha2.add(campoNome);

        JPanel linha3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        linha3.add(label1);

        JPanel linha4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        linha4.add(campoPapel);

        JPanel linha5 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        linha5.add(label2);

        JPanel linha6 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        linha6.add(campoCreditos);

        JPanel linhaBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        linhaBotoes.add(botaoSalvar);
        linhaBotoes.add(botaoVoltar);

        // Painel principal com BoxLayout vertical
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        painelPrincipal.add(linha1);
        painelPrincipal.add(linha2);
        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 10)));

        painelPrincipal.add(linha3);
        painelPrincipal.add(linha4);
        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 10)));

        painelPrincipal.add(linha5);
        painelPrincipal.add(linha6);
        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 15)));

        painelPrincipal.add(linhaBotoes);

        frame.setContentPane(painelPrincipal);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    static void telaCadastro7() {
        JFrame frame = new JFrame("Cadastrar Resumo Expandido");

        JLabel label = new JLabel("Digite o título do resumo:");
        JTextField campoNome = new JTextField(30);

        JLabel label1 = new JLabel("Digite o evento de apresentação:");
        JTextField campoEvento = new JTextField(30);

        JButton botaoSalvar = new JButton("Salvar");
        JButton botaoVoltar = new JButton("Voltar");

        Action salvarAcao = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                telaCadastro8();
            }
        };

        campoNome.addActionListener(salvarAcao);
        campoEvento.addActionListener(salvarAcao);
        botaoSalvar.addActionListener(salvarAcao);

        botaoVoltar.addActionListener(e -> {
            frame.dispose();
            telaCadastro6();
        });

        // Linhas com FlowLayout centralizado para labels e campos
        JPanel linha1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        linha1.add(label);

        JPanel linha2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        linha2.add(campoNome);

        JPanel linha3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        linha3.add(label1);

        JPanel linha4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        linha4.add(campoEvento);

        JPanel linhaBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        linhaBotoes.add(botaoSalvar);
        linhaBotoes.add(botaoVoltar);

        // Painel principal com BoxLayout vertical e borda para espaçamento
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        painelPrincipal.add(linha1);
        painelPrincipal.add(linha2);
        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 10)));

        painelPrincipal.add(linha3);
        painelPrincipal.add(linha4);
        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 15)));

        painelPrincipal.add(linhaBotoes);

        frame.setContentPane(painelPrincipal);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    static void telaCadastro8() {

        JFrame frame = new JFrame("Situação do ENADE");
        JLabel label = new JLabel("Selecione sua situação no ENADE:");

        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.addActionListener(e -> {
            frame.dispose();  // Fecha a tela atual
            telaCadastro7();          // Chama a tela1
        });

        String[] opcoes = {"Regular", "Irregular"};
        JComboBox<String> comboSituacao = new JComboBox<>(opcoes);

        JButton botaoSalvar = new JButton("Salvar");

        Action salvarAcao = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String situacaoSelecionada = (String) comboSituacao.getSelectedItem();

                frame.dispose();
                tela1(); // Chama a próxima tela
            }
        };

        comboSituacao.addActionListener(salvarAcao);
        botaoSalvar.addActionListener(salvarAcao);

        JPanel painelCentro = new JPanel();
        painelCentro.add(label);
        painelCentro.add(comboSituacao);
        painelCentro.add(botaoSalvar);
        painelCentro.add(botaoVoltar);

        JPanel painel = new JPanel(new BorderLayout());
        painel.add(painelCentro, BorderLayout.CENTER);

        frame.setContentPane(painel);
        frame.setSize(400, 120);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void telaCadastro1(List<String> materias) {
        JFrame frame = new JFrame("Selecione as matérias");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.addActionListener(e -> {
            frame.dispose();  // Fecha a tela atual
            tela4();          // Chama a tela1
        });

        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));
        List<JCheckBox> checkBoxes = new ArrayList<>();

        for (String materia : materias) {
            JCheckBox checkBox = new JCheckBox(materia);
            checkBoxes.add(checkBox);
            checkBoxPanel.add(checkBox);
        }

        JScrollPane scrollPane = new JScrollPane(checkBoxPanel);
        scrollPane.setPreferredSize(new Dimension(300, 200));

        JButton botaoConfirmar = new JButton("Confirmar seleção");
        botaoConfirmar.addActionListener(e -> {
            List<String> selecionadas = new ArrayList<>();
            for (JCheckBox checkBox : checkBoxes) {
                if (checkBox.isSelected()) {
                    selecionadas.add(checkBox.getText());
                }
            }

            // Aqui você pode usar a lista 'selecionadas'
            frame.dispose();
            telaCadastro2(); // Altere conforme necessário
        });

        JPanel painel = new JPanel(new BorderLayout());
        painel.add(scrollPane, BorderLayout.NORTH);
        painel.add(botaoConfirmar, BorderLayout.CENTER);
        painel.add(botaoVoltar, BorderLayout.SOUTH);

        frame.setContentPane(painel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
