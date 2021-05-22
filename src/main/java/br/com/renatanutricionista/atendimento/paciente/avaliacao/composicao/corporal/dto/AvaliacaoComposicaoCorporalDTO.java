package br.com.renatanutricionista.atendimento.paciente.avaliacao.composicao.corporal.dto;

import java.math.BigDecimal;
import java.util.Objects;

import br.com.renatanutricionista.atendimento.paciente.avaliacao.composicao.corporal.model.AvaliacaoComposicaoCorporal;
import lombok.Getter;


@Getter
public class AvaliacaoComposicaoCorporalDTO {

	private Long id;
	private String pesoAtualKg;
	private String estaturaMetros;
	private String indiceMassaCorporalKgMetrosQuadrado;
	private String porcentagemGorduraDensidadeCorporal;
	private String dobraCutaneaTricipitalMilimetros;
	private String dobraCutaneaCoxaMilimetros;
	private String dobraCutaneaPanturrilhaMilimetros;
	private String dobraCutaneaPeitoralHomemMilimetros;
	private String dobraCutaneaAbdominalHomemMilimetros;
	private String dobraCutaneaSupraIliacaMulherMilimetros;
	private String resultadoCalculoDensidadeCorporal;
	
	
	public AvaliacaoComposicaoCorporalDTO(AvaliacaoComposicaoCorporal avaliacaoComposicaoCorporal) {
		id = avaliacaoComposicaoCorporal.getId();
		pesoAtualKg = avaliacaoComposicaoCorporal.getPesoAtualKg().toString();
		estaturaMetros = avaliacaoComposicaoCorporal.getEstaturaMetros().toString();
		indiceMassaCorporalKgMetrosQuadrado = avaliacaoComposicaoCorporal.getIndiceMassaCorporalKgMetrosQuadrado().toString();
		porcentagemGorduraDensidadeCorporal = avaliacaoComposicaoCorporal.getPorcentagemGorduraDensidadeCorporal().toString();
		dobraCutaneaTricipitalMilimetros = avaliacaoComposicaoCorporal.getDobraCutaneaTricipitalMilimetros().toString();
		dobraCutaneaCoxaMilimetros = avaliacaoComposicaoCorporal.getDobraCutaneaCoxaMilimetros().toString();
		dobraCutaneaPanturrilhaMilimetros = avaliacaoComposicaoCorporal.getDobraCutaneaPanturrilhaMilimetros().toString();
		
		dobraCutaneaPeitoralHomemMilimetros = verificarSeValorEstaNulo(avaliacaoComposicaoCorporal.getDobraCutaneaPeitoralHomemMilimetros());
		dobraCutaneaAbdominalHomemMilimetros = verificarSeValorEstaNulo(avaliacaoComposicaoCorporal.getDobraCutaneaAbdominalHomemMilimetros());
		dobraCutaneaSupraIliacaMulherMilimetros = verificarSeValorEstaNulo(avaliacaoComposicaoCorporal.getDobraCutaneaSupraIliacaMulherMilimetros());
		
		resultadoCalculoDensidadeCorporal = avaliacaoComposicaoCorporal.getResultadoCalculoDensidadeCorporal().toString();
	}
	
	
	private String verificarSeValorEstaNulo(BigDecimal valor) {
		return (Objects.nonNull(valor)) ? valor.toString() : null;
	}
}
