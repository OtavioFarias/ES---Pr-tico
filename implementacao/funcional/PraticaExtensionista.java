package classes;
public class PraticaExtensionista implements AtividadeCurricular{
    private String projeto;
    private int creditos;
	public PraticaExtensionista(String projeto, String papel, int creditos) {
		super();
		this.projeto = projeto;
		this.creditos = creditos;
	}
	@Override
	public String toString() {
		return "PraticaExtensionista [projeto=" + projeto + ", creditos=" + creditos
				+ "]";
	}
	public String getProjeto() {
		return projeto;
	}
	public void setProjeto(String projeto) {
		this.projeto = projeto;
	}

	public int getCreditos() {
		return creditos;
	}
	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}
}
