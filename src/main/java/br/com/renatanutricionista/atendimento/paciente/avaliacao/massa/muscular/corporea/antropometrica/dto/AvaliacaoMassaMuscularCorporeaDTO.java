package br.com.renatanutricionista.atendimento.paciente.avaliacao.massa.muscular.corporea.antropometrica.dto;

import br.com.renatanutricionista.atendimento.paciente.avaliacao.massa.muscular.corporea.antropometrica.model.AvaliacaoMassaMuscularCorporea;
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
		circunferenciaCinturaCentimetros = avaliacaoMassaMuscularCorporea.getCircunferenciaCinturaCentimetros().toString();
		circunferenciaBracoCentimentros = avaliacaoMassaMuscularCorporea.getCircunferenciaBracoCentimentros().toString();
		circunferenciaCoxaCentimetros = avaliacaoMassaMuscularCorporea.getCircunferenciaCoxaCentimetros().toString();
		circunferenciaPanturrilhaCentimetros = avaliacaoMassaMuscularCorporea.getCircunferenciaPanturrilhaCentimetros().toString();
		circunferenciaPunhoCentrimetros = avaliacaoMassaMuscularCorporea.getCircunferenciaPunhoCentrimetros().toString();
		massaMuscularKg = avaliacaoMassaMuscularCorporea.getMassaMuscularKg().toString();
		massaMuscularPorcentagem = avaliacaoMassaMuscularCorporea.getMassaMuscularPorcentagem().toString();
		indiceMassaMuscularKgMetroQuadrado = avaliacaoMassaMuscularCorporea.getIndiceMassaMuscularKgMetroQuadrado().toString();
	}
}
