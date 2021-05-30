package br.com.renatanutricionista.ficha.identificacao.historico.atividade.fisica.dto;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import br.com.renatanutricionista.paciente.model.Paciente;
import br.com.renatanutricionista.tabelas.parametro.atendimento.paciente.model.AtendimentoPacienteParametro;
import br.com.renatanutricionista.utils.ConversaoUtils;
import lombok.Getter;


@Getter
public class InformacoesHistoricosAtividadeFisicaDTO {

	private List<HistoricoAtividadeFisicaDTO> historicosAtividadesFisicas;
	private String dataProximaAtualizacaoHistoricoAtividadeFisica;
	
	
	public InformacoesHistoricosAtividadeFisicaDTO(Paciente paciente, AtendimentoPacienteParametro atendimentoPacienteParametro) {
		historicosAtividadesFisicas = HistoricoAtividadeFisicaDTO.converterParaListaAtividadeFisicaDTO(paciente.getHistoricosAtividadeFisica());
		calcularDataProximaAtualizacaoHistoricoAtividadeFisica(paciente, atendimentoPacienteParametro);
	}
	
	
	private void calcularDataProximaAtualizacaoHistoricoAtividadeFisica(Paciente paciente, AtendimentoPacienteParametro atendimentoPacienteParametro) {
		Optional<LocalDate> dataHistoricoAtividadeFisicaMaisRecente = paciente.getHistoricosAtividadeFisica()
				.stream().map(atividadeFisica -> atividadeFisica.getDataHoraCadastroAtividadeFisica().toLocalDate())
				.max(Comparator.comparing(LocalDate::toEpochDay));
		
		if (dataHistoricoAtividadeFisicaMaisRecente.isPresent()) {
			LocalDate dataHistoricoAtividadeFisica = dataHistoricoAtividadeFisicaMaisRecente.get()
					.plusMonths(atendimentoPacienteParametro.getTempoMesesAtualizarHistoricoAtividadeFisica());
			
			if (LocalDate.now().isAfter(dataHistoricoAtividadeFisica)) {
				dataProximaAtualizacaoHistoricoAtividadeFisica = "O Histórico de Atividade Física do paciente está desatualizado!";
			}
			else {
				dataProximaAtualizacaoHistoricoAtividadeFisica = ConversaoUtils.converterLocalDateParaString(dataHistoricoAtividadeFisica);
			}
		}
	}
}
