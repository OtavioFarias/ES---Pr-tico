package interfaceGrafica;

import classes.*;
import interfaceGrafica.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaAcompanharCurso {

    public void show(Discente discente) {
        if (discente == null) {
            JOptionPane.showMessageDialog(null, "Nenhum discente carregado.");
            return;
        }

        HistoricoComputacao historico = discente.getHistorico();

        JFrame frame = new JFrame("Acompanhamento de Curso");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new GridLayout(12, 1, 5, 5));

        JButton[] botoes = {
            new JButton("Ver Artigos"),
            new JButton("Ver Atividades Complementares"),
            new JButton("Ver Cadeiras Obrigatórias"),
            new JButton("Ver Cadeiras Opcionais"),
            new JButton("Ver Estágios Obrigatórios"),
            new JButton("Ver Estágios Não Obrigatórios"),
            new JButton("Ver Práticas Extensionistas"),
            new JButton("Ver Resumos Expandidos"),
            new JButton("Ver Serviços Comunitários"),
            new JButton("Ver Situação ENADE")
        };

        for (int i = 0; i < botoes.length; i++) {
            int opcao = i + 1;
            botoes[i].addActionListener(e -> mostrarDados(historico, opcao));
            frame.add(botoes[i]);
        }

        JButton voltar = new JButton("Voltar ao Menu Inicial");
        voltar.addActionListener(e -> {
            frame.dispose();
            //new TelaInicial().show();
        });

        frame.add(voltar);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void mostrarDados(HistoricoComputacao historico, int opcao) {
        StringBuilder mensagem = new StringBuilder();
        switch (opcao) {
            case 1:
                List<ArtigoCientifico> artigos = historico.getArtigo();
                mensagem.append("Artigos:\n");
                artigos.forEach(a -> mensagem.append(a).append("\n"));
                break;
            case 2:
                List<AtividadeComplementar> atividades = historico.getAtividadesComplementares();
                mensagem.append("Atividades Complementares:\n");
                atividades.forEach(a -> mensagem.append(a).append("\n"));
                break;
            case 3:
                List<CadeiraObrigatoria> obrigatorias = historico.getCadeirasObrigatorias();
                mensagem.append("Cadeiras Obrigatórias:\n");
                obrigatorias.forEach(c -> mensagem.append(c).append("\n"));
                break;
            case 4:
                List<CadeiraOpcional> opcionais = historico.getCadeirasOpcionais();
                mensagem.append("Cadeiras Opcionais:\n");
                opcionais.forEach(c -> mensagem.append(c).append("\n"));
                break;
            case 5:
                List<Estagio_Obrigatorio> estagiosOb = historico.getEstagioObrigatorio();
                mensagem.append("Estágios Obrigatórios:\n");
                estagiosOb.forEach(e -> mensagem.append(e).append("\n"));
                break;
            case 6:
                List<Estagio_Nao_Obrigatorio> estagiosNaoOb = historico.getEstagioNaoObrigatorio();
                mensagem.append("Estágios Não Obrigatórios:\n");
                estagiosNaoOb.forEach(e -> mensagem.append(e).append("\n"));
                break;
            case 7:
                List<PraticaExtensionista> praticas = historico.getPraticasExtensionistas();
                mensagem.append("Práticas Extensionistas:\n");
                praticas.forEach(p -> mensagem.append(p).append("\n"));
                break;
            case 8:
                List<ResumoExpandido> resumos = historico.getResumos();
                mensagem.append("Resumos Expandidos:\n");
                resumos.forEach(r -> mensagem.append(r).append("\n"));
                break;
            case 9:
                List<ServicoComunitario> servicos = historico.getServicoComunitario();
                mensagem.append("Serviços Comunitários:\n");
                servicos.forEach(s -> mensagem.append(s).append("\n"));
                break;
            case 10:
                boolean situacao = historico.getSituacaoENADE();
                mensagem.append("Situação ENADE: ").append(situacao ? "Regular" : "Irregular");
                break;
        }

        JTextArea textArea = new JTextArea(mensagem.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JOptionPane.showMessageDialog(null, scrollPane, "Dados", JOptionPane.INFORMATION_MESSAGE);
    }
}
