package classes.tools;

import java.util.ArrayList;
import java.util.List;
import classes.atributos.*;
import classes.tools.*;

public class AcompanhamentoCurso {

		HistoricoModelo modelo = new HistoricoModelo();
		Historico historico;

		public AcompanhamentoCurso(Historico historico){

			this.historico = historico;

		}

		public double acompanharComponenteCurricularObrigatorio(){

			List<Integer> componentesId = historico.getIDComponentesCurricularesObrigatorios();
			int n = componentesId.size();
			/*
			List<ComponenteCurricularObrigatorio> componentes = modelo.getComponentesCurricularesObrigatorios();

			int n = 0;

			for(Integer ar : componentesId){

				for(ComponenteCurricularObrigatorio comp : componentes){

					if(ar == comp.getId()){

						n += comp.getCreditos();

					}

				}

			}
			*/
			return n*100/ modelo.QuantidadeComponenteCurricularObrigatorio;

		}

    public double acompanharComponentesCurricularesComplementares(){

			List<Integer> componentesId = historico.getIDComponentesCurricularesNaoObrigatorios();
			int n = componentesId.size();
			/*
			List<ComponenteCurricularNaoObrigatorio> componentes = modelo.getComponentesCurricularesNaoObrigatorios();

			int n = 0;

			for(Integer ar : componentesId){

				for(ComponenteCurricularNaoObrigatorio comp : componentes){

					if(ar == comp.getId()){

						n += comp.getCreditos();

					}

				}

			}
			*/
			return n*100/ modelo.QuantidadeComponentesCurricularesComplementares;

    }

    public double acompanharUnipampaCidada(){

			List<ServicoComunitario> servicos = historico.getServicoComunitario();

			int n = 0;

			for(ServicoComunitario ar : servicos){

				n += ar.getHoras();

			}

			return (n*100/15)/modelo.QuantidadeUnipampaCidada;

    }

    public double acompanharEstagioObrigatorio(){

			List<Estagio_Obrigatorio> estagios = historico.getEstagioObrigatorio();

			int n = 0;

			for(Estagio_Obrigatorio ar : estagios){

				n += ar.getHoras();

			}

			return (n*100/15)/modelo.QuantidadeEstagioObrigatorio;

    }

    public double acompanharPraticasExtensionistas(){

			List<PraticaExtensionista> praticas = historico.getPraticasExtensionistas();

			int n = 0;

			for(PraticaExtensionista ar : praticas){

				n += ar.getCreditos();

			}

			return n*100/modelo.QuantidadePraticasExtensionistas;

    }

    public double acompanharAtividadesComplementares(){

			List<AtividadeComplementar> atividades = historico.getAtividadesComplementares();

			int n = 0;

			for(AtividadeComplementar ar : atividades){

				n += ar.getHoras();

			}

			return (n*100/15)/modelo.QuantidadeAtividadesComplementares;

    }

    public double acompanharResumoExpandido(){

			List<ResumoExpandido> resumos = historico.getResumos();

			int n = 0;

			for(ResumoExpandido ar : resumos){

				n ++;

			}

			return (n*100)/modelo.QuantidadeResumoExpandido;

    }

    public double acompanharArtigoCientífico(){

			List<ArtigoCientifico> artigos = historico.getArtigo();

			int n = 0;

			for(ArtigoCientifico ar : artigos){

				n ++;

			}

			return n*100/modelo.QuantidadeArtigoCientifico;

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
