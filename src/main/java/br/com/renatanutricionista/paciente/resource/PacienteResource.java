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

import br.com.renatanutricionista.ficha.identificacao.atividade.fisica.form.AtividadeFisicaFORM;
import br.com.renatanutricionista.ficha.identificacao.historico.social.form.HistoricoSocialFORM;
import br.com.renatanutricionista.paciente.dto.PacienteDTO;
import br.com.renatanutricionista.paciente.form.AtualizacaoPacienteFORM;
import br.com.renatanutricionista.paciente.form.PacienteFORM;
import br.com.renatanutricionista.paciente.service.PacienteService;


@RestController
@RequestMapping("/paciente")
public class PacienteResource {

	@Autowired
	private PacienteService pacienteService;
	
	
	@GetMapping
	public ResponseEntity<List<PacienteDTO>> listarTodosPacientes() {
		return pacienteService.listarTodosPacientes();
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
	
	
	@PostMapping("/historico-social/{idPaciente}")
	@Transactional
	public ResponseEntity<Void> cadastrarHistoricoSocialDoPaciente(@PathVariable Long idPaciente, 
			@RequestBody @Valid HistoricoSocialFORM historicoSocialFORM) {
		
		return pacienteService.cadastrarHistoricoSocialDoPaciente(idPaciente, historicoSocialFORM);
	}
	
	
//	@PostMapping("/historico-alimentar/{idPaciente}")
//	@Transactional
//	public ResponseEntity<Void> cadastrarHistoricoAlimentarDoPaciente(@PathVariable Long idPaciente, 
//			@RequestBody @Valid HistoricoAlimentarFORM historicoAlimentarFORM) {
//		
//		return pacienteService.cadastrarHistoricoAlimentarDoPaciente(idPaciente, historicoAlimentarFORM);
//	}
	
	
	@PostMapping("/atividade-fisica/{idPaciente}")
	@Transactional
	public ResponseEntity<Void> cadastrarAtividadeFisicaDoPaciente(@PathVariable Long idPaciente, 
			@RequestBody @Valid AtividadeFisicaFORM atividadeFisicaFORM) {
		
		return pacienteService.cadastrarAtividadeFisicaDoPaciente(idPaciente, atividadeFisicaFORM);
	}
	
	
	@DeleteMapping("/{idPaciente}")
	@Transactional
	public ResponseEntity<Void> removerPaciente(@PathVariable Long idPaciente) {
		return pacienteService.removerPaciente(idPaciente);
	}
}
