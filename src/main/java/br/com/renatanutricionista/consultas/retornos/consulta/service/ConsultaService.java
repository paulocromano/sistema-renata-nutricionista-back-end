package br.com.renatanutricionista.consultas.retornos.consulta.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.renatanutricionista.calendario.agendamento.paciente.enums.PeriodoDisponivel;
import br.com.renatanutricionista.calendario.agendamento.paciente.model.CalendarioAgendamentoPaciente;
import br.com.renatanutricionista.calendario.agendamento.paciente.service.CalendarioAgendamentoPacienteService;
import br.com.renatanutricionista.consultas.retornos.avaliacao.composicao.corporal.form.AvaliacaoComposicaoCorporalFORM;
import br.com.renatanutricionista.consultas.retornos.avaliacao.consumo.habitual.form.AvaliacaoConsumoHabitualFORM;
import br.com.renatanutricionista.consultas.retornos.consulta.enums.SituacaoConsulta;
import br.com.renatanutricionista.consultas.retornos.consulta.form.AgendamentoConsultaFORM;
import br.com.renatanutricionista.consultas.retornos.consulta.form.ConfirmacaoConsultaFORM;
import br.com.renatanutricionista.consultas.retornos.consulta.form.ReagendamentoConsultaFORM;
import br.com.renatanutricionista.consultas.retornos.consulta.model.Consulta;
import br.com.renatanutricionista.consultas.retornos.consulta.respository.ConsultaRepository;
import br.com.renatanutricionista.exception.custom.ObjectNotFoundException;
import br.com.renatanutricionista.exception.custom.PacienteException;
import br.com.renatanutricionista.paciente.model.Paciente;
import br.com.renatanutricionista.paciente.utils.PacienteUtils;
import br.com.renatanutricionista.utils.enums.SexoUtils;


@Service
public class ConsultaService {

	@Autowired
	private ConsultaRepository consultaRepository;
	
	@Autowired
	private PacienteUtils pacienteUtils;
	
