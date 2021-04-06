package br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.dto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.dto.FrequenciaAlimentarDTO;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.enums.consumo.ConsumoCarneVermelha;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.enums.consumo.ConsumoPeixe;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.enums.consumo.ConsumoTipoBebida;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.enums.consumo.ConsumoTipoLeite;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.model.QuestionarioFrequenciaAlimentar;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.enums.consumo.ConsumoFrango;
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
		consumoTipoBebida = ConsumoTipoBebida.converterParaDescricao(questionario.getConsumoTipoBebida());
		consumoTipoLeite = ConsumoTipoLeite.concatenarTiposLeiteConsumidos(questionario.getConsumoTipoLeite());
		consumoCarneVermelha = ConsumoCarneVermelha.concatenarModosPreparoCarneVermelha(questionario.getConsumoCarneVermelha());
		consumoFrango = ConsumoFrango.concatenarModosConsumoFrango(questionario.getConsumoFrango());
		consumoPeixe = ConsumoPeixe.concatenarModosConsumoPeixe(questionario.getConsumoPeixe());
	}
	
	
	public static List<QuestionarioFrequenciaAlimentarDTO> converterParaQuestionarioFrequenciaAlimentarDTO(
			List<QuestionarioFrequenciaAlimentar> questionariosFrequenciaAlimentar) {
		
		return questionariosFrequenciaAlimentar.stream().map(QuestionarioFrequenciaAlimentarDTO::new).collect(Collectors.toList());
	}
}
