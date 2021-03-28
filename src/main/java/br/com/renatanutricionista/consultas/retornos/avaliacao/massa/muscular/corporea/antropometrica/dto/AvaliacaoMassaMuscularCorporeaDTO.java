package br.com.renatanutricionista.consultas.retornos.avaliacao.massa.muscular.corporea.antropometrica.dto;

import java.math.BigDecimal;

import br.com.renatanutricionista.consultas.retornos.avaliacao.massa.muscular.corporea.antropometrica.model.AvaliacaoMassaMuscularCorporea;
import lombok.Getter;


@Getter
public class AvaliacaoMassaMuscularCorporeaDTO {

	private Long id;
	private BigDecimal circunferenciaCinturaCentimetros;
	private BigDecimal circunferenciaBracoCentimentros;
	private BigDecimal circunferenciaCoxaCentimetros;
	private BigDecimal circunferenciaPanturrilhaCentimetros;
	private BigDecimal circunferenciaPunhoCentrimetros;
	private BigDecimal massaMuscularKg;
	private BigDecimal massaMuscularPorcentagem;
	private BigDecimal indiceMassaMuscularKgMetroQuadrado;
	
	
	public AvaliacaoMassaMuscularCorporeaDTO(AvaliacaoMassaMuscularCorporea avaliacaoMassaMuscularCorporea) {
		id = avaliacaoMassaMuscularCorporea.getId();
		circunferenciaCinturaCentimetros = avaliacaoMassaMuscularCorporea.getCircunferenciaCinturaCentimetros();
		circunferenciaBracoCentimentros = avaliacaoMassaMuscularCorporea.getCircunferenciaBracoCentimentros();
		circunferenciaCoxaCentimetros = avaliacaoMassaMuscularCorporea.getCircunferenciaCoxaCentimetros();
		circunferenciaPanturrilhaCentimetros = avaliacaoMassaMuscularCorporea.getCircunferenciaPanturrilhaCentimetros();
		circunferenciaPunhoCentrimetros = avaliacaoMassaMuscularCorporea.getCircunferenciaPunhoCentrimetros();
		massaMuscularKg = avaliacaoMassaMuscularCorporea.getMassaMuscularKg();
		massaMuscularPorcentagem = avaliacaoMassaMuscularCorporea.getMassaMuscularPorcentagem();
		indiceMassaMuscularKgMetroQuadrado = avaliacaoMassaMuscularCorporea.getIndiceMassaMuscularKgMetroQuadrado();
	}
}
