package classes;

import java.lang.Boolean;
import classes.*;
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
                Discente discente = LerDiscentes.buscarPorMatricula("2310100999", "Discentes");

                if (discente != null) {
                    tela2(discente, discente.getHistorico());
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
    static void tela2(Discente discente, HistoricoComputacao historico) {
        JFrame frame = new JFrame("Aluno Encontrado");
        JLabel label = new JLabel("Bem-vindo, " + discente.getMatricula() + "!");
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
                if (CadastroArq.cadastrarDiscenteArq(nomeArquivo) == null) {
                    tela3();
                } else {//arrumar aqui
                    // Exemplo: cria um Aluno fictício
                    Discente discente = new Discente("2310100999", "batata"); // substitua conforme a lógica real
                    tela2(discente, discente.getHistorico());
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


static void telaCadastro2(Discente discente, HistoricoComputacao historico) {
    JFrame frame = new JFrame("Cadastrar Artigo Científico");
    List<JTextField[]> listaDeCampos = new ArrayList<>();
    JPanel painelCampos = new JPanel();
    painelCampos.setLayout(new BoxLayout(painelCampos, BoxLayout.Y_AXIS));
    JButton botaoAdicionar = new JButton("Adicionar novo artigo");
    JButton botaoSalvar = new JButton("Salvar");
    JButton botaoVoltar = new JButton("Voltar");

    Runnable adicionarCampos = () -> {
        JTextField campoTitulo = new JTextField(20);
        JTextField campoVeiculo = new JTextField(20);

        JPanel linha1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        linha1.add(new JLabel("Título do artigo:"));
        linha1.add(campoTitulo);

        JPanel linha2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        linha2.add(new JLabel("Veículo de publicação:"));
        linha2.add(campoVeiculo);

        painelCampos.add(linha1);
        painelCampos.add(linha2);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 10)));

        listaDeCampos.add(new JTextField[] { campoTitulo, campoVeiculo });

        painelCampos.revalidate();
        painelCampos.repaint();
    };
    adicionarCampos.run();
    botaoAdicionar.addActionListener(e -> adicionarCampos.run());

    botaoSalvar.addActionListener(e -> {
        for (JTextField[] campos : listaDeCampos) {
            String titulo = campos[0].getText();
            String veiculo = campos[1].getText();
            if (!titulo.isEmpty() && !veiculo.isEmpty()) {
                //historico.cadastrarArtigo(titulo, veiculo);
            }
        }
        frame.dispose();
        telaCadastro3(discente, historico);
    });

    botaoVoltar.addActionListener(e -> {
        frame.dispose();
        tela4();
    });

    JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
    painelBotoes.add(botaoAdicionar);
    painelBotoes.add(botaoSalvar);
    painelBotoes.add(botaoVoltar);

    JPanel painelPrincipal = new JPanel();
    painelPrincipal.setLayout(new BorderLayout());
    painelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
    painelPrincipal.add(new JScrollPane(painelCampos), BorderLayout.CENTER);
    painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

    frame.setContentPane(painelPrincipal);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
}

