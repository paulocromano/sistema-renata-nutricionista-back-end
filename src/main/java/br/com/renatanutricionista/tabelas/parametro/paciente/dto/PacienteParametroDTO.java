package br.com.renatanutricionista.tabelas.parametro.paciente.dto;

import br.com.renatanutricionista.tabelas.parametro.paciente.model.PacienteParametro;
import lombok.Getter;


@Getter
public class PacienteParametroDTO {

	private Integer id;
	private Integer tempoMesesAtualizarQuestionarioFrequenciaAlimentar;
	private Integer tempoMesesAtualizarHistoricoSocial;
	private Integer tempoMesesAtualizarHistoricoFamiliar;
	private Integer tempoMesesAtualizarHistoricoAtividadeFisica;
	private Integer tempoMesesAtualizarHistoricoAlimentar;
	private Integer tempoMesesAtualizarHistoricoFrequenciaAlimentar;
	
	
	public PacienteParametroDTO(PacienteParametro pacienteParametro) {
		id = pacienteParametro.getId();
		tempoMesesAtualizarQuestionarioFrequenciaAlimentar = pacienteParametro.getTempoMesesAtualizarQuestionarioFrequenciaAlimentar();
		tempoMesesAtualizarHistoricoSocial = pacienteParametro.getTempoMesesAtualizarHistoricoSocial();
		tempoMesesAtualizarHistoricoFamiliar = pacienteParametro.getTempoMesesAtualizarHistoricoFamiliar();
		tempoMesesAtualizarHistoricoAtividadeFisica = pacienteParametro.getTempoMesesAtualizarHistoricoAtividadeFisica();
		tempoMesesAtualizarHistoricoAlimentar = pacienteParametro.getTempoMesesAtualizarHistoricoAlimentar();
		tempoMesesAtualizarHistoricoFrequenciaAlimentar = pacienteParametro.getTempoMesesAtualizarHistoricoFrequenciaAlimentar();
	}
}
