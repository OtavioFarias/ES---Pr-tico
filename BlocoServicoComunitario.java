import java.util.List;

public class BlocoServicoComunitario {
    public BlocoServicoComunitario(List<ServicoComunitario> atividades, int horas, int creditos) {
		super();
		this.atividades = atividades;
		this.horas = horas;
		this.creditos = creditos;
	}
	@Override
	public String toString() {
		return "BlocoServicoComunitario [atividades=" + atividades + ", horas=" + horas + ", creditos=" + creditos
				+ ", getAtividades()=" + getAtividades() + ", getHoras()=" + getHoras() + ", getCreditos()="
				+ getCreditos() + "]";
	}
	public List<ServicoComunitario> getAtividades() {
		return atividades;
	}
	public void setAtividades(List<ServicoComunitario> atividades) {
		this.atividades = atividades;
	}
	public int getHoras() {
		return horas;
	}
	public void setHoras(int horas) {
		this.horas = horas;
	}
	public int getCreditos() {
		return creditos;
	}
	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}
	private List<ServicoComunitario> atividades;
    private int horas;
    private int creditos;
}
