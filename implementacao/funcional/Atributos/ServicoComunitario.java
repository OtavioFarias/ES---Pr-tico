package classes.atributos;

public class ServicoComunitario implements AtividadeCurricular{
    public String getAtividade() {
		return atividade;
	}
	public void setAtividade(String atividade) {
		this.atividade = atividade;
	}
	public int getHoras() {
		return horas;
	}
	public void setHoras(int horas) {
		this.horas = horas;
	}
	public ServicoComunitario(String atividade, int horas) {
		super();
		this.atividade = atividade;
		this.horas = horas;
	}
	@Override
	public String toString() {
		return "ServicoComunitario \natividade=" + atividade + "\nhoras=" + horas + "\n";
	}
	private String atividade;
    private int horas;
}
