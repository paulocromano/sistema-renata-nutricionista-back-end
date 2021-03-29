package br.com.renatanutricionista.atendimento.paciente.retorno.resource;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.renatanutricionista.atendimento.paciente.retorno.form.AgendamentoRetornoFORM;
import br.com.renatanutricionista.atendimento.paciente.retorno.form.ReagendamentoRetornoFORM;
import br.com.renatanutricionista.atendimento.paciente.retorno.service.RetornoConsultaService;


@RestController
@RequestMapping("/retorno-consulta-paciente")
public class RetornoConsultaResource {

	@Autowired
	private RetornoConsultaService retornoConsultaService;
	
	
	@PostMapping("/agendar/{idPaciente}/{idConsulta}")
	@Transactional
	public ResponseEntity<Void> agendarRetorno(@PathVariable Long idPaciente, @PathVariable Long idConsulta, 
			@RequestBody @Valid AgendamentoRetornoFORM agendamentoRetorno) {
		
		return retornoConsultaService.agendarRetorno(idPaciente, idConsulta, agendamentoRetorno);
	}
	
	
	@PutMapping("/reagendar/{idPaciente}/{idRetorno}")
	@Transactional
	public ResponseEntity<Void> reagendarRetorno(@PathVariable Long idPaciente, @PathVariable Long idRetorno, 
			@RequestBody @Valid ReagendamentoRetornoFORM reagendamentoRetorno) {
		
		return retornoConsultaService.reagendarRetorno(idPaciente, idRetorno, reagendamentoRetorno);
	}
}
