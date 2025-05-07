public class Discente {
    private String nome;
    private int matricula;
    public Discente(String nome, int matricula, Historico historico) {
		super();
		this.nome = nome;
		this.matricula = matricula;
		this.historico = historico;
	}
	@Override
	public String toString() {
		return "Discente [nome=" + nome + ", matricula=" + matricula + ", historico=" + historico + ", getNome()="
				+ getNome() + ", getMatricula()=" + getMatricula() + ", getHistorico()=" + getHistorico() + "]";
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getMatricula() {
		return matricula;
	}
	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}
	public Historico getHistorico() {
		return historico;
	}
	public void setHistorico(Historico historico) {
		this.historico = historico;
	}
	private Historico historico;
}
