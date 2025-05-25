package classes;

public class ArtigoCientifico {
    public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getVeiculo() {
		return veiculo;
	}
	public void setVeiculo(String veiculo) {
		this.veiculo = veiculo;
	}
	public int getSemestre() {
		return semestre;
	}
	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}
	public ArtigoCientifico(String titulo, String veiculo, int semestre) {
		super();
		this.titulo = titulo;
		this.veiculo = veiculo;
		this.semestre = semestre;
	}
	public ArtigoCientifico(String titulo, String veiculo) {
		super();
		this.titulo = titulo;
		this.veiculo = veiculo;
	}
	@Override
	public String toString() {
		return "ArtigoCientifico [titulo=" + titulo + ", veiculo=" + veiculo + ", semestre=" + semestre
				+ ", getTitulo()=" + getTitulo() + ", getVeiculo()=" + getVeiculo() + ", getSemestre()=" + getSemestre()
				+ "]";
	}
	private String titulo;
    private String veiculo;
    private int semestre;
}
