package br.com.renatanutricionista.paciente.resource;

import java.util.List;

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

import br.com.renatanutricionista.paciente.dto.PacienteDTO;
import br.com.renatanutricionista.paciente.dto.PacientePreviaHistoricosDTO;
import br.com.renatanutricionista.paciente.form.AtualizacaoPacienteFORM;
import br.com.renatanutricionista.paciente.form.PacienteFORM;
import br.com.renatanutricionista.paciente.service.PacienteService;


@RestController
@RequestMapping("/paciente")
public class PacienteResource {

	@Autowired
	private PacienteService pacienteService;
	
	
	@GetMapping
	public ResponseEntity<List<PacienteDTO>> listarTodosPacientesPorOrdemAlfabetica() {
		return pacienteService.listarTodosPacientesPorOrdemAlfabetica();
	}
	
	
	@GetMapping("informacoes-previas-historicos/{idPaciente}")
	public ResponseEntity<PacientePreviaHistoricosDTO> buscarInformacoesPreviasHistoricosDoPaciente(@PathVariable Long idPaciente) {
		return pacienteService.buscarInformacoesPreviasHistoricosDoPaciente(idPaciente);
	}

	
	@PostMapping("/endereco")
	@Transactional
	public ResponseEntity<Void> cadastrarPacienteEndereco(@RequestBody @Valid PacienteFORM pacienteFORM) {
		return pacienteService.cadastrarPacienteEndereco(pacienteFORM);
	}
	
	
	@PutMapping("/endereco/{idPaciente}")
	@Transactional
	public ResponseEntity<Void> atualizarPacienteEndereco(@PathVariable Long idPaciente, 
			@RequestBody @Valid AtualizacaoPacienteFORM atualizacaoPaciente) {
		
		return pacienteService.atualizarPacienteEndereco(idPaciente, atualizacaoPaciente);
	}
	
	
	@DeleteMapping("/{idPaciente}")
	@Transactional
	public ResponseEntity<Void> removerPaciente(@PathVariable Long idPaciente) {
		return pacienteService.removerPaciente(idPaciente);
	}
}
