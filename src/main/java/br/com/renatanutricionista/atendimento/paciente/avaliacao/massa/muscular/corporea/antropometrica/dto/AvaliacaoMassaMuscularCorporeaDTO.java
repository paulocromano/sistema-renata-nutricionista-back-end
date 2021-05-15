package br.com.renatanutricionista.atendimento.paciente.avaliacao.massa.muscular.corporea.antropometrica.dto;

import br.com.renatanutricionista.atendimento.paciente.avaliacao.massa.muscular.corporea.antropometrica.model.AvaliacaoMassaMuscularCorporea;
import br.com.renatanutricionista.utils.FormatacaoUtils;
import lombok.Getter;


@Getter
public class AvaliacaoMassaMuscularCorporeaDTO {

	private Long id;
	private String circunferenciaCinturaCentimetros;
	private String circunferenciaBracoCentimentros;
	private String circunferenciaCoxaCentimetros;
	private String circunferenciaPanturrilhaCentimetros;
	private String circunferenciaPunhoCentrimetros;
	private String massaMuscularKg;
	private String massaMuscularPorcentagem;
	private String indiceMassaMuscularKgMetroQuadrado;
	
	
	public AvaliacaoMassaMuscularCorporeaDTO(AvaliacaoMassaMuscularCorporea avaliacaoMassaMuscularCorporea) {
		id = avaliacaoMassaMuscularCorporea.getId();
		circunferenciaCinturaCentimetros = FormatacaoUtils.substituirPontoPorVirgula(avaliacaoMassaMuscularCorporea.getCircunferenciaCinturaCentimetros());
		circunferenciaBracoCentimentros = FormatacaoUtils.substituirPontoPorVirgula(avaliacaoMassaMuscularCorporea.getCircunferenciaBracoCentimentros());
		circunferenciaCoxaCentimetros = FormatacaoUtils.substituirPontoPorVirgula(avaliacaoMassaMuscularCorporea.getCircunferenciaCoxaCentimetros());
		circunferenciaPanturrilhaCentimetros = FormatacaoUtils.substituirPontoPorVirgula(avaliacaoMassaMuscularCorporea.getCircunferenciaPanturrilhaCentimetros());
		circunferenciaPunhoCentrimetros = FormatacaoUtils.substituirPontoPorVirgula(avaliacaoMassaMuscularCorporea.getCircunferenciaPunhoCentrimetros());
		massaMuscularKg = FormatacaoUtils.substituirPontoPorVirgula(avaliacaoMassaMuscularCorporea.getMassaMuscularKg());
		massaMuscularPorcentagem = FormatacaoUtils.substituirPontoPorVirgula(avaliacaoMassaMuscularCorporea.getMassaMuscularPorcentagem());
		indiceMassaMuscularKgMetroQuadrado = FormatacaoUtils.substituirPontoPorVirgula(avaliacaoMassaMuscularCorporea.getIndiceMassaMuscularKgMetroQuadrado());
	}
}
