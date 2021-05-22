package br.com.renatanutricionista.atendimento.paciente.retorno.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.renatanutricionista.atendimento.paciente.consulta.enums.situacao.consulta.SituacaoConsulta;
import br.com.renatanutricionista.atendimento.paciente.consulta.model.Consulta;
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
import br.com.renatanutricionista.paciente.model.Paciente;
import br.com.renatanutricionista.paciente.service.PacienteService;
import br.com.renatanutricionista.tabelas.parametro.atendimento.paciente.model.AtendimentoPacienteParametro;
import br.com.renatanutricionista.tabelas.parametro.atendimento.paciente.service.AtendimentoPacienteParametroService;
import br.com.renatanutricionista.utils.ConversaoUtils;


@Service
public class RetornoConsultaService {
	
	@Autowired
	private RetornoConsultaRepository retornoConsultaRepository;

	@Autowired
	private PacienteService pacienteService;
	
	@Autowired
	private AtendimentoPacienteParametroService atendimentoPacienteParametroService;
	
	@Autowired
	private CalendarioAtendimentoPacienteService calendarioAtendimentoService;
	
	
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
	
	
	public ResponseEntity<Void> cancelarRetornoConsulta(Long idPaciente, Long idRetornoConsulta) {
		RetornoConsulta retornoConsulta = verificarSeRetornoConsultaPertenceAoPaciente(idPaciente, idRetornoConsulta);
		
		if (retornoConsulta.getSituacaoRetorno().equals(SituacaoRetorno.RETORNO_FINALIZADO))
			throw new AtendimentoException("Não é possível cancelar um Retorno Finalizado!");

		calendarioAtendimentoService.alterarPeriodoDoCalendarioParaDisponivel(retornoConsulta.getData(), retornoConsulta.getHorario());
		retornoConsulta.getConsulta().setRetornoConsulta(null);
		
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
	
	
	public ResponseEntity<Void> finalizarRetornoConsulta(Long idPaciente, Long idRetornoConsulta, RetornoConsultaFORM formularioRetornoConsulta) {
		RetornoConsulta retornoConsulta = verificarSeRetornoConsultaPertenceAoPaciente(idPaciente, idRetornoConsulta);
		
		if (!retornoConsulta.getSituacaoRetorno().equals(SituacaoRetorno.RETORNO_INICIADO))
			throw new AtendimentoException("Só é possível finalizar o Retorno da Consulta que esteja com a Situação de " 
					+ SituacaoRetorno.RETORNO_INICIADO.getDescricao() + "!");
		
		formularioRetornoConsulta.atualizarInformacoesRetornoConsulta(retornoConsulta);
		
		return ResponseEntity.ok().build();
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
		RetornoConsulta retornoConsultaPaciente = verificarSeRetornoConsultaExiste(idRetornoConsulta);
		
		if (!retornoConsultaPaciente.getConsulta().getPaciente().getId().equals(idPaciente))
			throw new IllegalArgumentException("Retorno de Consulta não pertence ao Paciente!");
		
		return retornoConsultaPaciente;
	}
	
	
	private Consulta buscarConsultaFinalizadaMaisRecenteDoPaciente(Paciente paciente) {
		List<Consulta> consultas = paciente.getConsultas();
		
		if (consultas.isEmpty()) 
			throw new AtendimentoException("Não foi possível agendar o retorno pois não existe uma "
					+ "consulta previamente finalizada!");
		
		Consulta consulta = consultas.stream().sorted(Comparator.comparing(Consulta::getData).reversed()).findFirst().get();	
		
		if (Objects.nonNull(consulta.getRetornoConsulta()))
			throw new AtendimentoException("Não é possível agendar o Retorno pois "
					+ "já existe um cadastrado!");
		
		if (!consulta.getSituacaoConsulta().equals(SituacaoConsulta.CONSULTA_FINALIZADA))
			throw new AtendimentoException("É necessário finalizar a Consulta para realizar "
					+ "o agendamento do Retorno!");
		
		return consulta;
	}
	
	
	private void validarIntervaloDeTempoMinimoEntreConsultaParaAgendamentoDoRetorno(Paciente paciente, Consulta consulta, String data) {
		LocalDate dataRetornoConsulta = ConversaoUtils.converterStringParaLocalDate(data);
		AtendimentoPacienteParametro  atendimentoPacienteParametro = atendimentoPacienteParametroService.buscarAtendimentoPacienteParametro();
		Period intervaloEntreConsultaEPeriodoAgendado = Period.between(consulta.getData(), dataRetornoConsulta);
		
		if (paciente.getConsultas().size() == 1) {
			if (Math.abs(intervaloEntreConsultaEPeriodoAgendado.getDays()) < atendimentoPacienteParametro.getIntervaloDiasEntrePrimeiraConsultaRetorno()) {
				throw new AtendimentoException("O intervalo mínimo entre a primeira consulta e o retorno é de " 
						+ atendimentoPacienteParametro.getIntervaloDiasEntrePrimeiraConsultaRetorno() + " dias!");
			}
		}
		else {
			if (Math.abs(intervaloEntreConsultaEPeriodoAgendado.getDays()) < atendimentoPacienteParametro.getIntervaloDiasEntreConsultaRetorno()) {
				throw new AtendimentoException("O intervalo mínimo entre a consulta e o retorno é de " 
						+ atendimentoPacienteParametro.getIntervaloDiasEntreConsultaRetorno() + " dias!");
			}
		}
	}
}
