package br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.dto;

import java.util.Set;

import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.dto.FrequenciaAlimentarDTO;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.enums.ConcatenacaoValoresEnum;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.enums.ConsumoCarneVermelha;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.enums.ConsumoFrango;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.enums.ConsumoPeixe;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.enums.ConsumoTipoBebida;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.enums.ConsumoTipoLeite;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.model.QuestionarioFrequenciaAlimentar;
import br.com.renatanutricionista.utils.ConversaoUtils;
import lombok.Getter;


@Getter
public class QuestionarioFrequenciaAlimentarDTO {

	private Long id;
	private String dataHoraCadastroQuestionario;
	private Set<FrequenciaAlimentarDTO> frequenciaConsumoAlimentos;
	private String consumoTipoBebida;
	private String consumoTipoLeite;
	private String consumoCarneVermelha;
	private String consumoFrango;
	private String consumoPeixe;
	
	
	public QuestionarioFrequenciaAlimentarDTO(QuestionarioFrequenciaAlimentar questionario) {
		id = questionario.getId();
		dataHoraCadastroQuestionario = ConversaoUtils.converterLocalDateTimeParaStringDataHoraMinuto(questionario.getDataHoraCadastroQuestionario());
		frequenciaConsumoAlimentos = FrequenciaAlimentarDTO.converterParaListaFrequenciaAlimentarDTO(questionario.getFrequenciaConsumoAlimentos());
		
		ConcatenacaoValoresEnum concatenacao = new ConcatenacaoValoresEnum();
		consumoTipoBebida = concatenacao.concatenarValoresEnum(questionario.getConsumoTipoBebida(), ConsumoTipoBebida.values());
		consumoTipoLeite = concatenacao.concatenarValoresEnum(questionario.getConsumoTipoLeite(), ConsumoTipoLeite.values());
		consumoCarneVermelha = concatenacao.concatenarValoresEnum(questionario.getConsumoCarneVermelha(), ConsumoCarneVermelha.values());
		consumoFrango = concatenacao.concatenarValoresEnum(questionario.getConsumoFrango(), ConsumoFrango.values());
		consumoPeixe = concatenacao.concatenarValoresEnum(questionario.getConsumoPeixe(), ConsumoPeixe.values());
	}
}
