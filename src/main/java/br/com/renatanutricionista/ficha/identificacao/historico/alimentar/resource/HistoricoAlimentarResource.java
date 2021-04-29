package br.com.renatanutricionista.ficha.identificacao.historico.alimentar.resource;

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
import org.springframework.web.bind.annotation.RestController;

import br.com.renatanutricionista.ficha.identificacao.historico.alimentar.dto.HistoricoAlimentarDTO;
import br.com.renatanutricionista.ficha.identificacao.historico.alimentar.dto.InformacoesPreviasHistoricosAlimentaresDTO;
import br.com.renatanutricionista.ficha.identificacao.historico.alimentar.form.HistoricoAlimentarFORM;
import br.com.renatanutricionista.ficha.identificacao.historico.alimentar.service.HistoricoAlimentarService;


@RestController
@RequestMapping("/historico-alimentar")
public class HistoricoAlimentarResource {

	@Autowired
	private HistoricoAlimentarService historicoAlimentarService;
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/informacoes-previas/{idPaciente}")
	public ResponseEntity<InformacoesPreviasHistoricosAlimentaresDTO> buscarInformacoesPreviasHistoricosAlimentaresDoPaciente(@PathVariable Long idPaciente) {
		return historicoAlimentarService.buscarInformacoesPreviasHistoricosAlimentaresDoPaciente(idPaciente);
	}
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/paciente/{idHistoricoAlimentar}")
	public ResponseEntity<HistoricoAlimentarDTO> buscarHistoricoAlimentarDoPaciente(@PathVariable Long idHistoricoAlimentar) {
		return historicoAlimentarService.buscarHistoricoAlimentarDoPaciente(idHistoricoAlimentar);
	}
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping("/{idPaciente}")
	@Transactional
	public ResponseEntity<Void> cadastrarHistoricoAlimentarDoPaciente(@PathVariable Long idPaciente, 
			@RequestBody @Valid HistoricoAlimentarFORM historicoAlimentarFORM) {
		
		return historicoAlimentarService.cadastrarHistoricoAlimentarDoPaciente(idPaciente, historicoAlimentarFORM);
	}
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/{idHistoricoAlimentar}")
	@Transactional
	public ResponseEntity<Void> excluirHistoricoAlimentar(@PathVariable Long idHistoricoAlimentar) {
		return historicoAlimentarService.excluirHistoricoAlimentar(idHistoricoAlimentar);
	}
}
