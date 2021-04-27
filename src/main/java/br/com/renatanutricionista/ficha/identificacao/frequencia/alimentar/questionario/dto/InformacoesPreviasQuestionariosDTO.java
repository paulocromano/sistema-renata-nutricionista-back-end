package br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.dto;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import br.com.renatanutricionista.paciente.model.Paciente;
import br.com.renatanutricionista.tabelas.parametro.paciente.model.PacienteParametro;
import br.com.renatanutricionista.utils.ConversaoUtils;
import lombok.Getter;


@Getter
public class InformacoesPreviasQuestionariosDTO {

	private List<PreviaQuestionarioFrequenciaAlimentarDTO> previaQuestionariosFrequenciaAlimentar;
	private String dataProximaAtualizacaoQuestionarioFrequenciaAlimentar;
	
	
	public InformacoesPreviasQuestionariosDTO(Paciente paciente, PacienteParametro pacienteParametro) {
		previaQuestionariosFrequenciaAlimentar = PreviaQuestionarioFrequenciaAlimentarDTO.converterParaListaInformacoesPreviasQuestionarioDTO(
				paciente.getQuestionariosFrequenciaAlimentar());
		
		calcularDataProximaAtualizacaoQuestionarioFrequenciaAlimentar(paciente, pacienteParametro);
	}
	
	
	private void calcularDataProximaAtualizacaoQuestionarioFrequenciaAlimentar(Paciente paciente, PacienteParametro pacienteParametro) {
		
		Optional<LocalDate> dataQuestionarioMaisRecente = paciente.getQuestionariosFrequenciaAlimentar()
				.stream().map(questionario -> questionario.getDataHoraCadastroQuestionario().toLocalDate())
				.max(Comparator.comparing(LocalDate::toEpochDay));
		
		if (dataQuestionarioMaisRecente.isPresent()) {
			LocalDate dataQuestionarioFrequenciaAlimentar = dataQuestionarioMaisRecente.get()
					.plusMonths(pacienteParametro.getTempoMesesAtualizarQuestionarioFrequenciaAlimentar());
			
			if (LocalDate.now().isAfter(dataQuestionarioFrequenciaAlimentar)) {
				dataProximaAtualizacaoQuestionarioFrequenciaAlimentar = "O Questionário de Frequência Alimentar do paciente "
						+ "está desatualizado!";
			}
			else {
				dataProximaAtualizacaoQuestionarioFrequenciaAlimentar = ConversaoUtils.converterLocalDateParaString(dataQuestionarioFrequenciaAlimentar);
			}
		}
	}

}
