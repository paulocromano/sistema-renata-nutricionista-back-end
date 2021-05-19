package br.com.renatanutricionista.paciente.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.renatanutricionista.atendimento.paciente.consulta.enums.situacao.consulta.SituacaoConsulta;
import br.com.renatanutricionista.atendimento.paciente.consulta.model.Consulta;
import br.com.renatanutricionista.atendimento.paciente.retorno.enums.SituacaoRetorno;
import br.com.renatanutricionista.exception.custom.ObjectNotFoundException;
import br.com.renatanutricionista.paciente.dto.ListagemCadastroPacienteDTO;
import br.com.renatanutricionista.paciente.dto.PacienteAgendamentoAtendimentoDTO;
import br.com.renatanutricionista.paciente.dto.PacientePreviaHistoricosDTO;
import br.com.renatanutricionista.paciente.form.AtualizacaoPacienteFORM;
import br.com.renatanutricionista.paciente.form.PacienteFORM;
import br.com.renatanutricionista.paciente.model.Paciente;
import br.com.renatanutricionista.paciente.repository.PacienteRepository;
import br.com.renatanutricionista.tabelas.parametro.paciente.model.PacienteParametro;
import br.com.renatanutricionista.tabelas.parametro.paciente.service.PacienteParametroService;


@Service
public class PacienteService {

	@Autowired
	private PacienteRepository pacienteRepository;

	@Autowired
	private PacienteParametroService pacienteParametroService;
	
	
	public ResponseEntity<ListagemCadastroPacienteDTO> buscarInformacoesListagemCadastroPaciente() {	
		return ResponseEntity.ok().body(new ListagemCadastroPacienteDTO(pacienteRepository.findAll()));
	}
	
	
	public ResponseEntity<List<PacienteAgendamentoAtendimentoDTO>> buscarPacientesParaAgendarAtendimento() {
		Predicate<Consulta> predicateConsulta = consulta -> consulta.getSituacaoConsulta().equals(SituacaoConsulta.CONSULTA_FINALIZADA)
				&& (Objects.isNull(consulta.getRetornoConsulta()) || consulta.getRetornoConsulta().getSituacaoRetorno().equals(SituacaoRetorno.RETORNO_FINALIZADO));
						
		List<Paciente> pacientes = pacienteRepository.findAll().stream().filter(paciente -> 
			paciente.getConsultas().isEmpty() || paciente.getConsultas().stream().filter(predicateConsulta).findFirst().isPresent()).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(PacienteAgendamentoAtendimentoDTO.converterParaListaPacienteAgendamentoAtendimentoDTO(pacientes));
	}
	
	
	public ResponseEntity<PacientePreviaHistoricosDTO> buscarInformacoesPreviasHistoricosDoPaciente(Long idPaciente) {
		Paciente paciente = verificarSePacienteExiste(idPaciente);
		PacienteParametro pacienteParametro = pacienteParametroService.buscarPacienteParametro();
		
		return ResponseEntity.ok().body(new PacientePreviaHistoricosDTO(paciente, pacienteParametro));
	}
	
	
	public ResponseEntity<Void> cadastrarPacienteEndereco(PacienteFORM pacienteFORM) {
		pacienteRepository.save(pacienteFORM.converterParaPaciente());
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	public ResponseEntity<Void> atualizarPacienteEndereco(Long idPaciente, AtualizacaoPacienteFORM atualizacaoPaciente) {
		Paciente paciente = verificarSePacienteExiste(idPaciente);
		
		atualizacaoPaciente.atualizarPacienteEndereco(paciente);
		
		return ResponseEntity.ok().build();
	}
	
	
	public ResponseEntity<Void> removerPaciente(Long idPaciente) {
		Paciente paciente = verificarSePacienteExiste(idPaciente);
		pacienteRepository.delete(paciente);
		
		return ResponseEntity.noContent().build();
	}

	
	public Paciente verificarSePacienteExiste(Long idPaciente) {
		if (Objects.isNull(idPaciente))
			throw new NullPointerException("O ID do Paciente está nulo!");
			
		Optional<Paciente> paciente = pacienteRepository.findById(idPaciente);
		
		if (paciente.isEmpty())
			throw new ObjectNotFoundException("Paciente não encontrado!");
		
		return paciente.get();
	}
}
