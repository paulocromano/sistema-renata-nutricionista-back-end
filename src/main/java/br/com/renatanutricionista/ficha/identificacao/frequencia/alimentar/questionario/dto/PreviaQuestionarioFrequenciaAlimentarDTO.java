package br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.dto;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.model.QuestionarioFrequenciaAlimentar;
import br.com.renatanutricionista.utils.ConversaoUtils;
import lombok.Getter;


@Getter
public class PreviaQuestionarioFrequenciaAlimentarDTO {

	private Long id;
	private String dataHoraCadastroQuestionario;
	
	
	public PreviaQuestionarioFrequenciaAlimentarDTO(QuestionarioFrequenciaAlimentar questionario) {
		id = questionario.getId();
		dataHoraCadastroQuestionario = ConversaoUtils.converterLocalDateTimeParaFrontEndEmString(questionario.getDataHoraCadastroQuestionario());
	}
	
	
	public static List<PreviaQuestionarioFrequenciaAlimentarDTO> converterParaListaInformacoesPreviasQuestionarioDTO(
			List<QuestionarioFrequenciaAlimentar> questionariosFrequenciaAlimentar) {
		
		return questionariosFrequenciaAlimentar.stream()
				.sorted(Comparator.comparing(QuestionarioFrequenciaAlimentar::getDataHoraCadastroQuestionario)
				.reversed())
				.map(PreviaQuestionarioFrequenciaAlimentarDTO::new)
				.collect(Collectors.toList());
	}
}
