package classes;
import java.util.ArrayList;
import java.util.List;
import java.lang.Boolean;
import classes.*;

public class Historico {

    private List<Cadeira> cadeirasObrigatorias = new ArrayList<>();
    private List<Estagio> estagio = new ArrayList<>();
    private List<Cadeira> cadeirasOpcionais = new ArrayList<>();
    private List<ServicoComunitario> servicoComunitario = new ArrayList<>();
	private List<PraticaExtensionista> praticasExtensionistas = new ArrayList<>();
    private List<AtividadeComplementar> atividadesComplementares = new ArrayList<>();
    private List<ArtigoCientifico> artigo = new ArrayList<>();
    private List<ResumoExpandido> resumos = new ArrayList<>();
    private boolean situacaoENADE;

	public Historico() {

		this.cadeirasObrigatorias = null;
		this.estagio = null;
		this.cadeirasOpcionais = null;
		this.servicoComunitario = null;
		this.praticasExtensionistas = null;
		this.atividadesComplementares = null;
		this.artigo = null;
		this.resumos = null;
		this.situacaoENADE = false;
	}

	public void cadastrarArtigo(String titulo, String veiculo){

        artigo.add(new ArtigoCientifico(titulo, veiculo));

    }

    public void cadastrarAtividadeComplementar(String descricao, int horas){

		atividadesComplementares.add(new AtividadeComplementar(descricao, horas));

    }

    public void cadastrarCadeiraObrigatoria(int id){

		cadeirasObrigatorias.add(new Cadeira(id));

    }

    public void cadastrarCadeiraOpcional(int id){

		cadeirasOpcionais.add(new Cadeira(id));

    }

    public void cadastrarEstagio(String empresa, int horas, String tipo){

		estagio.add(new Estagio(empresa, horas, tipo));

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

    public List<Cadeira> getCadeirasObrigatorias() {
    return cadeirasObrigatorias;
    }

    public List<Estagio> getEstagio() {
        return estagio;
    }

    public List<Cadeira> getCadeirasOpcionais() {
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
