package br.com.renatanutricionista.atendimento.paciente.consulta.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import br.com.renatanutricionista.atendimento.paciente.consulta.model.Consulta;
import br.com.renatanutricionista.calendario.atendimento.paciente.enums.PeriodoDisponivel;
import br.com.renatanutricionista.calendario.atendimento.paciente.model.CalendarioAtendimentoPaciente;
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
	
	
	public void atualizarInformacoesDaConsulta(Consulta consultaPaciente, CalendarioAtendimentoPaciente periodoConsultaRemarcada) {
		consultaPaciente.getPeriodoConsulta().setPeriodoDisponivel(PeriodoDisponivel.SIM);
		
		periodoConsultaRemarcada.setPeriodoDisponivel(PeriodoDisponivel.NAO);
		consultaPaciente.setPeriodoConsulta(periodoConsultaRemarcada);
	}
}
