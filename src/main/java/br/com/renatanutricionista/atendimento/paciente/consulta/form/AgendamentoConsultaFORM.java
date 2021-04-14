package br.com.renatanutricionista.atendimento.paciente.consulta.form;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import br.com.renatanutricionista.atendimento.paciente.consulta.enums.situacao.consulta.SituacaoConsulta;
import br.com.renatanutricionista.atendimento.paciente.consulta.model.Consulta;
import br.com.renatanutricionista.calendario.atendimento.paciente.enums.PeriodoDisponivel;
import br.com.renatanutricionista.calendario.atendimento.paciente.model.CalendarioAtendimentoPaciente;
import br.com.renatanutricionista.paciente.model.Paciente;
import br.com.renatanutricionista.utils.ConversaoUtils;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AgendamentoConsultaFORM {

	@NotEmpty(message = "O campo da Data de Agendamento não pode estar nula/vazia!")
	@Pattern(regexp = "(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}", message = "O formato da Data de Agendamento da Consulta é inválida!")
	private String data;
	
	@NotEmpty(message = "O campo do Horário de Agendamento não pode estar nula/vazia!")
	@Pattern(regexp = "(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]", message = "O formato do Horário de Agendamento da Consulta é inválido!")
	private String horario;
	
	@NotEmpty(message = "O campo Motivo da Consulta não pode estar nulo/vazio!")
	@Size(max = 250, message = "O campo Motivo da Consulta deve ter no máximo {max} caracteres!")
	private String motivoConsulta;
	
	
	public Consulta converterParaConsulta(Paciente paciente, CalendarioAtendimentoPaciente periodoAgendamento) {
		LocalDateTime dataHorario = ConversaoUtils.converterStringParaLocalDateTime(data, horario);
		periodoAgendamento.setPeriodoDisponivel(PeriodoDisponivel.NAO);
		
		return new Consulta.Builder()
				.situacaoConsulta(SituacaoConsulta.AGUARDANDO_CONFIRMACAO)
				.dataHorario(dataHorario)
				.motivoConsulta(motivoConsulta)
				.paciente(paciente)
				.build();
	}
}
