package br.com.renatanutricionista.paciente.dto;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Optional;

import br.com.renatanutricionista.paciente.model.Paciente;
import br.com.renatanutricionista.tabelas.parametro.paciente.model.PacienteParametro;
import br.com.renatanutricionista.utils.ConversaoUtils;
import lombok.Getter;

@Getter
public class DataProximaAtualizacaoHistoricosPacienteDTO {

	private String dataProximaAtualizacaoQuestionarioFrequenciaAlimentar;
	private String dataProximaAtualizacaoHistoricoAlimentar;
	private String dataProximaAtualizacaoHistoricoAtividadeFisica;
	private String dataProximaAtualizacaoHistoricoPatologiasFamiliares;
	private String dataProximaAtualizacaoHistoricoPatologiasPaciente;
	private String dataProximaAtualizacaoHistoricoSocial;
	
	
	public DataProximaAtualizacaoHistoricosPacienteDTO(Paciente paciente, PacienteParametro pacieteParametro) {
		calcularDataProximaAtualizacaoQuestionarioFrequenciaAlimentar(paciente, pacieteParametro);
	}
	
	
	private void calcularDataProximaAtualizacaoQuestionarioFrequenciaAlimentar(Paciente paciente, PacienteParametro pacieteParametro) {
		
		Optional<LocalDate> dataQuestionarioMaisRecente = paciente.getQuestionarioFrequenciaAlimentar()
				.stream().map(questionario -> questionario.getDataHoraCadastroQuestionario().toLocalDate())
				.max(Comparator.comparing(LocalDate::toEpochDay));
		
		if (dataQuestionarioMaisRecente.isPresent()) {
			dataProximaAtualizacaoQuestionarioFrequenciaAlimentar = ConversaoUtils.converterLocalDateParaString(
					dataQuestionarioMaisRecente.get().plusMonths(pacieteParametro.getTempoMesesAtualizarQuestionarioFrequenciaAlimentar()));
		}
	}
}
