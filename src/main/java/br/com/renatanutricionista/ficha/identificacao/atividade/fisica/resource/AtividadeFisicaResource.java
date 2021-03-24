package br.com.renatanutricionista.ficha.identificacao.atividade.fisica.resource;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.renatanutricionista.ficha.identificacao.atividade.fisica.form.AtividadeFisicaFORM;
import br.com.renatanutricionista.ficha.identificacao.atividade.fisica.service.AtividadeFisicaService;


@RestController
@RequestMapping("/atividade-fisica-paciente")
public class AtividadeFisicaResource {
	
	@Autowired
	private AtividadeFisicaService atividadeFisicaResource;

	
	@PostMapping("/{idPaciente}")
	@Transactional
	public ResponseEntity<Void> cadastrarAtividadeFisicaDoPaciente(@PathVariable Long idPaciente, 
			@RequestBody @Valid AtividadeFisicaFORM atividadeFisicaFORM) {
		
		return atividadeFisicaResource.cadastrarAtividadeFisicaDoPaciente(idPaciente, atividadeFisicaFORM);
	}
}
