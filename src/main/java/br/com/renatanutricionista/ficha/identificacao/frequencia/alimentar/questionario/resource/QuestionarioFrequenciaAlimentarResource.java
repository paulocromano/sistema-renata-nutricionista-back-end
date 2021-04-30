package br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.resource;

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

import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.dto.InformacoesPreviasQuestionariosDTO;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.dto.QuestionarioFrequenciaAlimentarDTO;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.form.QuestionarioFrequenciaAlimentarFORM;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.service.QuestionarioFrequenciaAlimentarService;


@RestController
@RequestMapping("/questionario-frequencia-alimentar")
public class QuestionarioFrequenciaAlimentarResource {

	@Autowired
	private QuestionarioFrequenciaAlimentarService questionarioFrequenciaAlimentarService;
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/informacoes-previas/{idPaciente}")
	public ResponseEntity<InformacoesPreviasQuestionariosDTO> buscarInformacoesPreviasQuestionariosDoPaciente(@PathVariable Long idPaciente) {
		return questionarioFrequenciaAlimentarService.buscarInformacoesPreviasQuestionariosDoPaciente(idPaciente);
	}
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/{idQuestionario}")
	public ResponseEntity<QuestionarioFrequenciaAlimentarDTO> buscarQuestionarioFrequenciaAlimentarDoPaciente(@PathVariable Long idQuestionario) {
		return questionarioFrequenciaAlimentarService.buscarQuestionarioFrequenciaAlimentarDoPaciente(idQuestionario);
	}
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping("/{idPaciente}")
	@Transactional
	public ResponseEntity<Void> cadastrarQuestionarioFrequenciaAlimentar(@PathVariable Long idPaciente, 
			@RequestBody @Valid QuestionarioFrequenciaAlimentarFORM questionarioFrequenciaAlimentar) {
		
		return questionarioFrequenciaAlimentarService.cadastrarQuestionarioFrequenciaAlimentar(idPaciente, questionarioFrequenciaAlimentar);
	}
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/{idQuestionarioFrequenciaAlimentar}")
	@Transactional
	public ResponseEntity<Void> excluirQuestionarioFrequenciaAlimentar(@PathVariable Long idQuestionarioFrequenciaAlimentar) {
		return questionarioFrequenciaAlimentarService.excluirQuestionarioFrequenciaAlimentar(idQuestionarioFrequenciaAlimentar);
	}
}
