package br.com.renatanutricionista.atendimento.paciente.avaliacao.consumo.habitual.dto;

import br.com.renatanutricionista.atendimento.paciente.avaliacao.consumo.habitual.model.AvaliacaoConsumoHabitual;
import br.com.renatanutricionista.utils.FormatacaoUtils;
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
		energiaKcal = FormatacaoUtils.substituirPontoPorVirgula(avaliacaoConsumoHabitual.getEnergiaKcal());
		proteinaTotalGramas = FormatacaoUtils.substituirPontoPorVirgula(avaliacaoConsumoHabitual.getProteinaTotalGramas());
		proteinaAVBGramas = FormatacaoUtils.substituirPontoPorVirgula(avaliacaoConsumoHabitual.getProteinaAVBGramas());
		carboidratoGramas = FormatacaoUtils.substituirPontoPorVirgula(avaliacaoConsumoHabitual.getCarboidratoGramas());
		lipideoTotalGramas = FormatacaoUtils.substituirPontoPorVirgula(avaliacaoConsumoHabitual.getLipideoTotalGramas());
		lipideoSaturadoGramas = FormatacaoUtils.substituirPontoPorVirgula(avaliacaoConsumoHabitual.getLipideoSaturadoGramas());
		lipideoPoliinsaturadoGramas = FormatacaoUtils.substituirPontoPorVirgula(avaliacaoConsumoHabitual.getLipideoPoliinsaturadoGramas());
		lipideoMonoinsaturadoGramas = FormatacaoUtils.substituirPontoPorVirgula(avaliacaoConsumoHabitual.getLipideoMonoinsaturadoGramas());
		colesterolMiligramas = FormatacaoUtils.substituirPontoPorVirgula(avaliacaoConsumoHabitual.getColesterolMiligramas());
		fibraGramas = FormatacaoUtils.substituirPontoPorVirgula(avaliacaoConsumoHabitual.getFibraGramas());
		calcioGramas = FormatacaoUtils.substituirPontoPorVirgula(avaliacaoConsumoHabitual.getCalcioGramas());
		fosforoGramas = FormatacaoUtils.substituirPontoPorVirgula(avaliacaoConsumoHabitual.getFosforoGramas());
		ferroMiligramas = FormatacaoUtils.substituirPontoPorVirgula(avaliacaoConsumoHabitual.getFerroMiligramas());
		zincoMiligramas = FormatacaoUtils.substituirPontoPorVirgula(avaliacaoConsumoHabitual.getZincoMiligramas());
		magnesioMiligramas = FormatacaoUtils.substituirPontoPorVirgula(avaliacaoConsumoHabitual.getMagnesioMiligramas());
		potassioMiligramas = FormatacaoUtils.substituirPontoPorVirgula(avaliacaoConsumoHabitual.getPotassioMiligramas());
		vitaminaB6Miligramas = FormatacaoUtils.substituirPontoPorVirgula(avaliacaoConsumoHabitual.getVitaminaB6Miligramas());
		vitaminaCMiligramas = FormatacaoUtils.substituirPontoPorVirgula(avaliacaoConsumoHabitual.getVitaminaCMiligramas());
		leucinaMiligramas = FormatacaoUtils.substituirPontoPorVirgula(avaliacaoConsumoHabitual.getLeucinaMiligramas());
	}
}
