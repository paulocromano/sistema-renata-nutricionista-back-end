package br.com.renatanutricionista.ficha.identificacao.historico.social.resource;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.renatanutricionista.ficha.identificacao.historico.social.form.HistoricoSocialFORM;
import br.com.renatanutricionista.ficha.identificacao.historico.social.service.HistoricoSocialService;


@RestController
@RequestMapping("/historico-social-paciente")
public class HistoricoSocialResource {

	@Autowired
	private HistoricoSocialService historicoSocialService;
	
	
	@PostMapping("/{idPaciente}")
	@Transactional
	public ResponseEntity<Void> cadastrarHistoricoSocialDoPaciente(@PathVariable Long idPaciente, 
			@RequestBody @Valid HistoricoSocialFORM historicoSocialFORM) {
		
		return historicoSocialService.cadastrarHistoricoSocialDoPaciente(idPaciente, historicoSocialFORM);
	}
}
