package br.com.renatanutricionista.atendimento.paciente.avaliacao.composicao.corporal.dto;

import br.com.renatanutricionista.atendimento.paciente.avaliacao.composicao.corporal.model.AvaliacaoComposicaoCorporal;
import br.com.renatanutricionista.utils.FormatacaoUtils;
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
		pesoAtualKg = FormatacaoUtils.substituirPontoPorVirgula(avaliacaoComposicaoCorporal.getPesoAtualKg());
		estaturaMetros = FormatacaoUtils.substituirPontoPorVirgula(avaliacaoComposicaoCorporal.getEstaturaMetros());
		indiceMassaCorporalKgMetrosQuadrado = FormatacaoUtils.substituirPontoPorVirgula(avaliacaoComposicaoCorporal.getIndiceMassaCorporalKgMetrosQuadrado());
		porcentagemGorduraDensidadeCorporal = FormatacaoUtils.substituirPontoPorVirgula(avaliacaoComposicaoCorporal.getPorcentagemGorduraDensidadeCorporal());
		dobraCutaneaTricipitalMilimetros = FormatacaoUtils.substituirPontoPorVirgula(avaliacaoComposicaoCorporal.getDobraCutaneaTricipitalMilimetros());
		dobraCutaneaCoxaMilimetros = FormatacaoUtils.substituirPontoPorVirgula(avaliacaoComposicaoCorporal.getDobraCutaneaCoxaMilimetros());
		dobraCutaneaPanturrilhaMilimetros = FormatacaoUtils.substituirPontoPorVirgula(avaliacaoComposicaoCorporal.getDobraCutaneaPanturrilhaMilimetros());
		dobraCutaneaPeitoralHomemMilimetros = FormatacaoUtils.substituirPontoPorVirgula(avaliacaoComposicaoCorporal.getDobraCutaneaPeitoralHomemMilimetros());
		dobraCutaneaAbdominalHomemMilimetros = FormatacaoUtils.substituirPontoPorVirgula(avaliacaoComposicaoCorporal.getDobraCutaneaAbdominalHomemMilimetros());
		dobraCutaneaSupraIliacaMulherMilimetros = FormatacaoUtils.substituirPontoPorVirgula(avaliacaoComposicaoCorporal.getDobraCutaneaSupraIliacaMulherMilimetros());
		resultadoCalculoDensidadeCorporal = FormatacaoUtils.substituirPontoPorVirgula(avaliacaoComposicaoCorporal.getResultadoCalculoDensidadeCorporal());
	}
}
