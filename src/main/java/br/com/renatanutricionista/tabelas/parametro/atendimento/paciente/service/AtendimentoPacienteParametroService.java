package br.com.renatanutricionista.tabelas.parametro.atendimento.paciente.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.renatanutricionista.exception.custom.ObjectNotFoundException;
import br.com.renatanutricionista.tabelas.parametro.atendimento.paciente.dto.AtendimentoPacienteParametroDTO;
import br.com.renatanutricionista.tabelas.parametro.atendimento.paciente.form.AtendimentoPacienteParametroFORM;
import br.com.renatanutricionista.tabelas.parametro.atendimento.paciente.model.AtendimentoPacienteParametro;
import br.com.renatanutricionista.tabelas.parametro.atendimento.paciente.repository.AtendimentoPacienteParametroRepository;


@Service
public class AtendimentoPacienteParametroService {

	@Autowired
	private AtendimentoPacienteParametroRepository atendimentoPacienteParametroRepository;
	
	
	public ResponseEntity<AtendimentoPacienteParametroDTO> buscarInformacoesDosParametrosDeAtendimentoDePaciente() {
		AtendimentoPacienteParametro atendimentoPacienteParametro = verificarSeExisteAtendimentoPacienteParametro(1);
		
		return ResponseEntity.ok().body(new AtendimentoPacienteParametroDTO(atendimentoPacienteParametro));
	}
	
	
	public ResponseEntity<AtendimentoPacienteParametroDTO> atualizarInformacoesParametrosAtendimentoDoPaciente(
			Integer idAtendimentoPacienteParametro, AtendimentoPacienteParametroFORM atendimentoPacienteParametro) {
		
		AtendimentoPacienteParametro atendimento = verificarSeExisteAtendimentoPacienteParametro(idAtendimentoPacienteParametro);
		atendimentoPacienteParametro.atualizarInformacoesDosParametrosParaAtendimentoDePaciente(atendimento);
		
		return ResponseEntity.ok().body(new AtendimentoPacienteParametroDTO(atendimento));
	}
	
	
	private AtendimentoPacienteParametro verificarSeExisteAtendimentoPacienteParametro(Integer idAtendimentoPacienteParametro) {
		if (Objects.isNull(idAtendimentoPacienteParametro))
			throw new NullPointerException("O ID do Parâmetro de Atendimento de Paciente não pode estar nulo!");
			
		Optional<AtendimentoPacienteParametro> atendimento = atendimentoPacienteParametroRepository.findById(idAtendimentoPacienteParametro);
		
		if (atendimento.isEmpty())
			throw new ObjectNotFoundException("Parâmetros de Atendimento de Paciente não encontrado!");
		
		return atendimento.get();
	}
}
