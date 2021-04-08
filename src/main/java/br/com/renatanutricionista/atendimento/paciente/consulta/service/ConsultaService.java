package br.com.renatanutricionista.atendimento.paciente.consulta.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.renatanutricionista.atendimento.paciente.avaliacao.composicao.corporal.form.AvaliacaoComposicaoCorporalFORM;
import br.com.renatanutricionista.atendimento.paciente.avaliacao.consumo.habitual.form.AvaliacaoConsumoHabitualFORM;
import br.com.renatanutricionista.atendimento.paciente.avaliacao.massa.muscular.corporea.antropometrica.form.AvaliacaoMassaMuscularCorporeaFORM;
import br.com.renatanutricionista.atendimento.paciente.conduta.nutricional.form.CondutaNutricionalFORM;
import br.com.renatanutricionista.atendimento.paciente.consulta.enums.SituacaoConsulta;
import br.com.renatanutricionista.atendimento.paciente.consulta.form.AgendamentoConsultaFORM;
import br.com.renatanutricionista.atendimento.paciente.consulta.form.ConfirmacaoConsultaFORM;
import br.com.renatanutricionista.atendimento.paciente.consulta.form.ReagendamentoConsultaFORM;
import br.com.renatanutricionista.atendimento.paciente.consulta.model.Consulta;
import br.com.renatanutricionista.atendimento.paciente.consulta.respository.ConsultaRepository;
import br.com.renatanutricionista.atendimento.paciente.registro.dieta.enums.TipoRegistroDieta;
import br.com.renatanutricionista.atendimento.paciente.registro.dieta.form.RegistroDietaFORM;
import br.com.renatanutricionista.atendimento.paciente.utils.AtendimentoUtils;
import br.com.renatanutricionista.calendario.atendimento.paciente.enums.PeriodoDisponivel;
import br.com.renatanutricionista.calendario.atendimento.paciente.model.CalendarioAtendimentoPaciente;
import br.com.renatanutricionista.calendario.atendimento.paciente.service.CalendarioAtendimentoPacienteService;
import br.com.renatanutricionista.exception.custom.AtendimentoException;
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
	private AtendimentoUtils atendimentoUtils;
	
	@Autowired
	private CalendarioAtendimentoPacienteService calendarioAtendimentoService;
	
	
	public ResponseEntity<Void> agendarConsulta(Long idPaciente, AgendamentoConsultaFORM agendamentoConsulta) {
		Paciente paciente = pacienteUtils.verificarSePacienteExiste(idPaciente);
		verificarSeExisteConsultaEmAberto(paciente);
		
		CalendarioAtendimentoPaciente periodoAgendamento = calendarioAtendimentoService
				.verificarPossibilidadeDeAgendarConsultaRetorno(agendamentoConsulta.getData(), agendamentoConsulta.getHorario());
		
		periodoAgendamento.setPeriodoDisponivel(PeriodoDisponivel.NAO);
		consultaRepository.save(agendamentoConsulta.converterParaConsulta(paciente, periodoAgendamento));
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	public ResponseEntity<Void> reagendarConsulta(Long idPaciente, Long idConsulta, ReagendamentoConsultaFORM reagendamentoConsulta) {
		Paciente paciente = pacienteUtils.verificarSePacienteExiste(idPaciente);
		
		Consulta consultaPaciente = atendimentoUtils.verificarSeConsultaExiste(idConsulta);
		atendimentoUtils.verificarSeConsultaPertenceAoPaciente(paciente, consultaPaciente);
		verificarSeConsultaParaRemarcarEstaAguardandoConfirmacao(consultaPaciente);
		
		CalendarioAtendimentoPaciente periodoConsultaRemarcada = calendarioAtendimentoService
				.verificarPossibilidadeDeAgendarConsultaRetorno(reagendamentoConsulta.getData(), reagendamentoConsulta.getHorario());
		
		reagendamentoConsulta.atualizarInformacoesDaConsulta(consultaPaciente, periodoConsultaRemarcada);
		
		return ResponseEntity.ok().build();
	}
	
	
	public ResponseEntity<Void> confirmarConsulta(Long idPaciente, Long idConsulta, ConfirmacaoConsultaFORM confirmacaoConsulta) {
		Consulta consulta = atendimentoUtils.verificarPacienteConsulta(idPaciente, idConsulta);
		
		if (!consulta.getSituacaoConsulta().equals(SituacaoConsulta.AGUARDANDO_CONFIRMACAO))
			throw new AtendimentoException("Só é possível Confirmar uma Consulta quando a Situação for "
					+ SituacaoConsulta.AGUARDANDO_CONFIRMACAO.getDescricao() + "!");
		
		confirmacaoConsulta.atualizarInformacoesConsulta(consulta);
		
		return ResponseEntity.ok().build();
	}
	
	
	public ResponseEntity<Void> cancelarConsulta(Long idPaciente, Long idConsulta) {
		Consulta consulta = atendimentoUtils.verificarPacienteConsulta(idPaciente, idConsulta);

		if (consulta.getSituacaoConsulta().equals(SituacaoConsulta.CONSULTA_FINALIZADA))
			throw new AtendimentoException("Não é possível cancelar uma Consulta Finalizada!");
		
		consultaRepository.delete(consulta);
		calendarioAtendimentoService.alterarPeriodoDoCalendarioParaDisponivel(consulta.getPeriodoConsulta().getId());
		
		return ResponseEntity.noContent().build();
	}
	
	
	public ResponseEntity<Void> iniciarConsulta(Long idPaciente, Long idConsulta) {
		Consulta consulta = atendimentoUtils.verificarPacienteConsulta(idPaciente, idConsulta);	
		
		if (!consulta.getSituacaoConsulta().equals(SituacaoConsulta.AGUARDANDO_ATENDIMENTO))
			throw new AtendimentoException("Só é possível iniciar uma Consulta que esteja com a Situação de " 
					+ SituacaoConsulta.AGUARDANDO_ATENDIMENTO.getDescricao() + "!");
		
		consulta.setSituacaoConsulta(SituacaoConsulta.CONSULTA_INICIADA);
		
		return ResponseEntity.ok().build();
	}
	
	
	public ResponseEntity<Void> finalizarConsulta(Long idPaciente, Long idConsulta) {
		Consulta consulta = atendimentoUtils.verificarPacienteConsulta(idPaciente, idConsulta);	
		
		if (!consulta.getSituacaoConsulta().equals(SituacaoConsulta.CONSULTA_FINALIZADA))
			throw new AtendimentoException("Só é possível finalizar uma Consulta que esteja com a Situação de " 
					+ SituacaoConsulta.CONSULTA_FINALIZADA.getDescricao() + "!");
		
		consulta.setSituacaoConsulta(SituacaoConsulta.CONSULTA_FINALIZADA);
		
		return ResponseEntity.ok().build();
	}
	
	
	public ResponseEntity<Void> cadastrarAvaliacaoConsumoHabitual(Long idPaciente, Long idConsulta,
			AvaliacaoConsumoHabitualFORM avaliacaoConsumoHabitual) {

		Consulta consulta = atendimentoUtils.verificarPacienteConsulta(idPaciente, idConsulta);	
		validarSituacaoConsultaParaCadastroDeInformacoes(consulta);
		
		consulta.setAvaliacaoConsumoHabitual(avaliacaoConsumoHabitual.criarAvaliacaoConsumoHabitual());
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	public ResponseEntity<Void> cadastrarAvaliacaoComposicaoCorporal(Long idPaciente, Long idConsulta,
			AvaliacaoComposicaoCorporalFORM avaliacaoComposicaoCorporal) {
		
		Consulta consulta = atendimentoUtils.verificarPacienteConsulta(idPaciente, idConsulta);	
		validarSituacaoConsultaParaCadastroDeInformacoes(consulta);
		
		SexoUtils sexoPaciente = consulta.getPaciente().getSexo();
		
		consulta.setAvaliacaoComposicaoCorporal(avaliacaoComposicaoCorporal.criarAvaliacaoComposicaoCorporal(sexoPaciente));
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	public ResponseEntity<Void> cadastrarAvaliacaoMassaMuscularCorporeaMedidaAntropometrica(Long idPaciente, 
			Long idConsulta, AvaliacaoMassaMuscularCorporeaFORM avaliacaoMassaMuscularCorporea) {
		
		Consulta consulta = atendimentoUtils.verificarPacienteConsulta(idPaciente, idConsulta);	
		validarSituacaoConsultaParaCadastroDeInformacoes(consulta);
		
		consulta.setAvaliacaoMassaMuscularCorporeaAntropometrica(avaliacaoMassaMuscularCorporea.criarAvaliacaoMassaMuscularCorporea());
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	public ResponseEntity<Void> cadastrarCondutaNutricional(Long idPaciente, Long idConsulta,
			CondutaNutricionalFORM condutaNutricional) {
		
		Consulta consulta = atendimentoUtils.verificarPacienteConsulta(idPaciente, idConsulta);	
		validarSituacaoConsultaParaCadastroDeInformacoes(consulta);
		
		consulta.setCondutaNutricional(condutaNutricional.converterParaCondutaNutricional());
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	public ResponseEntity<Void> cadastrarRegistroDietaHabitual(Long idPaciente, Long idConsulta, 
			RegistroDietaFORM registroDietaHabitual) {
		
		Consulta consulta = atendimentoUtils.verificarPacienteConsulta(idPaciente, idConsulta);	
		validarSituacaoConsultaParaCadastroDeInformacoes(consulta);
		
		consulta.setRegistroDietaHabitual(registroDietaHabitual.converterParaRegistroDieta(TipoRegistroDieta.HABITUAL));
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	private void verificarSeExisteConsultaEmAberto(Paciente paciente) {
		Optional<Consulta> consulta = consultaRepository.findByPacienteAndSituacaoConsultaNot(
				paciente, SituacaoConsulta.CONSULTA_FINALIZADA);
		
		if (consulta.isPresent())
			throw new AtendimentoException("Não é possível Agendar Consulta quando existe "
					+ "outra Consulta em aberto!");
	}
	
	
	private void verificarSeConsultaParaRemarcarEstaAguardandoConfirmacao(Consulta consultaPacienteQueSeraCancelada) {
		if (!consultaPacienteQueSeraCancelada.getSituacaoConsulta().equals(SituacaoConsulta.AGUARDANDO_CONFIRMACAO))
			throw new AtendimentoException("Não é possível remarcar uma Consulta que não esteja com a Situação de "
					+ SituacaoConsulta.AGUARDANDO_CONFIRMACAO.getDescricao() + "!");
	}
	
	
	private void validarSituacaoConsultaParaCadastroDeInformacoes(Consulta consulta) {
		if (!consulta.getSituacaoConsulta().equals(SituacaoConsulta.CONSULTA_INICIADA))
			throw new AtendimentoException("Só é possível cadastrar informações em uma "
					+ "Consulta caso ela tenha sido iniciada!");
	}
}
