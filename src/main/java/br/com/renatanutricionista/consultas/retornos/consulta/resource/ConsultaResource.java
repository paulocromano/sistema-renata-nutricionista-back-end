package br.com.renatanutricionista.consultas.retornos.consulta.resource;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.renatanutricionista.consultas.retornos.consulta.form.AgendamentoConsultaFORM;
import br.com.renatanutricionista.consultas.retornos.consulta.service.ConsultaService;


@RestController
@RequestMapping("/consulta-paciente")
public class ConsultaResource {

	@Autowired
	private ConsultaService consultaService;
	
	
	@PostMapping("/agendar/{idPaciente}")
	@Transactional
	public ResponseEntity<Void> agendarConsulta(@PathVariable Long idPaciente, 
			@RequestBody @Valid AgendamentoConsultaFORM agendamentoConsulta) {
		
		return consultaService.agendarConsulta(idPaciente, agendamentoConsulta);
	}
	
	
	@DeleteMapping("/{idConsulta}")
	@Transactional
	public ResponseEntity<Void> cancelarConsulta(@PathVariable Long idConsulta) {
		return consultaService.cancelarConsulta(idConsulta);
	}
}
