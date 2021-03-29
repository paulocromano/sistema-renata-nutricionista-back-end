package br.com.renatanutricionista.atendimento.paciente.retorno.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.renatanutricionista.atendimento.paciente.consulta.enums.SituacaoConsulta;
import br.com.renatanutricionista.atendimento.paciente.consulta.model.Consulta;
import br.com.renatanutricionista.atendimento.paciente.retorno.enums.SituacaoRetorno;
import br.com.renatanutricionista.atendimento.paciente.retorno.form.AgendamentoRetornoFORM;
import br.com.renatanutricionista.atendimento.paciente.retorno.form.ReagendamentoRetornoFORM;
import br.com.renatanutricionista.atendimento.paciente.retorno.model.RetornoConsulta;
import br.com.renatanutricionista.atendimento.paciente.retorno.repository.RetornoConsultaRepository;
import br.com.renatanutricionista.atendimento.paciente.utils.AtendimentoUtils;
import br.com.renatanutricionista.calendario.atendimento.paciente.enums.PeriodoDisponivel;
import br.com.renatanutricionista.calendario.atendimento.paciente.model.CalendarioAtendimentoPaciente;
import br.com.renatanutricionista.calendario.atendimento.paciente.service.CalendarioAtendimentoPacienteService;
import br.com.renatanutricionista.exception.custom.AtendimentoException;
import br.com.renatanutricionista.exception.custom.ObjectNotFoundException;
import br.com.renatanutricionista.paciente.model.Paciente;
import br.com.renatanutricionista.paciente.utils.PacienteUtils;


@Service
public class RetornoConsultaService {
	
	@Autowired
	private RetornoConsultaRepository retornoConsultaRepository;

	@Autowired
	private PacienteUtils pacienteUtils;
	
	@Autowired
	private AtendimentoUtils atendimentoUtils;
	
	@Autowired
	private CalendarioAtendimentoPacienteService calendarioAtendimentoService;

	
	public ResponseEntity<Void> agendarRetorno(Long idPaciente, Long idConsulta, AgendamentoRetornoFORM agendamentoRetorno) {
		Consulta consulta = atendimentoUtils.verificarPacienteConsulta(idPaciente, idConsulta);
		verificarSeConsultaEstaFinalizada(consulta);
		
		CalendarioAtendimentoPaciente periodoAgendamento = calendarioAtendimentoService
				.verificarPossibilidadeDeAgendarConsultaRetorno(agendamentoRetorno.getData(), agendamentoRetorno.getHorario());
		
		periodoAgendamento.setPeriodoDisponivel(PeriodoDisponivel.NAO);
		consulta.setRetornoConsulta(agendamentoRetorno.converterParaRetornoConsulta(periodoAgendamento));
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	public ResponseEntity<Void> reagendarRetorno(Long idPaciente, Long idRetorno, ReagendamentoRetornoFORM reagendamentoRetorno) {
		Paciente paciente = pacienteUtils.verificarSePacienteExiste(idPaciente);
		
		RetornoConsulta retornoPaciente = verificarSeRetornoConsultaExiste(idRetorno);
		verificarSeRetornoConsultaPertenceAoPaciente(paciente, retornoPaciente);
		verificarSeRetornoConsultaParaRemarcarEstaAgurdandoConfirmacao(retornoPaciente);
		
		CalendarioAtendimentoPaciente periodoRetornoRemarcado = calendarioAtendimentoService
				.verificarPossibilidadeDeAgendarConsultaRetorno(reagendamentoRetorno.getData(), reagendamentoRetorno.getHorario());
			
		reagendamentoRetorno.atualizarInformacoesRetornoPaciente(retornoPaciente, periodoRetornoRemarcado);
		
		return ResponseEntity.ok().build();
	}
	
	
	private void verificarSeRetornoConsultaParaRemarcarEstaAgurdandoConfirmacao(RetornoConsulta retornoPacienteQueSeraCancelado) {
		if (!retornoPacienteQueSeraCancelado.getSituacaoRetorno().equals(SituacaoRetorno.AGUARDANDO_CONFIRMACAO))
			throw new AtendimentoException("Não é possível remarcar um Retorno que não esteja com a Situação de " 
										+ SituacaoRetorno.AGUARDANDO_CONFIRMACAO.getDescricao() + "!");
	}
	
	
	private RetornoConsulta verificarSeRetornoConsultaExiste(Long idRetorno) {
		if (Objects.isNull(idRetorno))
			throw new AtendimentoException("O ID do Retorno está nulo!");
		
		Optional<RetornoConsulta> retornoConsulta = retornoConsultaRepository.findById(idRetorno);
		
		if (retornoConsulta.isEmpty())
			throw new ObjectNotFoundException("Retorno não encontrado!");
		
		return retornoConsulta.get();
	}
	
	
	private void verificarSeRetornoConsultaPertenceAoPaciente(Paciente paciente, RetornoConsulta retornoPacienteQueSeraCancelado) {
		if (!retornoPacienteQueSeraCancelado.getConsulta().getPaciente().getId().equals(paciente.getId()))
			throw new IllegalArgumentException("Retorno de Consulta não pertence ao Paciente!");
	}
	
	
	private void verificarSeConsultaEstaFinalizada(Consulta consulta) {
		if (Objects.nonNull(consulta.getRetornoConsulta()))
			throw new AtendimentoException("Não é possível agendar o Retorno pois "
					+ "já existe um cadastrado!");
		
		if (!consulta.getSituacaoConsulta().equals(SituacaoConsulta.CONSULTA_FINALIZADA))
			throw new AtendimentoException("É necessário finalizar a Consulta para realizar "
					+ "o agendamento do Retorno!");
	}
}
