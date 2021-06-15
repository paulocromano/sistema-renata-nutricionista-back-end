package br.com.renatanutricionista.calendario.atendimento.paciente.resource;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.renatanutricionista.calendario.atendimento.paciente.dto.CalendarioAtendimentoPacienteDTO;
import br.com.renatanutricionista.calendario.atendimento.paciente.form.CalendarioAtendimentoPacienteFORM;
import br.com.renatanutricionista.calendario.atendimento.paciente.form.PeriodoAtendimentoFORM;
import br.com.renatanutricionista.calendario.atendimento.paciente.service.CalendarioAtendimentoPacienteService;


@RestController
@RequestMapping("/calendario-atendimento-paciente")
public class CalendarioAtendimentoPacienteResource {

	@Autowired
	private CalendarioAtendimentoPacienteService calendarioAtendimentoPacienteService;
	
	
	@GetMapping("/horarios-disponiveis")
	public ResponseEntity<List<CalendarioAtendimentoPacienteDTO>> buscarHorariosDisponiveisParaDiaDoAgendamentoDeAtendimento(@RequestParam String data) {
		return calendarioAtendimentoPacienteService.buscarHorariosDisponiveisParaDiaDoAgendamentoDeAtendimento(data);
	}
	
	
	@GetMapping
	public ResponseEntity<List<CalendarioAtendimentoPacienteDTO>> listarHorariosAPartirDoDiaAtual() {
		return calendarioAtendimentoPacienteService.listarHorariosAPartirDoDiaAtual();
	}
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping("/cadastrar-periodo")
	@Transactional
	public ResponseEntity<Void> cadastrarUmPeriodoNoCalendario(@RequestBody @Valid PeriodoAtendimentoFORM periodoAtendimento) {
		return calendarioAtendimentoPacienteService.cadastrarUmPeriodoNoCalendario(periodoAtendimento);
	}
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping("/cadastrar-periodos-automaticamente")
	@Transactional
	public ResponseEntity<Void> cadastrarPeriodosAutomaticamenteNoCalendarioParaAtendimentoPaciente() {
		return calendarioAtendimentoPacienteService.cadastrarPeriodosAutomaticamenteNoCalendarioParaAtendimentoPaciente();
	}
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping("/cadastrar-periodos-manualmente")
	@Transactional
	public ResponseEntity<Void> cadastrarPeriodosManualmenteNoCalendarioParaAtendimentoPaciente(
			@RequestBody @Valid CalendarioAtendimentoPacienteFORM calendarioAtendimento) {
		return calendarioAtendimentoPacienteService.cadastrarPeriodosManualmenteNoCalendarioParaAtendimentoPaciente(calendarioAtendimento);
	}
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/excluir-periodos")
	@Transactional
	public ResponseEntity<Void> excluirPeriodosConformeDataInicialFinal(@RequestParam String dataInicio, @RequestParam String dataFim) {
		return calendarioAtendimentoPacienteService.excluirPeriodosConformeDataInicialFinal(dataInicio, dataFim);
	}
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/excluir-periodo/{idPeriodo}")
	@Transactional
	public ResponseEntity<Void> excluirPeriodo(@PathVariable Long idPeriodo) {
		return calendarioAtendimentoPacienteService.excluirPeriodo(idPeriodo);
	}
}
