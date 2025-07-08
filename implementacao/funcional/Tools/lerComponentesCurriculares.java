package classes.tools;

import java.io.*;
import java.util.*;

public class lerComponentesCurriculares {

    // Vetores (ArrayLists) para armazenar cada coluna
    static List<Integer> codigos = new ArrayList<>();
    static List<String> componentes = new ArrayList<>();
    static List<Integer> ct = new ArrayList<>();
    static List<Integer> cp = new ArrayList<>();
    static List<Integer> eadPratica = new ArrayList<>();
    static List<Integer> ext = new ArrayList<>();
    static List<String> preRequisitos = new ArrayList<>();

    public lerComponentesCurriculares(String arq) {
        String caminhoArquivo = arq + ".csv"; // Caminho do seu arquivo CSV

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            boolean primeiraLinha = true;

            while ((linha = br.readLine()) != null) {
                // Ignorar o cabeçalho
                if (primeiraLinha) {
                    primeiraLinha = false;
                    continue;
                }

                // Divisão de campos tratando aspas e vírgulas
                List<String> campos = parseCSVLine(linha);

                // Adiciona os dados em suas respectivas listas
                codigos.add(Integer.parseInt(campos.get(0).trim()));
                componentes.add(campos.get(1).trim());
                ct.add(Integer.parseInt(campos.get(2).trim()));
                cp.add(Integer.parseInt(campos.get(3).trim()));
                eadPratica.add(Integer.parseInt(campos.get(4).trim()));
                ext.add(Integer.parseInt(campos.get(5).trim()));
                preRequisitos.add(campos.get(6).trim());
            }

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    // Método para tratar campos CSV com vírgulas dentro de aspas
    private static List<String> parseCSVLine(String linha) {
        List<String> campos = new ArrayList<>();
        StringBuilder campo = new StringBuilder();
        boolean dentroDeAspas = false;

        for (int i = 0; i < linha.length(); i++) {
            char c = linha.charAt(i);
            if (c == '"') {
                dentroDeAspas = !dentroDeAspas;
            } else if (c == ',' && !dentroDeAspas) {
                campos.add(campo.toString());
                campo.setLength(0); // limpa o StringBuilder
            } else {
                campo.append(c);
            }
        }
        campos.add(campo.toString()); // último campo
        return campos;
    }

    public List<String> getComponentes(){
    		return componentes;
    }

    public List<String> getPreRequisitos(){
    		return preRequisitos;
    }
}
