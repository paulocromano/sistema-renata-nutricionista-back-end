package br.com.renatanutricionista.consultas.retornos.retorno.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.renatanutricionista.calendario.atendimento.paciente.service.CalendarioAtendimentoPacienteService;
import br.com.renatanutricionista.consultas.retornos.retorno.repository.RetornoConsultaRepository;
import br.com.renatanutricionista.paciente.utils.PacienteUtils;


@Service
public class RetornoConsultaService {
	
	@Autowired
	private RetornoConsultaRepository retornoConsultaRepository;

	@Autowired
	private PacienteUtils pacienteUtils;
	
	@Autowired
	private CalendarioAtendimentoPacienteService calendarioAtendimentoService;

}
