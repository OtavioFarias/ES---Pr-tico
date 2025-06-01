package classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import classes.*;

public class ArqDiscente {

    public static Discente importarDiscenteDeCSV(String matricula) {
        String caminho = "Discentes/" + matricula + ".csv";
        String nomeAluno = "";
        HistoricoComputacao historico = new HistoricoComputacao();

        try (BufferedReader reader = new BufferedReader(new FileReader(caminho))) {
            String linha;
            String tipoAtual = "";
            String detalhesAcumulados = "";

            while ((linha = reader.readLine()) != null) {
                // pula cabeçalho
                if (linha.startsWith("Nome do Aluno")) continue;

                String[] partes = linha.split(",", 3); // Nome, Tipo, Detalhes

                // Nome só precisa ser lido uma vez
                if (!partes[0].isBlank()) {
                    nomeAluno = partes[0];
                }

                if (!partes[1].isBlank()) {
                    tipoAtual = partes[1].trim();
                    detalhesAcumulados = partes.length >= 3 ? partes[2].trim() : "";
                } else {
                    detalhesAcumulados += "\n" + (partes.length >= 3 ? partes[2].trim() : "");
                }

                // Verifica se é o fim do bloco do item atual (próxima linha tem um tipo novo ou EOF)
                reader.mark(500); // marca posição no buffer
                String proxLinha = reader.readLine();
                if (proxLinha == null || (proxLinha.split(",").length > 1 && !proxLinha.split(",")[1].isBlank())) {
                    // Parse e cadastrar
                    switch (tipoAtual) {
                        case "Artigo":
                            String[] art = detalhesAcumulados.split("\n");
                            String titulo = art[0].replace("Título: ", "").trim();
                            String veiculo = art[1].replace("Veículo: ", "").trim();
                            historico.cadastrarArtigo(titulo, veiculo);
                            break;

                        case "Atividade Complementar":
                            String[] ac = detalhesAcumulados.split("\n");
                            String descAC = ac[0].replace("Descrição: ", "").trim();
                            int horasAC = Integer.parseInt(ac[1].replace("Horas: ", "").trim());
                            historico.cadastrarAtividadeComplementar(descAC, horasAC);
                            break;

                        case "Cadeira Obrigatória":
                            int idCO = Integer.parseInt(detalhesAcumulados.replace("ID: ", "").trim());
                            historico.cadastrarCadeiraObrigatoria(idCO);
                            break;

                        case "Cadeira Opcional":
                            int idOp = Integer.parseInt(detalhesAcumulados.replace("ID: ", "").trim());
                            historico.cadastrarCadeiraOpcional(idOp);
                            break;

                        case "Estágio Obrigatório":
                            String[] eo = detalhesAcumulados.split("\n");
                            String empEO = eo[0].replace("Empresa: ", "").trim();
                            String[] restanteEO = eo[1].replace("Horas: ", "").replace("Tipo: ", "").split(",");
                            int horasEO = Integer.parseInt(restanteEO[0].trim());
                            String tipoEO = restanteEO[1].trim();
                            historico.cadastrarEstagioObrigatorio(empEO, horasEO, tipoEO);
                            break;

                        case "Estágio Não Obrigatório":
                            String[] eno = detalhesAcumulados.split("\n");
                            String empEN = eno[0].replace("Empresa: ", "").trim();
                            String[] restanteEN = eno[1].replace("Horas: ", "").replace("Tipo: ", "").split(",");
                            int horasEN = Integer.parseInt(restanteEN[0].trim());
                            String tipoEN = restanteEN[1].trim();
                            historico.cadastrarEstagioNaoObrigatorio(empEN, horasEN, tipoEN);
                            break;

                        case "Prática Extensionista":
                            String[] pe = detalhesAcumulados.split("\n");
                            String atv = pe[0].replace("Atividade: ", "").trim();
                            int horasPE = Integer.parseInt(pe[1].replace("Horas: ", "").trim());
                            historico.cadastrarPraticaExtensionista(atv, horasPE);
                            break;

                        case "Resumo Expandido":
                            String[] re = detalhesAcumulados.split("\n");
                            String tituloR = re[0].replace("Título: ", "").trim();
                            String[] resto = re[1].split(",");
                            String evento = resto[0].replace("Evento: ", "").trim();
                            int semestre = Integer.parseInt(resto[1].replace("Semestre: ", "").trim());
                            historico.cadastrarResumoExpandido(tituloR, evento, semestre);
                            break;

                        case "Serviço Comunitário":
                            String[] sc = detalhesAcumulados.split("\n");
                            String descSC = sc[0].replace("Descrição: ", "").trim();
                            int horasSC = Integer.parseInt(sc[1].replace("Horas: ", "").trim());
                            historico.cadastrarServicoComunitario(descSC, horasSC);
                            break;

                        case "ENADE":
                            String sit = detalhesAcumulados.replace("Situação: ", "").trim();
                            historico.cadastrarENADE(sit.equalsIgnoreCase("Regular"));
                            break;
                    }

                    detalhesAcumulados = "";
                } else {
                    reader.reset(); // volta para linha marcada
                }
            }

        } catch (IOException e) {
            System.err.println("Erro ao importar CSV: " + e.getMessage());
            return null;
        }

        return new Discente(nomeAluno, matricula, historico);
    }

