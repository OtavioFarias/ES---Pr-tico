package interfaceGrafica;

import classes.*;
import interfaceGrafica.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;

public class TelaCadastrarDiscente {

    private JFrame frame;
    private Discente discente;

    public void show(Consumer<Discente> onFinish) {
        frame = new JFrame("Cadastro de Discente");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(4, 1, 10, 10));

        JTextField campoNome = new JTextField();
        JTextField campoMatricula = new JTextField();
        JButton botaoContinuar = new JButton("Continuar");

        frame.add(new JLabel("Nome completo:"));
        frame.add(campoNome);
        frame.add(new JLabel("Matrícula (10 dígitos):"));
        frame.add(campoMatricula);
        frame.add(botaoContinuar);

        botaoContinuar.addActionListener((ActionEvent e) -> {
            String nome = campoNome.getText().trim();
            String matricula = campoMatricula.getText().trim();

            if (nome.isEmpty() || !matricula.matches("\\d{10}")) {
                JOptionPane.showMessageDialog(frame, "Preencha corretamente os dados.");
                return;
            }

            discente = new Discente(nome, matricula);
            frame.dispose();

            // Primeiro mostra o menu de cadastro
            mostrarMenuCadastro();

            // Depois chama a função de callback
            if (onFinish != null) {
                onFinish.accept(discente);
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    private void mostrarMenuCadastro() {
        JFrame menuFrame = new JFrame("Cadastrar Informações");
        menuFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        menuFrame.setSize(500, 400);
        menuFrame.setLayout(new GridLayout(12, 1, 5, 5));

        String[] opcoes = {
                "Cadastrar Artigo",
                "Cadastrar Atividade Complementar",
                "Cadastrar Cadeira Obrigatória",
                "Cadastrar Cadeira Opcional",
                "Cadastrar Estágio Obrigatório",
                "Cadastrar Estágio Não Obrigatório",
                "Cadastrar Prática Extensionista",
                "Cadastrar Resumo Expandido",
                "Cadastrar Serviço Comunitário",
                "Cadastrar ENADE"
        };

        for (int i = 0; i < opcoes.length; i++) {
            int opcao = i + 1;
            JButton botao = new JButton(opcoes[i]);
            botao.addActionListener(e -> executarCadastro(opcao));
            menuFrame.add(botao);
        }

        JButton botaoVoltar = new JButton("Voltar ao Menu Inicial");
        botaoVoltar.addActionListener(e -> {
            ArqDiscente.exportarHistoricoParaCSV(discente);
            menuFrame.dispose();
            //new TelaInicial().show(); // Volta ao menu principal
        });

        menuFrame.add(botaoVoltar);
        menuFrame.setLocationRelativeTo(null);
        menuFrame.setVisible(true);
    }

    private void executarCadastro(int opcao) {
        switch (opcao) {
            case 1: // Artigo
                String titulo = JOptionPane.showInputDialog("Título do artigo:");
                String veiculo = JOptionPane.showInputDialog("Veículo de publicação:");
                discente.getHistorico().cadastrarArtigo(titulo, veiculo);
                break;

            case 2:
                String descAtiv = JOptionPane.showInputDialog("Descrição da atividade:");
                int horasAtv = Integer.parseInt(JOptionPane.showInputDialog("Horas:"));
                discente.getHistorico().cadastrarAtividadeComplementar(descAtiv, horasAtv);
                break;

            case 3:
                int idOb = Integer.parseInt(JOptionPane.showInputDialog("ID da cadeira obrigatória:"));
                discente.getHistorico().cadastrarCadeiraObrigatoria(idOb);
                break;

            case 4:
                int idOp = Integer.parseInt(JOptionPane.showInputDialog("ID da cadeira opcional:"));
                discente.getHistorico().cadastrarCadeiraOpcional(idOp);
                break;

            case 5:
                String empOb = JOptionPane.showInputDialog("Empresa:");
                int horasOb = Integer.parseInt(JOptionPane.showInputDialog("Horas:"));
                String tipoOb = JOptionPane.showInputDialog("Tipo:");
                discente.getHistorico().cadastrarEstagioObrigatorio(empOb, horasOb, tipoOb);
                break;

            case 6:
                String empNOb = JOptionPane.showInputDialog("Empresa:");
                int horasNOb = Integer.parseInt(JOptionPane.showInputDialog("Horas:"));
                String tipoNOb = JOptionPane.showInputDialog("Tipo:");
                discente.getHistorico().cadastrarEstagioNaoObrigatorio(empNOb, horasNOb, tipoNOb);
                break;

            case 7:
                String atividade = JOptionPane.showInputDialog("Atividade:");
                int horasPr = Integer.parseInt(JOptionPane.showInputDialog("Horas:"));
                discente.getHistorico().cadastrarPraticaExtensionista(atividade, horasPr);
                break;

            case 8:
                String projeto = JOptionPane.showInputDialog("Projeto:");
                String papel = JOptionPane.showInputDialog("Papel no projeto:");
                int creditos = Integer.parseInt(JOptionPane.showInputDialog("Créditos:"));
                discente.getHistorico().cadastrarResumoExpandido(projeto, papel, creditos);
                break;

            case 9:
                String descServ = JOptionPane.showInputDialog("Descrição do serviço:");
                int horasServ = Integer.parseInt(JOptionPane.showInputDialog("Horas:"));
                discente.getHistorico().cadastrarServicoComunitario(descServ, horasServ);
                break;

            case 10:
                int confirm = JOptionPane.showConfirmDialog(null, "Situação ENADE regular?");
                discente.getHistorico().cadastrarENADE(confirm == JOptionPane.YES_OPTION);
                break;

            default:
                JOptionPane.showMessageDialog(null, "Opção inválida.");
        }

        JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
    }


}

