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
			double p = n*100/ modelo.QuantidadeComponenteCurricularObrigatorio;
			if (p > 100) return 100;
			return p;

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
			double p = n*100/ modelo.QuantidadeComponentesCurricularesComplementares;
			if (p > 100) return 100;
			return p;

    }

    public double acompanharUnipampaCidada(){

			List<ServicoComunitario> servicos = historico.getServicoComunitario();

			int n = 0;

			for(ServicoComunitario ar : servicos){

				n += ar.getHoras();

			}

			double p = (n*100/15)/modelo.QuantidadeUnipampaCidada;
			if (p > 100) return 100;
			return p;

    }

    public double acompanharEstagioObrigatorio(){

			List<Estagio_Obrigatorio> estagios = historico.getEstagioObrigatorio();

			int n = 0;

			for(Estagio_Obrigatorio ar : estagios){

				n += ar.getHoras();

			}

			double p = (n*100/15)/modelo.QuantidadeEstagioObrigatorio;
			if (p > 100) return 100;
			return p;

    }

    public double acompanharPraticasExtensionistas(){

			List<PraticaExtensionista> praticas = historico.getPraticasExtensionistas();

			int n = 0;

			for(PraticaExtensionista ar : praticas){

				n += ar.getCreditos();

			}

			double p = n*100/modelo.QuantidadePraticasExtensionistas;
			if (p > 100) return 100;
			return p;
    }

    public double acompanharAtividadesComplementares(){

			List<AtividadeComplementar> atividades = historico.getAtividadesComplementares();

			int n = 0;

			for(AtividadeComplementar ar : atividades){

				n += ar.getHoras();

			}

			double p = (n*100/15)/modelo.QuantidadeAtividadesComplementares;
			if (p > 100) return 100;
			return p;

    }

    public double acompanharResumoExpandido(){

			List<ResumoExpandido> resumos = historico.getResumos();

			int n = 0;

			for(ResumoExpandido ar : resumos){

				n ++;

			}

			double p = (n*100)/modelo.QuantidadeResumoExpandido;
			if (p > 100) return 100;
			return p;

    }

    public double acompanharArtigoCientífico(){

			List<ArtigoCientifico> artigos = historico.getArtigo();

			int n = 0;

			for(ArtigoCientifico ar : artigos){

				n ++;

			}

			double p = n*100/modelo.QuantidadeArtigoCientifico;
			if (p > 100) return 100;
			return p;

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
