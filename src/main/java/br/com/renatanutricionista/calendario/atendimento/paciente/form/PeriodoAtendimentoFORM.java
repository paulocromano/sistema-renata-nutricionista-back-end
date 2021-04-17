package br.com.renatanutricionista.calendario.atendimento.paciente.form;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import br.com.renatanutricionista.calendario.atendimento.paciente.model.CalendarioAtendimentoPaciente;
import br.com.renatanutricionista.utils.ConversaoUtils;
import br.com.renatanutricionista.utils.RegexUtils;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PeriodoAtendimentoFORM {

	@NotEmpty(message = "O campo da Data não pode estar vazia/nula!")
	@Pattern(regexp = RegexUtils.DATA, message = "O formato da Data Inicial para cadastro de período é inválida!")
	private String data;
	
	@NotEmpty(message = "O Horário não pode estar nulo/vazio!")
	@Pattern(regexp = RegexUtils.HORA_MINUTO, message = "O formato do Horário é inválido!")
	private String horario;
	
	
	public CalendarioAtendimentoPaciente converterParaCalendarioAtendimentoPaciente() {
		LocalDate data = ConversaoUtils.converterStringParaLocalDate(this.data);
		LocalTime horario = ConversaoUtils.converterStringParaLocalTimeHoraMinuto(this.horario);
		
		return new CalendarioAtendimentoPaciente(data, horario);
	}
}
