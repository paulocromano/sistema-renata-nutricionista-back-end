package br.com.renatanutricionista.endereco.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.renatanutricionista.endereco.dto.EnderecoDTO;
import br.com.renatanutricionista.endereco.model.Endereco;
import br.com.renatanutricionista.endereco.model.EnderecoAPIViaCEP;
import br.com.renatanutricionista.endereco.repository.EnderecoRepository;
import br.com.renatanutricionista.exception.custom.ObjectNotFoundException;


@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private CEPService cepService;
	
	
	public ResponseEntity<EnderecoDTO> buscarEnderecoPaciente(Long idPaciente) {
		Endereco endereco = verificarSeExisteEnderecoPeloIDPaciente(idPaciente);
		
		return ResponseEntity.ok().body(new EnderecoDTO(endereco));
	}
	
	
	public ResponseEntity<EnderecoDTO> buscarEnderecoPeloCEP(String cep) {
		validarCEP(cep);
		EnderecoAPIViaCEP endereco = cepService.buscarEnderecoPorCEP(cep);

		if (Objects.nonNull(endereco.getErro()))
			throw new ObjectNotFoundException("CEP não encontrado!");
		
		return ResponseEntity.ok().body(new EnderecoDTO(endereco));
	}
	
	
	private void validarCEP(String cep) {
		if (Objects.isNull(cep))
			throw new NullPointerException("O CEP não pode ser nulo!");
		
		if (cep.length() != 8)
			throw new IllegalArgumentException("O formato do CEP é inválido!");
		
		try {
			Integer.parseInt(cep);
		}
		catch (NumberFormatException e) {
			throw new NumberFormatException("O CEP deve conter apenas números!");
		}
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
