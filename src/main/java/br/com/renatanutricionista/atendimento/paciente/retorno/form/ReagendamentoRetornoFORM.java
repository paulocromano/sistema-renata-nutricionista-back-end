package br.com.renatanutricionista.atendimento.paciente.retorno.form;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import br.com.renatanutricionista.atendimento.paciente.retorno.model.RetornoConsulta;
import br.com.renatanutricionista.calendario.atendimento.paciente.model.CalendarioAtendimentoPaciente;
import br.com.renatanutricionista.utils.ConversaoUtils;
import br.com.renatanutricionista.utils.RegexUtils;
import br.com.renatanutricionista.utils.enums.resposta.RespostaUtils;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ReagendamentoRetornoFORM {

	@NotEmpty(message = "O campo da Data de Agendamento não pode estar nula/vazia!")
	@Pattern(regexp = RegexUtils.DATA, message = "O formato da Data de Reagendamento do Retorno é inválida!")
	private String data;
	
	@NotEmpty(message = "O campo do Horário de Agendamento não pode estar nula/vazia!")
	@Pattern(regexp = RegexUtils.HORA_MINUTO, message = "O formato do Horário de Reagendamento do Retorno é inválido!")
	private String horario;
	
	
	public void atualizarInformacoesRetornoPaciente(RetornoConsulta retornoPaciente, CalendarioAtendimentoPaciente periodoReagendamento) {
		LocalDate data = ConversaoUtils.converterStringParaLocalDate(this.data);
		LocalTime horario = ConversaoUtils.converterStringParaLocalTimeHoraMinuto(this.horario);
		
		retornoPaciente.setData(data);
		retornoPaciente.setHorario(horario);
		
		periodoReagendamento.setPeriodoDisponivel(RespostaUtils.NAO);
	}
}
