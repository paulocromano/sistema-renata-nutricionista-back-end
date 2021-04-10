package br.com.renatanutricionista.tabelas.parametro.paciente.service;

import java.util.Objects;
import java.util.Optional;

import javax.persistence.Convert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.renatanutricionista.exception.custom.ObjectNotFoundException;
import br.com.renatanutricionista.tabelas.parametro.paciente.dto.PacienteParametroDTO;
import br.com.renatanutricionista.tabelas.parametro.paciente.form.PacienteParametroFORM;
import br.com.renatanutricionista.tabelas.parametro.paciente.model.PacienteParametro;
import br.com.renatanutricionista.tabelas.parametro.paciente.repository.PacienteParametroRepository;


@Service
public class PacienteParametroService {

	@Autowired
	private PacienteParametroRepository pacienteParametroRepository;
	
	@Value("${id.paciente.parametro}")
	@Convert(attributeName = "idPacienteParametro", converter = Integer.class)
	private Integer idPacienteParametro;
	
	
	public ResponseEntity<PacienteParametroDTO> buscarInformacoesPacienteParametro() {
		return ResponseEntity.ok().body(new PacienteParametroDTO(buscarPacienteParametro()));
	}
	
	
	public ResponseEntity<Void> atualizarPacienteParametro(PacienteParametroFORM pacienteParametro) {
		pacienteParametro.atualizarInformacoesPacienteParametro(buscarPacienteParametro());
		
		return ResponseEntity.ok().build();
	}
	
	
	public PacienteParametro buscarPacienteParametro() {
		if (Objects.isNull(idPacienteParametro))
			throw new NullPointerException("O ID do Parâmetro de Paciente não pode ser nulo!");
			
		Optional<PacienteParametro> pacienteParametro = pacienteParametroRepository.findById(idPacienteParametro);
		
		if (pacienteParametro.isEmpty())
			throw new ObjectNotFoundException("Paciente Parâmetro não encontrado!");
		
		return pacienteParametro.get();
	}
}
