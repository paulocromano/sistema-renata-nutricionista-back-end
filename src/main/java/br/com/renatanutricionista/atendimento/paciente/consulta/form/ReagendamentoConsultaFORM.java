package br.com.renatanutricionista.atendimento.paciente.consulta.form;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import br.com.renatanutricionista.atendimento.paciente.consulta.model.Consulta;
import br.com.renatanutricionista.utils.ConversaoUtils;
import br.com.renatanutricionista.utils.RegexUtils;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ReagendamentoConsultaFORM {

	@NotEmpty(message = "O campo da Data de Agendamento não pode estar nula/vazia!")
	@Pattern(regexp = RegexUtils.DATA, message = "O formato da Data de Reagendamento da Consulta é inválida!")
	private String data;
	
	@NotEmpty(message = "O campo do Horário de Agendamento não pode estar nula/vazia!")
	@Pattern(regexp = RegexUtils.HORA_MINUTO, message = "O formato do Horário de Reagendamento da Consulta é inválido!")
	private String horario;
	
	
	public void atualizarInformacoesDaConsulta(Consulta consultaPaciente) {
		LocalDate data = ConversaoUtils.converterStringParaLocalDate(this.data);
		LocalTime horario = ConversaoUtils.converterStringParaLocalTimeHoraMinuto(this.horario);
		
		consultaPaciente.setData(data);
		consultaPaciente.setHorario(horario);
	}
}
