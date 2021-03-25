package br.com.renatanutricionista.paciente.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.renatanutricionista.paciente.dto.PacienteDTO;
import br.com.renatanutricionista.paciente.form.AtualizacaoPacienteFORM;
import br.com.renatanutricionista.paciente.form.PacienteFORM;
import br.com.renatanutricionista.paciente.model.Paciente;
import br.com.renatanutricionista.paciente.repository.PacienteRepository;
import br.com.renatanutricionista.paciente.utils.PacienteUtils;


@Service
public class PacienteService {

	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Autowired
	private PacienteUtils pacienteUtils;
	
	
	public ResponseEntity<List<PacienteDTO>> listarTodosPacientes() {	
		return ResponseEntity.ok().body(PacienteDTO.converterParaListaPacienteDTO(pacienteRepository.findAll()));
	}
	
	
	public ResponseEntity<Void> cadastrarPacienteEndereco(PacienteFORM pacienteFORM) {
		pacienteRepository.save(pacienteFORM.converterParaPaciente());
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	public ResponseEntity<Void> atualizarPacienteEndereco(Long idPaciente, AtualizacaoPacienteFORM atualizacaoPaciente) {
		Paciente paciente = pacienteUtils.verificarSePacienteExiste(idPaciente);
		
		atualizacaoPaciente.atualizarPacienteEndereco(paciente);
		
		return ResponseEntity.ok().build();
	}
	
	
	public ResponseEntity<Void> removerPaciente(Long idPaciente) {
		Paciente paciente = pacienteUtils.verificarSePacienteExiste(idPaciente);
		pacienteRepository.delete(paciente);
		
		return ResponseEntity.noContent().build();
	}
}
