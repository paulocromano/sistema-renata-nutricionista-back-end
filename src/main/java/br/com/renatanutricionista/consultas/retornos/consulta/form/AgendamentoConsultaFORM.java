package br.com.renatanutricionista.consultas.retornos.consulta.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import br.com.renatanutricionista.calendario.atendimento.paciente.model.CalendarioAtendimentoPaciente;
import br.com.renatanutricionista.consultas.retornos.consulta.enums.SituacaoConsulta;
import br.com.renatanutricionista.consultas.retornos.consulta.model.Consulta;
import br.com.renatanutricionista.paciente.model.Paciente;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AgendamentoConsultaFORM {

	@NotEmpty(message = "O campo da Data de Agendamento não pode estar nula/vazia!")
	@Size(max = 10, message = "O campo da Data de Agendamento deve ter no máximo {max} caracteres!")
	private String data;
	
	@NotEmpty(message = "O campo do Horário de Agendamento não pode estar nula/vazia!")
	@Size(max = 5, message = "O campo do Horário de Agendamento deve ter no máximo {max} caracteres!")
	private String horario;
	
	@NotEmpty(message = "O campo Motivo da Consulta não pode estar nulo/vazio!")
	@Size(max = 250, message = "O campo Motivo da Consulta deve ter no máximo {max} caracteres!")
	private String motivoConsulta;
	
	
	public Consulta converterParaConsulta(Paciente paciente, CalendarioAtendimentoPaciente periodoAgendamento) {
		return new Consulta.Builder()
				.situacaoConsulta(SituacaoConsulta.AGUARDANDO_CONFIRMACAO)
				.motivoConsulta(motivoConsulta)
				.paciente(paciente)
				.periodoConsulta(periodoAgendamento)
				.build();
	}
}
