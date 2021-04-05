package br.com.renatanutricionista.calendario.atendimento.paciente.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.renatanutricionista.calendario.atendimento.paciente.service.CalendarioAtendimentoPacienteService;


@RestController
@RequestMapping("/calendario-atendimento-paciente")
public class CalendarioAtendimentoPacienteResource {

	@Autowired
	private CalendarioAtendimentoPacienteService calendarioAtendimentoPacienteService;
}
