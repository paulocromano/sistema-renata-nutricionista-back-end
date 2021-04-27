package br.com.renatanutricionista.ficha.identificacao.historico.alimentar.dto;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import br.com.renatanutricionista.paciente.model.Paciente;
import br.com.renatanutricionista.tabelas.parametro.paciente.model.PacienteParametro;
import br.com.renatanutricionista.utils.ConversaoUtils;
import lombok.Getter;


@Getter
public class InformacoesPreviasHistoricosAlimentaresDTO {

	private List<PreviaHistoricoAlimentarDTO> previaHistoricosAlimentares;
	private String dataProximaAtualizacaoHistoricoAlimentar;
	
	
	public InformacoesPreviasHistoricosAlimentaresDTO(Paciente paciente, PacienteParametro pacienteParametro) {
		previaHistoricosAlimentares = PreviaHistoricoAlimentarDTO
				.converterParaListaInformacoesPreviasHistoricoAlimentarDTO(paciente.getHistoricosAlimentares());
		
		calcularDataProximaAtualizacaoHistoricoAlimentar(paciente, pacienteParametro);
	}
	
	
	private void calcularDataProximaAtualizacaoHistoricoAlimentar(Paciente paciente, PacienteParametro pacienteParametro) {
		Optional<LocalDate> dataHistoricoAlimentarMaisRecente = paciente.getHistoricosAlimentares()
				.stream().map(historicoAlimentar -> historicoAlimentar.getDataHoraCadastroHistoricoAlimentar().toLocalDate())
				.max(Comparator.comparing(LocalDate::toEpochDay));
		
		if (dataHistoricoAlimentarMaisRecente.isPresent()) {
			LocalDate dataHistoricoAlimentar = dataHistoricoAlimentarMaisRecente.get().plusMonths(pacienteParametro.getTempoMesesAtualizarHistoricoAlimentar());
			
			if (LocalDate.now().isAfter(dataHistoricoAlimentar)) {
				dataProximaAtualizacaoHistoricoAlimentar = "O Histórico Alimentar do paciente está desatualizado!";
			}
			else {
				dataProximaAtualizacaoHistoricoAlimentar = ConversaoUtils.converterLocalDateParaString(dataHistoricoAlimentar);
			}
		}
	}
}
