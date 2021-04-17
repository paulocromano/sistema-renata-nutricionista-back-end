package br.com.renatanutricionista.atendimento.paciente.consulta.form;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import br.com.renatanutricionista.atendimento.paciente.consulta.enums.situacao.consulta.SituacaoConsulta;
import br.com.renatanutricionista.atendimento.paciente.consulta.model.Consulta;
import br.com.renatanutricionista.calendario.atendimento.paciente.model.CalendarioAtendimentoPaciente;
import br.com.renatanutricionista.paciente.model.Paciente;
import br.com.renatanutricionista.utils.ConversaoUtils;
import br.com.renatanutricionista.utils.RegexUtils;
import br.com.renatanutricionista.utils.enums.resposta.RespostaUtils;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AgendamentoConsultaFORM {

	@NotEmpty(message = "O campo da Data de Agendamento não pode estar nula/vazia!")
	@Pattern(regexp = RegexUtils.DATA, message = "O formato da Data de Agendamento da Consulta é inválida!")
	private String data;
	
	@NotEmpty(message = "O campo do Horário de Agendamento não pode estar nula/vazia!")
	@Pattern(regexp = RegexUtils.HORA_MINUTO, message = "O formato do Horário de Agendamento da Consulta é inválido!")
	private String horario;
	
	@NotEmpty(message = "O campo Motivo da Consulta não pode estar nulo/vazio!")
	@Size(max = 250, message = "O campo Motivo da Consulta deve ter no máximo {max} caracteres!")
	private String motivoConsulta;
	
	
	public Consulta converterParaConsulta(Paciente paciente, CalendarioAtendimentoPaciente periodoAgendamento) {
		LocalDate data = ConversaoUtils.converterStringParaLocalDate(this.data);
		LocalTime horario = ConversaoUtils.converterStringParaLocalTimeHoraMinuto(this.horario);
		
		periodoAgendamento.setPeriodoDisponivel(RespostaUtils.NAO);
		
		return new Consulta.Builder()
				.situacaoConsulta(SituacaoConsulta.AGUARDANDO_CONFIRMACAO)
				.data(data)
				.horario(horario)
				.motivoConsulta(motivoConsulta)
				.paciente(paciente)
				.build();
	}
}
