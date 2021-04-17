package br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.renatanutricionista.exception.custom.PacienteException;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.alimentos.model.AlimentoFrequenciaAlimentar;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.alimentos.repository.AlimentoFrequenciaAlimentarRepository;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.form.FrequenciaAlimentarFORM;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.model.FrequenciaAlimentar;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.form.QuestionarioFrequenciaAlimentarFORM;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.repository.QuestionarioFrequenciaAlimentarRepository;
import br.com.renatanutricionista.paciente.model.Paciente;
import br.com.renatanutricionista.paciente.service.PacienteService;


@Service
public class QuestionarioFrequenciaAlimentarService {
	
	@Autowired
	private QuestionarioFrequenciaAlimentarRepository questionarioFrequenciaAlimentarRepository;
	
	@Autowired
	private AlimentoFrequenciaAlimentarRepository alimentoFrequenciaAlimentarRepository;

	@Autowired
	private PacienteService pacienteService;
	
	
	public ResponseEntity<Void> cadastrarQuestionarioFrequenciaAlimentar(Long idPaciente, QuestionarioFrequenciaAlimentarFORM questionarioFrequenciaAlimentar) {
		Paciente paciente = pacienteService.verificarSePacienteExiste(idPaciente);
		Set<FrequenciaAlimentar> frequenciaAlimentar = gerarSetFrequenciaAlimentar(questionarioFrequenciaAlimentar.getFrequenciaConsumoAlimentos());
		
		questionarioFrequenciaAlimentarRepository.save(questionarioFrequenciaAlimentar.converterParaQuestionarioFrequenciaAlimentar(paciente, frequenciaAlimentar));
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	private Set<FrequenciaAlimentar> gerarSetFrequenciaAlimentar(Set<FrequenciaAlimentarFORM> frequenciaAlimentar) {
		List<AlimentoFrequenciaAlimentar> alimentos = alimentoFrequenciaAlimentarRepository.findAll();
		
		if (frequenciaAlimentar.size() != alimentos.size())
			throw new PacienteException("O número de Alimentos do Questionário de Frequência Alimentar deve ser igual aos "
					+ "contidos na tabela de Alimentos para este questionário!");
		
		try {
			return frequenciaAlimentar.stream().map(frequencia -> 
				new FrequenciaAlimentar(alimentos.get(frequencia.getIdAlimentoFrequenciaAlimentar() - 1), frequencia.getFrequenciaConsumoAlimento()))
				.collect(Collectors.toSet());
		}
		
		catch (IndexOutOfBoundsException e) {
			throw new IllegalArgumentException("Existe(m) Alimento(s) inválido(s) na lista de Alimentos " + 
					"do Questionário informada pelo Usuário!");
		}
	}
}
