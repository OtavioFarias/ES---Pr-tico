package classes;

public class AtividadeComplementar implements AtividadeCurricular{
    public AtividadeComplementar(String descricao, int horas) {
		super();
		this.descricao = descricao;
		this.horas = horas;
	}
	@Override
	public String toString() {
		return "AtividadeComplementar [descricao=" + descricao + ", horas=" + horas + "]";
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getHoras() {
		return horas;
	}
	public void setHoras(int horas) {
		this.horas = horas;
	}
	private String descricao;
    private int horas;
}
