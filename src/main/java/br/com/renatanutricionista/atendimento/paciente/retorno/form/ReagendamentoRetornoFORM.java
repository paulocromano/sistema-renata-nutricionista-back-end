package br.com.renatanutricionista.atendimento.paciente.retorno.form;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import br.com.renatanutricionista.atendimento.paciente.retorno.model.RetornoConsulta;
import br.com.renatanutricionista.utils.ConversaoUtils;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ReagendamentoRetornoFORM {

	@NotEmpty(message = "O campo da Data de Agendamento não pode estar nula/vazia!")
	@Pattern(regexp = "(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}", message = "O formato da Data de Reagendamento do Retorno é inválida!")
	private String data;
	
	@NotEmpty(message = "O campo do Horário de Agendamento não pode estar nula/vazia!")
	@Pattern(regexp = "(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]", message = "O formato do Horário de Reagendamento do Retorno é inválido!")
	private String horario;
	
	
	public void atualizarInformacoesRetornoPaciente(RetornoConsulta retornoPaciente) {
		LocalDateTime dataHorario = ConversaoUtils.converterStringParaLocalDateTime(data, horario);
		
		retornoPaciente.setDataHorario(dataHorario);
	}
}
