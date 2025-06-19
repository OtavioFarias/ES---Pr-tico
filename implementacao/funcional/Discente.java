package classes;
import classes.Historico;

public class Discente {
    private String nome;
    private String matricula;
    private HistoricoComputacao historico = new HistoricoComputacao();

    public Discente(String nome, String matricula) {
		this.nome = nome;
		this.matricula = matricula;
		this.historico = new HistoricoComputacao();
	}

	public Discente(String nome, String matricula, HistoricoComputacao historico) {
		this.nome = nome;
		this.matricula = matricula;
		this.historico = historico;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public HistoricoComputacao getHistorico(){

		return historico;

	}

}
