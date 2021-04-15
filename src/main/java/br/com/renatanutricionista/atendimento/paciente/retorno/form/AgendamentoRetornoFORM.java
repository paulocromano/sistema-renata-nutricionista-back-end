package br.com.renatanutricionista.atendimento.paciente.retorno.form;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import br.com.renatanutricionista.atendimento.paciente.retorno.enums.SituacaoRetorno;
import br.com.renatanutricionista.atendimento.paciente.retorno.model.RetornoConsulta;
import br.com.renatanutricionista.calendario.atendimento.paciente.model.CalendarioAtendimentoPaciente;
import br.com.renatanutricionista.utils.ConversaoUtils;
import br.com.renatanutricionista.utils.enums.resposta.RespostaUtils;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AgendamentoRetornoFORM {

	@NotEmpty(message = "O campo da Data de Agendamento não pode estar nula/vazia!")
	@Pattern(regexp = "(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}", message = "O formato da Data de Agendamento do Retorno é inválida!")
	private String data;
	
	@NotEmpty(message = "O campo do Horário de Agendamento não pode estar nula/vazia!")
	@Pattern(regexp = "(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]", message = "O formato do Horário de Agendamento do Retorno é inválido!")
	private String horario;
	
	
	public RetornoConsulta converterParaRetornoConsulta(CalendarioAtendimentoPaciente periodoAgendamento) {
		LocalDateTime dataHorario = ConversaoUtils.converterStringParaLocalDateTime(data, horario);
		periodoAgendamento.setPeriodoDisponivel(RespostaUtils.NAO);
		
		return new RetornoConsulta(SituacaoRetorno.AGUARDANDO_CONFIRMACAO, dataHorario);
	}
}
