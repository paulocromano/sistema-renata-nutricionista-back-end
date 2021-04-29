package br.com.renatanutricionista.ficha.identificacao.historico.atividade.fisica.dto;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import br.com.renatanutricionista.paciente.model.Paciente;
import br.com.renatanutricionista.tabelas.parametro.paciente.model.PacienteParametro;
import br.com.renatanutricionista.utils.ConversaoUtils;
import lombok.Getter;


@Getter
public class InformacoesHistoricosAtividadeFisicaDTO {

	private List<HistoricoAtividadeFisicaDTO> historicosAtividadesFisicas;
	private String dataProximaAtualizacaoHistoricoAtividadeFisica;
	
	
	public InformacoesHistoricosAtividadeFisicaDTO(Paciente paciente, PacienteParametro pacienteParametro) {
		historicosAtividadesFisicas = HistoricoAtividadeFisicaDTO.converterParaListaAtividadeFisicaDTO(paciente.getHistoricosAtividadeFisica());
		calcularDataProximaAtualizacaoHistoricoAtividadeFisica(paciente, pacienteParametro);
	}
	
	
	private void calcularDataProximaAtualizacaoHistoricoAtividadeFisica(Paciente paciente, PacienteParametro pacienteParametro) {
		Optional<LocalDate> dataHistoricoAtividadeFisicaMaisRecente = paciente.getHistoricosAtividadeFisica()
				.stream().map(atividadeFisica -> atividadeFisica.getDataHoraCadastroAtividadeFisica().toLocalDate())
				.max(Comparator.comparing(LocalDate::toEpochDay));
		
		if (dataHistoricoAtividadeFisicaMaisRecente.isPresent()) {
			LocalDate dataHistoricoAtividadeFisica = dataHistoricoAtividadeFisicaMaisRecente.get()
					.plusMonths(pacienteParametro.getTempoMesesAtualizarHistoricoAtividadeFisica());
			
			if (LocalDate.now().isAfter(dataHistoricoAtividadeFisica)) {
				dataProximaAtualizacaoHistoricoAtividadeFisica = "O Histórico de Atividade Física do paciente está desatualizado!";
			}
			else {
				dataProximaAtualizacaoHistoricoAtividadeFisica = ConversaoUtils.converterLocalDateParaString(dataHistoricoAtividadeFisica);
			}
		}
	}
}
