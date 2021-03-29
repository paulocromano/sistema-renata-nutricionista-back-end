package br.com.renatanutricionista.atendimento.paciente.avaliacao.consumo.habitual.dto;

import java.math.BigDecimal;

import br.com.renatanutricionista.atendimento.paciente.avaliacao.consumo.habitual.model.AvaliacaoConsumoHabitual;
import lombok.Getter;


@Getter
public class AvaliacaoConsumoHabitualDTO {

	private Long id;
	private BigDecimal energiaKcal;
	private BigDecimal proteinaTotalGramas;
	private BigDecimal proteinaAVBGramas;
	private BigDecimal carboidratoGramas;
	private BigDecimal lipideoTotalGramas;
	private BigDecimal lipideoSaturadoGramas;
	private BigDecimal lipideoPoliinsaturadoGramas;
	private BigDecimal lipideoMonoinsaturadoGramas;
	private BigDecimal colesterolMiligramas;
	private BigDecimal fibraGramas;
	private BigDecimal calcioGramas;
	private BigDecimal fosforoGramas;
	private BigDecimal ferroMiligramas;
	private BigDecimal zincoMiligramas;
	private BigDecimal magnesioMiligramas;
	private BigDecimal potassioMiligramas;
	private BigDecimal vitaminaB6Miligramas;
	private BigDecimal vitaminaCMiligramas;
	private BigDecimal leucinaMiligramas;
	
	
	public AvaliacaoConsumoHabitualDTO(AvaliacaoConsumoHabitual avaliacaoConsumoHabitual) {
		id = avaliacaoConsumoHabitual.getId();
		energiaKcal = avaliacaoConsumoHabitual.getEnergiaKcal();
		proteinaTotalGramas = avaliacaoConsumoHabitual.getProteinaTotalGramas();
		proteinaAVBGramas = avaliacaoConsumoHabitual.getProteinaAVBGramas();
		carboidratoGramas = avaliacaoConsumoHabitual.getCarboidratoGramas();
		lipideoTotalGramas = avaliacaoConsumoHabitual.getLipideoTotalGramas();
		lipideoSaturadoGramas = avaliacaoConsumoHabitual.getLipideoSaturadoGramas();
		lipideoPoliinsaturadoGramas = avaliacaoConsumoHabitual.getLipideoPoliinsaturadoGramas();
		lipideoMonoinsaturadoGramas = avaliacaoConsumoHabitual.getLipideoMonoinsaturadoGramas();
		colesterolMiligramas = avaliacaoConsumoHabitual.getColesterolMiligramas();
		fibraGramas = avaliacaoConsumoHabitual.getFibraGramas();
		calcioGramas = avaliacaoConsumoHabitual.getCalcioGramas();
		fosforoGramas = avaliacaoConsumoHabitual.getFosforoGramas();
		ferroMiligramas = avaliacaoConsumoHabitual.getFerroMiligramas();
		zincoMiligramas = avaliacaoConsumoHabitual.getZincoMiligramas();
		magnesioMiligramas = avaliacaoConsumoHabitual.getMagnesioMiligramas();
		potassioMiligramas = avaliacaoConsumoHabitual.getPotassioMiligramas();
		vitaminaB6Miligramas = avaliacaoConsumoHabitual.getVitaminaB6Miligramas();
		vitaminaCMiligramas = avaliacaoConsumoHabitual.getVitaminaCMiligramas();
		leucinaMiligramas = avaliacaoConsumoHabitual.getLeucinaMiligramas();
	}
}
