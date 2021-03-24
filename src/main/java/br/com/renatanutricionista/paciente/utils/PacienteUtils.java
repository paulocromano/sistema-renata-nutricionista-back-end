package br.com.renatanutricionista.paciente.utils;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.renatanutricionista.paciente.model.Paciente;
import br.com.renatanutricionista.paciente.repository.PacienteRepository;


@Service
public final class PacienteUtils {
	
	@Autowired
	private PacienteRepository pacienteRepository;
	

	public final void atualizarDataHoraUltimaAlteracaoNosDadosDoPaciente(Paciente paciente) {
		paciente.setDataUltimaAtualizacaoDadosDoPaciente(LocalDateTime.now());
		pacienteRepository.saveAndFlush(paciente);
	}
}
