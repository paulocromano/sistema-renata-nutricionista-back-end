package br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.renatanutricionista.exception.custom.PacienteException;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.alimentos.repository.AlimentoFrequenciaAlimentarRepository;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.form.FrequenciaAlimentarFORM;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.form.QuestionarioFrequenciaAlimentarFORM;
import br.com.renatanutricionista.paciente.model.Paciente;
import br.com.renatanutricionista.paciente.utils.PacienteUtils;


@Service
public class QuestionarioFrequenciaAlimentarService {
	
	@Autowired
	private AlimentoFrequenciaAlimentarRepository alimentoFrequenciaAlimentarRepository;

	@Autowired
	private PacienteUtils pacienteUtils;
	
	public ResponseEntity<Void> cadastrarQuestionarioFrequenciaAlimentar(Long idPaciente, QuestionarioFrequenciaAlimentarFORM questionarioFrequenciaAlimentar) {
		Paciente paciente = pacienteUtils.verificarSePacienteExiste(idPaciente);
		validarQuantidadeAlimentosPresentesNoQuestionario(questionarioFrequenciaAlimentar.getFrequenciaConsumoAlimentos());

		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	private void validarQuantidadeAlimentosPresentesNoQuestionario(Set<FrequenciaAlimentarFORM> frequenciaAlimentar) {
		long totalAlimentosParaQuestionarioDeFrequenciaAlimentar = alimentoFrequenciaAlimentarRepository.count();
		
		if (frequenciaAlimentar.size() != totalAlimentosParaQuestionarioDeFrequenciaAlimentar)
			throw new PacienteException("O número de Alimentos do Questionário de Frequência Alimentar deve ser igual aos "
					+ "contidos na tabela de Alimentos para este questinário!");
	}
}