static void telaCadastro3(Discente discente, HistoricoComputacao historico) {
    JFrame frame = new JFrame("Cadastrar Atividade Complementar");
    List<JTextField[]> listaDeCampos = new ArrayList<>();
    JPanel painelCampos = new JPanel();
    painelCampos.setLayout(new BoxLayout(painelCampos, BoxLayout.Y_AXIS));
    JButton botaoAdicionar = new JButton("Adicionar nova atividade");
    JButton botaoSalvar = new JButton("Salvar");
    JButton botaoVoltar = new JButton("Voltar");

    Runnable adicionarCampos = () -> {
        JTextField campoDescricao = new JTextField(20);
        JTextField campoHoras = new JTextField(5);

        JPanel linha1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        linha1.add(new JLabel("Descrição da atividade:"));
        linha1.add(campoDescricao);

        JPanel linha2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        linha2.add(new JLabel("Horas da atividade:"));
        linha2.add(campoHoras);

        painelCampos.add(linha1);
        painelCampos.add(linha2);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 10)));

        listaDeCampos.add(new JTextField[] { campoDescricao, campoHoras });
        painelCampos.revalidate();
        painelCampos.repaint();
    };
    adicionarCampos.run();
    botaoAdicionar.addActionListener(e -> adicionarCampos.run());

    botaoSalvar.addActionListener(e -> {
        for (JTextField[] campos : listaDeCampos) {
            String desc = campos[0].getText();
            String horasStr = campos[1].getText();
            if (!desc.isEmpty() && !horasStr.isEmpty()) {
                try {
                    int horas = Integer.parseInt(horasStr);
                    //historico.cadastrarPraticaExtensionista(desc, horas);
                } catch (NumberFormatException ignored) {}
            }
        }
        frame.dispose();
        telaCadastro4(discente, historico);
    });

    botaoVoltar.addActionListener(e -> {
        frame.dispose();
        telaCadastro2(discente, historico);
    });

    JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
    painelBotoes.add(botaoAdicionar);
    painelBotoes.add(botaoSalvar);
    painelBotoes.add(botaoVoltar);

    JPanel painelPrincipal = new JPanel();
    painelPrincipal.setLayout(new BorderLayout());
    painelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
    painelPrincipal.add(new JScrollPane(painelCampos), BorderLayout.CENTER);
    painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

    frame.setContentPane(painelPrincipal);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
}

static void telaCadastro4(Discente discente, HistoricoComputacao historico) {
    JFrame frame = new JFrame("Cadastrar Estágio");
    List<JTextField[]> listaDeCampos = new ArrayList<>();
    JPanel painelCampos = new JPanel();
    painelCampos.setLayout(new BoxLayout(painelCampos, BoxLayout.Y_AXIS));
    JButton botaoAdicionar = new JButton("Adicionar novo estágio");
    JButton botaoSalvar = new JButton("Salvar");
    JButton botaoVoltar = new JButton("Voltar");

    Runnable adicionarCampos = () -> {
        JTextField campoHoras = new JTextField(5);
        JTextField campoEmpresa = new JTextField(20);
        JTextField campoTipo = new JTextField(10);

        JPanel linha1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        linha1.add(new JLabel("Horas do estágio:"));
        linha1.add(campoHoras);

        JPanel linha2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        linha2.add(new JLabel("Empresa:"));
        linha2.add(campoEmpresa);

        JPanel linha3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        linha3.add(new JLabel("Tipo de estágio:"));
        linha3.add(campoTipo);

        painelCampos.add(linha1);
        painelCampos.add(linha2);
        painelCampos.add(linha3);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 10)));

        listaDeCampos.add(new JTextField[] { campoHoras, campoEmpresa, campoTipo });
        painelCampos.revalidate();
        painelCampos.repaint();
    };
    adicionarCampos.run();
    botaoAdicionar.addActionListener(e -> adicionarCampos.run());

    botaoSalvar.addActionListener(e -> {
        for (JTextField[] campos : listaDeCampos) {
            String horasStr = campos[0].getText();
            String empresa = campos[1].getText();
            String tipo = campos[2].getText();
            if (!horasStr.isEmpty() && !empresa.isEmpty() && !tipo.isEmpty()) {
                try {
                    int horas = Integer.parseInt(horasStr);
                    //historico.cadastrarEstagio(empresa, horas, tipo);
                } catch (NumberFormatException ignored) {}
            }
        }
        frame.dispose();
        telaCadastro5(discente, historico);
    });

    botaoVoltar.addActionListener(e -> {
        frame.dispose();
        telaCadastro3(discente, historico);
    });

    JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
    painelBotoes.add(botaoAdicionar);
    painelBotoes.add(botaoSalvar);
    painelBotoes.add(botaoVoltar);

    JPanel painelPrincipal = new JPanel();
    painelPrincipal.setLayout(new BorderLayout());
    painelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
    painelPrincipal.add(new JScrollPane(painelCampos), BorderLayout.CENTER);
    painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

    frame.setContentPane(painelPrincipal);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
}

