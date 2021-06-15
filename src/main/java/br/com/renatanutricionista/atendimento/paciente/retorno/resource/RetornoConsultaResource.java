package br.com.renatanutricionista.atendimento.paciente.retorno.resource;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.renatanutricionista.atendimento.paciente.retorno.dto.InformacoesCadastroRetornoConsultaDTO;
import br.com.renatanutricionista.atendimento.paciente.retorno.dto.RetornoConsultaDTO;
import br.com.renatanutricionista.atendimento.paciente.retorno.form.AgendamentoRetornoFORM;
import br.com.renatanutricionista.atendimento.paciente.retorno.form.ReagendamentoRetornoFORM;
import br.com.renatanutricionista.atendimento.paciente.retorno.form.RetornoConsultaFORM;
import br.com.renatanutricionista.atendimento.paciente.retorno.service.RetornoConsultaService;


@RestController
@RequestMapping("/retorno-consulta-paciente")
public class RetornoConsultaResource {

	@Autowired
	private RetornoConsultaService retornoConsultaService;
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/buscar/{tipoAtendimento}/{idRetornoConsulta}")
	public ResponseEntity<RetornoConsultaDTO> buscarRetornoConsultaDoPaciente(@PathVariable Integer tipoAtendimento, @PathVariable Long idRetornoConsulta) {
		return retornoConsultaService.buscarRetornoConsultaDoPaciente(tipoAtendimento, idRetornoConsulta);
	}
	
	
	@PostMapping("/agendar/{idPaciente}")
	@Transactional
	public ResponseEntity<Void> agendarRetorno(@PathVariable Long idPaciente, @RequestBody @Valid AgendamentoRetornoFORM agendamentoRetorno) {
		return retornoConsultaService.agendarRetorno(idPaciente, agendamentoRetorno);
	}
	
	
	@PutMapping("/reagendar/{idPaciente}/{idRetornoConsulta}")
	@Transactional
	public ResponseEntity<Void> reagendarRetorno(@PathVariable Long idPaciente, @PathVariable Long idRetornoConsulta, 
			@RequestBody @Valid ReagendamentoRetornoFORM reagendamentoRetorno) {
		
		return retornoConsultaService.reagendarRetorno(idPaciente, idRetornoConsulta, reagendamentoRetorno);
	}
	
	
	@PutMapping("/confirmar/{idPaciente}/{idRetornoConsulta}")
	@Transactional
	public ResponseEntity<Void> confirmarRetornoConsulta(@PathVariable Long idPaciente, @PathVariable Long idRetornoConsulta) {
		return retornoConsultaService.confirmarRetornoConsulta(idPaciente, idRetornoConsulta);
	}
	
	
	@DeleteMapping("/cancelar/{idPaciente}/{idRetornoConsulta}")
	@Transactional
	public ResponseEntity<Void> cancelarRetornoConsulta(@PathVariable Long idPaciente, @PathVariable Long idRetornoConsulta) {
		return retornoConsultaService.cancelarRetornoConsulta(idPaciente, idRetornoConsulta);
	}
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("/iniciar/{idPaciente}/{idRetornoConsulta}")
	@Transactional
	public ResponseEntity<Void> iniciarRetornoConsulta(@PathVariable Long idPaciente, @PathVariable Long idRetornoConsulta) {
		return retornoConsultaService.iniciarRetornoConsulta(idPaciente, idRetornoConsulta);
	}
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/informacoes-cadastro-retorno-consulta/{idPaciente}/{idRetornoConsulta}")
	public ResponseEntity<InformacoesCadastroRetornoConsultaDTO> informacoesParaCadastrarRetornoConsulta(
			@PathVariable Long idPaciente, @PathVariable Long idRetornoConsulta) {
		
		return retornoConsultaService.informacoesParaCadastrarRetornoConsulta(idPaciente, idRetornoConsulta);
	}
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("/finalizar/{idPaciente}/{idRetornoConsulta}")
	@Transactional
	public ResponseEntity<Void> finalizarRetornoConsulta(@PathVariable Long idPaciente, @PathVariable Long idRetornoConsulta,
			@RequestBody @Valid RetornoConsultaFORM formularioRetornoConsulta) {
		
		return retornoConsultaService.finalizarRetornoConsulta(idPaciente, idRetornoConsulta, formularioRetornoConsulta);
	}
}
