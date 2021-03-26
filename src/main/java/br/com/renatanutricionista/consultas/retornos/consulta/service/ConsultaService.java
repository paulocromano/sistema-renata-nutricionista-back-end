package br.com.renatanutricionista.consultas.retornos.consulta.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.renatanutricionista.calendario.agendamento.paciente.enums.PeriodoDisponivel;
import br.com.renatanutricionista.calendario.agendamento.paciente.model.CalendarioAgendamentoPaciente;
import br.com.renatanutricionista.calendario.agendamento.paciente.repository.CalendarioAgendamentoPacienteRepository;
import br.com.renatanutricionista.calendario.agendamento.paciente.service.CalendarioAgendamentoPacienteService;
import br.com.renatanutricionista.consultas.retornos.consulta.enums.SituacaoConsulta;
import br.com.renatanutricionista.consultas.retornos.consulta.form.AgendamentoConsultaFORM;
import br.com.renatanutricionista.consultas.retornos.consulta.model.Consulta;
import br.com.renatanutricionista.consultas.retornos.consulta.respository.ConsultaRepository;
import br.com.renatanutricionista.exception.custom.ObjectNotFoundException;
import br.com.renatanutricionista.exception.custom.PacienteException;
import br.com.renatanutricionista.paciente.model.Paciente;
import br.com.renatanutricionista.paciente.utils.PacienteUtils;


@Service
public class ConsultaService {

	@Autowired
	private ConsultaRepository consultaRepository;
	
	@Autowired
	private CalendarioAgendamentoPacienteRepository calendarioAgendamentoPacienteRepository;
	
	@Autowired
	private PacienteUtils pacienteUtils;
	
	@Autowired
	private CalendarioAgendamentoPacienteService calendarioAgendamentoService;
	
	
	public ResponseEntity<Void> agendarConsulta(Long idPaciente, AgendamentoConsultaFORM agendamentoConsulta) {
		Paciente paciente = pacienteUtils.verificarSePacienteExiste(idPaciente);
		
		CalendarioAgendamentoPaciente periodoAgendamento = calendarioAgendamentoService
				.verificarPossibilidadeDeAgendarConsulta(agendamentoConsulta.getData(), agendamentoConsulta.getHorario());
		
		periodoAgendamento.setPeriodoDisponivel(PeriodoDisponivel.NAO);
		consultaRepository.save(agendamentoConsulta.converterParaConsulta(paciente, periodoAgendamento));
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	public ResponseEntity<Void> remarcarConsulta(Long idPaciente, Long idConsulta, AgendamentoConsultaFORM remarcacaoConsulta) {
		Paciente paciente = pacienteUtils.verificarSePacienteExiste(idPaciente);
		
		CalendarioAgendamentoPaciente periodoConsultaRemarcada = calendarioAgendamentoService
				.verificarPossibilidadeDeAgendarConsulta(remarcacaoConsulta.getData(), remarcacaoConsulta.getHorario());
		
		Consulta consultaPacienteQueSeraCancelada = verificarSeConsultaExiste(idConsulta);
		verificarSeConsultaPertenceAoPaciente(paciente, consultaPacienteQueSeraCancelada);
		
		periodoConsultaRemarcada.setPeriodoDisponivel(PeriodoDisponivel.NAO);
		consultaRepository.save(remarcacaoConsulta.converterParaConsulta(paciente, periodoConsultaRemarcada));
		
		consultaPacienteQueSeraCancelada.getPeriodoAgendamentoConsulta().setPeriodoDisponivel(PeriodoDisponivel.SIM);
		calendarioAgendamentoPacienteRepository.saveAndFlush(consultaPacienteQueSeraCancelada.getPeriodoAgendamentoConsulta());
		consultaRepository.delete(consultaPacienteQueSeraCancelada);
		
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	private void verificarSeConsultaPertenceAoPaciente(Paciente paciente, Consulta consultaPaciente) {
		if (!paciente.getId().equals(consultaPaciente.getPaciente().getId()))
			throw new IllegalArgumentException("A Consulta selecionada não pertence ao Paciente!");
	}
	
	
	public ResponseEntity<Void> cancelarConsulta(Long idConsulta) {
		Consulta consulta = verificarSeConsultaExiste(idConsulta);
		
		if (consulta.getSituacaoConsulta().equals(SituacaoConsulta.CONSULTA_FINALIZADA))
			throw new PacienteException("Não é possível cancelar uma Consulta Finalizada!");
		
		consultaRepository.delete(consulta);
		calendarioAgendamentoService.alterarPeriodoDoCalendarioParaDisponivel(consulta.getPeriodoAgendamentoConsulta().getId());
		
		return ResponseEntity.noContent().build();
	}
	
	
	private Consulta verificarSeConsultaExiste(Long idConsulta) {
		Optional<Consulta> consulta = consultaRepository.findById(idConsulta);
		
		if (consulta.isEmpty())
			throw new ObjectNotFoundException("Consulta não encontrada!");
		
		return consulta.get();
	}
}