    public static void exportarHistoricoParaCSV(Discente discente) {
        String nomeArquivo = "Discentes/" + discente.getMatricula() + ".csv";

        try (FileWriter writer = new FileWriter(nomeArquivo)) {

            String nome = discente.getNome();
            HistoricoComputacao historico = discente.getHistorico();

            // Cabeçalho
            writer.write("Nome do Aluno,Tipo,Detalhes\n");

            // Artigos
            for (ArtigoCientifico artigo : historico.getArtigo()) {
                writer.write(String.format("%s,Artigo,Título: %s\n", nome, artigo.getTitulo()));
                writer.write(String.format(", ,Veículo: %s\n", artigo.getVeiculo()));
            }

            // Atividades Complementares
            for (AtividadeComplementar atv : historico.getAtividadesComplementares()) {
                writer.write(String.format("%s,Atividade Complementar,Descrição: %s\n", nome, atv.getDescricao()));
                writer.write(String.format(", ,Horas: %d\n", atv.getHoras()));
            }

            // Cadeiras Obrigatórias
            for (CadeiraObrigatoria co : historico.getCadeirasObrigatorias()) {
                writer.write(String.format("%s,Cadeira Obrigatória,ID: %d\n", nome, co.getId()));
            }

            // Cadeiras Opcionais
            for (CadeiraOpcional op : historico.getCadeirasOpcionais()) {
                writer.write(String.format("%s,Cadeira Opcional,ID: %d\n", nome, op.getId()));
            }

            // Estágios Obrigatórios
            for (Estagio_Obrigatorio eo : historico.getEstagioObrigatorio()) {
                writer.write(String.format("%s,Estágio Obrigatório,Empresa: %s\n", nome, eo.getEmpresa()));
                writer.write(String.format(", ,Horas: %d, Tipo: %s\n", eo.getHoras(), eo.getTipo()));
            }

            // Estágios Não Obrigatórios
            for (Estagio_Nao_Obrigatorio eno : historico.getEstagioNaoObrigatorio()) {
                writer.write(String.format("%s,Estágio Não Obrigatório,Empresa: %s\n", nome, eno.getEmpresa()));
                writer.write(String.format(", ,Horas: %d, Tipo: %s\n", eno.getHoras(), eno.getTipo()));
            }

            // Práticas Extensionistas
            for (PraticaExtensionista pe : historico.getPraticasExtensionistas()) {
                writer.write(String.format("%s,Prática Extensionista,Atividade: %s\n", nome, pe.getProjeto()));
                writer.write(String.format(", ,Horas: %d\n", pe.getCreditos()));
            }

            // Resumos Expandidos
            for (ResumoExpandido re : historico.getResumos()) {
                writer.write(String.format("%s,Resumo Expandido,Título: %s\n", nome, re.getTitulo()));
                writer.write(String.format(", ,Evento: %s, Semestre: %s\n", re.getEvento(), re.getSemestre()));
            }

            // Serviços Comunitários
            for (ServicoComunitario sc : historico.getServicoComunitario()) {
                writer.write(String.format("%s,Serviço Comunitário,Descrição: %s\n", nome, sc.getAtividade()));
                writer.write(String.format(", ,Horas: %d\n", sc.getHoras()));
            }

            // ENADE
            writer.write(String.format("%s,ENADE,Situação: %s\n",
                    nome,
                    historico.getSituacaoENADE() ? "Regular" : "Irregular"));

            writer.flush();
            System.out.println("Arquivo CSV salvo como: " + nomeArquivo);

        } catch (IOException e) {
            System.err.println("Erro ao salvar arquivo CSV: " + e.getMessage());
        }
    }
}
