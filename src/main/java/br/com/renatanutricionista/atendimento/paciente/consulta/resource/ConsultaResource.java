package br.com.renatanutricionista.atendimento.paciente.consulta.resource;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.renatanutricionista.atendimento.paciente.consulta.form.AgendamentoConsultaFORM;
import br.com.renatanutricionista.atendimento.paciente.consulta.form.ConfirmacaoConsultaFORM;
import br.com.renatanutricionista.atendimento.paciente.consulta.form.ConsultaFORM;
import br.com.renatanutricionista.atendimento.paciente.consulta.form.ReagendamentoConsultaFORM;
import br.com.renatanutricionista.atendimento.paciente.consulta.service.ConsultaService;


@RestController
@RequestMapping("/consulta-paciente")
public class ConsultaResource {

	@Autowired
	private ConsultaService consultaService;
	
	
	@GetMapping("/gerar-relatorio-pagamentos-pendentes")
	public ResponseEntity<byte[]> gerarRelatorioDosPagamentosPendentes() {
		return consultaService.gerarRelatorioDosPagamentosPendentes();
	}
	
	
	@PostMapping("/agendar/{idPaciente}")
	@Transactional
	public ResponseEntity<Void> agendarConsulta(@PathVariable Long idPaciente, 
			@RequestBody @Valid AgendamentoConsultaFORM agendamentoConsulta) {
		
		return consultaService.agendarConsulta(idPaciente, agendamentoConsulta);
	}
	
	
	@PutMapping("/reagendar/{idPaciente}/{idConsulta}")
	@Transactional
	public ResponseEntity<Void> reagendarConsulta(@PathVariable Long idPaciente, @PathVariable Long idConsulta, 
			@RequestBody @Valid ReagendamentoConsultaFORM reagendamentoConsulta) {
		
		return consultaService.reagendarConsulta(idPaciente, idConsulta, reagendamentoConsulta);
	}
	
	
	@PutMapping("/confirmar/{idPaciente}/{idConsulta}")
	@Transactional
	public ResponseEntity<Void> confirmarConsulta(@PathVariable Long idPaciente, @PathVariable Long idConsulta, 
			@RequestBody @Valid ConfirmacaoConsultaFORM confirmacaoConsulta) {
		
		return consultaService.confirmarConsulta(idPaciente, idConsulta, confirmacaoConsulta);
	}
	
	
	@DeleteMapping("/cancelar/{idPaciente}/{idConsulta}")
	@Transactional
	public ResponseEntity<Void> cancelarConsulta(@PathVariable Long idPaciente, @PathVariable Long idConsulta) {
		return consultaService.cancelarConsulta(idPaciente, idConsulta);
	}
	
	
	@PutMapping("/iniciar/{idPaciente}/{idConsulta}")
	@Transactional
	public ResponseEntity<Void> iniciarConsulta(@PathVariable Long idPaciente, @PathVariable Long idConsulta) { 
		return consultaService.iniciarConsulta(idPaciente, idConsulta);
	}
	
	
	@PutMapping("/finalizar/{idPaciente}/{idConsulta}")
	@Transactional
	public ResponseEntity<Void> finalizarConsulta(@PathVariable Long idPaciente, @PathVariable Long idConsulta,
			@RequestBody @Valid ConsultaFORM formularioConsulta) {
		return consultaService.finalizarConsulta(idPaciente, idConsulta, formularioConsulta);
	}
}
