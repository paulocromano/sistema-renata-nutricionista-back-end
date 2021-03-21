package br.com.renatanutricionista.utils;

import java.util.Objects;
import java.util.Optional;

import br.com.renatanutricionista.exception.custom.ObjectNotFoundException;
import br.com.renatanutricionista.paciente.model.Paciente;
import br.com.renatanutricionista.paciente.repository.PacienteRepository;


public final class VerificacaoUtils {	
	
	public static final Paciente verificarSePacienteExiste(Long idPaciente, PacienteRepository pacienteRepository) {
		if (Objects.isNull(idPaciente))
			throw new NullPointerException("O ID do Paciente está nulo!");
			
		Optional<Paciente> paciente = pacienteRepository.findById(idPaciente);
		
		if (paciente.isEmpty())
			throw new ObjectNotFoundException("Paciente não encontrado!");
		
		return paciente.get();
	}
}
