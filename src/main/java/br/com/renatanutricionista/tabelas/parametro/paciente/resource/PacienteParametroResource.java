package br.com.renatanutricionista.tabelas.parametro.paciente.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.renatanutricionista.tabelas.parametro.paciente.dto.PacienteParametroDTO;
import br.com.renatanutricionista.tabelas.parametro.paciente.service.PacienteParametroService;


@RestController
@RequestMapping("/paciente-parametro")
public class PacienteParametroResource {

	@Autowired
	private PacienteParametroService pacienteParametroService;
	
	
	@GetMapping
	public ResponseEntity<PacienteParametroDTO> buscarInformacoesPacienteParametro() {
		return pacienteParametroService.buscarInformacoesPacienteParametro();
	}
}
