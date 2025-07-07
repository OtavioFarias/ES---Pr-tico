package classes.atributos;

public class PraticaExtensionista implements AtividadeCurricular{
    private String projeto;
    private int creditos;
	public PraticaExtensionista(String projeto, int creditos) {
		super();
		this.projeto = projeto;
		this.creditos = creditos;
	}
	@Override
	public String toString() {
		return "PraticaExtensionista \nprojeto=" + projeto + "\ncreditos=" + creditos + "\n";
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
