package br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.renatanutricionista.exception.custom.ObjectNotFoundException;
import br.com.renatanutricionista.exception.custom.PacienteException;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.alimentos.model.AlimentoFrequenciaAlimentar;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.alimentos.repository.AlimentoFrequenciaAlimentarRepository;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.form.FrequenciaAlimentarFORM;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.model.FrequenciaAlimentar;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.dto.InformacoesPreviasQuestionariosDTO;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.dto.QuestionarioFrequenciaAlimentarDTO;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.form.QuestionarioFrequenciaAlimentarFORM;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.model.QuestionarioFrequenciaAlimentar;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.repository.QuestionarioFrequenciaAlimentarRepository;
import br.com.renatanutricionista.paciente.model.Paciente;
import br.com.renatanutricionista.paciente.service.PacienteService;
import br.com.renatanutricionista.tabelas.parametro.paciente.model.PacienteParametro;
import br.com.renatanutricionista.tabelas.parametro.paciente.service.PacienteParametroService;


@Service
public class QuestionarioFrequenciaAlimentarService {
	
	@Autowired
	private QuestionarioFrequenciaAlimentarRepository questionarioFrequenciaAlimentarRepository;
	
	@Autowired
	private AlimentoFrequenciaAlimentarRepository alimentoFrequenciaAlimentarRepository;

	@Autowired
	private PacienteService pacienteService;
	
	@Autowired
	private PacienteParametroService pacienteParametroService;
	
	
	public ResponseEntity<InformacoesPreviasQuestionariosDTO> buscarInformacoesPreviasQuestionariosDoPaciente(Long idPaciente) {
		Paciente paciente = pacienteService.verificarSePacienteExiste(idPaciente);
		PacienteParametro pacienteParametro = pacienteParametroService.buscarPacienteParametro();

		return ResponseEntity.ok().body(new InformacoesPreviasQuestionariosDTO(paciente, pacienteParametro));
	}
	
	
	public ResponseEntity<QuestionarioFrequenciaAlimentarDTO> buscarQuestionarioFrequenciaAlimentarDoPaciente(Long idQuestionario) {
		return ResponseEntity.ok().body(new QuestionarioFrequenciaAlimentarDTO(verificarSeQuestionarioFrequenciaAlimentarExiste(idQuestionario)));
	} 
	
	
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
	
	
	public ResponseEntity<Void> excluirQuestionarioFrequenciaAlimentar(Long idQuestionarioFrequenciaAlimentar) {
		QuestionarioFrequenciaAlimentar questionario = verificarSeQuestionarioFrequenciaAlimentarExiste(idQuestionarioFrequenciaAlimentar);
		questionarioFrequenciaAlimentarRepository.delete(questionario);
		
		return ResponseEntity.noContent().build();
	}
	
	
	private QuestionarioFrequenciaAlimentar verificarSeQuestionarioFrequenciaAlimentarExiste(Long idQuestionarioFrequenciaAlimentar) {
		if (Objects.isNull(idQuestionarioFrequenciaAlimentar))
			throw new NullPointerException("O ID do Questionário de Frequência Alimentar não pode ser nulo!");
		
		Optional<QuestionarioFrequenciaAlimentar> questionario = questionarioFrequenciaAlimentarRepository.findById(idQuestionarioFrequenciaAlimentar);
		
		if (questionario.isEmpty())
			throw new ObjectNotFoundException("Questionário de Frequência Alimentar não encontrado!");
		
		return questionario.get();
	}
}
