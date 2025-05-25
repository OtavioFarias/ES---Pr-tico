package classes;

import java.io.*;
import java.util.*;
import classes.*;
public class CadastroArq{

    public static Discente cadastrarDiscenteArq(String filePath) {
        Discente discenteObj = null;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String header = br.readLine(); // Ignora cabeçalho
            String line;

            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");

                String nomeDiscente = columns[0];
                discenteObj = new Discente(nomeDiscente, "MATRICULA_EXEMPLO"); // Substitua conforme necessário
                Historico historico = discenteObj.getHistorico();

                for (int i = 1; i < columns.length; ) {
                    String tipo = columns[i].trim();

                    switch (tipo) {
                        case "ArtigoCientifico":
                            String artigoTitulo = columns[i + 1];
                            String artigoVeiculo = columns[i + 2];
                            historico.cadastrarArtigo(artigoTitulo, artigoVeiculo);
                            i += 3;
                            break;

                        case "AtividadeComplementar":
                            String atividadeDescricao = columns[i + 1];
                            int atividadeHoras = Integer.parseInt(columns[i + 2]);
                            historico.cadastrarAtividadeComplementar(atividadeDescricao, atividadeHoras);
                            i += 3;
                            break;

                        case "cadeiraObrigatoria":
                            int idOb = Integer.parseInt(columns[i + 1]);
                            historico.cadastrarCadeiraObrigatoria(idOb);
                            i += 2;
                            break;

                        case "cadeiraOpcional":
                            int idOp = Integer.parseInt(columns[i + 1]);
                            historico.cadastrarCadeiraOpcional(idOp);
                            i += 2;
                            break;

                        case "estagio":
                            String empresa = columns[i + 1];
                            int horasEstagio = Integer.parseInt(columns[i + 2]);
                            String tipoEstagio = columns[i + 3];
                            historico.cadastrarEstagio(empresa, horasEstagio, tipoEstagio);
                            i += 4;
                            break;

                        case "PraticaExtensionista":
                            String projeto = columns[i + 1];
                            int creditosPratica = Integer.parseInt(columns[i + 2]);
                            historico.cadastrarPraticaExtensionista(projeto, creditosPratica);
                            i += 3;
                            break;

                        case "ResumoExpandido":
                            String resumoTitulo = columns[i + 1];
                            String evento = columns[i + 2];
                            int semestreResumo = Integer.parseInt(columns[i + 3]);
                            historico.cadastrarResumoExpandido(resumoTitulo, evento, semestreResumo);
                            i += 4;
                            break;

                        case "servicoComunitario":
                            String atividadeServ = columns[i + 1];
                            int horasServ = Integer.parseInt(columns[i + 2]);
                            historico.cadastrarServicoComunitario(atividadeServ, horasServ);
                            i += 3;
                            break;

                        case "SituacaoENADE":
                            boolean regular = Boolean.parseBoolean(columns[i + 1]);
                            historico.cadastrarENADE(regular);
                            i += 2;
                            break;

                        default:
                            System.out.println("Tipo desconhecido: " + tipo);
                            i++;
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return discenteObj;
    }


    public static void salvarDiscenteArq(Discente discente) {
        String fileName = discente.getMatricula() + ".csv";
        Historico historico = discente.getHistorico();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            // Primeira coluna com nome
            bw.write(discente.getNome());

            // Escreve todos os artigos
            for (ArtigoCientifico a : historico.getArtigo()) {
                bw.write(",ArtigoCientifico," + a.getTitulo() + "," + a.getVeiculo() + "," + a.getSemestre());
            }

            // Atividades complementares
            for (AtividadeComplementar ac : historico.getAtividadesComplementares()) {
                bw.write(",AtividadeComplementar," + ac.getDescricao() + "," + ac.getHoras());
            }

            // Cadeiras obrigatórias
            for (Cadeira ob : historico.getCadeirasObrigatorias()) {
                bw.write(",cadeiraObrigatoria," +
                        ob.getId() + "," +
                        ob.getNome() + "," +
                        ob.getCreditos() + "," +
                        listToString(ob.getPrerequisitos()) + "," +
                        listToString(ob.getCorequisitos()) + "," +
                        ob.getCargaHoraria() + "," +
                        ob.getSemestre());
            }

            // Cadeiras opcionais
            for (Cadeira op : historico.getCadeirasOpcionais()) {
                bw.write(",cadeiraOpcional," +
                        op.getId() + "," +
                        op.getNome() + "," +
                        op.getCreditos() + "," +
                        listToString(op.getPrerequisitos()) + "," +
                        listToString(op.getCorequisitos()) + "," +
                        op.getCargaHoraria() + "," +
                        op.getSemestre());
            }

            // Estágios
            for (Estagio e : historico.getEstagio()) {
                bw.write(",estagio," + e.getEmpresa() + "," + e.getHoras() + "," + e.getTipo());
            }

            // Práticas Extensionistas
            for (PraticaExtensionista p : historico.getPraticasExtensionistas()) {
                bw.write(",PraticaExtensionista," + p.getProjeto() + "," + p.getCreditos());
            }

            // Resumos
            for (ResumoExpandido r : historico.getResumos()) {
                bw.write(",ResumoExpandido," + r.getTitulo() + "," + r.getEvento() + "," + r.getSemestre());
            }

            // Serviços comunitários
            for (ServicoComunitario s : historico.getServicoComunitario()) {
                bw.write(",servicoComunitario," + s.getAtividade() + "," + s.getHoras());
            }

            // Situação ENADE

            bw.write(",SituacaoENADE," + historico.getSituacaoENADE());


            bw.newLine();
            System.out.println("Arquivo " + fileName + " salvo com sucesso!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String listToString(List<Integer> list) {
        if (list == null || list.isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i < list.size() - 1) sb.append(";");
        }
        sb.append("]");
        return sb.toString();
    }

    private static List<Integer> parseList(String str) {
        List<Integer> result = new ArrayList<>();
        if (str == null || str.length() < 2 || str.equals("[]")) return result;

        // Remove colchetes e divide por ';'
        str = str.substring(1, str.length() - 1);
        String[] parts = str.split(";");
        for (String part : parts) {
            try {
                result.add(Integer.parseInt(part.trim()));
            } catch (NumberFormatException e) {
                // Ignora valores não numéricos
            }
        }
        return result;
    }

}



