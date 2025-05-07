import java.util.List;

public class BlocoPublicacoes {
    private List<ResumoExpandido> resumos;
    private List<ArtigoCientifico> artigos;
	public BlocoPublicacoes(List<ResumoExpandido> resumos, List<ArtigoCientifico> artigos) {
		super();
		this.resumos = resumos;
		this.artigos = artigos;
	}
	@Override
	public String toString() {
		return "BlocoPublicacoes [resumos=" + resumos + ", artigos=" + artigos + ", getResumos()=" + getResumos()
				+ ", getArtigos()=" + getArtigos() + "]";
	}
	public List<ResumoExpandido> getResumos() {
		return resumos;
	}
	public void setResumos(List<ResumoExpandido> resumos) {
		this.resumos = resumos;
	}
	public List<ArtigoCientifico> getArtigos() {
		return artigos;
	}
	public void setArtigos(List<ArtigoCientifico> artigos) {
		this.artigos = artigos;
	}
}
