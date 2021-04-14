package br.com.renatanutricionista.ficha.identificacao.historico.atividade.fisica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.renatanutricionista.ficha.identificacao.historico.atividade.fisica.form.HistoricoAtividadeFisicaFORM;
import br.com.renatanutricionista.ficha.identificacao.historico.atividade.fisica.repository.HistoricoAtividadeFisicaRepository;
import br.com.renatanutricionista.paciente.model.Paciente;
import br.com.renatanutricionista.paciente.utils.PacienteUtils;


@Service
public class HistoricoAtividadeFisicaService {

	@Autowired
	private HistoricoAtividadeFisicaRepository historicoAtividadeFisicaRepository;
	
	@Autowired
	private PacienteUtils pacienteUtils;
	
	
	public ResponseEntity<Void> cadastrarAtividadeFisicaDoPaciente(Long idPaciente, HistoricoAtividadeFisicaFORM historicoAtividadeFisicaFORM) {
		Paciente paciente = pacienteUtils.verificarSePacienteExiste(idPaciente);
		historicoAtividadeFisicaRepository.save(historicoAtividadeFisicaFORM.converterParaAtividadeFisica(paciente));
		
		pacienteUtils.atualizarDataHoraUltimaAlteracaoNosDadosDoPaciente(paciente);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
