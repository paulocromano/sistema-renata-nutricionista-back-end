package br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.dto;

import java.util.List;

import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.dto.FrequenciaAlimentarDTO;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.model.QuestionarioFrequenciaAlimentar;
import br.com.renatanutricionista.utils.ConversaoUtils;
import lombok.Getter;


@Getter
public class QuestionarioFrequenciaAlimentarDTO {

	private Long id;
	private String dataHoraCadastroQuestionario;
	private List<FrequenciaAlimentarDTO> frequenciaConsumoAlimentos;
	private String consumoTipoBebida;
	private String consumoTipoLeite;
	private String consumoCarneVermelha;
	private String ConsumoFrango;
	private String consumoPeixe;
	
	
	public QuestionarioFrequenciaAlimentarDTO(QuestionarioFrequenciaAlimentar questionario) {
		id = questionario.getId();
		dataHoraCadastroQuestionario = ConversaoUtils.converterLocalDateTimeParaStringDataHoraMinuto(questionario.getDataHoraCadastroQuestionario());
		frequenciaConsumoAlimentos = FrequenciaAlimentarDTO.converterParaListaFrequenciaAlimentarDTO(questionario.getFrequenciaConsumoAlimentos());
		consumoTipoBebida = questionario.getConsumoTipoBebida();
		consumoTipoLeite = questionario.getConsumoTipoLeite();
		consumoCarneVermelha = questionario.getConsumoCarneVermelha();
		ConsumoFrango = questionario.getConsumoFrango();
		consumoPeixe = questionario.getConsumoPeixe();
	}
}
