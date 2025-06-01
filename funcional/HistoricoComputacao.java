package classes;
import java.util.ArrayList;
import java.util.List;
import java.lang.Boolean;
import classes.*;

public class HistoricoComputacao implements Historico {

    private List<CadeiraObrigatoria> cadeirasObrigatorias = new ArrayList<>();
    private List<Estagio_Obrigatorio> estagioObrigatorio = new ArrayList<>();
    private List<Estagio_Nao_Obrigatorio> estagioNaoObrigatorio = new ArrayList<>();
    private List<CadeiraOpcional> cadeirasOpcionais = new ArrayList<>();
    private List<ServicoComunitario> servicoComunitario = new ArrayList<>();
	private List<PraticaExtensionista> praticasExtensionistas = new ArrayList<>();
    private List<AtividadeComplementar> atividadesComplementares = new ArrayList<>();
    private List<ArtigoCientifico> artigo = new ArrayList<>();
    private List<ResumoExpandido> resumos = new ArrayList<>();
    private boolean situacaoENADE;

	public HistoricoComputacao() {

		this.situacaoENADE = false;
	}

	public void cadastrarArtigo(String titulo, String veiculo){

        artigo.add(new ArtigoCientifico(titulo, veiculo));

    }

    public void cadastrarAtividadeComplementar(String descricao, int horas){

		atividadesComplementares.add(new AtividadeComplementar(descricao, horas));

    }

    public void cadastrarCadeiraObrigatoria(int id){

		cadeirasObrigatorias.add(new CadeiraObrigatoria(id));

    }

    public void cadastrarCadeiraOpcional(int id){

		cadeirasOpcionais.add(new CadeiraOpcional(id));

    }

    public void cadastrarEstagioObrigatorio(String empresa, int horas, String tipo){

		estagioObrigatorio.add(new Estagio_Obrigatorio(empresa, horas, tipo));

    }

    public void cadastrarEstagioNaoObrigatorio(String empresa, int horas, String tipo){

		estagioNaoObrigatorio.add(new Estagio_Nao_Obrigatorio(empresa, horas, tipo));

    }

    public void cadastrarPraticaExtensionista(String atividade, int horas){

		servicoComunitario.add(new ServicoComunitario(atividade, horas));

    }

    public void cadastrarResumoExpandido(String projeto, String papel, int creditos){


		praticasExtensionistas.add(new PraticaExtensionista(projeto, papel, creditos));


    }

    public void cadastrarServicoComunitario(String descricao, int horas){


		atividadesComplementares.add(new AtividadeComplementar(descricao, horas));

    }

    public void cadastrarENADE(boolean situacao){

		situacaoENADE = situacao;

    }

    public List<CadeiraObrigatoria> getCadeirasObrigatorias() {
    return cadeirasObrigatorias;
    }

    public List<Estagio_Nao_Obrigatorio> getEstagioNaoObrigatorio() {
        return estagioNaoObrigatorio;
    }

    public List<Estagio_Obrigatorio> getEstagioObrigatorio() {
        return estagioObrigatorio;
    }

    public List<CadeiraOpcional> getCadeirasOpcionais() {
        return cadeirasOpcionais;
    }

    public List<ServicoComunitario> getServicoComunitario() {
        return servicoComunitario;
    }

    public List<PraticaExtensionista> getPraticasExtensionistas() {
        return praticasExtensionistas;
    }

    public List<AtividadeComplementar> getAtividadesComplementares() {
        return atividadesComplementares;
    }

    public List<ArtigoCientifico> getArtigo() {
        return artigo;
    }

    public List<ResumoExpandido> getResumos() {
        return resumos;
    }

    public boolean getSituacaoENADE() {
        return situacaoENADE;
    }


  }
