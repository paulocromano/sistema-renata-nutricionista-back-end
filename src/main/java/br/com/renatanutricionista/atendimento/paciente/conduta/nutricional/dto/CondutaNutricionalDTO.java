package br.com.renatanutricionista.atendimento.paciente.conduta.nutricional.dto;

import br.com.renatanutricionista.atendimento.paciente.conduta.nutricional.model.CondutaNutricional;
import br.com.renatanutricionista.utils.FormatacaoUtils;
import lombok.Getter;


@Getter
public class CondutaNutricionalDTO {

	private Long id;
	private String energiaKcalTotal;
	private String carboidratroTotalGramas;
	private String carboidratoGramasKgPeso;
	private String carboidratoGramasKgMassaMagra;
	private String proteinaTotalGramas;
	private String proteinaAVBGramas;
	private String proteinaAVBGramasKgPeso;
	private String lipideoTotal;
	private String lipideoSaturado;
	private String lipideoPoiinsaturado;
	private String lipideoMonoinsaturado;
	private String relacaoLipideoInsaturadoSaturado;
	
	
	public CondutaNutricionalDTO(CondutaNutricional condutaNutricional) {
		id = condutaNutricional.getId();
		energiaKcalTotal = FormatacaoUtils.substituirPontoPorVirgula(condutaNutricional.getEnergiaKcalTotal());
		carboidratroTotalGramas = FormatacaoUtils.substituirPontoPorVirgula(condutaNutricional.getCarboidratroTotalGramas());
		carboidratoGramasKgPeso = FormatacaoUtils.substituirPontoPorVirgula(condutaNutricional.getCarboidratoGramasKgPeso());
		carboidratoGramasKgMassaMagra = FormatacaoUtils.substituirPontoPorVirgula(condutaNutricional.getCarboidratoGramasKgMassaMagra());
		proteinaTotalGramas = FormatacaoUtils.substituirPontoPorVirgula(condutaNutricional.getProteinaTotalGramas());
		proteinaAVBGramas = FormatacaoUtils.substituirPontoPorVirgula(condutaNutricional.getProteinaAVBGramas());
		proteinaAVBGramasKgPeso = FormatacaoUtils.substituirPontoPorVirgula(condutaNutricional.getProteinaAVBGramasKgPeso());
		lipideoTotal = FormatacaoUtils.substituirPontoPorVirgula(condutaNutricional.getLipideoTotal());
		lipideoSaturado = FormatacaoUtils.substituirPontoPorVirgula(condutaNutricional.getLipideoSaturado());
		lipideoPoiinsaturado = FormatacaoUtils.substituirPontoPorVirgula(condutaNutricional.getLipideoPoiinsaturado());
		lipideoMonoinsaturado = FormatacaoUtils.substituirPontoPorVirgula(condutaNutricional.getLipideoMonoinsaturado());
		relacaoLipideoInsaturadoSaturado = FormatacaoUtils.substituirPontoPorVirgula(condutaNutricional.getRelacaoLipideoInsaturadoSaturado());
	}
}