	@Autowired
	private CalendarioAgendamentoPacienteService calendarioAgendamentoService;
	
	
	public ResponseEntity<Void> agendarConsulta(Long idPaciente, AgendamentoConsultaFORM agendamentoConsulta) {
		Paciente paciente = pacienteUtils.verificarSePacienteExiste(idPaciente);
		verificarSeExisteConsultaEmAberto(paciente);
		
		CalendarioAgendamentoPaciente periodoAgendamento = calendarioAgendamentoService
				.verificarPossibilidadeDeAgendarConsulta(agendamentoConsulta.getData(), agendamentoConsulta.getHorario());
		
		periodoAgendamento.setPeriodoDisponivel(PeriodoDisponivel.NAO);
		consultaRepository.save(agendamentoConsulta.converterParaConsulta(paciente, periodoAgendamento));
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	public ResponseEntity<Void> reagendarConsulta(Long idPaciente, Long idConsulta, ReagendamentoConsultaFORM reagendamentoConsulta) {
		Paciente paciente = pacienteUtils.verificarSePacienteExiste(idPaciente);
		
		CalendarioAgendamentoPaciente periodoConsultaRemarcada = calendarioAgendamentoService
				.verificarPossibilidadeDeAgendarConsulta(reagendamentoConsulta.getData(), reagendamentoConsulta.getHorario());
		
		Consulta consultaPacienteQueSeraCancelada = verificarSeConsultaExiste(idConsulta);
		verificarSeConsultaPertenceAoPaciente(paciente, consultaPacienteQueSeraCancelada);
		verificarSeConsultaParaRemarcarEstaAguardandoConfirmacao(consultaPacienteQueSeraCancelada);
		
		periodoConsultaRemarcada.setPeriodoDisponivel(PeriodoDisponivel.NAO);
		consultaRepository.save(reagendamentoConsulta.converterParaConsulta(paciente, periodoConsultaRemarcada,
				consultaPacienteQueSeraCancelada));
		
		consultaPacienteQueSeraCancelada.getPeriodoConsulta().setPeriodoDisponivel(PeriodoDisponivel.SIM);
		consultaRepository.delete(consultaPacienteQueSeraCancelada);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	public ResponseEntity<Void> confirmarConsulta(Long idPaciente, Long idConsulta, ConfirmacaoConsultaFORM confirmacaoConsulta) {
		Consulta consulta = verificarPacienteConsulta(idPaciente, idConsulta);
		
		if (!consulta.getSituacaoConsulta().equals(SituacaoConsulta.AGUARDANDO_CONFIRMACAO))
			throw new PacienteException("Só é possível Confirmar uma Consulta quando a Situação for "
					+ SituacaoConsulta.AGUARDANDO_CONFIRMACAO.getDescricao() + "!");
		
		confirmacaoConsulta.atualizarInformacoesConsulta(consulta);
		
		return ResponseEntity.ok().build();
	}
	
	
	public ResponseEntity<Void> cancelarConsulta(Long idPaciente, Long idConsulta) {
		Consulta consulta = verificarPacienteConsulta(idPaciente, idConsulta);

		if (consulta.getSituacaoConsulta().equals(SituacaoConsulta.CONSULTA_FINALIZADA))
			throw new PacienteException("Não é possível cancelar uma Consulta Finalizada!");
		
		consultaRepository.delete(consulta);
		calendarioAgendamentoService.alterarPeriodoDoCalendarioParaDisponivel(consulta.getPeriodoConsulta().getId());
		
		return ResponseEntity.noContent().build();
	}
	
	
	public ResponseEntity<Void> iniciarConsulta(Long idPaciente, Long idConsulta) {
		Consulta consulta = verificarPacienteConsulta(idPaciente, idConsulta);	
		
		if (!consulta.getSituacaoConsulta().equals(SituacaoConsulta.AGUARDANDO_ATENDIMENTO))
			throw new PacienteException("Só é possível iniciar uma Consulta que esteja com a Situação de " 
					+ SituacaoConsulta.AGUARDANDO_ATENDIMENTO.getDescricao() + "!");
		
		consulta.setSituacaoConsulta(SituacaoConsulta.CONSULTA_INICIADA);
		
		return ResponseEntity.ok().build();
	}
	
	
	private Consulta verificarPacienteConsulta(Long idPaciente, Long idConsulta) {
		Paciente paciente = pacienteUtils.verificarSePacienteExiste(idPaciente);
		Consulta consulta = verificarSeConsultaExiste(idConsulta);
		verificarSeConsultaPertenceAoPaciente(paciente, consulta);
		
		return consulta;
	}
	
	
	private void verificarSeConsultaPertenceAoPaciente(Paciente paciente, Consulta consultaPaciente) {
		if (!paciente.getId().equals(consultaPaciente.getPaciente().getId()))
			throw new IllegalArgumentException("A Consulta selecionada não pertence ao Paciente!");
	}
	
	
	private Consulta verificarSeConsultaExiste(Long idConsulta) {
		if (Objects.isNull(idConsulta))
			throw new NullPointerException("O ID da Consulta está nulo!");
			
		Optional<Consulta> consulta = consultaRepository.findById(idConsulta);
		
		if (consulta.isEmpty())
			throw new ObjectNotFoundException("Consulta não encontrada!");
		
		return consulta.get();
	}
	
	
	private void verificarSeExisteConsultaEmAberto(Paciente paciente) {
		Optional<Consulta> consulta = consultaRepository.findByPacienteAndSituacaoConsultaNot(
				paciente, SituacaoConsulta.CONSULTA_FINALIZADA);
		
		if (consulta.isPresent())
			throw new PacienteException("Não é possível Agendar Consulta quando existe "
					+ "outra Consulta em aberto!");
	}
	
	
	private void verificarSeConsultaParaRemarcarEstaAguardandoConfirmacao(Consulta consultaPacienteQueSeraCancelada) {
		if (!consultaPacienteQueSeraCancelada.getSituacaoConsulta().equals(SituacaoConsulta.AGUARDANDO_CONFIRMACAO))
			throw new PacienteException("Não é possível remarcar uma Consulta que não esteja com a Situação de "
					+ SituacaoConsulta.AGUARDANDO_CONFIRMACAO.getDescricao() + "!");
	}
	
	
	private void validarSituacaoConsultaParaCadastroDeInformacoes(Consulta consulta) {
		if (!consulta.getSituacaoConsulta().equals(SituacaoConsulta.CONSULTA_INICIADA))
			throw new PacienteException("Só é possível cadastrar informações em uma "
					+ "Consulta caso ela não tenha sido iniciada!");
	}
	
	
	public ResponseEntity<Void> cadastrarAvaliacaoConsumoHabitual(Long idPaciente, Long idConsulta,
			AvaliacaoConsumoHabitualFORM avaliacaoConsumoHabitual) {

		Consulta consulta = verificarPacienteConsulta(idPaciente, idConsulta);	
		validarSituacaoConsultaParaCadastroDeInformacoes(consulta);
		
		consulta.setAvaliacaoConsumoHabitual(avaliacaoConsumoHabitual.criarAvaliacaoConsumoHabitual());
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	public ResponseEntity<Void> cadastrarAvaliacaoComposicaoCorporal(Long idPaciente, Long idConsulta,
			AvaliacaoComposicaoCorporalFORM avaliacaoComposicaoCorporal) {
		
		Consulta consulta = verificarPacienteConsulta(idPaciente, idConsulta);	
		validarSituacaoConsultaParaCadastroDeInformacoes(consulta);
		
		SexoUtils sexoPaciente = consulta.getPaciente().getSexo();
		
		consulta.setAvaliacaoComposicaoCorporal(avaliacaoComposicaoCorporal.criarAvaliacaoComposicaoCorporal(sexoPaciente));
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
