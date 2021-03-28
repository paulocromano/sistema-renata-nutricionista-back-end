package br.com.renatanutricionista.consultas.retornos.avaliacao.composicao.corporal.dto;

import java.math.BigDecimal;

import br.com.renatanutricionista.consultas.retornos.avaliacao.composicao.corporal.model.AvaliacaoComposicaoCorporal;
import lombok.Getter;


@Getter
public class AvaliacaoComposicaoCorporalDTO {

	private Long id;
	private BigDecimal pesoAtualKg;
	private BigDecimal estaturaMetros;
	private BigDecimal indiceMassaCorporalKgMetrosQuadrado;
	private BigDecimal porcentagemGorduraDensidadeCorporal;
	private BigDecimal dobraCutaneaTricipitalMilimetros;
	private BigDecimal dobraCutaneaCoxaMilimetros;
	private BigDecimal dobraCutaneaPanturrilhaMilimetros;
	private BigDecimal dobraCutaneaPeitoralHomemMilimetros;
	private BigDecimal dobraCutaneaAbdominalHomemMilimetros;
	private BigDecimal dobraCutaneaSupraIliacaMulherMilimetros;
	private BigDecimal resultadoCalculoDensidadeCorporal;
	
	
	public AvaliacaoComposicaoCorporalDTO(AvaliacaoComposicaoCorporal avaliacaoComposicaoCorporal) {
		id = avaliacaoComposicaoCorporal.getId();
		pesoAtualKg = avaliacaoComposicaoCorporal.getPesoAtualKg();
		estaturaMetros = avaliacaoComposicaoCorporal.getEstaturaMetros();
		indiceMassaCorporalKgMetrosQuadrado = avaliacaoComposicaoCorporal.getIndiceMassaCorporalKgMetrosQuadrado();
		porcentagemGorduraDensidadeCorporal = avaliacaoComposicaoCorporal.getPorcentagemGorduraDensidadeCorporal();
		dobraCutaneaTricipitalMilimetros = avaliacaoComposicaoCorporal.getDobraCutaneaTricipitalMilimetros();
		dobraCutaneaCoxaMilimetros = avaliacaoComposicaoCorporal.getDobraCutaneaCoxaMilimetros();
		dobraCutaneaPanturrilhaMilimetros = avaliacaoComposicaoCorporal.getDobraCutaneaPanturrilhaMilimetros();
		
		dobraCutaneaPeitoralHomemMilimetros = avaliacaoComposicaoCorporal.getDobraCutaneaPeitoralHomemMilimetros();
		dobraCutaneaAbdominalHomemMilimetros = avaliacaoComposicaoCorporal.getDobraCutaneaAbdominalHomemMilimetros();
		
		dobraCutaneaSupraIliacaMulherMilimetros = avaliacaoComposicaoCorporal.getDobraCutaneaSupraIliacaMulherMilimetros();
		
		resultadoCalculoDensidadeCorporal = avaliacaoComposicaoCorporal.getResultadoCalculoDensidadeCorporal();
	}
}
