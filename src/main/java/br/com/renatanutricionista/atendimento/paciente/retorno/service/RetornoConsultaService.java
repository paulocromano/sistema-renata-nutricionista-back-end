package br.com.renatanutricionista.atendimento.paciente.retorno.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.renatanutricionista.atendimento.paciente.consulta.enums.situacao.consulta.SituacaoConsulta;
import br.com.renatanutricionista.atendimento.paciente.consulta.model.Consulta;
import br.com.renatanutricionista.atendimento.paciente.retorno.dto.InformacoesCadastroRetornoConsultaDTO;
import br.com.renatanutricionista.atendimento.paciente.retorno.dto.RetornoConsultaDTO;
import br.com.renatanutricionista.atendimento.paciente.retorno.enums.SituacaoRetorno;
import br.com.renatanutricionista.atendimento.paciente.retorno.form.AgendamentoRetornoFORM;
import br.com.renatanutricionista.atendimento.paciente.retorno.form.ReagendamentoRetornoFORM;
import br.com.renatanutricionista.atendimento.paciente.retorno.form.RetornoConsultaFORM;
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


@Service
public class RetornoConsultaService {
	
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
	private AtendimentoPacienteParametroService atendimentoPacienteParametroService;
	
	@Autowired
	private CalendarioAtendimentoPacienteService calendarioAtendimentoService;
	
