package br.com.renatanutricionista.ficha.identificacao.historico.patologia.por.data.familiares.dto;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Optional;
import java.util.Set;

import br.com.renatanutricionista.paciente.model.Paciente;
import br.com.renatanutricionista.tabelas.parametro.atendimento.paciente.model.AtendimentoPacienteParametro;
import br.com.renatanutricionista.utils.ConversaoUtils;
import lombok.Getter;


@Getter
public class InformacoesPreviasHistoricosFamiliaresPorDataDTO {

	private Set<PreviaHistoricoPatologiaFamiliaresPorDataDTO> previaHistoricosPatologiaFamiliaresPorData;
	private String dataProximaAtualizacaoHistoricoPatologiasFamiliares;
	private Boolean historicoEstaDesatualizado;
	
	
	public InformacoesPreviasHistoricosFamiliaresPorDataDTO(Paciente paciente, AtendimentoPacienteParametro atendimentoPacienteParametro) {
		previaHistoricosPatologiaFamiliaresPorData = PreviaHistoricoPatologiaFamiliaresPorDataDTO
				.converterParaSetPreviaHistoricoPatologiaFamiliaresPorDataDTO(paciente.getHistoricosPatologiaFamiliaresPorData());
		
		calcularDataProximaAtualizacaoHistoricoPatologiasFamiliares(paciente, atendimentoPacienteParametro);
	}
	
	
	private void calcularDataProximaAtualizacaoHistoricoPatologiasFamiliares(Paciente paciente, AtendimentoPacienteParametro atendimentoPacienteParametro) {
		Optional<LocalDate> dataHistoricoPatologiaFamiliaresMaisRecente = paciente.getHistoricosPatologiaFamiliaresPorData()
				.stream().map(patologiaFamiliares -> patologiaFamiliares.getDataHoraCadastroPatologiasFamiliares().toLocalDate())
				.max(Comparator.comparing(LocalDate::toEpochDay));
		
		if (dataHistoricoPatologiaFamiliaresMaisRecente.isPresent()) {
			LocalDate dataHistoricoPatologiaFamiliares = dataHistoricoPatologiaFamiliaresMaisRecente.get()
					.plusMonths(atendimentoPacienteParametro.getTempoMesesAtualizarHistoricoPatologiaFamiliares());
			
			if (LocalDate.now().isAfter(dataHistoricoPatologiaFamiliares)) {
				historicoEstaDesatualizado = true;
				dataProximaAtualizacaoHistoricoPatologiasFamiliares = "O Histórico de Patologias dos Familiares "
						+ "do paciente está desatualizado!";
			}
			else {
				historicoEstaDesatualizado = false;
				dataProximaAtualizacaoHistoricoPatologiasFamiliares = ConversaoUtils.converterLocalDateParaString(dataHistoricoPatologiaFamiliares);
			}
		}
	}
}
