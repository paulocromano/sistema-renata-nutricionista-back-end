package br.com.renatanutricionista.ficha.identificacao.atividade.fisica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.renatanutricionista.ficha.identificacao.atividade.fisica.form.AtividadeFisicaFORM;
import br.com.renatanutricionista.ficha.identificacao.atividade.fisica.repository.AtividadeFisicaRepository;
import br.com.renatanutricionista.paciente.model.Paciente;
import br.com.renatanutricionista.paciente.repository.PacienteRepository;
import br.com.renatanutricionista.paciente.utils.PacienteUtils;
import br.com.renatanutricionista.utils.VerificacaoUtils;


@Service
public class AtividadeFisicaService {

	@Autowired
	private AtividadeFisicaRepository atividadeFisicaRepository;
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Autowired
	private PacienteUtils pacienteUtils;
	
	
	public ResponseEntity<Void> cadastrarAtividadeFisicaDoPaciente(Long idPaciente, AtividadeFisicaFORM atividadeFisicaFORM) {
		Paciente paciente = VerificacaoUtils.verificarSePacienteExiste(idPaciente, pacienteRepository);
		atividadeFisicaRepository.save(atividadeFisicaFORM.converterParaAtividadeFisica(paciente));
		
		pacienteUtils.atualizarDataHoraUltimaAlteracaoNosDadosDoPaciente(paciente);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
