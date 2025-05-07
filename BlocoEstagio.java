import java.util.List;

public class BlocoEstagio {
    private List<Estagio> estagios;
    @Override
	public String toString() {
		return "BlocoEstagio [estagios=" + estagios + ", horas=" + horas + ", creditos=" + creditos + ", getEstagios()="
				+ getEstagios() + ", getHoras()=" + getHoras() + ", getCreditos()=" + getCreditos() + "]";
	}
	public List<Estagio> getEstagios() {
		return estagios;
	}
	public void setEstagios(List<Estagio> estagios) {
		this.estagios = estagios;
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
	public BlocoEstagio(List<Estagio> estagios, int horas, int creditos) {
		super();
		this.estagios = estagios;
		this.horas = horas;
		this.creditos = creditos;
	}
	private int horas;
    private int creditos;
}
