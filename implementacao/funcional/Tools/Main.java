package tools;
import java.util.Scanner;
import classes.*;
import java.util.List;
import java.util.ArrayList;


public class Main{
    public static void main(String[] args){

    Scanner scanner = new Scanner(System.in);

    Discente discenteAtual = null;

    int choose = 1;

        while(choose != 0){
            System.out.print("\n\n1-Buscar discente \n2-Cadastrar novo discente \n3-Acompanhar curso \n0-Sair\n\n");
            choose = scanner.nextInt();
            scanner.nextLine();
            switch(choose){
                case 1:

                    String matricula;

                    System.out.print("\nDigite a matrícula: \n");
                    matricula = scanner.nextLine();

                    discenteAtual = ArqDiscente.importarDiscenteDeCSV(matricula);

                    if(discenteAtual == null){

                        System.out.println("Discente não encontrado");
                        return;

                    }

                    System.out.println("Discente encontrado");

                break;


                case 2:

                    System.out.print("Digite seu nome completo: \n");
                    String nome = scanner.nextLine();
                    System.out.print("Digite sua matrícula:\n");
                    String matricula1 = scanner.nextLine();

                    Discente discente = new Discente(nome, matricula1);
                    HistoricoComputacao historico = discente.getHistorico();

                    int choose1 = 1;

                    while(choose1 != 0){

                    System.out.print(
                        "\n1 - Cadastrar Artigo\n" +
                        "2 - Cadastrar Atividade Complementar\n" +
                        "3 - Cadastrar Cadeira Obrigatória\n" +
                        "4 - Cadastrar Cadeira Opcional\n" +
                        "5 - Cadastrar Estágio Obrigatório\n" +
                        "6 - Cadastrar Estágio Não Obrigatório\n" +
                        "7 - Cadastrar Prática Extensionista\n" +
                        "8 - Cadastrar Resumo Expandido\n" +
                        "9 - Cadastrar Serviço Comunitário\n" +
                        "10 - Cadastrar ENADE\n" +
                        "0 - Voltar\n" +
                        "Escolha uma opção: "
                    );

                    choose1 = scanner.nextInt();
                    scanner.nextLine(); // Consumir quebra de linha

                    switch (choose1) {
                        case 1:
                            System.out.print("Título do artigo: ");
                            String titulo = scanner.nextLine();
                            System.out.print("Veículo de publicação: ");
                            String veiculo = scanner.nextLine();
                            historico.cadastrarArtigo(titulo, veiculo);
                            break;

                        case 2:
                            System.out.print("Descrição da atividade: ");
                            String descricaoAtv = scanner.nextLine();
                            System.out.print("Horas: ");
                            int horasAtv = scanner.nextInt();
                            scanner.nextLine();
                            historico.cadastrarAtividadeComplementar(descricaoAtv, horasAtv);
                            break;

                        case 3:
                            System.out.print("ID da cadeira obrigatória: ");
                            int idObrigatoria = scanner.nextInt();
                            scanner.nextLine();
                            historico.cadastrarCadeiraObrigatoria(idObrigatoria);
                            break;

                        case 4:
                            System.out.print("ID da cadeira opcional: ");
                            int idOpcional = scanner.nextInt();
                            scanner.nextLine();
                            historico.cadastrarCadeiraOpcional(idOpcional);
                            break;

                        case 5:
                            System.out.print("Empresa: ");
                            String empresaOb = scanner.nextLine();
                            System.out.print("Horas: ");
                            int horasOb = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Tipo: ");
                            String tipoOb = scanner.nextLine();
                            historico.cadastrarEstagioObrigatorio(empresaOb, horasOb, tipoOb);
                            break;

                        case 6:
                            System.out.print("Empresa: ");
                            String empresaNaoOb = scanner.nextLine();
                            System.out.print("Horas: ");
                            int horasNaoOb = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Tipo: ");
                            String tipoNaoOb = scanner.nextLine();
                            historico.cadastrarEstagioNaoObrigatorio(empresaNaoOb, horasNaoOb, tipoNaoOb);
                            break;

                        case 7:
                            System.out.print("Atividade: ");
                            String atividade = scanner.nextLine();
                            System.out.print("Horas: ");
                            int horasPratica = scanner.nextInt();
                            scanner.nextLine();
                            historico.cadastrarPraticaExtensionista(atividade, horasPratica);
                            break;

                        case 8:
                            System.out.print("Projeto: ");
                            String projeto = scanner.nextLine();
                            System.out.print("Papel no projeto: ");
                            String papel = scanner.nextLine();
                            System.out.print("Créditos: ");
                            int creditos = scanner.nextInt();
                            scanner.nextLine();
                            historico.cadastrarResumoExpandido(projeto, papel, creditos);
                            break;

                        case 9:
                            System.out.print("Descrição do serviço: ");
                            String descricaoServ = scanner.nextLine();
                            System.out.print("Horas: ");
                            int horasServ = scanner.nextInt();
                            scanner.nextLine();
                            historico.cadastrarServicoComunitario(descricaoServ, horasServ);
                            break;

                        case 10:
                            System.out.print("Situação ENADE? (true-Regular / false-Irregular): ");
                            boolean situacao = scanner.nextBoolean();
                            scanner.nextLine();
                            historico.cadastrarENADE(situacao);
                            break;

                        case 0:
                            System.out.println("Voltar");
                            break;

                        default:
                            System.out.println("Opção inválida. Tente novamente.");
                    }
                }

                discenteAtual = discente;
                ArqDiscente.exportarHistoricoParaCSV(discente);

                break;

                case 3:

                    if(discenteAtual == null){System.out.println("Sem discente carregado");return;}
                    int choose2 = 1;
                    while (choose2 != 0) {
                    System.out.print(
                        "\n1 - Ver Artigos\n" +
                        "2 - Ver Atividades Complementares\n" +
                        "3 - Ver Cadeiras Obrigatórias\n" +
                        "4 - Ver Cadeiras Opcionais\n" +
                        "5 - Ver Estágios Obrigatórios\n" +
                        "6 - Ver Estágios Não Obrigatórios\n" +
                        "7 - Ver Práticas Extensionistas\n" +
                        "8 - Ver Resumos Expandidos\n" +
                        "9 - Ver Serviços Comunitários\n" +
                        "10 - Ver Situação ENADE\n" +
                        "0 - Voltar\n" +
                        "Escolha uma opção: "
                    );

                    choose2 = scanner.nextInt();
                    scanner.nextLine();

                    HistoricoComputacao historico1 = discenteAtual.getHistorico();


                    switch (choose2) {
                        case 1:
                            System.out.println("\nArtigos cadastrados:");
                            for (ArtigoCientifico a : historico1.getArtigo()) {
                                System.out.println(a);
                            }
                            break;

                        case 2:
                            System.out.println("\nAtividades Complementares:");
                            for (AtividadeComplementar ac : historico1.getAtividadesComplementares()) {
                                System.out.println(ac);
                            }
                            break;

                        case 3:
                            System.out.println("\nCadeiras Obrigatórias:");
                            for (CadeiraObrigatoria co : historico1.getCadeirasObrigatorias()) {
                                System.out.println(co);
                            }
                            break;

                        case 4:
                            System.out.println("\nCadeiras Opcionais:");
                            for (CadeiraOpcional op : historico1.getCadeirasOpcionais()) {
                                System.out.println(op);
                            }
                            break;

                        case 5:
                            System.out.println("\nEstágios Obrigatórios:");
                            for (Estagio_Obrigatorio eo : historico1.getEstagioObrigatorio()) {
                                System.out.println(eo);
                            }
                            break;

                        case 6:
                            System.out.println("\nEstágios Não Obrigatórios:");
                            for (Estagio_Nao_Obrigatorio eno : historico1.getEstagioNaoObrigatorio()) {
                                System.out.println(eno);
                            }
                            break;

                        case 7:
                            System.out.println("\nPráticas Extensionistas:");
                            for (PraticaExtensionista p : historico1.getPraticasExtensionistas()) {
                                System.out.println(p);
                            }
                            break;

                        case 8:
                            System.out.println("\nResumos Expandidos:");
                            for (ResumoExpandido r : historico1.getResumos()) {
                                System.out.println(r);
                            }
                            break;

                        case 9:
                            System.out.println("\nServiços Comunitários:");
                            for (ServicoComunitario s : historico1.getServicoComunitario()) {
                                System.out.println(s);
                            }
                            break;

                        case 10:
                            System.out.println("\nSituação ENADE: " + (historico1.getSituacaoENADE() ? "Regular" : "Irregular"));
                            break;

                        case 0:
                            System.out.println("Saindo...");
                            break;

                        default:
                            System.out.println("Opção inválida. Tente novamente.");
                    }
                    }

                break;

                default: break;

            }
        }

    }

}
