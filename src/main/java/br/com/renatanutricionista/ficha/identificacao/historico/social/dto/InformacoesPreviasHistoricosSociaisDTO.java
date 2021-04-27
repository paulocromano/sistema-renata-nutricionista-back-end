package br.com.renatanutricionista.ficha.identificacao.historico.social.dto;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import br.com.renatanutricionista.paciente.model.Paciente;
import br.com.renatanutricionista.tabelas.parametro.paciente.model.PacienteParametro;
import br.com.renatanutricionista.utils.ConversaoUtils;
import lombok.Getter;


@Getter
public class InformacoesPreviasHistoricosSociaisDTO {

	private List<PreviaHistoricoSocialDTO> previaHistoricosSociais;
	private String dataProximaAtualizacaoHistoricoSocial;
	
	
	public InformacoesPreviasHistoricosSociaisDTO(Paciente paciente, PacienteParametro pacienteParametro) {
		previaHistoricosSociais = PreviaHistoricoSocialDTO.converterParaListaPreviasHistoricoSocialDTO(paciente.getHistoricosSociais());
		calcularDataProximaAtualizacaoHistoricoSocial(paciente, pacienteParametro);
	}
	
	
	private void calcularDataProximaAtualizacaoHistoricoSocial(Paciente paciente, PacienteParametro pacienteParametro) {
		Optional<LocalDate> dataHistoricoSocialMaisRecente = paciente.getHistoricosSociais()
				.stream().map(historicoSocial -> historicoSocial.getDataHoraCadastroHistoricoSocial().toLocalDate())
				.max(Comparator.comparing(LocalDate::toEpochDay));
		
		if (dataHistoricoSocialMaisRecente.isPresent()) {
			LocalDate dataHistoricoSocial = dataHistoricoSocialMaisRecente.get().plusMonths(pacienteParametro.getTempoMesesAtualizarHistoricoSocial());
			
			if (LocalDate.now().isAfter(dataHistoricoSocial)) {
				dataProximaAtualizacaoHistoricoSocial = "O Histórico Social do paciente está desatualizado!";
			}
			else {
				dataProximaAtualizacaoHistoricoSocial = ConversaoUtils.converterLocalDateParaString(dataHistoricoSocial);
			}
		}
	}
}
