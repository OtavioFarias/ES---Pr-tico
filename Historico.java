import java.util.List;

public class Historico {
    private int semestreAtual;
    private PlanoDeFormacao planoDeFormacao;
    private List<Cadeira> cadeirasConcluidas;
	public Historico(int semestreAtual, PlanoDeFormacao planoDeFormacao, List<Cadeira> cadeirasConcluidas) {
		super();
		this.semestreAtual = semestreAtual;
		this.planoDeFormacao = planoDeFormacao;
		this.cadeirasConcluidas = cadeirasConcluidas;
	}
	@Override
	public String toString() {
		return "Historico [semestreAtual=" + semestreAtual + ", planoDeFormacao=" + planoDeFormacao
				+ ", cadeirasConcluidas=" + cadeirasConcluidas + ", getSemestreAtual()=" + getSemestreAtual()
				+ ", getPlanoDeFormacao()=" + getPlanoDeFormacao() + ", getCadeirasConcluidas()="
				+ getCadeirasConcluidas() + "]";
	}
	public int getSemestreAtual() {
		return semestreAtual;
	}
	public void setSemestreAtual(int semestreAtual) {
		this.semestreAtual = semestreAtual;
	}
	public PlanoDeFormacao getPlanoDeFormacao() {
		return planoDeFormacao;
	}
	public void setPlanoDeFormacao(PlanoDeFormacao planoDeFormacao) {
		this.planoDeFormacao = planoDeFormacao;
	}
	public List<Cadeira> getCadeirasConcluidas() {
		return cadeirasConcluidas;
	}
	public void setCadeirasConcluidas(List<Cadeira> cadeirasConcluidas) {
		this.cadeirasConcluidas = cadeirasConcluidas;
	}
}
