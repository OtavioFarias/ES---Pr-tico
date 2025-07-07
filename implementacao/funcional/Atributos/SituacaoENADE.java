package classes.atributos;

public class SituacaoENADE {
    private boolean regular;

	public SituacaoENADE(boolean regular) {
		super();
		this.regular = regular;
	}

	@Override
	public String toString() {
		return "SituacaoENADE \nregular=" + regular + "\n";
	}

	public boolean getRegular() {
		return regular;
	}

	public void setRegular(boolean regular) {
		this.regular = regular;
	}
}
