package br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.dto;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import br.com.renatanutricionista.paciente.model.Paciente;
import br.com.renatanutricionista.tabelas.parametro.atendimento.paciente.model.AtendimentoPacienteParametro;
import br.com.renatanutricionista.utils.ConversaoUtils;
import lombok.Getter;


@Getter
public class InformacoesPreviasQuestionariosDTO {

	private List<PreviaQuestionarioFrequenciaAlimentarDTO> previaQuestionariosFrequenciaAlimentar;
	private String dataProximaAtualizacaoQuestionario;
	private Boolean historicoEstaDesatualizado;
	
	
	public InformacoesPreviasQuestionariosDTO(Paciente paciente, AtendimentoPacienteParametro atendimentoPacienteParametro) {
		previaQuestionariosFrequenciaAlimentar = PreviaQuestionarioFrequenciaAlimentarDTO.converterParaListaInformacoesPreviasQuestionarioDTO(
				paciente.getQuestionariosFrequenciaAlimentar());
		
		calcularDataProximaAtualizacaoQuestionarioFrequenciaAlimentar(paciente, atendimentoPacienteParametro);
	}
	
	
	private void calcularDataProximaAtualizacaoQuestionarioFrequenciaAlimentar(Paciente paciente, AtendimentoPacienteParametro atendimentoPacienteParametro) {
		
		Optional<LocalDate> dataQuestionarioMaisRecente = paciente.getQuestionariosFrequenciaAlimentar()
				.stream().map(questionario -> questionario.getDataHoraCadastroQuestionario().toLocalDate())
				.max(Comparator.comparing(LocalDate::toEpochDay));
		
		if (dataQuestionarioMaisRecente.isPresent()) {
			LocalDate dataQuestionarioFrequenciaAlimentar = dataQuestionarioMaisRecente.get()
					.plusMonths(atendimentoPacienteParametro.getTempoMesesAtualizarQuestionarioFrequenciaAlimentar());
			
			if (LocalDate.now().isAfter(dataQuestionarioFrequenciaAlimentar)) {
				historicoEstaDesatualizado = true;
				dataProximaAtualizacaoQuestionario = "O Questionário de Frequência Alimentar está desatualizado!";
			}
			else {
				historicoEstaDesatualizado = false;
				dataProximaAtualizacaoQuestionario = ConversaoUtils.converterLocalDateParaString(dataQuestionarioFrequenciaAlimentar);
			}
		}
	}

}
