package br.com.renatanutricionista.ficha.identificacao.historico.atividade.fisica.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.renatanutricionista.exception.custom.ObjectNotFoundException;
import br.com.renatanutricionista.ficha.identificacao.historico.atividade.fisica.form.HistoricoAtividadeFisicaFORM;
import br.com.renatanutricionista.ficha.identificacao.historico.atividade.fisica.model.HistoricoAtividadeFisica;
import br.com.renatanutricionista.ficha.identificacao.historico.atividade.fisica.repository.HistoricoAtividadeFisicaRepository;
import br.com.renatanutricionista.paciente.model.Paciente;
import br.com.renatanutricionista.paciente.service.PacienteService;


@Service
public class HistoricoAtividadeFisicaService {

	@Autowired
	private HistoricoAtividadeFisicaRepository historicoAtividadeFisicaRepository;
	
	@Autowired
	private PacienteService pacienteService;
	
	
	public ResponseEntity<Void> cadastrarAtividadeFisicaDoPaciente(Long idPaciente, HistoricoAtividadeFisicaFORM historicoAtividadeFisicaFORM) {
		Paciente paciente = pacienteService.verificarSePacienteExiste(idPaciente);
		historicoAtividadeFisicaRepository.save(historicoAtividadeFisicaFORM.converterParaAtividadeFisica(paciente));
		
		pacienteService.atualizarDataHoraUltimaAlteracaoNosDadosDoPaciente(paciente);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	public ResponseEntity<Void> excluirHistoricoAtividadeFisica(Long idHistoricoAtividadeFisica) {
		HistoricoAtividadeFisica historicoAtividadeFisica = verificarSeHistoricoAtividadeFisicaExiste(idHistoricoAtividadeFisica);
		historicoAtividadeFisicaRepository.delete(historicoAtividadeFisica);
		
		return ResponseEntity.noContent().build();
	}
	
	
	private HistoricoAtividadeFisica verificarSeHistoricoAtividadeFisicaExiste(Long idHistoricoAtividadeFisica) {
		if (Objects.isNull(idHistoricoAtividadeFisica))
			throw new NullPointerException("O ID do Histórico da Atividadde Física não pode ser nulo!");
		
		Optional<HistoricoAtividadeFisica> historicoAtividadeFisica = historicoAtividadeFisicaRepository.findById(idHistoricoAtividadeFisica);
		
		if (historicoAtividadeFisica.isEmpty())
			throw new ObjectNotFoundException("Histórico da Atividadde Física não encontrado!");
		
		return historicoAtividadeFisica.get();
	}
}
