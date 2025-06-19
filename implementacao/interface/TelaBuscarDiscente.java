package interfaceGrafica;

import classes.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;

public class TelaBuscarDiscente {

    public void show(Consumer<Discente> callback) {
        JFrame frame = new JFrame("Verificar Discente");
        JTextField campoMatricula = new JTextField(10);
        JButton botao = new JButton("Verificar");
        JPanel painel = new JPanel();
        painel.add(new JLabel("Digite a matrícula:"));
        painel.add(campoMatricula);
        painel.add(botao);

        // Ação de verificação
        Action verificarAcao = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String matricula = campoMatricula.getText();
                if (matricula.length() != 10 || !matricula.matches("\\d+")) {
                    JOptionPane.showMessageDialog(frame, "Matrícula inválida. Digite 10 números.");
                    return;
                }

                // Importa discente e mostra informação (ou trate erro aqui)
                Discente discente = ArqDiscente.importarDiscenteDeCSV(matricula);
                if (discente != null) {
                    JOptionPane.showMessageDialog(frame, "Discente encontrado:\n" + discente);
                    callback.accept(discente); // Envia o discente para quem chamou
                } else {
                    JOptionPane.showMessageDialog(frame, "Discente não encontrado.");
                    callback.accept(null); // Também pode enviar null, se quiser tratar isso
                }

                frame.dispose(); // Fecha a tela atual
            }
        };

        botao.addActionListener(verificarAcao);
        campoMatricula.addActionListener(verificarAcao);
        frame.getRootPane().setDefaultButton(botao);

        frame.setContentPane(painel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(350, 100);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
