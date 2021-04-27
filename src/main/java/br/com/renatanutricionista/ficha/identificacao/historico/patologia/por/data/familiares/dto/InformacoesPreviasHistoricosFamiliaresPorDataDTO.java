package br.com.renatanutricionista.ficha.identificacao.historico.patologia.por.data.familiares.dto;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Optional;
import java.util.Set;

import br.com.renatanutricionista.paciente.model.Paciente;
import br.com.renatanutricionista.tabelas.parametro.paciente.model.PacienteParametro;
import br.com.renatanutricionista.utils.ConversaoUtils;
import lombok.Getter;


@Getter
public class InformacoesPreviasHistoricosFamiliaresPorDataDTO {

	private Set<PreviaHistoricoPatologiaFamiliaresPorDataDTO> previaHistoricosPatologiaFamiliaresPorData;
	private String dataProximaAtualizacaoHistoricoPatologiasFamiliares;
	
	
	public InformacoesPreviasHistoricosFamiliaresPorDataDTO(Paciente paciente, PacienteParametro pacienteParametro) {
		previaHistoricosPatologiaFamiliaresPorData = PreviaHistoricoPatologiaFamiliaresPorDataDTO
				.converterParaSetPreviaHistoricoPatologiaFamiliaresPorDataDTO(paciente.getHistoricosPatologiaFamiliaresPorData());
		
		calcularDataProximaAtualizacaoHistoricoPatologiasFamiliares(paciente, pacienteParametro);
	}
	
	
	private void calcularDataProximaAtualizacaoHistoricoPatologiasFamiliares(Paciente paciente, PacienteParametro pacienteParametro) {
		Optional<LocalDate> dataHistoricoPatologiaFamiliaresMaisRecente = paciente.getHistoricosPatologiaFamiliaresPorData()
				.stream().map(patologiaFamiliares -> patologiaFamiliares.getDataHoraCadastroPatologiasFamiliares().toLocalDate())
				.max(Comparator.comparing(LocalDate::toEpochDay));
		
		if (dataHistoricoPatologiaFamiliaresMaisRecente.isPresent()) {
			LocalDate dataHistoricoPatologiaFamiliares = dataHistoricoPatologiaFamiliaresMaisRecente.get()
					.plusMonths(pacienteParametro.getTempoMesesAtualizarHistoricoPatologiaFamiliares());
			
			if (LocalDate.now().isAfter(dataHistoricoPatologiaFamiliares)) {
				dataProximaAtualizacaoHistoricoPatologiasFamiliares = "O Histórico de Patologias dos Familiares "
						+ "do paciente está desatualizado!";
			}
			else {
				dataProximaAtualizacaoHistoricoPatologiasFamiliares = ConversaoUtils.converterLocalDateParaString(dataHistoricoPatologiaFamiliares);
			}
		}
	}
}
