package tools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import classes.*;

public class HistoricoModelo {

    public final int QuantidadeComponenteCurricularObrigatorio = 231;
    public final int QuantidadeComponentesCurricularesComplementares = 12;
    public final int QuantidadeUnipampaCidada = 4;
    public final int QuantidadeEstagioObrigatorio = 12;
    public final int QuantidadePraticasExtensionistas = 20;
    public final int QuantidadeAtividadesComplementares = 75;
    public final int QuantidadeResumoExpandido = 2;
    public final int QuantidadeArtigoCientifico = 1;

    private List<ComponenteCurricularObrigatorio> componentesCurricularesObrigatorios = new ArrayList<>();
    private List<ComponenteCurricularNaoObrigatorio> componentesCurricularesNaoObrigatorios = new ArrayList<>();

    public void cadastrarComponenteCurricularNaoObrigatorio(int id) {
        componentesCurricularesNaoObrigatorios.add(new ComponenteCurricularNaoObrigatorio(id));
    }

    public void cadastrarComponenteCurricularObrigatorio(int id) {
        componentesCurricularesObrigatorios.add(new ComponenteCurricularObrigatorio(id));
    }

    public List<ComponenteCurricularObrigatorio> getComponentesCurricularesObrigatorios() {
        return componentesCurricularesObrigatorios;
    }

    public List<ComponenteCurricularNaoObrigatorio> getComponentesCurricularesNaoObrigatorios() {
        return componentesCurricularesNaoObrigatorios;
    }

    // Método para ler o CSV e carregar os componentes obrigatórios
    public void carregarComponentesObrigatoriosDeCSV(String caminhoCSV) {
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoCSV))) {
            String linha;
            boolean primeiraLinha = true;

            while ((linha = br.readLine()) != null) {
                if (primeiraLinha) {
                    primeiraLinha = false;
                    continue; // pula o cabeçalho
                }

                String[] campos = linha.split(",");

                if (campos.length < 7) continue; // linha inválida

                int id = Integer.parseInt(campos[0].trim());
                String nome = campos[1].trim();

                int creditos1 = Integer.parseInt(campos[2].trim());
                int creditos2 = Integer.parseInt(campos[3].trim());
                int creditos3 = Integer.parseInt(campos[4].trim());
                int creditos = creditos1 + creditos2 + creditos3;

								String prereqStr = campos[6].trim();
								List<Integer> prerequisitos = new ArrayList<>();

								if (!prereqStr.isEmpty()) {
										String[] ids = prereqStr.split(",");
										for (String idStr : ids) {
												prerequisitos.add(Integer.parseInt(idStr.trim()));
										}
								}


                // Criar o componente — ajuste os parâmetros conforme sua classe
                ComponenteCurricularObrigatorio componente = new ComponenteCurricularObrigatorio(
                    id,
                    nome,
                    creditos,
                    prerequisitos
                );

                componentesCurricularesObrigatorios.add(componente);
            }

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo CSV: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Erro ao converter número no CSV: " + e.getMessage());
        }
    }
}
