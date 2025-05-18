import java.io.*;
import java.util.*;

public class LerCursos {

    static Map<String, RequisitosCurso> carregarCursos(String caminho) {

        Map<String, RequisitosCurso> cursos = new LinkedHashMap<>();
        File pasta = new File(caminho);
        File[] arquivos = pasta.listFiles((dir, name) -> name.endsWith(".csv"));

        if (arquivos != null) {
            for (File arq : arquivos) {
                try (BufferedReader br = new BufferedReader(new FileReader(arq))) {
                    String linha;
                    while ((linha = br.readLine()) != null) {
                        String[] partes = linha.split(",");
                        if (partes.length >= 2) {
                            int codigo = Integer.parseInt(partes[0].trim());

                            int qtdDisciplinas = Integer.parseInt(partes[1].trim());

                            RequisitosCurso curso = new RequisitosCurso();
                            curso.setCodigo(codigo);
                            curso.setQtdDisciplinas(qtdDisciplinas);

                            // Usa o nome do arquivo como chave para exibição
                            String nomeExibicao = arq.getName().replace(".csv", "").replace("_", " ");
                            cursos.put(nomeExibicao, curso);
                        }
                    }
                } catch (Exception e) {
                    System.err.println("Erro lendo " + arq.getName() + ": " + e.getMessage());
                }
            }
        }

        return cursos;
    }
}
