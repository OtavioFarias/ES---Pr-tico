import java.util.List;

public class BlocoAtividadesComplementares {
    private List<AtividadeComplementar> atividades;
    private int horas;
	public List<AtividadeComplementar> getAtividades() {
		return atividades;
	}
	public void setAtividades(List<AtividadeComplementar> atividades) {
		this.atividades = atividades;
	}
	public int getHoras() {
		return horas;
	}
	public void setHoras(int horas) {
		this.horas = horas;
	}
	@Override
	public String toString() {
		return "BlocoAtividadesComplementares [atividades=" + atividades + ", horas=" + horas + ", getAtividades()="
				+ getAtividades() + ", getHoras()=" + getHoras() + "]";
	}
	public BlocoAtividadesComplementares(List<AtividadeComplementar> atividades, int horas) {
		super();
		this.atividades = atividades;
		this.horas = horas;
	}
}
