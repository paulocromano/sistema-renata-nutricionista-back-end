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
	private String dataProximaAtualizacaoHistoricoSocial;
	
	
	public DataProximaAtualizacaoHistoricosPacienteDTO(Paciente paciente, PacienteParametro pacieteParametro) {
		calcularDataProximaAtualizacaoQuestionarioFrequenciaAlimentar(paciente, pacieteParametro);
		calcularDataProximaAtualizacaoHistoricoAlimentar(paciente, pacieteParametro);
		calcularDataProximaAtualizacaoHistoricoAtividadeFisica(paciente, pacieteParametro);
		calcularDataProximaAtualizacaoHistoricoPatologiasFamiliares(paciente, pacieteParametro);
		calcularDataProximaAtualizacaoHistoricoSocial(paciente, pacieteParametro);
	}
	
	
	private void calcularDataProximaAtualizacaoQuestionarioFrequenciaAlimentar(Paciente paciente, PacienteParametro pacieteParametro) {
		
		Optional<LocalDate> dataQuestionarioMaisRecente = paciente.getQuestionarioFrequenciaAlimentar()
				.stream().map(questionario -> questionario.getDataHoraCadastroQuestionario().toLocalDate())
				.max(Comparator.comparing(LocalDate::toEpochDay));
		
		if (dataQuestionarioMaisRecente.isPresent()) {
			LocalDate dataQuestionarioFrequenciaAlimentar = dataQuestionarioMaisRecente.get()
					.plusMonths(pacieteParametro.getTempoMesesAtualizarQuestionarioFrequenciaAlimentar());
			
			if (LocalDate.now().isAfter(dataQuestionarioFrequenciaAlimentar)) {
				dataProximaAtualizacaoQuestionarioFrequenciaAlimentar = "O Questionário de Frequência Alimentar do paciente "
						+ "está desatualizado!";
			}
			else {
				dataProximaAtualizacaoQuestionarioFrequenciaAlimentar = ConversaoUtils.converterLocalDateParaString(dataQuestionarioFrequenciaAlimentar);
			}
		}
	}
	
	
	private void calcularDataProximaAtualizacaoHistoricoAlimentar(Paciente paciente, PacienteParametro pacieteParametro) {
		Optional<LocalDate> dataHistoricoAlimentarMaisRecente = paciente.getHistoricoAlimentar()
				.stream().map(historicoAlimentar -> historicoAlimentar.getDataHoraCadastroHistoricoAlimentar().toLocalDate())
				.max(Comparator.comparing(LocalDate::toEpochDay));
		
		if (dataHistoricoAlimentarMaisRecente.isPresent()) {
			LocalDate dataHistoricoAlimentar = dataHistoricoAlimentarMaisRecente.get().plusMonths(pacieteParametro.getTempoMesesAtualizarHistoricoAlimentar());
			
			if (LocalDate.now().isAfter(dataHistoricoAlimentar)) {
				dataProximaAtualizacaoHistoricoAlimentar = "O Histórico Alimentar do paciente está desatualizado!";
			}
			else {
				dataProximaAtualizacaoHistoricoAlimentar = ConversaoUtils.converterLocalDateParaString(dataHistoricoAlimentar);
			}
		}
	}
	
	
	private void calcularDataProximaAtualizacaoHistoricoAtividadeFisica(Paciente paciente, PacienteParametro pacieteParametro) {
		Optional<LocalDate> dataHistoricoAtividadeFisicaMaisRecente = paciente.getHistoricoAtividadeFisica()
				.stream().map(atividadeFisica -> atividadeFisica.getDataHoraCadastroAtividadeFisica().toLocalDate())
				.max(Comparator.comparing(LocalDate::toEpochDay));
		
		if (dataHistoricoAtividadeFisicaMaisRecente.isPresent()) {
			LocalDate dataHistoricoAtividadeFisica = dataHistoricoAtividadeFisicaMaisRecente.get()
					.plusMonths(pacieteParametro.getTempoMesesAtualizarHistoricoAtividadeFisica());
			
			if (LocalDate.now().isAfter(dataHistoricoAtividadeFisica)) {
				dataProximaAtualizacaoHistoricoAtividadeFisica = "O Histórico de Atividade Física do paciente está desatualizado!";
			}
			else {
				dataProximaAtualizacaoHistoricoAtividadeFisica = ConversaoUtils.converterLocalDateParaString(dataHistoricoAtividadeFisica);
			}
		}
	}
	
	
	private void calcularDataProximaAtualizacaoHistoricoPatologiasFamiliares(Paciente paciente, PacienteParametro pacieteParametro) {
		Optional<LocalDate> dataHistoricoPatologiaFamiliaresMaisRecente = paciente.getHistoricoPatologiaFamiliaresPorData()
				.stream().map(patologiaFamiliares -> patologiaFamiliares.getDataHoraCadastroPatologiasFamiliares().toLocalDate())
				.max(Comparator.comparing(LocalDate::toEpochDay));
		
		if (dataHistoricoPatologiaFamiliaresMaisRecente.isPresent()) {
			LocalDate dataHistoricoPatologiaFamiliares = dataHistoricoPatologiaFamiliaresMaisRecente.get()
					.plusMonths(pacieteParametro.getTempoMesesAtualizarHistoricoPatologiaFamiliares());
			
			if (LocalDate.now().isAfter(dataHistoricoPatologiaFamiliares)) {
				dataProximaAtualizacaoHistoricoPatologiasFamiliares = "O Histórico de Patologias dos Familiares "
						+ "do paciente está desatualizado!";
			}
			else {
				dataProximaAtualizacaoHistoricoPatologiasFamiliares = ConversaoUtils.converterLocalDateParaString(dataHistoricoPatologiaFamiliares);
			}
		}
	}
	
	
	private void calcularDataProximaAtualizacaoHistoricoSocial(Paciente paciente, PacienteParametro pacieteParametro) {
		Optional<LocalDate> dataHistoricoSocialMaisRecente = paciente.getHistoricoSocial()
				.stream().map(historicoSocial -> historicoSocial.getDataHoraCadastroHistoricoSocial().toLocalDate())
				.max(Comparator.comparing(LocalDate::toEpochDay));
		
		if (dataHistoricoSocialMaisRecente.isPresent()) {
			LocalDate dataHistoricoSocial = dataHistoricoSocialMaisRecente.get().plusMonths(pacieteParametro.getTempoMesesAtualizarHistoricoSocial());
			
			if (LocalDate.now().isAfter(dataHistoricoSocial)) {
				dataProximaAtualizacaoHistoricoSocial = "O Histórico Social do paciente está desatualizado!";
			}
			else {
				dataProximaAtualizacaoHistoricoSocial = ConversaoUtils.converterLocalDateParaString(dataHistoricoSocial);
			}
		}
	}
}
