package tools;

import java.util.ArrayList;
import java.util.List;
import classes.*;
import tools.*;

public class AcompanhamentoCurso {

		HistoricoModelo modelo;
		Historico historico;

		public AcompanhamentoCurso(HistoricoModelo modelo, Historico historico){

			this.modelo = modelo;

		}

		/*
		public double acompanharComponenteCurricularObrigatorio(){

			ComponenteCurricularObrigatorio componente = historico.getComponenteCurricularObrigatorio();

			List<AtividadesComplementares> componentes = historico.getAtividadeComplementar();

			int n = 0;

			for(ArtigoCientifico ar : atividades){

				n += ar.getHoras();

			}

			return n / modelo.ComponentesCurricularObrigatorio;

		}

		/*
    public double acompanharComponentesCurricularesComplementares(){

			List<AtividadesComplementares> atividades = historico.getAtividadeComplementar();

			int n = 0;

			for(ArtigoCientifico ar : atividades){

				n += ar.getHoras();

			}

			return n / modelo.ComponentesCurricularObrigatorio;

    }*/

    public double acompanharUnipampaCidada(){

			List<ServicoComunitario> servicos = historico.getServicoComunitario();

			int n = 0;

			for(ServicoComunitario ar : servicos){

				n += ar.getHoras();

			}

			return (n/15)/modelo.QuantidadeUnipampaCidada;

    }

    public double acompanharEstagioObrigatorio(){

			List<Estagio_Obrigatorio> estagios = historico.getEstagioObrigatorio();

			int n = 0;

			for(Estagio_Obrigatorio ar : estagios){

				n += ar.getHoras();

			}

			return (n/15)/modelo.QuantidadeEstagioObrigatorio;

    }

    public double acompanharPraticasExtensionistas(){

			List<PraticaExtensionista> praticas = historico.getPraticasExtensionistas();

			int n = 0;

			for(PraticaExtensionista ar : praticas){

				n += ar.getCreditos();

			}

			return n/modelo.QuantidadePraticasExtensionistas;

    }

    public double acompanharAtividadesComplementares(){

			List<AtividadeComplementar> atividades = historico.getAtividadesComplementares();

			int n = 0;

			for(AtividadeComplementar ar : atividades){

				n += ar.getHoras();

			}

			return (n/15)/modelo.QuantidadeAtividadesComplementares;

    }

    public double acompanharResumoExpandido(){

			List<ResumoExpandido> resumos = historico.getResumos();

			int n = 0;

			for(ResumoExpandido ar : resumos){

				n ++;

			}

			return (n/15)/modelo.QuantidadeResumoExpandido;

    }

    public double acompanharArtigoCientífico(){

			List<ArtigoCientifico> artigos = historico.getArtigo();

			int n = 0;

			for(ArtigoCientifico ar : artigos){

				n ++;

			}

			return (n/15)/modelo.QuantidadeArtigoCientifico;

    }

		public double acompanharCurso(){

			return (//acompanharComponenteCurricularObrigatorio() +
							//acompanharComponentesCurricularesComplementares() +
							acompanharUnipampaCidada() +
							acompanharEstagioObrigatorio() +
							acompanharPraticasExtensionistas() +
							acompanharAtividadesComplementares() +
							acompanharResumoExpandido() +
							acompanharArtigoCientífico()
							)/8;
		}

}
