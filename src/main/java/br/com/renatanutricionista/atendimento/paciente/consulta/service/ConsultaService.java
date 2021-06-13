package br.com.renatanutricionista.atendimento.paciente.consulta.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.renatanutricionista.atendimento.paciente.consulta.dto.ConfirmacaoAtendimentoDTO;
import br.com.renatanutricionista.atendimento.paciente.consulta.dto.ConsultaDTO;
import br.com.renatanutricionista.atendimento.paciente.consulta.dto.InformacoesCadastroConsultaDTO;
import br.com.renatanutricionista.atendimento.paciente.consulta.dto.InformacoesPreviasConsultaRetornoDTO;
import br.com.renatanutricionista.atendimento.paciente.consulta.enums.situacao.consulta.SituacaoConsulta;
import br.com.renatanutricionista.atendimento.paciente.consulta.form.AgendamentoConsultaFORM;
import br.com.renatanutricionista.atendimento.paciente.consulta.form.ConfirmacaoConsultaFORM;
import br.com.renatanutricionista.atendimento.paciente.consulta.form.ConsultaFORM;
import br.com.renatanutricionista.atendimento.paciente.consulta.form.ReagendamentoConsultaFORM;
import br.com.renatanutricionista.atendimento.paciente.consulta.model.Consulta;
import br.com.renatanutricionista.atendimento.paciente.consulta.respository.ConsultaRepository;
import br.com.renatanutricionista.atendimento.paciente.retorno.enums.SituacaoRetorno;
import br.com.renatanutricionista.atendimento.paciente.retorno.model.RetornoConsulta;
import br.com.renatanutricionista.atendimento.paciente.retorno.repository.RetornoConsultaRepository;
import br.com.renatanutricionista.atendimento.paciente.utils.TipoAtendimento;
import br.com.renatanutricionista.calendario.atendimento.paciente.model.CalendarioAtendimentoPaciente;
import br.com.renatanutricionista.calendario.atendimento.paciente.service.CalendarioAtendimentoPacienteService;
import br.com.renatanutricionista.exception.custom.AtendimentoException;
import br.com.renatanutricionista.exception.custom.ObjectNotFoundException;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.alimentos.repository.AlimentoFrequenciaAlimentarRepository;
import br.com.renatanutricionista.medicamento.repository.MedicamentoRepository;
import br.com.renatanutricionista.paciente.model.Paciente;
import br.com.renatanutricionista.paciente.service.PacienteService;
import br.com.renatanutricionista.patologia.repository.PatologiaRepository;
import br.com.renatanutricionista.suplemento.repository.SuplementoRepository;
import br.com.renatanutricionista.tabelas.parametro.atendimento.paciente.model.AtendimentoPacienteParametro;
import br.com.renatanutricionista.tabelas.parametro.atendimento.paciente.service.AtendimentoPacienteParametroService;
import br.com.renatanutricionista.utils.ConversaoUtils;
import br.com.renatanutricionista.utils.RelatorioUtils;


@Service
public class ConsultaService {

	@Autowired
	private ConsultaRepository consultaRepository;
	
	@Autowired
	private RetornoConsultaRepository retornoConsultaRepository;
	
	@Autowired
	private AlimentoFrequenciaAlimentarRepository alimentoFrequenciaAlimentarRepository;
	
	@Autowired
	private PatologiaRepository patologiaRepository;
	
	@Autowired
	private MedicamentoRepository medicamentoRepository;
	
	@Autowired
	private SuplementoRepository suplementoRepository;
	
	@Autowired
	private PacienteService pacienteService;
	
	@Autowired
	private CalendarioAtendimentoPacienteService calendarioAtendimentoService;
	
