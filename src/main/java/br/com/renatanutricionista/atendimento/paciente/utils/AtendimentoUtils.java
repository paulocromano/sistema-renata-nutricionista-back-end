package br.com.renatanutricionista.atendimento.paciente.utils;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.renatanutricionista.atendimento.paciente.consulta.model.Consulta;
import br.com.renatanutricionista.atendimento.paciente.consulta.respository.ConsultaRepository;
import br.com.renatanutricionista.exception.custom.ObjectNotFoundException;
import br.com.renatanutricionista.paciente.model.Paciente;
import br.com.renatanutricionista.paciente.utils.PacienteUtils;


@Service
public final class AtendimentoUtils {
	
	@Autowired
	private ConsultaRepository consultaRepository;

	@Autowired
	private PacienteUtils pacienteUtils;
	
	
	public final Consulta verificarPacienteConsulta(Long idPaciente, Long idConsulta) {
		Paciente paciente = pacienteUtils.verificarSePacienteExiste(idPaciente);
		Consulta consulta = verificarSeConsultaExiste(idConsulta);
		verificarSeConsultaPertenceAoPaciente(paciente, consulta);
		
		return consulta;
	}
	
	
	public final void verificarSeConsultaPertenceAoPaciente(Paciente paciente, Consulta consultaPaciente) {
		if (!paciente.getId().equals(consultaPaciente.getPaciente().getId()))
			throw new IllegalArgumentException("A Consulta selecionada não pertence ao Paciente!");
	}
	
	
	public final Consulta verificarSeConsultaExiste(Long idConsulta) {
		if (Objects.isNull(idConsulta))
			throw new NullPointerException("O ID da Consulta não pode nulo!");
			
		Optional<Consulta> consulta = consultaRepository.findById(idConsulta);
		
		if (consulta.isEmpty())
			throw new ObjectNotFoundException("Consulta não encontrada!");
		
		return consulta.get();
	}
}
