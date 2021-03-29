package br.com.renatanutricionista.calendario.atendimento.paciente.dto;

import br.com.renatanutricionista.calendario.atendimento.paciente.model.CalendarioAtendimentoPaciente;
import br.com.renatanutricionista.utils.ConversaoUtils;
import lombok.Getter;


@Getter
public class CalendarioAtendimentoPacienteDTO {

	private Long id;
	private String data;
	private String horario;
	private String periodoDisponivel;
	
	
	public CalendarioAtendimentoPacienteDTO(CalendarioAtendimentoPaciente calendarioAtendimentoPaciente) {
		id = calendarioAtendimentoPaciente.getId();
		data = ConversaoUtils.converterLocalDateParaString(calendarioAtendimentoPaciente.getData());
		horario = ConversaoUtils.converterLocalTimeParaString(calendarioAtendimentoPaciente.getHorario());
		periodoDisponivel = calendarioAtendimentoPaciente.getPeriodoDisponivel().getDescricao();
	}
}
