package br.com.renatanutricionista.endereco.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.renatanutricionista.endereco.dto.EnderecoDTO;
import br.com.renatanutricionista.endereco.model.Endereco;
import br.com.renatanutricionista.endereco.repository.EnderecoRepository;
import br.com.renatanutricionista.exception.custom.ObjectNotFoundException;


@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	
	public ResponseEntity<EnderecoDTO> buscarEnderecoPaciente(Long idPaciente) {
		Endereco endereco = verificarSeExisteEnderecoPeloIDPaciente(idPaciente);
		
		return ResponseEntity.ok().body(new EnderecoDTO(endereco));
	}
	
	
	private Endereco verificarSeExisteEnderecoPeloIDPaciente(Long idPaciente) {
		if (Objects.isNull(idPaciente))
			throw new NullPointerException("O ID do Paciente para buscar o Endereço "
					+ "não pode ser nulo!");
		
		Optional<Endereco> endereco = enderecoRepository.findByPaciente_Id(idPaciente);
		
		if (endereco.isEmpty())
			throw new ObjectNotFoundException("Endereço não encontrado!");
		
		return endereco.get();
	}
}
