package br.com.renatanutricionista.atendimento.paciente.retorno.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import br.com.renatanutricionista.atendimento.paciente.retorno.enums.SituacaoRetorno;
import br.com.renatanutricionista.atendimento.paciente.retorno.model.RetornoConsulta;
import br.com.renatanutricionista.calendario.atendimento.paciente.model.CalendarioAtendimentoPaciente;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AgendamentoRetornoFORM {

	@NotEmpty(message = "O campo da Data de Agendamento não pode estar nula/vazia!")
	@Size(max = 10, message = "O campo da Data de Agendamento deve ter no máximo {max} caracteres!")
	private String data;
	
	@NotEmpty(message = "O campo do Horário de Agendamento não pode estar nula/vazia!")
	@Size(max = 5, message = "O campo do Horário de Agendamento deve ter no máximo {max} caracteres!")
	private String horario;
	
	
	public RetornoConsulta converterParaRetornoConsulta(CalendarioAtendimentoPaciente periodoRetorno) {
		return new RetornoConsulta(SituacaoRetorno.AGUARDANDO_CONFIRMACAO, periodoRetorno);
	}
}
