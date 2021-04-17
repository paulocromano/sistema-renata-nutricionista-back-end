package br.com.renatanutricionista.paciente.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.renatanutricionista.exception.custom.ObjectNotFoundException;
import br.com.renatanutricionista.paciente.dto.PacienteDTO;
import br.com.renatanutricionista.paciente.dto.PacientePreviaHistoricosDTO;
import br.com.renatanutricionista.paciente.form.AtualizacaoPacienteFORM;
import br.com.renatanutricionista.paciente.form.PacienteFORM;
import br.com.renatanutricionista.paciente.model.Paciente;
import br.com.renatanutricionista.paciente.repository.PacienteRepository;
import br.com.renatanutricionista.tabelas.parametro.paciente.model.PacienteParametro;
import br.com.renatanutricionista.tabelas.parametro.paciente.service.PacienteParametroService;


@Service
public class PacienteService {

	@Autowired
	private PacienteRepository pacienteRepository;

	@Autowired
	private PacienteParametroService pacienteParametroService;
	
	
	public ResponseEntity<List<PacienteDTO>> listarTodosPacientesPorOrdemAlfabetica() {	
		return ResponseEntity.ok().body(PacienteDTO.converterParaListaPacienteDTOEmOrdemAlfabetica(pacienteRepository.findAll()));
	}
	
	
	public ResponseEntity<PacientePreviaHistoricosDTO> buscarInformacoesPreviasHistoricosDoPaciente(Long idPaciente) {
		Paciente paciente = verificarSePacienteExiste(idPaciente);
		PacienteParametro pacienteParametro = pacienteParametroService.buscarPacienteParametro();
		
		return ResponseEntity.ok().body(new PacientePreviaHistoricosDTO(paciente, pacienteParametro));
	}
	
	
	public ResponseEntity<Void> cadastrarPacienteEndereco(PacienteFORM pacienteFORM) {
		pacienteRepository.save(pacienteFORM.converterParaPaciente());
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	public ResponseEntity<Void> atualizarPacienteEndereco(Long idPaciente, AtualizacaoPacienteFORM atualizacaoPaciente) {
		Paciente paciente = verificarSePacienteExiste(idPaciente);
		
		atualizacaoPaciente.atualizarPacienteEndereco(paciente);
		
		return ResponseEntity.ok().build();
	}
	
	
	public ResponseEntity<Void> removerPaciente(Long idPaciente) {
		Paciente paciente = verificarSePacienteExiste(idPaciente);
		pacienteRepository.delete(paciente);
		
		return ResponseEntity.noContent().build();
	}
	
	
	public void atualizarDataHoraUltimaAlteracaoNosDadosDoPaciente(Paciente paciente) {
		paciente.setDataHoraUltimaAtualizacaoDadosDoPaciente(LocalDateTime.now());
	}
	
	
	public Paciente verificarSePacienteExiste(Long idPaciente) {
		if (Objects.isNull(idPaciente))
			throw new NullPointerException("O ID do Paciente está nulo!");
			
		Optional<Paciente> paciente = pacienteRepository.findById(idPaciente);
		
		if (paciente.isEmpty())
			throw new ObjectNotFoundException("Paciente não encontrado!");
		
		return paciente.get();
	}
}
