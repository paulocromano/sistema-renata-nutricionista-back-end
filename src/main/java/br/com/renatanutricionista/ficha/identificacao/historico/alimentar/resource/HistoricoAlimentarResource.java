package br.com.renatanutricionista.ficha.identificacao.historico.alimentar.resource;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.renatanutricionista.ficha.identificacao.historico.alimentar.form.HistoricoAlimentarFORM;
import br.com.renatanutricionista.ficha.identificacao.historico.alimentar.service.HistoricoAlimentarService;


@RestController
@RequestMapping("/historico-alimentar-paciente")
public class HistoricoAlimentarResource {

	@Autowired
	private HistoricoAlimentarService historicoAlimentarService;
	
	
	@PostMapping("/{idPaciente}")
	@Transactional
	public ResponseEntity<Void> cadastrarHistoricoAlimentarDoPaciente(@PathVariable Long idPaciente, 
			@RequestBody @Valid HistoricoAlimentarFORM historicoAlimentarFORM) {
		
		return historicoAlimentarService.cadastrarHistoricoAlimentarDoPaciente(idPaciente, historicoAlimentarFORM);
	}
}
