package br.com.renatanutricionista.tabelas.parametro.atendimento.paciente.service;

import java.util.Objects;
import java.util.Optional;

import javax.persistence.Convert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
	
	@Value("${id.atendimento.paciente.parametro}")
	@Convert(attributeName = "idAtendimentoPacienteParametro", converter = Integer.class)
	private Integer idAtendimentoPacienteParametro;
	
	@Value("${quantidade.maxima.parcelas}")
	@Convert(attributeName = "quantidadeMaximaParcelas", converter = Integer.class)
	private Integer quantidadeMaximaParcelas;
	
	@Value("${tempo.maximo.meses.geracao.automatica.horarios.atendimento}")
	@Convert(attributeName = "tempoMaximoEmMesesGeracaoAutomaticaHorariosAtendimento", converter = Integer.class)
	private Integer tempoMaximoEmMesesGeracaoAutomaticaHorariosAtendimento;
	
	
	public ResponseEntity<AtendimentoPacienteParametroDTO> buscarInformacoesDosParametrosDeAtendimentoDePaciente() {
		AtendimentoPacienteParametro atendimentoPacienteParametro = buscarAtendimentoPacienteParametro();
		
		return ResponseEntity.ok().body(new AtendimentoPacienteParametroDTO(atendimentoPacienteParametro));
	}
	
	
	public ResponseEntity<Void> atualizarParametrosAtendimentoDoPaciente(
			AtendimentoPacienteParametroFORM atendimentoPacienteParametro) {
		
		AtendimentoPacienteParametro atendimento = buscarAtendimentoPacienteParametro();
		atendimentoPacienteParametro.atualizarInformacoesDosParametrosParaAtendimentoDePaciente(atendimento, 
				quantidadeMaximaParcelas, tempoMaximoEmMesesGeracaoAutomaticaHorariosAtendimento);
		
		return ResponseEntity.ok().build();				
	}
	
	
	public AtendimentoPacienteParametro buscarAtendimentoPacienteParametro() {
		if (Objects.isNull(idAtendimentoPacienteParametro))
			throw new NullPointerException("O ID do Parâmetro de Atendimento de Paciente não pode estar nulo!");
			
		Optional<AtendimentoPacienteParametro> atendimento = atendimentoPacienteParametroRepository.findById(idAtendimentoPacienteParametro);
		
		if (atendimento.isEmpty())
			throw new ObjectNotFoundException("Parâmetros de Atendimento de Paciente não encontrado!");
		
		return atendimento.get();
	}
}
