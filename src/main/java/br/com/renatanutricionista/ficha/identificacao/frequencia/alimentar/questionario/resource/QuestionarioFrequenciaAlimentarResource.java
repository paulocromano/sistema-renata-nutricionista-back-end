package br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.service.QuestionarioFrequenciaAlimentarService;


@RestController
@RequestMapping("/questionario-frequencia-alimentar")
public class QuestionarioFrequenciaAlimentarResource {

	@Autowired
	private QuestionarioFrequenciaAlimentarService questionarioFrequenciaAlimentarService;
}
