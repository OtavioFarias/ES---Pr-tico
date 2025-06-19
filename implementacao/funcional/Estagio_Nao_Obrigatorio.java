package classes;
public class Estagio_Nao_Obrigatorio implements Estagio{
    private String empresa;
    private int horas;
    private String tipo;
	public Estagio_Nao_Obrigatorio(String empresa, int horas, String tipo) {
		super();
		this.empresa = empresa;
		this.horas = horas;
		this.tipo = tipo;
	}
	@Override
	public String toString() {
		return "Estagio [empresa=" + empresa + ", horas=" + horas + ", tipo=" + tipo + "]";
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public int getHoras() {
		return horas;
	}
	public void setHoras(int horas) {
		this.horas = horas;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
