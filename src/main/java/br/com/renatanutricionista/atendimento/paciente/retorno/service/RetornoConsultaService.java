package br.com.renatanutricionista.atendimento.paciente.retorno.service;

import java.util.Objects;
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
import br.com.renatanutricionista.atendimento.paciente.consulta.model.Consulta;
import br.com.renatanutricionista.atendimento.paciente.registro.dieta.enums.TipoRegistroDieta;
import br.com.renatanutricionista.atendimento.paciente.registro.dieta.form.RegistroDietaFORM;
import br.com.renatanutricionista.atendimento.paciente.retorno.enums.SituacaoRetorno;
import br.com.renatanutricionista.atendimento.paciente.retorno.form.AgendamentoRetornoFORM;
import br.com.renatanutricionista.atendimento.paciente.retorno.form.ReagendamentoRetornoFORM;
import br.com.renatanutricionista.atendimento.paciente.retorno.model.RetornoConsulta;
import br.com.renatanutricionista.atendimento.paciente.retorno.repository.RetornoConsultaRepository;
import br.com.renatanutricionista.atendimento.paciente.utils.AtendimentoUtils;
import br.com.renatanutricionista.calendario.atendimento.paciente.model.CalendarioAtendimentoPaciente;
import br.com.renatanutricionista.calendario.atendimento.paciente.service.CalendarioAtendimentoPacienteService;
import br.com.renatanutricionista.exception.custom.AtendimentoException;
import br.com.renatanutricionista.exception.custom.ObjectNotFoundException;
import br.com.renatanutricionista.paciente.model.Paciente;
import br.com.renatanutricionista.paciente.utils.PacienteUtils;
import br.com.renatanutricionista.utils.enums.SexoUtils;


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
		
		verificarSePeriodoRetornoConsultaProcedePeriodoConsulta(consulta, periodoAgendamento);
		
		consulta.setRetornoConsulta(agendamentoRetorno.converterParaRetornoConsulta(periodoAgendamento));
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	public ResponseEntity<Void> reagendarRetorno(Long idPaciente, Long idRetornoConsulta, ReagendamentoRetornoFORM reagendamentoRetorno) {
		RetornoConsulta retornoConsulta = verificarSeRetornoConsultaPertenceAoPaciente(idPaciente, idRetornoConsulta);
		verificarSeRetornoConsultaEstaAguardandoConfirmacao(retornoConsulta);
		
		CalendarioAtendimentoPaciente periodoRetornoRemarcado = calendarioAtendimentoService
				.verificarPossibilidadeDeAgendarConsultaRetorno(reagendamentoRetorno.getData(), reagendamentoRetorno.getHorario());
		
		verificarSePeriodoRetornoConsultaProcedePeriodoConsulta(retornoConsulta.getConsulta(), periodoRetornoRemarcado);
		reagendamentoRetorno.atualizarInformacoesRetornoPaciente(retornoConsulta);
		
		return ResponseEntity.ok().build();
	}
	
	
	public ResponseEntity<Void> confirmarRetornoConsulta(Long idPaciente, Long idRetornoConsulta) {
		RetornoConsulta retornoConsulta = verificarSeRetornoConsultaPertenceAoPaciente(idPaciente, idRetornoConsulta);
		verificarSeRetornoConsultaEstaAguardandoConfirmacao(retornoConsulta);
		
		retornoConsulta.setSituacaoRetorno(SituacaoRetorno.AGUARDANDO_ATENDIMENTO);
		
		return ResponseEntity.ok().build();
	}
	
	
	public ResponseEntity<Void> cancelarRetornoConsulta(Long idPaciente, Long idRetornoConsulta) {
		RetornoConsulta retornoConsulta = verificarSeRetornoConsultaPertenceAoPaciente(idPaciente, idRetornoConsulta);
		
		if (retornoConsulta.getSituacaoRetorno().equals(SituacaoRetorno.RETORNO_FINALIZADO))
			throw new AtendimentoException("Não é possível cancelar um Retorno Finalizado!");
		
		calendarioAtendimentoService.alterarPeriodoDoCalendarioParaDisponivel(retornoConsulta.getDataHorario());
		retornoConsultaRepository.delete(retornoConsulta);
		
		return ResponseEntity.noContent().build();
	}
	
	
	public ResponseEntity<Void> iniciarRetornoConsulta(Long idPaciente, Long idRetornoConsulta) {
		RetornoConsulta retornoConsulta = verificarSeRetornoConsultaPertenceAoPaciente(idPaciente, idRetornoConsulta);
		
		if (!retornoConsulta.getSituacaoRetorno().equals(SituacaoRetorno.AGUARDANDO_ATENDIMENTO))
			throw new AtendimentoException("Só é possível iniciar o Retorno da Consulta que esteja com a Situação de " 
					+ SituacaoRetorno.AGUARDANDO_ATENDIMENTO.getDescricao() + "!");
		
		retornoConsulta.setSituacaoRetorno(SituacaoRetorno.RETORNO_INICIADO);
		
		return ResponseEntity.ok().build();
	}
	
	
	public ResponseEntity<Void> finalizarRetornoConsulta(Long idPaciente, Long idRetornoConsulta) {
		RetornoConsulta retornoConsulta = verificarSeRetornoConsultaPertenceAoPaciente(idPaciente, idRetornoConsulta);
		
		if (!retornoConsulta.getSituacaoRetorno().equals(SituacaoRetorno.RETORNO_INICIADO))
			throw new AtendimentoException("Só é possível finalizar o Retorno da Consulta que esteja com a Situação de " 
					+ SituacaoRetorno.RETORNO_INICIADO.getDescricao() + "!");
		
		retornoConsulta.setSituacaoRetorno(SituacaoRetorno.RETORNO_FINALIZADO);
		
		return ResponseEntity.ok().build();
	}
	
	
	public ResponseEntity<Void> cadastrarAvaliacaoConsumoHabitual(Long idPaciente, Long idRetornoConsulta,
			AvaliacaoConsumoHabitualFORM avaliacaoConsumoHabitual) {
		
		RetornoConsulta retornoConsulta = verificarSeRetornoConsultaPertenceAoPaciente(idPaciente, idRetornoConsulta);
		validarSituacaoConsultaParaCadastroDeInformacoes(retornoConsulta);
		
		retornoConsulta.setAvaliacaoConsumoHabitual(avaliacaoConsumoHabitual.criarAvaliacaoConsumoHabitual());
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	public ResponseEntity<Void> cadastrarAvaliacaoComposicaoCorporal(Long idPaciente, Long idRetornoConsulta,
			AvaliacaoComposicaoCorporalFORM avaliacaoComposicaoCorporal) {
		
		RetornoConsulta retornoConsulta = verificarSeRetornoConsultaPertenceAoPaciente(idPaciente, idRetornoConsulta);
		validarSituacaoConsultaParaCadastroDeInformacoes(retornoConsulta);
		
		SexoUtils sexoPaciente = retornoConsulta.getConsulta().getPaciente().getSexo();
		retornoConsulta.setAvaliacaoComposicaoCorporal(avaliacaoComposicaoCorporal.criarAvaliacaoComposicaoCorporal(sexoPaciente));
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	public ResponseEntity<Void> cadastrarAvaliacaoMassaMuscularCorporeaMedidaAntropometrica(Long idPaciente, 
			Long idRetornoConsulta, AvaliacaoMassaMuscularCorporeaFORM avaliacaoMassaMuscularCorporea) {
		
		RetornoConsulta retornoConsulta = verificarSeRetornoConsultaPertenceAoPaciente(idPaciente, idRetornoConsulta);
		validarSituacaoConsultaParaCadastroDeInformacoes(retornoConsulta);
		
		retornoConsulta.setAvaliacaoMassaMuscularCorporeaAntropometrica(avaliacaoMassaMuscularCorporea.criarAvaliacaoMassaMuscularCorporea());
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	public ResponseEntity<Void> cadastrarCondutaNutricional(Long idPaciente, Long idRetornoConsulta,
			CondutaNutricionalFORM condutaNutricional) {
		
		RetornoConsulta retornoConsulta = verificarSeRetornoConsultaPertenceAoPaciente(idPaciente, idRetornoConsulta);
		validarSituacaoConsultaParaCadastroDeInformacoes(retornoConsulta);
		
		retornoConsulta.setCondutaNutricional(condutaNutricional.converterParaCondutaNutricional());
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	public ResponseEntity<Void> cadastrarRegistroDietaHabitual(Long idPaciente, Long idRetornoConsulta,
			RegistroDietaFORM registroDietaHabitual) {
		
		RetornoConsulta retornoConsulta = verificarSeRetornoConsultaPertenceAoPaciente(idPaciente, idRetornoConsulta);
		validarSituacaoConsultaParaCadastroDeInformacoes(retornoConsulta);
		
		retornoConsulta.setRegistroDietaHabitual(registroDietaHabitual.converterParaRegistroDieta(TipoRegistroDieta.HABITUAL));
	
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	public ResponseEntity<Void> cadastrarRegistroDieta24Horas(Long idPaciente, Long idRetornoConsulta,
			RegistroDietaFORM registroDieta24Horas) {
		
		RetornoConsulta retornoConsulta = verificarSeRetornoConsultaPertenceAoPaciente(idPaciente, idRetornoConsulta);
		validarSituacaoConsultaParaCadastroDeInformacoes(retornoConsulta);
		
		retornoConsulta.setRegistroDieta24Horas(registroDieta24Horas.converterParaRegistroDieta(TipoRegistroDieta.PERIODO_24H));
	
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	private void validarSituacaoConsultaParaCadastroDeInformacoes(RetornoConsulta retornoConsulta) {
		if (!retornoConsulta.getSituacaoRetorno().equals(SituacaoRetorno.RETORNO_INICIADO))
			throw new AtendimentoException("Só é possível cadastrar informações em um "
					+ "Retorno de Consulta caso ele tenha sido iniciado!");
	}
	
	
	private void verificarSePeriodoRetornoConsultaProcedePeriodoConsulta(Consulta consulta,
			CalendarioAtendimentoPaciente periodoAgendamento) {
			
		if (!periodoAgendamento.getData().isAfter(consulta.getDataHorario().toLocalDate()))
			throw new AtendimentoException("A data do Retorno deve ser marcada pelo menos "
					+ "um dia após a Consulta!");
	}
	
	
	private void verificarSeRetornoConsultaEstaAguardandoConfirmacao(RetornoConsulta retornoConsulta) {
		if (!retornoConsulta.getSituacaoRetorno().equals(SituacaoRetorno.AGUARDANDO_CONFIRMACAO))
			throw new AtendimentoException("Não é possível Remarcar/Confirmar um Retorno que não esteja com a Situação de " 
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
	
	
	private RetornoConsulta verificarSeRetornoConsultaPertenceAoPaciente(Long idPaciente, Long idRetornoConsulta) {
		Paciente paciente = pacienteUtils.verificarSePacienteExiste(idPaciente);	
		RetornoConsulta retornoConsultaPaciente = verificarSeRetornoConsultaExiste(idRetornoConsulta);
		
		if (!retornoConsultaPaciente.getConsulta().getPaciente().getId().equals(paciente.getId()))
			throw new IllegalArgumentException("Retorno de Consulta não pertence ao Paciente!");
		
		return retornoConsultaPaciente;
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
