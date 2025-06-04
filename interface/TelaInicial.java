package interfaceGrafica;

import classes.*;
import interfaceGrafica.*;
import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class TelaInicial {

    private Discente discente;

    public void show() {
        JFrame frame = new JFrame("Menu Inicial");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new GridLayout(4, 1, 10, 10)); // 3 botões + 1 sair

        JButton btnBuscar = new JButton("Buscar Discente");
        JButton btnCadastrar = new JButton("Cadastrar Novo Discente");
        JButton btnAcompanhar = new JButton("Acompanhar Curso");
        JButton btnSair = new JButton("Sair");

        // Buscar Discente com callback
        btnBuscar.addActionListener(e -> {
            new TelaBuscarDiscente().show(d -> {
                this.discente = d;
                JOptionPane.showMessageDialog(null, "Discente carregado: " + d.getNome());
                // Aqui você pode habilitar outros botões ou chamar outra tela
            });
        });

        // Cadastrar Discente com callback
        btnCadastrar.addActionListener(e -> {
            new TelaCadastrarDiscente().show(d -> {
                this.discente = d;
                JOptionPane.showMessageDialog(null, "Discente cadastrado: " + d.getNome());
                // Aqui também pode ir direto para outra ação se quiser
            });
        });

        // Acompanhar curso se houver discente
        btnAcompanhar.addActionListener(e -> {
            if (discente != null) {
                new TelaAcompanharCurso().show(discente);
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum discente carregado.");
            }
        });

        btnSair.addActionListener(e -> System.exit(0));

        frame.add(btnBuscar);
        frame.add(btnCadastrar);
        frame.add(btnAcompanhar);
        frame.add(btnSair);

        frame.setLocationRelativeTo(null); // Centraliza na tela
        frame.setVisible(true);
    }
}
