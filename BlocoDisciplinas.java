import java.util.List;

public class BlocoDisciplinas {
    private List<Cadeira> obrigatorias;
    public BlocoDisciplinas(List<Cadeira> obrigatorias, List<Cadeira> optativas, int creditosTotais) {
		super();
		this.obrigatorias = obrigatorias;
		this.optativas = optativas;
		this.creditosTotais = creditosTotais;
	}
	@Override
	public String toString() {
		return "BlocoDisciplinas [obrigatorias=" + obrigatorias + ", optativas=" + optativas + ", creditosTotais="
				+ creditosTotais + ", getObrigatorias()=" + getObrigatorias() + ", getOptativas()=" + getOptativas()
				+ ", getCreditosTotais()=" + getCreditosTotais() + "]";
	}
	public List<Cadeira> getObrigatorias() {
		return obrigatorias;
	}
	public void setObrigatorias(List<Cadeira> obrigatorias) {
		this.obrigatorias = obrigatorias;
	}
	public List<Cadeira> getOptativas() {
		return optativas;
	}
	public void setOptativas(List<Cadeira> optativas) {
		this.optativas = optativas;
	}
	public int getCreditosTotais() {
		return creditosTotais;
		
	}
	public void setCreditosTotais(int creditosTotais) {
		this.creditosTotais = creditosTotais;
	}
	private List<Cadeira> optativas;
    private int creditosTotais;
}