	@Autowired
	private AtendimentoPacienteParametroService atendimentoPacienteParametroService;
	
	
	public ResponseEntity<byte[]> gerarRelatorioDosPagamentosPendentes() {
		
		return RelatorioUtils.gerarRelatorioEmPDF("pagamentos-pendentes", new HashMap<>());
	}
	
	
	public ResponseEntity<List<InformacoesPreviasConsultaRetornoDTO>> listarAtendimentosAPartirDaDataAtual() {
		LocalDate periodoAtual = LocalDate.now();
		LocalDate periodoFinal = periodoAtual.plusDays(30);
		
		List<Consulta> consultas = consultaRepository.findByDataGreaterThanEqual(periodoAtual);
		List<RetornoConsulta> retornos = retornoConsultaRepository.findByDataGreaterThanEqual(periodoAtual);
		
		return ResponseEntity.ok().body(InformacoesPreviasConsultaRetornoDTO.converterParaListaInformacoesPreviasConsultaRetornoDTO(
				consultas, retornos, periodoAtual, periodoFinal));
	}
	
	
	public ResponseEntity<List<InformacoesPreviasConsultaRetornoDTO>> listarAtendimentosPorPeriodo(String dataInicial, String dataFinal) {
		LocalDate periodoInicial = ConversaoUtils.converterStringParaLocalDate(dataInicial);
		LocalDate periodoFinal = ConversaoUtils.converterStringParaLocalDate(dataFinal);
		
		if (periodoInicial.isAfter(periodoFinal))
			throw new IllegalArgumentException("A data inicial não pode ser posterior à data final!");
		
		List<Consulta> consultas = consultaRepository.findByDataBetween(periodoInicial, periodoFinal);
		List<RetornoConsulta> retornos = retornoConsultaRepository.findByDataBetween(periodoInicial, periodoFinal);
		
		return ResponseEntity.ok().body(InformacoesPreviasConsultaRetornoDTO.converterParaListaInformacoesPreviasConsultaRetornoDTO(
				consultas, retornos, periodoInicial, periodoFinal));
	}
	
	
	public ResponseEntity<Integer> verificarProximoTipoDeAtendimentoDoPaciente(Long idPaciente) {
		Paciente paciente = pacienteService.verificarSePacienteExiste(idPaciente);
		Optional<Consulta> consulta = consultaRepository.findFirstByPacienteOrderByDataDesc(paciente);
		
		Integer ordinalTipoAtendimento = consulta.isEmpty() || Objects.nonNull(consulta.get().getRetornoConsulta()) 
				? TipoAtendimento.CONSULTA.ordinal() : TipoAtendimento.RETORNO_CONSULTA.ordinal();
		
		return ResponseEntity.ok().body(ordinalTipoAtendimento);
	}
	
	
	public ResponseEntity<ConfirmacaoAtendimentoDTO> informacoesParaConfirmacaoDeAtendimento() {
		AtendimentoPacienteParametro atendimentoPacienteParametro = atendimentoPacienteParametroService.buscarAtendimentoPacienteParametro();
		
		return ResponseEntity.ok().body(new ConfirmacaoAtendimentoDTO(atendimentoPacienteParametro));
	}
	
	
	public ResponseEntity<ConsultaDTO> buscarConsultaDoPaciente(Integer tipoAtendimento, Long idConsulta) {
		if (!TipoAtendimento.converterParaEnum(tipoAtendimento).equals(TipoAtendimento.CONSULTA)) 
			throw new IllegalArgumentException("O Tipo de Atendimento deve ser uma consulta!");
			
		Consulta consulta = verificarSeConsultaExiste(idConsulta);
		
		return ResponseEntity.ok().body(new ConsultaDTO(consulta));
	}

	
	public ResponseEntity<Void> agendarConsulta(Long idPaciente, AgendamentoConsultaFORM agendamentoConsulta) {
		Paciente paciente = pacienteService.verificarSePacienteExiste(idPaciente);
		verificarSeExisteConsultaOuRetornoEmAberto(paciente);
		validarIntervaloDeTempoMinimoEntreRetornoEConsultaParaAgendamento(paciente, agendamentoConsulta.getData());
		
		CalendarioAtendimentoPaciente periodoAgendamento = calendarioAtendimentoService
				.verificarPossibilidadeDeAgendarConsultaRetorno(agendamentoConsulta.getData(), agendamentoConsulta.getHorario());
		
		consultaRepository.save(agendamentoConsulta.converterParaConsulta(paciente, periodoAgendamento));
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	public ResponseEntity<Void> reagendarConsulta(Long idPaciente, Long idConsulta, ReagendamentoConsultaFORM reagendamentoConsulta) {
		Paciente paciente = pacienteService.verificarSePacienteExiste(idPaciente);
		Consulta consultaPaciente = verificarSeConsultaExiste(idConsulta);
		
		verificarSeConsultaPertenceAoPaciente(paciente, consultaPaciente);
		verificarSeConsultaParaRemarcarEstaAguardandoConfirmacao(consultaPaciente);
		validarIntervaloDeTempoMinimoEntreRetornoEConsultaParaAgendamento(paciente, reagendamentoConsulta.getData());
		calendarioAtendimentoService.alterarPeriodoDoCalendarioParaDisponivel(consultaPaciente.getData(), consultaPaciente.getHorario());
		
		CalendarioAtendimentoPaciente periodoReagendamento = calendarioAtendimentoService.verificarPossibilidadeDeAgendarConsultaRetorno(
				reagendamentoConsulta.getData(), reagendamentoConsulta.getHorario());

		reagendamentoConsulta.atualizarInformacoesDaConsulta(consultaPaciente, periodoReagendamento);
		
		return ResponseEntity.ok().build();
	}
	
	
	public ResponseEntity<Void> confirmarConsulta(Long idPaciente, Long idConsulta, ConfirmacaoConsultaFORM confirmacaoConsulta) {
		Consulta consulta = verificarPacienteConsulta(idPaciente, idConsulta);
		
		if (!consulta.getSituacaoConsulta().equals(SituacaoConsulta.AGUARDANDO_CONFIRMACAO))
			throw new AtendimentoException("Só é possível Confirmar uma Consulta quando a Situação for "
					+ SituacaoConsulta.AGUARDANDO_CONFIRMACAO.getDescricao() + "!");
		
		confirmacaoConsulta.atualizarInformacoesConsulta(consulta);
		
		return ResponseEntity.ok().build();
	}
	
	
	public ResponseEntity<Void> cancelarConsulta(Long idPaciente, Long idConsulta) {
		Consulta consulta = verificarPacienteConsulta(idPaciente, idConsulta);

		if (consulta.getSituacaoConsulta().equals(SituacaoConsulta.CONSULTA_FINALIZADA))
			throw new AtendimentoException("Não é possível cancelar uma Consulta Finalizada!");
		
		consultaRepository.delete(consulta);
		calendarioAtendimentoService.alterarPeriodoDoCalendarioParaDisponivel(consulta.getData(), consulta.getHorario());
		
		return ResponseEntity.noContent().build();
	}
	
	
	public ResponseEntity<Void> iniciarConsulta(Long idPaciente, Long idConsulta) {
		Consulta consulta = verificarPacienteConsulta(idPaciente, idConsulta);	
		
		if (!consulta.getSituacaoConsulta().equals(SituacaoConsulta.AGUARDANDO_ATENDIMENTO))
			throw new AtendimentoException("Só é possível iniciar uma Consulta que esteja com a Situação de " 
					+ SituacaoConsulta.AGUARDANDO_ATENDIMENTO.getDescricao() + "!");
		
		consulta.setSituacaoConsulta(SituacaoConsulta.CONSULTA_INICIADA);
		
		return ResponseEntity.ok().build();
	}
	
	
	public ResponseEntity<InformacoesCadastroConsultaDTO> informacoesParaCadastrarConsulta(Long idPaciente, Long idConsulta) {
		Consulta consulta = verificarPacienteConsulta(idPaciente, idConsulta);	

		return ResponseEntity.ok().body(new InformacoesCadastroConsultaDTO(consulta, alimentoFrequenciaAlimentarRepository.findAll(),
				patologiaRepository.findAll(), medicamentoRepository.findAll(), suplementoRepository.findAll()));
	}
	
	
	public ResponseEntity<Void> finalizarConsulta(Long idPaciente, Long idConsulta, ConsultaFORM formularioConsulta) {
		Consulta consulta = verificarPacienteConsulta(idPaciente, idConsulta);	
		
		if (!consulta.getSituacaoConsulta().equals(SituacaoConsulta.CONSULTA_INICIADA))
			throw new AtendimentoException("Só é possível finalizar uma Consulta que esteja com a Situação de " 
					+ SituacaoConsulta.CONSULTA_INICIADA.getDescricao() + "!");
		
		validarHistoricosPaciente(consulta.getPaciente());
		formularioConsulta.atualizarInformacoesDaConsulta(consulta);
		
		return ResponseEntity.ok().build();
	}
	
	
	private void validarHistoricosPaciente(Paciente paciente) {
		if (Objects.isNull(paciente.getHistoricosSociais()))
			throw new NullPointerException("O Histórico Social do Paciente não pode ser nulo!");
		
		if (Objects.isNull(paciente.getHistoricosAlimentares()))
			throw new NullPointerException("O Histórico Alimentar do Paciente não pode ser nulo!");
		
		if (Objects.isNull(paciente.getHistoricosAtividadeFisica()))
			throw new NullPointerException("O Histórico de Atividades Físicas não pode ser nulo!");
		
		if (Objects.isNull(paciente.getHistoricosPatologiaFamiliaresPorData()))
			throw new NullPointerException("o Histórico de Patologias dos Familiares do Paciente por data "
					+ "não pode ser nulo!");
		
		if (Objects.isNull(paciente.getQuestionariosFrequenciaAlimentar()))
			throw new NullPointerException("O Questionário de Frequência Alimentar do Paciente "
					+ "não pode ser nulo!");
	}
	
	
	private void verificarSeExisteConsultaOuRetornoEmAberto(Paciente paciente) {
		Optional<Consulta> consulta = consultaRepository.findByPacienteAndSituacaoConsultaNot(
				paciente, SituacaoConsulta.CONSULTA_FINALIZADA);
		
		if (consulta.isPresent())
			throw new AtendimentoException("Não é possível agendar uma consulta quando existe "
					+ "outra consulta em aberto!");
		
		Optional<RetornoConsulta> retorno = retornoConsultaRepository.findByConsulta_PacienteAndSituacaoRetornoNot(paciente, SituacaoRetorno.RETORNO_FINALIZADO);
		
		if (retorno.isPresent()) {
			throw new AtendimentoException("Não é possível agendar uma onsulta quando existe "
					+ "outra um retorno de consulta em aberto!");
		}
	}
	
	
	private void verificarSeConsultaParaRemarcarEstaAguardandoConfirmacao(Consulta consultaPacienteQueSeraCancelada) {
		if (!consultaPacienteQueSeraCancelada.getSituacaoConsulta().equals(SituacaoConsulta.AGUARDANDO_CONFIRMACAO))
			throw new AtendimentoException("Não é possível remarcar uma Consulta que não esteja com a Situação de "
					+ SituacaoConsulta.AGUARDANDO_CONFIRMACAO.getDescricao() + "!");
	}
	
	
	public Consulta verificarPacienteConsulta(Long idPaciente, Long idConsulta) {
		Paciente paciente = pacienteService.verificarSePacienteExiste(idPaciente);
		Consulta consulta = verificarSeConsultaExiste(idConsulta);
		verificarSeConsultaPertenceAoPaciente(paciente, consulta);
		
		return consulta;
	}
	
	
	public void verificarSeConsultaPertenceAoPaciente(Paciente paciente, Consulta consultaPaciente) {
		if (!paciente.getId().equals(consultaPaciente.getPaciente().getId()))
			throw new IllegalArgumentException("A Consulta selecionada não pertence ao Paciente!");
	}
	
	
	private void validarIntervaloDeTempoMinimoEntreRetornoEConsultaParaAgendamento(Paciente paciente, String data) {
		LocalDate dataRetornoConsulta = ConversaoUtils.converterStringParaLocalDate(data);
		Optional<Consulta> consulta = consultaRepository.findFirstByPacienteOrderByDataDesc(paciente);

		if (consulta.isPresent() && Objects.nonNull(consulta.get().getRetornoConsulta())) {
			AtendimentoPacienteParametro  atendimentoPacienteParametro = atendimentoPacienteParametroService.buscarAtendimentoPacienteParametro();
			long intervaloEntreRetornoEPeriodoAgendado = ChronoUnit.DAYS.between(consulta.get().getRetornoConsulta().getData(), dataRetornoConsulta);

			if (intervaloEntreRetornoEPeriodoAgendado < atendimentoPacienteParametro.getIntervaloDiasEntreRetornoConsulta()) {
				throw new AtendimentoException("O intervalo mínimo entre o último retorno e a consulta é de " 
						+ atendimentoPacienteParametro.getIntervaloDiasEntreRetornoConsulta() + " dias!");
			}
		}
	}
	
	public Consulta verificarSeConsultaExiste(Long idConsulta) {
		if (Objects.isNull(idConsulta))
			throw new NullPointerException("O ID da Consulta não pode nulo!");
			
		Optional<Consulta> consulta = consultaRepository.findById(idConsulta);
		
		if (consulta.isEmpty())
			throw new ObjectNotFoundException("Consulta não encontrada!");
		
		return consulta.get();
	}
}
