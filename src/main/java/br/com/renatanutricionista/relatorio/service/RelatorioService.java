package br.com.renatanutricionista.relatorio.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.renatanutricionista.atendimento.paciente.consulta.respository.ConsultaRepository;
import br.com.renatanutricionista.atendimento.paciente.retorno.repository.RetornoConsultaRepository;
import br.com.renatanutricionista.utils.RelatorioUtils;


@Service
public class RelatorioService {
	
	@Autowired
	private ConsultaRepository consultaRepository;
	
	@Autowired
	private RetornoConsultaRepository retornoConsultaRepository;

	
	public ResponseEntity<byte[]> gerarRelatorioDosAtendimentosDoDia() {
		
		return RelatorioUtils.gerarRelatorioEmPDF("atendimentos", new HashMap<>());
	}
	
}
