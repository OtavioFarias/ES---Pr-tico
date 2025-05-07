public class BlocoProjetoFinal {
    private boolean PFC1;
    private boolean PFC2;
    private int creditos;
	public BlocoProjetoFinal(boolean pFC1, boolean pFC2, int creditos) {
		super();
		PFC1 = pFC1;
		PFC2 = pFC2;
		this.creditos = creditos;
	}
	@Override
	public String toString() {
		return "BlocoProjetoFinal [PFC1=" + PFC1 + ", PFC2=" + PFC2 + ", creditos=" + creditos + ", isPFC1()="
				+ isPFC1() + ", isPFC2()=" + isPFC2() + ", getCreditos()=" + getCreditos() + "]";
	}
	public boolean isPFC1() {
		return PFC1;
	}
	public void setPFC1(boolean pFC1) {
		PFC1 = pFC1;
	}
	public boolean isPFC2() {
		return PFC2;
	}
	public void setPFC2(boolean pFC2) {
		PFC2 = pFC2;
	}
	public int getCreditos() {
		return creditos;
	}
	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}
}
