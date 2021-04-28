package br.com.renatanutricionista.ficha.identificacao.historico.social.resource;

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

import br.com.renatanutricionista.ficha.identificacao.historico.social.dto.HistoricoSocialDTO;
import br.com.renatanutricionista.ficha.identificacao.historico.social.dto.InformacoesPreviasHistoricosSociaisDTO;
import br.com.renatanutricionista.ficha.identificacao.historico.social.form.HistoricoSocialFORM;
import br.com.renatanutricionista.ficha.identificacao.historico.social.service.HistoricoSocialService;


@RestController
@RequestMapping("/historico-social-paciente")
public class HistoricoSocialResource {

	@Autowired
	private HistoricoSocialService historicoSocialService;
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/historicos/{idPaciente}")
	public ResponseEntity<InformacoesPreviasHistoricosSociaisDTO> buscarInformacoesPreviasHistoricosSociaisDoPaciente(@PathVariable Long idPaciente) {
		return historicoSocialService.buscarInformacoesPreviasHistoricosSociaisDoPaciente(idPaciente);
	}
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/{idHistoricoSocial}")
	public ResponseEntity<HistoricoSocialDTO> buscarHistoricoSocialDoPaciente(@PathVariable Long idHistoricoSocial) {
		return historicoSocialService.buscarHistoricoSocialDoPaciente(idHistoricoSocial);
	}
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping("/{idPaciente}")
	@Transactional
	public ResponseEntity<Void> cadastrarHistoricoSocialDoPaciente(@PathVariable Long idPaciente, 
			@RequestBody @Valid HistoricoSocialFORM historicoSocialFORM) {
		
		return historicoSocialService.cadastrarHistoricoSocialDoPaciente(idPaciente, historicoSocialFORM);
	}
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/{idHistoricoSocial}")
	@Transactional
	public ResponseEntity<Void> excluirHistoricoSocial(@PathVariable Long idHistoricoSocial) {
		return historicoSocialService.excluirHistoricoSocial(idHistoricoSocial);
	}
}
