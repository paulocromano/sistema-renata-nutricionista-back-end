package br.com.renatanutricionista.calendario.agendamento.paciente.dto;

import br.com.renatanutricionista.calendario.agendamento.paciente.model.CalendarioAgendamentoPaciente;
import br.com.renatanutricionista.utils.ConversaoUtils;
import lombok.Getter;


@Getter
public class CalendarioAgendamentoPacienteDTO {

	private Long id;
	private String data;
	private String horario;
	private String periodoDisponivel;
	
	
	public CalendarioAgendamentoPacienteDTO(CalendarioAgendamentoPaciente calendarioAgendamentoPaciente) {
		id = calendarioAgendamentoPaciente.getId();
		data = ConversaoUtils.converterLocalDateParaString(calendarioAgendamentoPaciente.getData());
		horario = ConversaoUtils.converterLocalTimeParaString(calendarioAgendamentoPaciente.getHorario());
		periodoDisponivel = calendarioAgendamentoPaciente.getPeriodoDisponivel().getDescricao();
	}
}
