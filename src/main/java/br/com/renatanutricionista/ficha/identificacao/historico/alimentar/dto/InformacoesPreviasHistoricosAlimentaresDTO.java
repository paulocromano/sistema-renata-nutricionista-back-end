package br.com.renatanutricionista.ficha.identificacao.historico.alimentar.dto;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import br.com.renatanutricionista.paciente.model.Paciente;
import br.com.renatanutricionista.tabelas.parametro.atendimento.paciente.model.AtendimentoPacienteParametro;
import br.com.renatanutricionista.utils.ConversaoUtils;
import lombok.Getter;


@Getter
public class InformacoesPreviasHistoricosAlimentaresDTO {

	private List<PreviaHistoricoAlimentarDTO> previaHistoricosAlimentares;
	private String dataProximaAtualizacaoHistoricoAlimentar;
	private Boolean historicoEstaDesatualizado;
	
	
	public InformacoesPreviasHistoricosAlimentaresDTO(Paciente paciente, AtendimentoPacienteParametro atendimentoPacienteParametro) {
		previaHistoricosAlimentares = PreviaHistoricoAlimentarDTO
				.converterParaListaInformacoesPreviasHistoricoAlimentarDTO(paciente.getHistoricosAlimentares());
		
		calcularDataProximaAtualizacaoHistoricoAlimentar(paciente, atendimentoPacienteParametro);
	}
	
	
	private void calcularDataProximaAtualizacaoHistoricoAlimentar(Paciente paciente, AtendimentoPacienteParametro atendimentoPacienteParametro) {
		Optional<LocalDate> dataHistoricoAlimentarMaisRecente = paciente.getHistoricosAlimentares()
				.stream().map(historicoAlimentar -> historicoAlimentar.getDataHoraCadastroHistoricoAlimentar().toLocalDate())
				.max(Comparator.comparing(LocalDate::toEpochDay));
		
		if (dataHistoricoAlimentarMaisRecente.isPresent()) {
			LocalDate dataHistoricoAlimentar = dataHistoricoAlimentarMaisRecente.get().plusMonths(atendimentoPacienteParametro.getTempoMesesAtualizarHistoricoAlimentar());
			
			if (LocalDate.now().isAfter(dataHistoricoAlimentar)) {
				historicoEstaDesatualizado = true;
				dataProximaAtualizacaoHistoricoAlimentar = "O Histórico Alimentar está desatualizado!";
			}
			else {
				historicoEstaDesatualizado = false;
				dataProximaAtualizacaoHistoricoAlimentar = ConversaoUtils.converterLocalDateParaString(dataHistoricoAlimentar);
			}
		}
	}
}
