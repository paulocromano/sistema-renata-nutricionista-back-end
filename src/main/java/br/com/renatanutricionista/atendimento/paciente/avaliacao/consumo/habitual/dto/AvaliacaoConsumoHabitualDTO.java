package br.com.renatanutricionista.atendimento.paciente.avaliacao.consumo.habitual.dto;

import br.com.renatanutricionista.atendimento.paciente.avaliacao.consumo.habitual.model.AvaliacaoConsumoHabitual;
import lombok.Getter;


@Getter
public class AvaliacaoConsumoHabitualDTO {

	private Long id;
	private String energiaKcal;
	private String proteinaTotalGramas;
	private String proteinaAVBGramas;
	private String carboidratoGramas;
	private String lipideoTotalGramas;
	private String lipideoSaturadoGramas;
	private String lipideoPoliinsaturadoGramas;
	private String lipideoMonoinsaturadoGramas;
	private String colesterolMiligramas;
	private String fibraGramas;
	private String calcioGramas;
	private String fosforoGramas;
	private String ferroMiligramas;
	private String zincoMiligramas;
	private String magnesioMiligramas;
	private String potassioMiligramas;
	private String vitaminaB6Miligramas;
	private String vitaminaCMiligramas;
	private String leucinaMiligramas;
	
	
	public AvaliacaoConsumoHabitualDTO(AvaliacaoConsumoHabitual avaliacaoConsumoHabitual) {
		id = avaliacaoConsumoHabitual.getId();
		energiaKcal = avaliacaoConsumoHabitual.getEnergiaKcal().toString();
		proteinaTotalGramas = avaliacaoConsumoHabitual.getProteinaTotalGramas().toString();
		proteinaAVBGramas = avaliacaoConsumoHabitual.getProteinaAVBGramas().toString();
		carboidratoGramas = avaliacaoConsumoHabitual.getCarboidratoGramas().toString();
		lipideoTotalGramas = avaliacaoConsumoHabitual.getLipideoTotalGramas().toString();
		lipideoSaturadoGramas = avaliacaoConsumoHabitual.getLipideoSaturadoGramas().toString();
		lipideoPoliinsaturadoGramas = avaliacaoConsumoHabitual.getLipideoPoliinsaturadoGramas().toString();
		lipideoMonoinsaturadoGramas = avaliacaoConsumoHabitual.getLipideoMonoinsaturadoGramas().toString();
		colesterolMiligramas = avaliacaoConsumoHabitual.getColesterolMiligramas().toString();
		fibraGramas = avaliacaoConsumoHabitual.getFibraGramas().toString();
		calcioGramas = avaliacaoConsumoHabitual.getCalcioGramas().toString();
		fosforoGramas = avaliacaoConsumoHabitual.getFosforoGramas().toString();
		ferroMiligramas = avaliacaoConsumoHabitual.getFerroMiligramas().toString();
		zincoMiligramas = avaliacaoConsumoHabitual.getZincoMiligramas().toString();
		magnesioMiligramas = avaliacaoConsumoHabitual.getMagnesioMiligramas().toString();
		potassioMiligramas = avaliacaoConsumoHabitual.getPotassioMiligramas().toString();
		vitaminaB6Miligramas = avaliacaoConsumoHabitual.getVitaminaB6Miligramas().toString();
		vitaminaCMiligramas = avaliacaoConsumoHabitual.getVitaminaCMiligramas().toString();
		leucinaMiligramas = avaliacaoConsumoHabitual.getLeucinaMiligramas().toString();
	}
}
