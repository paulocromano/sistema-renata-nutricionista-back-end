package br.com.renatanutricionista.tabelas.parametro.paciente.form;

import javax.validation.constraints.NotNull;

import br.com.renatanutricionista.tabelas.parametro.paciente.model.PacienteParametro;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PacienteParametroFORM {

	@NotNull(message = "O campo do Tempo em Meses para Atualizar o Histórico Social não pode ser nulo!")
	private Integer tempoMesesAtualizarHistoricoSocial;

	@NotNull(message = "O campo do Tempo em Meses para Atualizar o Histórico Familiar não pode ser nulo!")
	private Integer tempoMesesAtualizarHistoricoFamiliar;

	@NotNull(message = "O campo do Tempo em Meses para Atualizar o Histórico de Atividade Física não pode ser nulo!")
	private Integer tempoMesesAtualizarHistoricoAtividadeFisica;

	@NotNull(message = "O campo do Tempo em Meses para Atualizar o Histórico Alimentar não pode ser nulo!")
	private Integer tempoMesesAtualizarHistoricoAlimentar;

	@NotNull(message = "O campo do Tempo em Meses para Atualizar o Histórico da Frequência Alimentar não pode ser nulo!")
	private Integer tempoMesesAtualizarHistoricoFrequenciaAlimentar;
	
	
	public void atualizarInformacoesPacienteParametro(PacienteParametro pacienteParametro) {
		pacienteParametro.setTempoMesesAtualizarHistoricoSocial(tempoMesesAtualizarHistoricoSocial);
		pacienteParametro.setTempoMesesAtualizarHistoricoFamiliar(tempoMesesAtualizarHistoricoFamiliar);
		pacienteParametro.setTempoMesesAtualizarHistoricoAtividadeFisica(tempoMesesAtualizarHistoricoAtividadeFisica);
		pacienteParametro.setTempoMesesAtualizarHistoricoAlimentar(tempoMesesAtualizarHistoricoAlimentar);
		pacienteParametro.setTempoMesesAtualizarHistoricoFrequenciaAlimentar(tempoMesesAtualizarHistoricoFrequenciaAlimentar);
	}
}
