public class PraticaExtensionista {
    private String projeto;
    private String papel;
    private int creditos;
	public PraticaExtensionista(String projeto, String papel, int creditos) {
		super();
		this.projeto = projeto;
		this.papel = papel;
		this.creditos = creditos;
	}
	@Override
	public String toString() {
		return "PraticaExtensionista [projeto=" + projeto + ", papel=" + papel + ", creditos=" + creditos
				+ ", getProjeto()=" + getProjeto() + ", getPapel()=" + getPapel() + ", getCreditos()=" + getCreditos()
				+ "]";
	}
	public String getProjeto() {
		return projeto;
	}
	public void setProjeto(String projeto) {
		this.projeto = projeto;
	}
	public String getPapel() {
		return papel;
	}
	public void setPapel(String papel) {
		this.papel = papel;
	}
	public int getCreditos() {
		return creditos;
	}
	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}
}
