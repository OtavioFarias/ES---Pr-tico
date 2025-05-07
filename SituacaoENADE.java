public class SituacaoENADE {
    private boolean regular;

	public SituacaoENADE(boolean regular) {
		super();
		this.regular = regular;
	}

	@Override
	public String toString() {
		return "SituacaoENADE [regular=" + regular + ", isRegular()=" + isRegular() + "]";
	}

	public boolean isRegular() {
		return regular;
	}

	public void setRegular(boolean regular) {
		this.regular = regular;
	}
}
