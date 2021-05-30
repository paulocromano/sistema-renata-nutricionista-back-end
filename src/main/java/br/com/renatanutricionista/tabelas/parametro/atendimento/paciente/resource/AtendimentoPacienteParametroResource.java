package br.com.renatanutricionista.tabelas.parametro.atendimento.paciente.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.renatanutricionista.tabelas.parametro.atendimento.paciente.dto.AtendimentoPacienteParametroDTO;
import br.com.renatanutricionista.tabelas.parametro.atendimento.paciente.form.AtendimentoPacienteParametroFORM;
import br.com.renatanutricionista.tabelas.parametro.atendimento.paciente.service.AtendimentoPacienteParametroService;


@RestController
@RequestMapping("/atendimento-paciente-parametro")
public class AtendimentoPacienteParametroResource {

	@Autowired
	private AtendimentoPacienteParametroService atendimentoPacienteParametroService;
	
	
	@GetMapping
	public ResponseEntity<AtendimentoPacienteParametroDTO> buscarInformacoesDosParametrosDeAtendimentoDePaciente() {
		return atendimentoPacienteParametroService.buscarInformacoesDosParametrosDeAtendimentoDePaciente();
	}
	
	
	@PutMapping
	public ResponseEntity<Void> atualizarParametrosAtendimentoDoPaciente(
			@RequestBody @Valid AtendimentoPacienteParametroFORM atendimentoPacienteParametro) {
		
		return atendimentoPacienteParametroService.atualizarParametrosAtendimentoDoPaciente(atendimentoPacienteParametro);
	}
}
