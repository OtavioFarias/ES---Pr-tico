package tools;

import java.util.ArrayList;
import java.util.List;
import classes.*;

public class HistoricoModelo {

public final int QuantidadeComponenteCurricularObrigatorio = 231;
public final int QuantidadeComponentesCurricularesComplementares = 12;
public final int QuantidadeUnipampaCidadâ = 4;
public final int QuantidadeEstágioObrigatório = 12;
public final int QuantidadePráticasExtensionistas = 20;
public final int QuantidadeAtividadesComplementares = 75;
public final int QuantidadeResumaexpandido = 2;
public final int QuantidadeArtigoCientífico = 1;

private List<ComponenteCurricularObrigatorio> componentesCurricularesObrigatorios = new ArrayList<>();
private List<ComponenteCurricularNaoObrigatorio> componentesCurricularesNaoObrigatorios = new ArrayList<>();

	public void cadastrarComponenteCurricularNaoObrigatorio(int id){

		componentesCurricularesNaoObrigatorios.add(new ComponenteCurricularNaoObrigatorio(id));

  }

  public void cadastrarComponenteCurricularObrigatorio(int id){

			componentesCurricularesObrigatorios.add(new ComponenteCurricularObrigatorio(id));

  }

  public List<ComponenteCurricularObrigatorio> getComponentesCurricularesObrigatorios() {
    	return componentesCurricularesObrigatorios;
  }

   public List<ComponenteCurricularNaoObrigatorio> getComponentesCurricularesNaoObrigatorios() {
        return componentesCurricularesNaoObrigatorios;
  }
}