	@Autowired
	private UsuarioTokenService usuarioTokenService;
	
	
	public ResponseEntity<RetornoConsultaDTO> buscarRetornoConsultaDoPaciente(Integer tipoAtendimento, Long idRetornoConsulta) {
		if (!TipoAtendimento.converterParaEnum(tipoAtendimento).equals(TipoAtendimento.RETORNO_CONSULTA)) 
			throw new IllegalArgumentException("O Tipo de Atendimento deve ser um retorno de consulta!");
		
		RetornoConsulta retornoConsulta = verificarSeRetornoConsultaExiste(idRetornoConsulta);
		
		return ResponseEntity.ok().body(new RetornoConsultaDTO(retornoConsulta));
	}

	
	public ResponseEntity<Void> agendarRetorno(Long idPaciente, AgendamentoRetornoFORM agendamentoRetorno) {
		Paciente paciente = pacienteService.verificarSePacienteExiste(idPaciente);
		Consulta consulta = buscarConsultaFinalizadaMaisRecenteDoPaciente(paciente);
		validarIntervaloDeTempoMinimoEntreConsultaParaAgendamentoDoRetorno(paciente, consulta, agendamentoRetorno.getData());
		
		CalendarioAtendimentoPaciente periodoAgendamento = calendarioAtendimentoService
				.verificarPossibilidadeDeAgendarConsultaRetorno(agendamentoRetorno.getData(), agendamentoRetorno.getHorario());
		
		consulta.setRetornoConsulta(agendamentoRetorno.converterParaRetornoConsulta(periodoAgendamento));
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	public ResponseEntity<Void> reagendarRetorno(Long idPaciente, Long idRetornoConsulta, ReagendamentoRetornoFORM reagendamentoRetorno) {
		Paciente paciente = pacienteService.verificarSePacienteExiste(idPaciente);
		RetornoConsulta retornoConsulta = verificarSeRetornoConsultaPertenceAoPaciente(idPaciente, idRetornoConsulta);
		verificarSeRetornoConsultaEstaAguardandoConfirmacao(retornoConsulta);
		validarIntervaloDeTempoMinimoEntreConsultaParaAgendamentoDoRetorno(paciente, retornoConsulta.getConsulta(), reagendamentoRetorno.getData());
		calendarioAtendimentoService.alterarPeriodoDoCalendarioParaDisponivel(retornoConsulta.getData(), retornoConsulta.getHorario());
		
		CalendarioAtendimentoPaciente periodoReagendamento = calendarioAtendimentoService.verificarPossibilidadeDeAgendarConsultaRetorno(
				reagendamentoRetorno.getData(), reagendamentoRetorno.getHorario());
		
		reagendamentoRetorno.atualizarInformacoesRetornoPaciente(retornoConsulta, periodoReagendamento);
		
		return ResponseEntity.ok().build();
	}
	
	
	public ResponseEntity<Void> confirmarRetornoConsulta(Long idPaciente, Long idRetornoConsulta) {
		RetornoConsulta retornoConsulta = verificarSeRetornoConsultaPertenceAoPaciente(idPaciente, idRetornoConsulta);
		verificarSeRetornoConsultaEstaAguardandoConfirmacao(retornoConsulta);
		
		retornoConsulta.setSituacaoRetorno(SituacaoRetorno.AGUARDANDO_ATENDIMENTO);
		
		return ResponseEntity.ok().build();
	}
	
	
	public ResponseEntity<Void> cancelarRetornoConsulta(HttpServletRequest reqeust, Long idPaciente, Long idRetornoConsulta) {
		RetornoConsulta retornoConsulta = verificarSeRetornoConsultaPertenceAoPaciente(idPaciente, idRetornoConsulta);
		verificarSituacaoRetornoParaCancelamento(reqeust, retornoConsulta);

		calendarioAtendimentoService.alterarPeriodoDoCalendarioParaDisponivel(retornoConsulta.getData(), retornoConsulta.getHorario());
		retornoConsulta.getConsulta().setRetornoConsulta(null);
		
		return ResponseEntity.noContent().build();
	}
	
	
	public ResponseEntity<Void> iniciarRetornoConsulta(Long idPaciente, Long idRetornoConsulta) {
		RetornoConsulta retornoConsulta = verificarSeRetornoConsultaPertenceAoPaciente(idPaciente, idRetornoConsulta);
		
		if (!retornoConsulta.getSituacaoRetorno().equals(SituacaoRetorno.AGUARDANDO_ATENDIMENTO))
			throw new AtendimentoException("S?? ?? poss??vel iniciar o Retorno da Consulta que esteja com a Situa????o de " 
					+ SituacaoRetorno.AGUARDANDO_ATENDIMENTO.getDescricao() + "!");
		
		retornoConsulta.setSituacaoRetorno(SituacaoRetorno.RETORNO_INICIADO);
		
		return ResponseEntity.ok().build();
	}
	
	
	public ResponseEntity<InformacoesCadastroRetornoConsultaDTO> informacoesParaCadastrarRetornoConsulta(Long idPaciente, Long idRetornoConsulta) {
		RetornoConsulta retornoConsulta = verificarSeRetornoConsultaPertenceAoPaciente(idPaciente, idRetornoConsulta);

		return ResponseEntity.ok().body(new InformacoesCadastroRetornoConsultaDTO(retornoConsulta.getConsulta(), alimentoFrequenciaAlimentarRepository.findAll(),
				patologiaRepository.findAll(), medicamentoRepository.findAll(), suplementoRepository.findAll(),
				imagemColoracaoDiureseService.buscarImagensCoresDiurese().getBody()));
	}
	
	
	public ResponseEntity<Void> finalizarRetornoConsulta(Long idPaciente, Long idRetornoConsulta, RetornoConsultaFORM formularioRetornoConsulta) {
		RetornoConsulta retornoConsulta = verificarSeRetornoConsultaPertenceAoPaciente(idPaciente, idRetornoConsulta);
		
		if (!retornoConsulta.getSituacaoRetorno().equals(SituacaoRetorno.RETORNO_INICIADO))
			throw new AtendimentoException("S?? ?? poss??vel finalizar o Retorno da Consulta que esteja com a Situa????o de " 
					+ SituacaoRetorno.RETORNO_INICIADO.getDescricao() + "!");
		
		formularioRetornoConsulta.atualizarInformacoesRetornoConsulta(retornoConsulta);
		
		return ResponseEntity.ok().build();
	}
	
	
	private void verificarSeRetornoConsultaEstaAguardandoConfirmacao(RetornoConsulta retornoConsulta) {
		if (!retornoConsulta.getSituacaoRetorno().equals(SituacaoRetorno.AGUARDANDO_CONFIRMACAO))
			throw new AtendimentoException("N??o ?? poss??vel Remarcar/Confirmar um Retorno que n??o esteja com a Situa????o de " 
										+ SituacaoRetorno.AGUARDANDO_CONFIRMACAO.getDescricao() + "!");
	}
	
	
	private RetornoConsulta verificarSeRetornoConsultaExiste(Long idRetorno) {
		if (Objects.isNull(idRetorno))
			throw new AtendimentoException("O ID do Retorno est?? nulo!");
		
		Optional<RetornoConsulta> retornoConsulta = retornoConsultaRepository.findById(idRetorno);
		
		if (retornoConsulta.isEmpty())
			throw new ObjectNotFoundException("Retorno n??o encontrado!");
		
		return retornoConsulta.get();
	}
	
	
	private RetornoConsulta verificarSeRetornoConsultaPertenceAoPaciente(Long idPaciente, Long idRetornoConsulta) {
		RetornoConsulta retornoConsultaPaciente = verificarSeRetornoConsultaExiste(idRetornoConsulta);
		
		if (!retornoConsultaPaciente.getConsulta().getPaciente().getId().equals(idPaciente))
			throw new IllegalArgumentException("Retorno de Consulta n??o pertence ao Paciente!");
		
		return retornoConsultaPaciente;
	}
	
	
	private Consulta buscarConsultaFinalizadaMaisRecenteDoPaciente(Paciente paciente) {
		List<Consulta> consultas = paciente.getConsultas();
		
		if (consultas.isEmpty()) 
			throw new AtendimentoException("N??o foi poss??vel agendar o retorno pois n??o existe uma "
					+ "consulta previamente finalizada!");
		
		Consulta consulta = consultas.stream().sorted(Comparator.comparing(Consulta::getData).reversed()).findFirst().get();	
		
		if (Objects.nonNull(consulta.getRetornoConsulta()))
			throw new AtendimentoException("N??o ?? poss??vel agendar o Retorno pois "
					+ "j?? existe um cadastrado!");
		
		if (!consulta.getSituacaoConsulta().equals(SituacaoConsulta.CONSULTA_FINALIZADA))
			throw new AtendimentoException("?? necess??rio finalizar a Consulta para realizar "
					+ "o agendamento do Retorno!");
		
		return consulta;
	}
	
	
	private void verificarSituacaoRetornoParaCancelamento(HttpServletRequest request, RetornoConsulta retornoConsulta) {
		if (retornoConsulta.getSituacaoRetorno().equals(SituacaoRetorno.RETORNO_FINALIZADO))
			throw new AtendimentoException("N??o ?? poss??vel cancelar um Retorno Finalizado!");
		
		if (retornoConsulta.getSituacaoRetorno().equals(SituacaoRetorno.RETORNO_INICIADO)) {
			usuarioTokenService.usuarioTemPermissaoDeAdmin(request);
		}
	}
	
	
	private void validarIntervaloDeTempoMinimoEntreConsultaParaAgendamentoDoRetorno(Paciente paciente, Consulta consulta, String data) {
		LocalDate dataRetornoConsulta = ConversaoUtils.converterStringParaLocalDate(data);
		AtendimentoPacienteParametro  atendimentoPacienteParametro = atendimentoPacienteParametroService.buscarAtendimentoPacienteParametro();
		long intervaloEntreConsultaEPeriodoAgendado = ChronoUnit.DAYS.between(consulta.getData(), dataRetornoConsulta);
		
		if (paciente.getConsultas().size() == 1) {
			if (intervaloEntreConsultaEPeriodoAgendado < atendimentoPacienteParametro.getIntervaloDiasEntrePrimeiraConsultaRetorno()) {
				throw new AtendimentoException("O intervalo m??nimo entre a primeira consulta e o retorno ?? de " 
						+ atendimentoPacienteParametro.getIntervaloDiasEntrePrimeiraConsultaRetorno() + " dias!");
			}
		}
		else {
			if (intervaloEntreConsultaEPeriodoAgendado < atendimentoPacienteParametro.getIntervaloDiasEntreConsultaRetorno()) {
				throw new AtendimentoException("O intervalo m??nimo entre a consulta e o retorno ?? de " 
						+ atendimentoPacienteParametro.getIntervaloDiasEntreConsultaRetorno() + " dias!");
			}
		}
	}
}
