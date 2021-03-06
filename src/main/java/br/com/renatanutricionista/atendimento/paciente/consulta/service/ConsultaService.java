package br.com.renatanutricionista.atendimento.paciente.consulta.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

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
import br.com.renatanutricionista.ficha.identificacao.historico.social.service.ImagemColoracaoDiureseService;
import br.com.renatanutricionista.medicamento.repository.MedicamentoRepository;
import br.com.renatanutricionista.paciente.model.Paciente;
import br.com.renatanutricionista.paciente.service.PacienteService;
import br.com.renatanutricionista.patologia.repository.PatologiaRepository;
import br.com.renatanutricionista.seguranca.usuario.service.UsuarioTokenService;
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
	private ImagemColoracaoDiureseService imagemColoracaoDiureseService;
	
	@Autowired
	private CalendarioAtendimentoPacienteService calendarioAtendimentoService;
	
	@Autowired
	private AtendimentoPacienteParametroService atendimentoPacienteParametroService;
	
	@Autowired
	private UsuarioTokenService usuarioTokenService;
	
	
	public ResponseEntity<byte[]> gerarRelatorioDosPagamentosPendentes() {
		
		return RelatorioUtils.gerarRelatorioEmPDF("pagamentos-pendentes", new HashMap<>());
	}
	
	
	public ResponseEntity<List<InformacoesPreviasConsultaRetornoDTO>> listarAtendimentosAPartirDaDataAtual() {
		LocalDate periodoAtual = LocalDate.now();
		
		List<Consulta> consultas = consultaRepository.findByDataGreaterThanEqual(periodoAtual);
		List<RetornoConsulta> retornos = retornoConsultaRepository.findByDataGreaterThanEqual(periodoAtual);
		
		return ResponseEntity.ok().body(InformacoesPreviasConsultaRetornoDTO.converterParaListaInformacoesPreviasConsultaRetornoDTO(consultas, retornos));
	}
	
	
	public ResponseEntity<List<InformacoesPreviasConsultaRetornoDTO>> listarAtendimentosAnterioresAoDiaAtual() {
		LocalDate periodoAtual = LocalDate.now();
		
		List<Consulta> consultas = consultaRepository.findByDataLessThan(periodoAtual);
		List<RetornoConsulta> retornos = retornoConsultaRepository.findByDataLessThan(periodoAtual);
		
		return ResponseEntity.ok().body(InformacoesPreviasConsultaRetornoDTO.converterParaListaInformacoesPreviasConsultaRetornoDTO(consultas, retornos));
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
			throw new AtendimentoException("S?? ?? poss??vel Confirmar uma Consulta quando a Situa????o for "
					+ SituacaoConsulta.AGUARDANDO_CONFIRMACAO.getDescricao() + "!");
		
		confirmacaoConsulta.atualizarInformacoesConsulta(consulta);
		
		return ResponseEntity.ok().build();
	}
	
	
	public ResponseEntity<Void> cancelarConsulta(HttpServletRequest request, Long idPaciente, Long idConsulta) {
		Consulta consulta = verificarPacienteConsulta(idPaciente, idConsulta);
		validarSituacaoConsultaParaCancelamento(request, consulta);
		
		consultaRepository.delete(consulta);
		calendarioAtendimentoService.alterarPeriodoDoCalendarioParaDisponivel(consulta.getData(), consulta.getHorario());
		
		return ResponseEntity.noContent().build();
	}
	
	
	public ResponseEntity<Void> iniciarConsulta(Long idPaciente, Long idConsulta) {
		Consulta consulta = verificarPacienteConsulta(idPaciente, idConsulta);	
		
		if (!consulta.getSituacaoConsulta().equals(SituacaoConsulta.AGUARDANDO_ATENDIMENTO))
			throw new AtendimentoException("S?? ?? poss??vel iniciar uma Consulta que esteja com a Situa????o de " 
					+ SituacaoConsulta.AGUARDANDO_ATENDIMENTO.getDescricao() + "!");
		
		consulta.setSituacaoConsulta(SituacaoConsulta.CONSULTA_INICIADA);
		
		return ResponseEntity.ok().build();
	}
	
	
	public ResponseEntity<InformacoesCadastroConsultaDTO> informacoesParaCadastrarConsulta(Long idPaciente, Long idConsulta) {
		Consulta consulta = verificarPacienteConsulta(idPaciente, idConsulta);	

		return ResponseEntity.ok().body(new InformacoesCadastroConsultaDTO(consulta, alimentoFrequenciaAlimentarRepository.findAll(),
				patologiaRepository.findAll(), medicamentoRepository.findAll(), suplementoRepository.findAll(),
				imagemColoracaoDiureseService.buscarImagensCoresDiurese().getBody()));
	}
	
	
	public ResponseEntity<Void> finalizarConsulta(Long idPaciente, Long idConsulta, ConsultaFORM formularioConsulta) {
		Consulta consulta = verificarPacienteConsulta(idPaciente, idConsulta);	
		
		if (!consulta.getSituacaoConsulta().equals(SituacaoConsulta.CONSULTA_INICIADA))
			throw new AtendimentoException("S?? ?? poss??vel finalizar uma Consulta que esteja com a Situa????o de " 
					+ SituacaoConsulta.CONSULTA_INICIADA.getDescricao() + "!");
		
		validarHistoricosPaciente(consulta.getPaciente());
		formularioConsulta.atualizarInformacoesDaConsulta(consulta);
		
		return ResponseEntity.ok().build();
	}
	
	
	private void validarHistoricosPaciente(Paciente paciente) {
		if (Objects.isNull(paciente.getHistoricosSociais()))
			throw new NullPointerException("O Hist??rico Social do Paciente n??o pode ser nulo!");
		
		if (Objects.isNull(paciente.getHistoricosAlimentares()))
			throw new NullPointerException("O Hist??rico Alimentar do Paciente n??o pode ser nulo!");
		
		if (Objects.isNull(paciente.getHistoricosAtividadeFisica()))
			throw new NullPointerException("O Hist??rico de Atividades F??sicas n??o pode ser nulo!");
		
		if (Objects.isNull(paciente.getHistoricosPatologiaFamiliaresPorData()))
			throw new NullPointerException("o Hist??rico de Patologias dos Familiares do Paciente por data "
					+ "n??o pode ser nulo!");
		
		if (Objects.isNull(paciente.getQuestionariosFrequenciaAlimentar()))
			throw new NullPointerException("O Question??rio de Frequ??ncia Alimentar do Paciente "
					+ "n??o pode ser nulo!");
	}
	
	
	private void verificarSeExisteConsultaOuRetornoEmAberto(Paciente paciente) {
		Optional<Consulta> consulta = consultaRepository.findByPacienteAndSituacaoConsultaNot(
				paciente, SituacaoConsulta.CONSULTA_FINALIZADA);
		
		if (consulta.isPresent())
			throw new AtendimentoException("N??o ?? poss??vel agendar uma consulta quando existe "
					+ "outra consulta em aberto!");
		
		Optional<RetornoConsulta> retorno = retornoConsultaRepository.findByConsulta_PacienteAndSituacaoRetornoNot(paciente, SituacaoRetorno.RETORNO_FINALIZADO);
		
		if (retorno.isPresent()) {
			throw new AtendimentoException("N??o ?? poss??vel agendar uma onsulta quando existe "
					+ "outra um retorno de consulta em aberto!");
		}
	}
	
	
	private void verificarSeConsultaParaRemarcarEstaAguardandoConfirmacao(Consulta consultaPacienteQueSeraCancelada) {
		if (!consultaPacienteQueSeraCancelada.getSituacaoConsulta().equals(SituacaoConsulta.AGUARDANDO_CONFIRMACAO))
			throw new AtendimentoException("N??o ?? poss??vel remarcar uma Consulta que n??o esteja com a Situa????o de "
					+ SituacaoConsulta.AGUARDANDO_CONFIRMACAO.getDescricao() + "!");
	}
	
	
	public Consulta verificarPacienteConsulta(Long idPaciente, Long idConsulta) {
		Paciente paciente = pacienteService.verificarSePacienteExiste(idPaciente);
		Consulta consulta = verificarSeConsultaExiste(idConsulta);
		verificarSeConsultaPertenceAoPaciente(paciente, consulta);
		
		return consulta;
	}
	
	
	private void validarSituacaoConsultaParaCancelamento(HttpServletRequest request, Consulta consulta) {
		if (consulta.getSituacaoConsulta().equals(SituacaoConsulta.CONSULTA_FINALIZADA))
			throw new AtendimentoException("N??o ?? poss??vel cancelar uma Consulta Finalizada!");
		
		if (consulta.getSituacaoConsulta().equals(SituacaoConsulta.CONSULTA_INICIADA)) {
			usuarioTokenService.usuarioTemPermissaoDeAdmin(request);
		}
	}
	
	
	public void verificarSeConsultaPertenceAoPaciente(Paciente paciente, Consulta consultaPaciente) {
		if (!paciente.getId().equals(consultaPaciente.getPaciente().getId()))
			throw new IllegalArgumentException("A Consulta selecionada n??o pertence ao Paciente!");
	}
	
	
	private void validarIntervaloDeTempoMinimoEntreRetornoEConsultaParaAgendamento(Paciente paciente, String data) {
		LocalDate dataRetornoConsulta = ConversaoUtils.converterStringParaLocalDate(data);
		Optional<Consulta> consulta = consultaRepository.findFirstByPacienteOrderByDataDesc(paciente);

		if (consulta.isPresent() && Objects.nonNull(consulta.get().getRetornoConsulta())) {
			AtendimentoPacienteParametro  atendimentoPacienteParametro = atendimentoPacienteParametroService.buscarAtendimentoPacienteParametro();
			long intervaloEntreRetornoEPeriodoAgendado = ChronoUnit.DAYS.between(consulta.get().getRetornoConsulta().getData(), dataRetornoConsulta);

			if (intervaloEntreRetornoEPeriodoAgendado < atendimentoPacienteParametro.getIntervaloDiasEntreRetornoConsulta()) {
				throw new AtendimentoException("O intervalo m??nimo entre o ??ltimo retorno e a consulta ?? de " 
						+ atendimentoPacienteParametro.getIntervaloDiasEntreRetornoConsulta() + " dias!");
			}
		}
	}
	
	public Consulta verificarSeConsultaExiste(Long idConsulta) {
		if (Objects.isNull(idConsulta))
			throw new NullPointerException("O ID da Consulta n??o pode nulo!");
			
		Optional<Consulta> consulta = consultaRepository.findById(idConsulta);
		
		if (consulta.isEmpty())
			throw new ObjectNotFoundException("Consulta n??o encontrada!");
		
		return consulta.get();
	}
}
