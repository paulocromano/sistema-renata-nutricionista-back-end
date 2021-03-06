package br.com.renatanutricionista.ficha.identificacao.historico.atividade.fisica.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.renatanutricionista.exception.custom.ObjectNotFoundException;
import br.com.renatanutricionista.ficha.identificacao.historico.atividade.fisica.dto.InformacoesHistoricosAtividadeFisicaDTO;
import br.com.renatanutricionista.ficha.identificacao.historico.atividade.fisica.form.HistoricoAtividadeFisicaFORM;
import br.com.renatanutricionista.ficha.identificacao.historico.atividade.fisica.model.HistoricoAtividadeFisica;
import br.com.renatanutricionista.ficha.identificacao.historico.atividade.fisica.repository.HistoricoAtividadeFisicaRepository;
import br.com.renatanutricionista.paciente.model.Paciente;
import br.com.renatanutricionista.paciente.service.PacienteService;
import br.com.renatanutricionista.tabelas.parametro.atendimento.paciente.model.AtendimentoPacienteParametro;
import br.com.renatanutricionista.tabelas.parametro.atendimento.paciente.service.AtendimentoPacienteParametroService;


@Service
public class HistoricoAtividadeFisicaService {

	@Autowired
	private HistoricoAtividadeFisicaRepository historicoAtividadeFisicaRepository;
	
	@Autowired
	private PacienteService pacienteService;
	
	@Autowired
	private AtendimentoPacienteParametroService atendimentoPacienteParametroService;
	
	
	public ResponseEntity<InformacoesHistoricosAtividadeFisicaDTO> buscarInformacoesHistoricosAtividadeFisicaDoPaciente(Long idPaciente) {
		Paciente paciente = pacienteService.verificarSePacienteExiste(idPaciente);
		AtendimentoPacienteParametro atendimentoPacienteParametro = atendimentoPacienteParametroService.buscarAtendimentoPacienteParametro();
		
		return ResponseEntity.ok().body(new InformacoesHistoricosAtividadeFisicaDTO(paciente, atendimentoPacienteParametro));
	}
	
	
	public ResponseEntity<Void> cadastrarAtividadeFisicaDoPaciente(Long idPaciente, HistoricoAtividadeFisicaFORM historicoAtividadeFisicaFORM) {
		Paciente paciente = pacienteService.verificarSePacienteExiste(idPaciente);
		historicoAtividadeFisicaRepository.save(historicoAtividadeFisicaFORM.converterParaAtividadeFisica(paciente));
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	public ResponseEntity<Void> excluirHistoricoAtividadeFisica(Long idHistoricoAtividadeFisica) {
		HistoricoAtividadeFisica historicoAtividadeFisica = verificarSeHistoricoAtividadeFisicaExiste(idHistoricoAtividadeFisica);
		historicoAtividadeFisicaRepository.delete(historicoAtividadeFisica);
		
		return ResponseEntity.noContent().build();
	}
	
	
	private HistoricoAtividadeFisica verificarSeHistoricoAtividadeFisicaExiste(Long idHistoricoAtividadeFisica) {
		if (Objects.isNull(idHistoricoAtividadeFisica))
			throw new NullPointerException("O ID do Hist??rico da Atividadde F??sica n??o pode ser nulo!");
		
		Optional<HistoricoAtividadeFisica> historicoAtividadeFisica = historicoAtividadeFisicaRepository.findById(idHistoricoAtividadeFisica);
		
		if (historicoAtividadeFisica.isEmpty())
			throw new ObjectNotFoundException("Hist??rico da Atividadde F??sica n??o encontrado!");
		
		return historicoAtividadeFisica.get();
	}
}