static void telaCadastro5(Discente discente, HistoricoComputacao historico) {
    JFrame frame = new JFrame("Cadastrar Serviço");
    List<JTextField[]> listaDeCampos = new ArrayList<>();
    JPanel painelCampos = new JPanel();
    painelCampos.setLayout(new BoxLayout(painelCampos, BoxLayout.Y_AXIS));
    JButton botaoAdicionar = new JButton("Adicionar novo serviço");
    JButton botaoSalvar = new JButton("Salvar");
    JButton botaoVoltar = new JButton("Voltar");

    Runnable adicionarCampos = () -> {
        JTextField campoAtividade = new JTextField(20);
        JTextField campoHoras = new JTextField(5);

        JPanel linha1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        linha1.add(new JLabel("Atividade do serviço:"));
        linha1.add(campoAtividade);

        JPanel linha2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        linha2.add(new JLabel("Horas do serviço:"));
        linha2.add(campoHoras);

        painelCampos.add(linha1);
        painelCampos.add(linha2);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 10)));

        listaDeCampos.add(new JTextField[] { campoAtividade, campoHoras });
        painelCampos.revalidate();
        painelCampos.repaint();
    };
    adicionarCampos.run();
    botaoAdicionar.addActionListener(e -> adicionarCampos.run());

    botaoSalvar.addActionListener(e -> {
        for (JTextField[] campos : listaDeCampos) {
            String atividade = campos[0].getText();
            String horasStr = campos[1].getText();
            if (!atividade.isEmpty() && !horasStr.isEmpty()) {
                try {
                    int horas = Integer.parseInt(horasStr);
                    //historico.cadastrarServicoComunitario(atividade, horas);
                } catch (NumberFormatException ignored) {}
            }
        }
        frame.dispose();
        telaCadastro6(discente, historico);
    });

    botaoVoltar.addActionListener(e -> {
        frame.dispose();
        telaCadastro4(discente, historico);
    });

    JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
    painelBotoes.add(botaoAdicionar);
    painelBotoes.add(botaoSalvar);
    painelBotoes.add(botaoVoltar);

    JPanel painelPrincipal = new JPanel();
    painelPrincipal.setLayout(new BorderLayout());
    painelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
    painelPrincipal.add(new JScrollPane(painelCampos), BorderLayout.CENTER);
    painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

    frame.setContentPane(painelPrincipal);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
}
static void telaCadastro6(Discente discente, HistoricoComputacao historico) {
    JFrame frame = new JFrame("Cadastrar Extensão");
    List<JTextField[]> listaDeCampos = new ArrayList<>();
    JPanel painelCampos = new JPanel();
    painelCampos.setLayout(new BoxLayout(painelCampos, BoxLayout.Y_AXIS));
    JButton botaoAdicionar = new JButton("Adicionar nova extensão");
    JButton botaoSalvar = new JButton("Salvar");
    JButton botaoVoltar = new JButton("Voltar");

    Runnable adicionarCampos = () -> {
        JTextField campoProjeto = new JTextField(20);
        JTextField campoPapel = new JTextField(20);
        JTextField campoCreditos = new JTextField(5);

        JPanel linha1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        linha1.add(new JLabel("Projeto de extensão:"));
        linha1.add(campoProjeto);

        JPanel linha2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        linha2.add(new JLabel("Papel na extensão:"));
        linha2.add(campoPapel);

        JPanel linha3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        linha3.add(new JLabel("Créditos:"));
        linha3.add(campoCreditos);

        painelCampos.add(linha1);
        painelCampos.add(linha2);
        painelCampos.add(linha3);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 10)));

        listaDeCampos.add(new JTextField[] { campoProjeto, campoPapel, campoCreditos });
        painelCampos.revalidate();
        painelCampos.repaint();
    };
    adicionarCampos.run();
    botaoAdicionar.addActionListener(e -> adicionarCampos.run());

    botaoSalvar.addActionListener(e -> {
        for (JTextField[] campos : listaDeCampos) {
            String projeto = campos[0].getText();
            String papel = campos[1].getText();
            String creditosStr = campos[2].getText();
            if (!projeto.isEmpty() && !papel.isEmpty() && !creditosStr.isEmpty()) {
                try {
                    int creditos = Integer.parseInt(creditosStr);
                    //historico.cadastrarPraticaExtensionista(projeto, creditos);
                } catch (NumberFormatException ignored) {}
            }
        }
        frame.dispose();
        telaCadastro7(discente, historico);
    });

    botaoVoltar.addActionListener(e -> {
        frame.dispose();
        telaCadastro5(discente, historico);
    });

    JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
    painelBotoes.add(botaoAdicionar);
    painelBotoes.add(botaoSalvar);
    painelBotoes.add(botaoVoltar);

    JPanel painelPrincipal = new JPanel();
    painelPrincipal.setLayout(new BorderLayout());
    painelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
    painelPrincipal.add(new JScrollPane(painelCampos), BorderLayout.CENTER);
    painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

    frame.setContentPane(painelPrincipal);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
}
static void telaCadastro7(Discente discente, HistoricoComputacao historico) {
    JFrame frame = new JFrame("Cadastrar Resumo");
    List<JTextField[]> listaDeCampos = new ArrayList<>();
    JPanel painelCampos = new JPanel();
    painelCampos.setLayout(new BoxLayout(painelCampos, BoxLayout.Y_AXIS));
    JButton botaoAdicionar = new JButton("Adicionar novo resumo");
    JButton botaoSalvar = new JButton("Salvar");
    JButton botaoVoltar = new JButton("Voltar");

    Runnable adicionarCampos = () -> {
        JTextField campoTitulo = new JTextField(20);
        JTextField campoEvento = new JTextField(20);

        JPanel linha1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        linha1.add(new JLabel("Título do resumo:"));
        linha1.add(campoTitulo);

        JPanel linha2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        linha2.add(new JLabel("Evento:"));
        linha2.add(campoEvento);

        painelCampos.add(linha1);
        painelCampos.add(linha2);
        painelCampos.add(Box.createRigidArea(new Dimension(0, 10)));

        listaDeCampos.add(new JTextField[] { campoTitulo, campoEvento });

        painelCampos.revalidate();
        painelCampos.repaint();
    };
    adicionarCampos.run();
    botaoAdicionar.addActionListener(e -> adicionarCampos.run());

    botaoSalvar.addActionListener(e -> {
        for (JTextField[] campos : listaDeCampos) {
            String titulo = campos[0].getText();
            String evento = campos[1].getText();
            if (!titulo.isEmpty() && !evento.isEmpty()) {
                //historico.cadastrarResumoExpandido(titulo, evento,0);
            }
        }
        frame.dispose();
        // Aqui pode finalizar o fluxo ou voltar para a tela inicial
        telaCadastro8(discente, historico);
    });

    botaoVoltar.addActionListener(e -> {
        frame.dispose();
        telaCadastro6(discente, historico);
    });

    JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
    painelBotoes.add(botaoAdicionar);
    painelBotoes.add(botaoSalvar);
    painelBotoes.add(botaoVoltar);

    JPanel painelPrincipal = new JPanel();
    painelPrincipal.setLayout(new BorderLayout());
    painelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
    painelPrincipal.add(new JScrollPane(painelCampos), BorderLayout.CENTER);
    painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

    frame.setContentPane(painelPrincipal);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
}



  static void telaCadastro8(Discente discente, HistoricoComputacao historico) {
    JFrame frame = new JFrame("Situação do ENADE");
    JLabel label = new JLabel("Selecione sua situação no ENADE:");

    JButton botaoVoltar = new JButton("Voltar");
    botaoVoltar.addActionListener(e -> {
        frame.dispose();
        telaCadastro7(discente, historico);
    });

    String[] opcoes = {"Regular", "Irregular"};
    JComboBox<String> comboSituacao = new JComboBox<>(opcoes);

    JButton botaoSalvar = new JButton("Salvar");

    Action salvarAcao = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String situacaoSelecionada = (String) comboSituacao.getSelectedItem();
            if (situacaoSelecionada != null && !situacaoSelecionada.isEmpty()) {
                boolean enadeRegular = situacaoSelecionada.equals("Regular");

                //historico.cadastrarENADE(enadeRegular); // <-- agora passa boolean
                CadastroArq.salvarDiscenteArq(discente);
            }
            frame.dispose();
            tela1(); // próxima tela
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
        Discente discente = new Discente("2310100999", "Discentes");
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
            telaCadastro2(discente, discente.getHistorico()); // Altere conforme necessário
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
