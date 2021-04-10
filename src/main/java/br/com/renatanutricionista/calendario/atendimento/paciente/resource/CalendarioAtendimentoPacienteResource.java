package br.com.renatanutricionista.calendario.atendimento.paciente.resource;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.renatanutricionista.calendario.atendimento.paciente.dto.CalendarioAtendimentoPacienteDTO;
import br.com.renatanutricionista.calendario.atendimento.paciente.service.CalendarioAtendimentoPacienteService;


@RestController
@RequestMapping("/calendario-atendimento-paciente")
public class CalendarioAtendimentoPacienteResource {

	@Autowired
	private CalendarioAtendimentoPacienteService calendarioAtendimentoPacienteService;
	
	
	@GetMapping
	public ResponseEntity<List<CalendarioAtendimentoPacienteDTO>> listarHorariosAPartirDoDiaAtual() {
		return calendarioAtendimentoPacienteService.listarHorariosAPartirDoDiaAtual();
	}
	
	
	@PostMapping("/cadastrar-periodos-automaticamente")
	@Transactional
	public ResponseEntity<Void> cadastrarPeriodosAutomaticamenteNoCalendarioParaAtendimentoPaciente() {
		return calendarioAtendimentoPacienteService.cadastrarPeriodosAutomaticamenteNoCalendarioParaAtendimentoPaciente();
	}
	
	
	@DeleteMapping("/{idCalendarioAtendimento}")
	@Transactional
	public ResponseEntity<Void> excluirPeriodo(@PathVariable Long idCalendarioAtendimento) {
		return calendarioAtendimentoPacienteService.excluirPeriodo(idCalendarioAtendimento);
	}
}
