package br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.dto;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.model.QuestionarioFrequenciaAlimentar;
import br.com.renatanutricionista.utils.ConversaoUtils;
import lombok.Getter;


@Getter
public class InformacoesPreviasQuestionarioDTO {

	private Long id;
	private String dataHoraCadastroQuestionario;
	
	
	public InformacoesPreviasQuestionarioDTO(QuestionarioFrequenciaAlimentar questionario) {
		id = questionario.getId();
		dataHoraCadastroQuestionario = ConversaoUtils.converterLocalDateTimeParaStringDataHoraMinuto(questionario.getDataHoraCadastroQuestionario());
	}
	
	
	public static List<InformacoesPreviasQuestionarioDTO> converterParaListaInformacoesPreviasQuestionarioDTO(
			List<QuestionarioFrequenciaAlimentar> questionariosFrequenciaAlimentar) {
		
		return questionariosFrequenciaAlimentar.stream()
				.sorted(Comparator.comparing(QuestionarioFrequenciaAlimentar::getDataHoraCadastroQuestionario)
				.reversed())
				.map(InformacoesPreviasQuestionarioDTO::new)
				.collect(Collectors.toList());
	}
}
