package br.com.renatanutricionista.atendimento.paciente.conduta.nutricional.dto;

import br.com.renatanutricionista.atendimento.paciente.conduta.nutricional.model.CondutaNutricional;
import lombok.Getter;


@Getter
public class CondutaNutricionalDTO {

	private Long id;
	private String prescricaoDietetica;
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
		prescricaoDietetica = condutaNutricional.getPrescricaoDietetica();
		energiaKcalTotal = condutaNutricional.getEnergiaKcalTotal().toString();
		carboidratroTotalGramas = condutaNutricional.getCarboidratroTotalGramas().toString();
		carboidratoGramasKgPeso = condutaNutricional.getCarboidratoGramasKgPeso().toString();
		carboidratoGramasKgMassaMagra = condutaNutricional.getCarboidratoGramasKgMassaMagra().toString();
		proteinaTotalGramas = condutaNutricional.getProteinaTotalGramas().toString();
		proteinaAVBGramas = condutaNutricional.getProteinaAVBGramas().toString();
		proteinaAVBGramasKgPeso = condutaNutricional.getProteinaAVBGramasKgPeso().toString();
		lipideoTotal = condutaNutricional.getLipideoTotal().toString();
		lipideoSaturado = condutaNutricional.getLipideoSaturado().toString();
		lipideoPoiinsaturado = condutaNutricional.getLipideoPoiinsaturado().toString();
		lipideoMonoinsaturado = condutaNutricional.getLipideoMonoinsaturado().toString();
		relacaoLipideoInsaturadoSaturado = condutaNutricional.getRelacaoLipideoInsaturadoSaturado().toString();
	}
}
