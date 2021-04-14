package br.com.renatanutricionista.atendimento.paciente.consulta.service;

import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.renatanutricionista.atendimento.paciente.consulta.dto.InformacoesConsultaHistoricoParaCadastroDTO;
import br.com.renatanutricionista.atendimento.paciente.consulta.enums.situacao.consulta.SituacaoConsulta;
import br.com.renatanutricionista.atendimento.paciente.consulta.form.AgendamentoConsultaFORM;
import br.com.renatanutricionista.atendimento.paciente.consulta.form.ConfirmacaoConsultaFORM;
import br.com.renatanutricionista.atendimento.paciente.consulta.form.ConsultaFORM;
import br.com.renatanutricionista.atendimento.paciente.consulta.form.ReagendamentoConsultaFORM;
import br.com.renatanutricionista.atendimento.paciente.consulta.model.Consulta;
import br.com.renatanutricionista.atendimento.paciente.consulta.respository.ConsultaRepository;
import br.com.renatanutricionista.atendimento.paciente.utils.AtendimentoUtils;
import br.com.renatanutricionista.calendario.atendimento.paciente.model.CalendarioAtendimentoPaciente;
import br.com.renatanutricionista.calendario.atendimento.paciente.service.CalendarioAtendimentoPacienteService;
import br.com.renatanutricionista.exception.custom.AtendimentoException;
import br.com.renatanutricionista.paciente.dto.PacientePreviaHistoricosDTO;
import br.com.renatanutricionista.paciente.model.Paciente;
import br.com.renatanutricionista.paciente.utils.PacienteUtils;
import br.com.renatanutricionista.utils.RelatorioUtils;


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
	
	
	public ResponseEntity<byte[]> gerarRelatorioDosPagamentosPendentes() {
		
		return RelatorioUtils.gerarRelatorioEmPDF("pagamentos-pendentes", new HashMap<>());
	}
	
	
	public ResponseEntity<Void> agendarConsulta(Long idPaciente, AgendamentoConsultaFORM agendamentoConsulta) {
		Paciente paciente = pacienteUtils.verificarSePacienteExiste(idPaciente);
		verificarSeExisteConsultaEmAberto(paciente);
		
		CalendarioAtendimentoPaciente periodoAgendamento = calendarioAtendimentoService
				.verificarPossibilidadeDeAgendarConsultaRetorno(agendamentoConsulta.getData(), agendamentoConsulta.getHorario());
		
		consultaRepository.save(agendamentoConsulta.converterParaConsulta(paciente, periodoAgendamento));
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	public ResponseEntity<Void> reagendarConsulta(Long idPaciente, Long idConsulta, ReagendamentoConsultaFORM reagendamentoConsulta) {
		Paciente paciente = pacienteUtils.verificarSePacienteExiste(idPaciente);
		
		Consulta consultaPaciente = atendimentoUtils.verificarSeConsultaExiste(idConsulta);
		
		atendimentoUtils.verificarSeConsultaPertenceAoPaciente(paciente, consultaPaciente);
		verificarSeConsultaParaRemarcarEstaAguardandoConfirmacao(consultaPaciente);
		calendarioAtendimentoService.verificarPossibilidadeDeAgendarConsultaRetorno(reagendamentoConsulta.getData(), reagendamentoConsulta.getHorario());
		
		reagendamentoConsulta.atualizarInformacoesDaConsulta(consultaPaciente);
		
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
		calendarioAtendimentoService.alterarPeriodoDoCalendarioParaDisponivel(consulta.getDataHorario());
		
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
	
	
	public ResponseEntity<InformacoesConsultaHistoricoParaCadastroDTO> informacoesPacienteHistoricosParaCadastroNaConsulta(Long idPaciente, Long idConsulta) {
		Consulta consulta = atendimentoUtils.verificarPacienteConsulta(idPaciente, idConsulta);	
		PacientePreviaHistoricosDTO pacientePreviaHistoricos = new PacientePreviaHistoricosDTO(consulta.getPaciente());
		
		return ResponseEntity.ok().body(new InformacoesConsultaHistoricoParaCadastroDTO(pacientePreviaHistoricos));
	}
	
	
	public ResponseEntity<Void> finalizarConsulta(Long idPaciente, Long idConsulta, ConsultaFORM formularioConsulta) {
		Consulta consulta = atendimentoUtils.verificarPacienteConsulta(idPaciente, idConsulta);	
		
		if (!consulta.getSituacaoConsulta().equals(SituacaoConsulta.CONSULTA_INICIADA))
			throw new AtendimentoException("Só é possível finalizar uma Consulta que esteja com a Situação de " 
					+ SituacaoConsulta.CONSULTA_INICIADA.getDescricao() + "!");
		
		validarHistoricosPaciente(consulta.getPaciente());
		formularioConsulta.atualizarInformacoesDaConsulta(consulta);
		
		return ResponseEntity.ok().build();
	}
	
	
	private void validarHistoricosPaciente(Paciente paciente) {
		if (Objects.isNull(paciente.getHistoricoSocial()))
			throw new NullPointerException("O Histórico Social do Paciente não pode ser nulo!");
		
		if (Objects.isNull(paciente.getHistoricoAlimentar()))
			throw new NullPointerException("O Histórico Alimentar do Paciente não pode ser nulo!");
		
		if (Objects.isNull(paciente.getHistoricoAtividadeFisica()))
			throw new NullPointerException("O Histórico de Atividades Físicas não pode ser nulo!");
		
		if (Objects.isNull(paciente.getHistoricoPatologiaFamiliaresPorData()))
			throw new NullPointerException("o Histórico de Patologias dos Familiares do Paciente por data "
					+ "não pode ser nulo!");
		
		if (Objects.isNull(paciente.getQuestionarioFrequenciaAlimentar()))
			throw new NullPointerException("O Questionário de Frequência Alimentar do Paciente "
					+ "não pode ser nulo!");
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
}
