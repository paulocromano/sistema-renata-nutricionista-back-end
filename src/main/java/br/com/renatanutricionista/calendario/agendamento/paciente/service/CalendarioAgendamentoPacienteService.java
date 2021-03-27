package br.com.renatanutricionista.calendario.agendamento.paciente.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.renatanutricionista.calendario.agendamento.paciente.enums.PeriodoDisponivel;
import br.com.renatanutricionista.calendario.agendamento.paciente.model.CalendarioAgendamentoPaciente;
import br.com.renatanutricionista.calendario.agendamento.paciente.repository.CalendarioAgendamentoPacienteRepository;
import br.com.renatanutricionista.exception.custom.ObjectNotFoundException;
import br.com.renatanutricionista.exception.custom.PacienteException;
import br.com.renatanutricionista.utils.ConversaoUtils;


@Service
public class CalendarioAgendamentoPacienteService {

	@Autowired
	private CalendarioAgendamentoPacienteRepository calendarioAgendamentoRepository;
	
	
	public CalendarioAgendamentoPaciente verificarPossibilidadeDeAgendarConsulta(String data, String horario) {
		LocalDate dataAgendamento = ConversaoUtils.converterStringParaLocalDate(data);
		LocalTime horarioAgendamento = ConversaoUtils.converterStringParaLocalTime(horario + ":00");
		
		verificarSeDataHorarioSaoValidos(dataAgendamento, horarioAgendamento);
		
		Optional<CalendarioAgendamentoPaciente> periodo = calendarioAgendamentoRepository.findByDataAndHorario(dataAgendamento, horarioAgendamento);
		
		if (periodo.isEmpty()) 
			throw new ObjectNotFoundException("A Data e/ou Horário não existe(m) no Calendário de Agendamento "
					+ "de Consulta e Retorno do Paciente!");
		
		verificarDisponibilidadeNoCalendario(periodo.get());
		
		return periodo.get();
	}
	
	private void verificarSeDataHorarioSaoValidos(LocalDate data, LocalTime horario) {
		if (!data.isAfter(LocalDate.now()))
			throw new IllegalArgumentException("Data inválida. A data deve ser um dia "
					+ "após o dia atual!");
	}
	
	
	private void verificarDisponibilidadeNoCalendario(CalendarioAgendamentoPaciente periodo) {
		if (periodo.getPeriodoDisponivel().equals(PeriodoDisponivel.NAO))
			throw new PacienteException("O Período escolhido não está disponível para Agendamento "
					+ "de Consulta ou Retorno!");
	}
	
	
	public CalendarioAgendamentoPaciente verificarSeCalendarioAgendamentoPacienteExiste(Long idCalendarioAgendamento) {
		Optional<CalendarioAgendamentoPaciente> calendarioAgendamento = 
				calendarioAgendamentoRepository.findById(idCalendarioAgendamento);
		
		if (calendarioAgendamento.isEmpty())
			throw new ObjectNotFoundException("O Período no Calendário de Agendamento do Paciente "
					+ "não foi encontrado!");
		
		return calendarioAgendamento.get();
	}
	
	
	public void alterarPeriodoDoCalendarioParaDisponivel(Long idCalendarioAgendamento) {
		CalendarioAgendamentoPaciente periodoAgendamento = verificarSeCalendarioAgendamentoPacienteExiste(idCalendarioAgendamento);
		periodoAgendamento.setPeriodoDisponivel(PeriodoDisponivel.SIM);
	}
}
