package br.com.renatanutricionista.atendimento.paciente.conduta.nutricional.dto;

import java.math.BigDecimal;

import br.com.renatanutricionista.atendimento.paciente.conduta.nutricional.model.CondutaNutricional;
import lombok.Getter;


@Getter
public class CondutaNutricionalDTO {

	private Long id;
	private BigDecimal energiaKcalTotal;
	private BigDecimal carboidratroTotalGramas;
	private BigDecimal carboidratoGramasKgPeso;
	private BigDecimal carboidratoGramasKgMassaMagra;
	private BigDecimal proteinaTotalGramas;
	private BigDecimal proteinaAVBGramas;
	private BigDecimal proteinaAVBGramasKgPeso;
	private BigDecimal lipideoTotal;
	private BigDecimal lipideoSaturado;
	private BigDecimal lipideoPoiinsaturado;
	private BigDecimal lipideoMonoinsaturado;
	private BigDecimal relacaoLipideoInsaturadoSaturado;
	
	
	public CondutaNutricionalDTO(CondutaNutricional condutaNutricional) {
		id = condutaNutricional.getId();
		energiaKcalTotal = condutaNutricional.getEnergiaKcalTotal();
		carboidratroTotalGramas = condutaNutricional.getCarboidratroTotalGramas();
		carboidratoGramasKgPeso = condutaNutricional.getCarboidratoGramasKgPeso();
		carboidratoGramasKgMassaMagra = condutaNutricional.getCarboidratoGramasKgMassaMagra();
		proteinaTotalGramas = condutaNutricional.getProteinaTotalGramas();
		proteinaAVBGramas = condutaNutricional.getProteinaAVBGramas();
		proteinaAVBGramasKgPeso = condutaNutricional.getProteinaAVBGramasKgPeso();
		lipideoTotal = condutaNutricional.getLipideoTotal();
		lipideoSaturado = condutaNutricional.getLipideoSaturado();
		lipideoPoiinsaturado = condutaNutricional.getLipideoPoiinsaturado();
		lipideoMonoinsaturado = condutaNutricional.getLipideoMonoinsaturado();
		relacaoLipideoInsaturadoSaturado = condutaNutricional.getRelacaoLipideoInsaturadoSaturado();
	}
}
