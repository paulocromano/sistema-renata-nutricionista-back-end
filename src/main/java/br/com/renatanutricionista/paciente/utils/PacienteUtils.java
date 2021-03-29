package br.com.renatanutricionista.paciente.utils;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.renatanutricionista.exception.custom.ObjectNotFoundException;
import br.com.renatanutricionista.paciente.model.Paciente;
import br.com.renatanutricionista.paciente.repository.PacienteRepository;


@Service
public final class PacienteUtils {
	
	@Autowired
	private PacienteRepository pacienteRepository;
	

	public final void atualizarDataHoraUltimaAlteracaoNosDadosDoPaciente(Paciente paciente) {
		paciente.setDataHoraUltimaAtualizacaoDadosDoPaciente(LocalDateTime.now());
	}
	
	
	public final Paciente verificarSePacienteExiste(Long idPaciente) {
		if (Objects.isNull(idPaciente))
			throw new NullPointerException("O ID do Paciente está nulo!");
			
		Optional<Paciente> paciente = pacienteRepository.findById(idPaciente);
		
		if (paciente.isEmpty())
			throw new ObjectNotFoundException("Paciente não encontrado!");
		
		return paciente.get();
	}
}
