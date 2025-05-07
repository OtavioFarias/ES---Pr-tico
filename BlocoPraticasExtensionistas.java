import java.util.List;

public class BlocoPraticasExtensionistas {
    private List<PraticaExtensionista> experiencias;
    private int creditos;
	public BlocoPraticasExtensionistas(List<PraticaExtensionista> experiencias, int creditos) {
		super();
		this.experiencias = experiencias;
		this.creditos = creditos;
	}
	@Override
	public String toString() {
		return "BlocoPraticasExtensionistas [experiencias=" + experiencias + ", creditos=" + creditos
				+ ", getExperiencias()=" + getExperiencias() + ", getCreditos()=" + getCreditos() + "]";
	}
	public List<PraticaExtensionista> getExperiencias() {
		return experiencias;
	}
	public void setExperiencias(List<PraticaExtensionista> experiencias) {
		this.experiencias = experiencias;
	}
	public int getCreditos() {
		return creditos;
	}
	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}
}
