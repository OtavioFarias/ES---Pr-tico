package classes;
public class ResumoExpandido implements Publicacoes{
    private String titulo;
    private String evento;
    private int semestre;
	public ResumoExpandido(String titulo, String evento, int semestre) {
		super();
		this.titulo = titulo;
		this.evento = evento;
		this.semestre = semestre;
	}
	@Override
	public String toString() {
		return "ResumoExpandido [titulo=" + titulo + ", evento=" + evento + ", semestre=" + semestre + "]";
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getEvento() {
		return evento;
	}
	public void setEvento(String evento) {
		this.evento = evento;
	}
	public int getSemestre() {
		return semestre;
	}
	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}
}
