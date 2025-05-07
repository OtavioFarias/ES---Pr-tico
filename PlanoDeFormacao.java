import java.util.List;

public class PlanoDeFormacao {
    private String nome;
    private int creditosTotais;
    private int cadeirasTotais;

    private BlocoDisciplinas blocoDisciplinas;
    private BlocoEstagio blocoEstagio;
    private BlocoProjetoFinal blocoProjetoFinal;
    private BlocoServicoComunitario blocoServicoComunitario;
    private BlocoPraticasExtensionistas blocoPraticasExtensionistas;
    private BlocoAtividadesComplementares blocoAtividadesComplementares;
    private BlocoPublicacoes blocoPublicacoes;
    private SituacaoENADE situacaoENADE;
	public PlanoDeFormacao(String nome, int creditosTotais, int cadeirasTotais, BlocoDisciplinas blocoDisciplinas,
			BlocoEstagio blocoEstagio, BlocoProjetoFinal blocoProjetoFinal,
			BlocoServicoComunitario blocoServicoComunitario, BlocoPraticasExtensionistas blocoPraticasExtensionistas,
			BlocoAtividadesComplementares blocoAtividadesComplementares, BlocoPublicacoes blocoPublicacoes,
			SituacaoENADE situacaoENADE) {
		super();
		this.nome = nome;
		this.creditosTotais = creditosTotais;
		this.cadeirasTotais = cadeirasTotais;
		this.blocoDisciplinas = blocoDisciplinas;
		this.blocoEstagio = blocoEstagio;
		this.blocoProjetoFinal = blocoProjetoFinal;
		this.blocoServicoComunitario = blocoServicoComunitario;
		this.blocoPraticasExtensionistas = blocoPraticasExtensionistas;
		this.blocoAtividadesComplementares = blocoAtividadesComplementares;
		this.blocoPublicacoes = blocoPublicacoes;
		this.situacaoENADE = situacaoENADE;
	}
	@Override
	public String toString() {
		return "PlanoDeFormacao [nome=" + nome + ", creditosTotais=" + creditosTotais + ", cadeirasTotais="
				+ cadeirasTotais + ", blocoDisciplinas=" + blocoDisciplinas + ", blocoEstagio=" + blocoEstagio
				+ ", blocoProjetoFinal=" + blocoProjetoFinal + ", blocoServicoComunitario=" + blocoServicoComunitario
				+ ", blocoPraticasExtensionistas=" + blocoPraticasExtensionistas + ", blocoAtividadesComplementares="
				+ blocoAtividadesComplementares + ", blocoPublicacoes=" + blocoPublicacoes + ", situacaoENADE="
				+ situacaoENADE + ", getNome()=" + getNome() + ", getCreditosTotais()=" + getCreditosTotais()
				+ ", getCadeirasTotais()=" + getCadeirasTotais() + ", getBlocoDisciplinas()=" + getBlocoDisciplinas()
				+ ", getBlocoEstagio()=" + getBlocoEstagio() + ", getBlocoProjetoFinal()=" + getBlocoProjetoFinal()
				+ ", getBlocoServicoComunitario()=" + getBlocoServicoComunitario()
				+ ", getBlocoPraticasExtensionistas()=" + getBlocoPraticasExtensionistas()
				+ ", getBlocoAtividadesComplementares()=" + getBlocoAtividadesComplementares()
				+ ", getBlocoPublicacoes()=" + getBlocoPublicacoes() + ", getSituacaoENADE()=" + getSituacaoENADE()
				+ "]";
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getCreditosTotais() {
		return creditosTotais;
	}
	public void setCreditosTotais(int creditosTotais) {
		this.creditosTotais = creditosTotais;
	}
	public int getCadeirasTotais() {
		return cadeirasTotais;
	}
	public void setCadeirasTotais(int cadeirasTotais) {
		this.cadeirasTotais = cadeirasTotais;
	}
	public BlocoDisciplinas getBlocoDisciplinas() {
		return blocoDisciplinas;
	}
	public void setBlocoDisciplinas(BlocoDisciplinas blocoDisciplinas) {
		this.blocoDisciplinas = blocoDisciplinas;
	}
	public BlocoEstagio getBlocoEstagio() {
		return blocoEstagio;
	}
	public void setBlocoEstagio(BlocoEstagio blocoEstagio) {
		this.blocoEstagio = blocoEstagio;
	}
	public BlocoProjetoFinal getBlocoProjetoFinal() {
		return blocoProjetoFinal;
	}
	public void setBlocoProjetoFinal(BlocoProjetoFinal blocoProjetoFinal) {
		this.blocoProjetoFinal = blocoProjetoFinal;
	}
	public BlocoServicoComunitario getBlocoServicoComunitario() {
		return blocoServicoComunitario;
	}
	public void setBlocoServicoComunitario(BlocoServicoComunitario blocoServicoComunitario) {
		this.blocoServicoComunitario = blocoServicoComunitario;
	}
	public BlocoPraticasExtensionistas getBlocoPraticasExtensionistas() {
		return blocoPraticasExtensionistas;
	}
	public void setBlocoPraticasExtensionistas(BlocoPraticasExtensionistas blocoPraticasExtensionistas) {
		this.blocoPraticasExtensionistas = blocoPraticasExtensionistas;
	}
	public BlocoAtividadesComplementares getBlocoAtividadesComplementares() {
		return blocoAtividadesComplementares;
	}
	public void setBlocoAtividadesComplementares(BlocoAtividadesComplementares blocoAtividadesComplementares) {
		this.blocoAtividadesComplementares = blocoAtividadesComplementares;
	}
	public BlocoPublicacoes getBlocoPublicacoes() {
		return blocoPublicacoes;
	}
	public void setBlocoPublicacoes(BlocoPublicacoes blocoPublicacoes) {
		this.blocoPublicacoes = blocoPublicacoes;
	}
	public SituacaoENADE getSituacaoENADE() {
		return situacaoENADE;
	}
	public void setSituacaoENADE(SituacaoENADE situacaoENADE) {
		this.situacaoENADE = situacaoENADE;
	}
}
