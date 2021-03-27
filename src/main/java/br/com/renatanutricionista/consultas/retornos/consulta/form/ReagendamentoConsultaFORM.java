package br.com.renatanutricionista.consultas.retornos.consulta.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import br.com.renatanutricionista.calendario.agendamento.paciente.model.CalendarioAgendamentoPaciente;
import br.com.renatanutricionista.consultas.retornos.consulta.enums.SituacaoConsulta;
import br.com.renatanutricionista.consultas.retornos.consulta.model.Consulta;
import br.com.renatanutricionista.paciente.model.Paciente;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ReagendamentoConsultaFORM {

	@NotEmpty(message = "O campo da Data de Agendamento não pode estar nula/vazia!")
	@Size(max = 10, message = "O campo da Data de Agendamento deve ter no máximo {max} caracteres!")
	private String data;
	
	@NotEmpty(message = "O campo do Horário de Agendamento não pode estar nula/vazia!")
	@Size(max = 5, message = "O campo do Horário de Agendamento deve ter no máximo {max} caracteres!")
	private String horario;
	
	
	public Consulta converterParaConsulta(Paciente paciente, CalendarioAgendamentoPaciente periodoAgendamento,
			Consulta consultaPacienteQueSeraCancelada) {
		
		return new Consulta.ConsultaBuilder()
				.situacaoConsulta(SituacaoConsulta.AGUARDANDO_CONFIRMACAO)
				.motivoConsulta(consultaPacienteQueSeraCancelada.getMotivoConsulta())
				.paciente(paciente)
				.periodoAgendamentoConsulta(periodoAgendamento)
				.criarConsulta();
	}
}
