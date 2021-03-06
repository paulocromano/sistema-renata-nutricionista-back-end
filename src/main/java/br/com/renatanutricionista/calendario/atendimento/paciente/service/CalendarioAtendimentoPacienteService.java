package br.com.renatanutricionista.calendario.atendimento.paciente.service;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.renatanutricionista.calendario.atendimento.paciente.dto.CalendarioAtendimentoPacienteDTO;
import br.com.renatanutricionista.calendario.atendimento.paciente.form.CalendarioAtendimentoPacienteFORM;
import br.com.renatanutricionista.calendario.atendimento.paciente.form.PeriodoAtendimentoFORM;
import br.com.renatanutricionista.calendario.atendimento.paciente.model.CalendarioAtendimentoPaciente;
import br.com.renatanutricionista.calendario.atendimento.paciente.repository.CalendarioAtendimentoPacienteRepository;
import br.com.renatanutricionista.exception.custom.AtendimentoException;
import br.com.renatanutricionista.exception.custom.EmptyResultDataAccessException;
import br.com.renatanutricionista.exception.custom.ObjectNotFoundException;
import br.com.renatanutricionista.tabelas.parametro.atendimento.horario.model.HorarioAtendimento;
import br.com.renatanutricionista.tabelas.parametro.atendimento.horario.repository.HorarioAtendimentoRepository;
import br.com.renatanutricionista.tabelas.parametro.atendimento.paciente.model.AtendimentoPacienteParametro;
import br.com.renatanutricionista.tabelas.parametro.atendimento.paciente.service.AtendimentoPacienteParametroService;
import br.com.renatanutricionista.utils.ConversaoUtils;
import br.com.renatanutricionista.utils.enums.resposta.RespostaUtils;


@Service
public class CalendarioAtendimentoPacienteService {

	@Autowired
	private CalendarioAtendimentoPacienteRepository calendarioAgendamentoRepository;
	
	@Autowired
	private HorarioAtendimentoRepository horarioAtendimentoRepository;
	
	@Autowired
	private AtendimentoPacienteParametroService atendimentoPacienteParametroService;
	
	
	public ResponseEntity<List<CalendarioAtendimentoPacienteDTO>> buscarHorariosDisponiveisParaDiaDoAgendamentoDeAtendimento(String data) {
		LocalDate dataConvertida = ConversaoUtils.converterStringParaLocalDate(data);
		
		List<CalendarioAtendimentoPaciente> horariosDisponiveisDaData = calendarioAgendamentoRepository
				.findByDataAndPeriodoDisponivelOrderByHorarioAsc(dataConvertida, RespostaUtils.SIM);
		
		return ResponseEntity.ok().body(CalendarioAtendimentoPacienteDTO.converterParaListaCalendarioAtendimentoPacienteDTO(horariosDisponiveisDaData));
	}

	
	public ResponseEntity<List<CalendarioAtendimentoPacienteDTO>> listarHorariosAPartirDoDiaAtual() {
		List<CalendarioAtendimentoPaciente> periodos = calendarioAgendamentoRepository.findAll();
		
		List<CalendarioAtendimentoPaciente> periodosAPartirDoDiaAtual = periodos.stream()
				.filter(periodo -> !periodo.getData().isBefore(LocalDate.now())).collect(Collectors.toList());
		
		excluirPeriodosDoCalendarioAnterioresADataAtual(periodos);
		
		return ResponseEntity.ok().body(CalendarioAtendimentoPacienteDTO
				.converterParaListaCalendarioAtendimentoPacienteDTOOrdenadaPorDataHorario(periodosAPartirDoDiaAtual));
	}
	
	
	private void excluirPeriodosDoCalendarioAnterioresADataAtual(List<CalendarioAtendimentoPaciente> periodos) {
		List<CalendarioAtendimentoPaciente> periodosASeremExcluidos = periodos.stream()
				.filter(periodo -> periodo.getData().isBefore(LocalDate.now()))
				.collect(Collectors.toList());
		
		if (!periodosASeremExcluidos.isEmpty()) {
			calendarioAgendamentoRepository.deleteAll(periodosASeremExcluidos);
		}
	}
	
	
	public ResponseEntity<Void> cadastrarUmPeriodoNoCalendario(PeriodoAtendimentoFORM periodoAtendimento) {
		CalendarioAtendimentoPaciente periodo = verificarHorarioParaCadastroDeUmPeriodo(periodoAtendimento);
		calendarioAgendamentoRepository.save(periodo);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	private CalendarioAtendimentoPaciente verificarHorarioParaCadastroDeUmPeriodo(PeriodoAtendimentoFORM periodoAtendimento) {
		LocalDate dataPeriodo = ConversaoUtils.converterStringParaLocalDate(periodoAtendimento.getData());
		LocalTime horarioParaCadastro = ConversaoUtils.converterStringParaLocalTimeHoraMinuto(periodoAtendimento.getHorario());
		validarDataHorarioParaCadastroDeUmPeriodo(dataPeriodo, horarioParaCadastro);
	
		AtendimentoPacienteParametro parametroAtendimento = atendimentoPacienteParametroService.buscarAtendimentoPacienteParametro();
		int intervaloMinutosEntreAtendimentos = parametroAtendimento.getIntervaloMinutosEntreAtendimentos();
		
		List<CalendarioAtendimentoPaciente> horariosDoDia = calendarioAgendamentoRepository
				.findAllByDataAndHorarioBetween(dataPeriodo, horarioParaCadastro.minusMinutes(intervaloMinutosEntreAtendimentos),
						horarioParaCadastro.plusMinutes(intervaloMinutosEntreAtendimentos)); 

		if (!horariosDoDia.isEmpty()) {				
			horariosDoDia.forEach(horarioDoDia -> {
				Duration duracao = Duration.between(horarioDoDia.getHorario(), horarioParaCadastro);
				
				if (duracao.abs().toMinutes() < intervaloMinutosEntreAtendimentos) 
					throw new AtendimentoException("O per??odo de Atendimento deve ter um intervalo de "
							+ intervaloMinutosEntreAtendimentos + " minutos entre o atendimento anterior e o pr??ximo!");
			});
		}
		
		return periodoAtendimento.converterParaCalendarioAtendimentoPaciente();
	}
	
	
	private void validarDataHorarioParaCadastroDeUmPeriodo(LocalDate dataPeriodo, LocalTime horarioParaCadastro) {
		if (dataPeriodo.isBefore(LocalDate.now()))
			throw new AtendimentoException("A Data n??o pode ser anterior ?? data atual!");
		
		if (dataPeriodo.isEqual(LocalDate.now()) && !horarioParaCadastro.isAfter(LocalTime.now()))
			throw new AtendimentoException("O Hor??rio n??o pode ser anterior ou igual ao hor??rio atual!");
	}
	
	
	public ResponseEntity<Void> cadastrarPeriodosAutomaticamenteNoCalendarioParaAtendimentoPaciente() {
		
		AtendimentoPacienteParametro parametroAtendimento = atendimentoPacienteParametroService.buscarAtendimentoPacienteParametro();
		List<HorarioAtendimento> diasDeAtendimento = horarioAtendimentoRepository.findAll();
		
		LocalDate hoje = LocalDate.now();
		List<LocalDate> datasDeAtendimento = gerarListaComDatasDeAtendimento(hoje.plusDays(1), 
				hoje.plusMonths(parametroAtendimento.getTempoMesesGeracaoAutomaticaHorariosAtendimento()), diasDeAtendimento);
		
		List<CalendarioAtendimentoPaciente> calendario = gerarListaComOsNovosPeriodosDeAtendimento(parametroAtendimento, datasDeAtendimento, diasDeAtendimento);
		calendarioAgendamentoRepository.saveAll(calendario);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	public ResponseEntity<Void> cadastrarPeriodosManualmenteNoCalendarioParaAtendimentoPaciente(CalendarioAtendimentoPacienteFORM calendarioAtendimento) {
		
		AtendimentoPacienteParametro parametroAtendimento = atendimentoPacienteParametroService.buscarAtendimentoPacienteParametro();
		calendarioAtendimento.validarDataInicialFinal(parametroAtendimento.getTempoMesesGeracaoAutomaticaHorariosAtendimento());
		List<HorarioAtendimento> diasDeAtendimento = horarioAtendimentoRepository.findAll();
		
		LocalDate dataInicial = ConversaoUtils.converterStringParaLocalDate(calendarioAtendimento.getDataInicial());
		LocalDate dataFinal = ConversaoUtils.converterStringParaLocalDate(calendarioAtendimento.getDataFinal());
		
		List<LocalDate> datasDeAtendimento = gerarListaComDatasDeAtendimento(dataInicial, dataFinal, diasDeAtendimento);
		List<CalendarioAtendimentoPaciente> calendario = gerarListaComOsNovosPeriodosDeAtendimento(parametroAtendimento, datasDeAtendimento, diasDeAtendimento);
		calendarioAgendamentoRepository.saveAll(calendario);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	private List<CalendarioAtendimentoPaciente> gerarListaComOsNovosPeriodosDeAtendimento(AtendimentoPacienteParametro parametroAtendimento,
			List<LocalDate> datasDeAtendimento, List<HorarioAtendimento> diasDeAtendimento) {
		
		Map<DayOfWeek, HorarioAtendimento> horarioAtendimentoSeparadoPeloDiaDaSemana = gerarMapComHorarioAtendimentoSeparadoPeloDiaDaSemana(diasDeAtendimento);
		List<CalendarioAtendimentoPaciente> calendarioAtendimento = new ArrayList<>();
		
		int intervaloMinutosEntreAtendimentos = parametroAtendimento.getIntervaloMinutosEntreAtendimentos();
		
		datasDeAtendimento.forEach(data -> {
			DayOfWeek diaDaSemana = data.getDayOfWeek();
			HorarioAtendimento horarioAtendimento = horarioAtendimentoSeparadoPeloDiaDaSemana.get(diaDaSemana);
			
			LocalTime horarioInicial;
			LocalTime horarioFinal;
			Predicate<LocalTime> predicate;
			
			if (Objects.nonNull(horarioAtendimento.getHorarioEntradaAntesAlmoco())) {
				horarioInicial = horarioAtendimento.getHorarioEntradaAntesAlmoco();
				horarioFinal = horarioAtendimento.getHorarioSaidaAntesAlmoco();
				
				predicate = horarioAtual -> !horarioAtual.isAfter(horarioAtendimento.getHorarioSaidaAntesAlmoco().minusMinutes(intervaloMinutosEntreAtendimentos));
				
				calendarioAtendimento.addAll(gerarListaComOsNovosPeriodosDeAtendimentoConformePeriodoDeTrabalho(data, horarioInicial, 
						horarioFinal, intervaloMinutosEntreAtendimentos, predicate));
			}
			
			if (Objects.nonNull(horarioAtendimento.getHorarioEntradaDepoisAlmoco())) {
				horarioInicial = horarioAtendimento.getHorarioEntradaDepoisAlmoco();
				horarioFinal = horarioAtendimento.getHorarioSaidaDepoisAlmoco();
				
				predicate = horarioAtual-> !horarioAtual.isAfter(horarioAtendimento.getHorarioSaidaDepoisAlmoco().minusMinutes(intervaloMinutosEntreAtendimentos));
				
				calendarioAtendimento.addAll(gerarListaComOsNovosPeriodosDeAtendimentoConformePeriodoDeTrabalho(data, horarioInicial, 
						horarioFinal, intervaloMinutosEntreAtendimentos, predicate));
			}
		});
		
		return calendarioAtendimento;
	}
	
	
	private List<CalendarioAtendimentoPaciente> gerarListaComOsNovosPeriodosDeAtendimentoConformePeriodoDeTrabalho(LocalDate data, 
			LocalTime horarioInicial, LocalTime horarioFinal, int intervaloMinutosEntreAtendimentos, Predicate<LocalTime> predicate) {
		
		List<CalendarioAtendimentoPaciente> calendarioAtendimento = new ArrayList<>();
		
		for (LocalTime agora = horarioInicial; agora.isBefore(horarioFinal); agora = agora.plusMinutes(intervaloMinutosEntreAtendimentos)) {
			if (predicate.test(agora)) {
				calendarioAtendimento.add(new CalendarioAtendimentoPaciente(data, agora));
			}
		}
		
		return calendarioAtendimento;
	}

	
	private Map<DayOfWeek, HorarioAtendimento> gerarMapComHorarioAtendimentoSeparadoPeloDiaDaSemana(List<HorarioAtendimento> diasDeAtendimento) {
		Map<DayOfWeek, HorarioAtendimento> horarioAtendimentoSeparadoPeloDiaDaSemana = new HashMap<>();
		
		diasDeAtendimento.forEach(diaAtendimento -> {
			DayOfWeek diaDaSemana = diaAtendimento.getDiaDaSemana();
			horarioAtendimentoSeparadoPeloDiaDaSemana.put(diaDaSemana, diaAtendimento);
		});
		
		return horarioAtendimentoSeparadoPeloDiaDaSemana;
	}
	
	
	private List<LocalDate> gerarListaComDatasDeAtendimento(LocalDate dataInicial, LocalDate dataFinal,
			List<HorarioAtendimento> diasDeAtendimento) {
		
		Set<DayOfWeek> diasDeAtendimentoDaSemana = gerarListaComOsDiasDeAtendimentoDaSemana(diasDeAtendimento);
		List<CalendarioAtendimentoPaciente> calendarioAtendimento = calendarioAgendamentoRepository.findByDataBetween(dataInicial, dataFinal);
		List<LocalDate> datasExistentesNoCalendario = calendarioAtendimento.stream().map(CalendarioAtendimentoPaciente::getData).collect(Collectors.toList());
		
		List<LocalDate> datasDeAtendimento = new ArrayList<>();
		
		if (calendarioAtendimento.isEmpty()) {
			for (LocalDate data = dataInicial; !data.isAfter(dataFinal); data = data.plusDays(1)) {
				DayOfWeek diaDaSemana = data.getDayOfWeek();
				
				if (diasDeAtendimentoDaSemana.contains(diaDaSemana))
					datasDeAtendimento.add(data);
			}
		}
		else {
			for (LocalDate data = dataInicial; !data.isAfter(dataFinal); data = data.plusDays(1)) {
				DayOfWeek diaDaSemana = data.getDayOfWeek();

				if (!datasExistentesNoCalendario.contains(data) && diasDeAtendimentoDaSemana.contains(diaDaSemana))
					datasDeAtendimento.add(data);
			}
		}
		
		return datasDeAtendimento;
	}
	
	
	private Set<DayOfWeek> gerarListaComOsDiasDeAtendimentoDaSemana(List<HorarioAtendimento> diasDeAtendimento) {
		if (diasDeAtendimento.isEmpty())
			throw new EmptyResultDataAccessException("N??o foi encontrado nenhum hor??rio de atendimento "
					+ "para gera????o de per??odos de atendimento!");
		
		return diasDeAtendimento.stream().map(HorarioAtendimento::getDiaDaSemana).collect(Collectors.toSet()); 
	}
	
	
	public ResponseEntity<Void> excluirPeriodo(Long idPeriodo) {
		CalendarioAtendimentoPaciente horarioAtendimento = verificarSeExistePeriodoNoCalendarioAtendimento(idPeriodo);
		
		if (horarioAtendimento.getPeriodoDisponivel().equals(RespostaUtils.NAO))
			throw new AtendimentoException("N??o ?? poss??vel remover um per??odo que n??o est?? dispon??vel!");
		
		calendarioAgendamentoRepository.delete(horarioAtendimento);
		
		return ResponseEntity.noContent().build();
	}

	
	public ResponseEntity<Void> excluirPeriodosConformeDataInicialFinal(String dataInicio, String dataFim) {
		List<CalendarioAtendimentoPaciente> periodosAtendimento = validarPeriodosParaExclusao(dataInicio, dataFim);
		calendarioAgendamentoRepository.deleteAll(periodosAtendimento);
		
		return ResponseEntity.noContent().build();
	}
	
	
	private List<CalendarioAtendimentoPaciente> validarPeriodosParaExclusao(String dataInicio, String dataFim) {
		LocalDate dataInicial = ConversaoUtils.converterStringParaLocalDate(dataInicio);
		LocalDate dataFinal = ConversaoUtils.converterStringParaLocalDate(dataFim);
		
		if (dataInicial.isAfter(dataFinal))
			throw new IllegalArgumentException("A data inicial n??o pode ser posterior ?? data final!");
		
		List<CalendarioAtendimentoPaciente> periodosAtendimento = calendarioAgendamentoRepository.findByDataBetweenAndPeriodoDisponivel(dataInicial, 
				dataFinal, RespostaUtils.SIM);
		
		if (periodosAtendimento.isEmpty())
			throw new EmptyResultDataAccessException("N??o h?? per??odos para serem exclu??dos!");
		
		return periodosAtendimento;
	}

	
	public CalendarioAtendimentoPaciente verificarPossibilidadeDeAgendarConsultaRetorno(String data, String horario) {
		LocalDate dataAgendamento = ConversaoUtils.converterStringParaLocalDate(data);
		LocalTime horarioAgendamento = ConversaoUtils.converterStringParaLocalTime(horario + ":00");
		
		verificarSeDataHorarioSaoValidas(dataAgendamento, horarioAgendamento);
		
		Optional<CalendarioAtendimentoPaciente> periodo = calendarioAgendamentoRepository.findByDataAndHorario(dataAgendamento, horarioAgendamento);
		
		if (periodo.isEmpty()) 
			throw new ObjectNotFoundException("A data e/ou hor??rio n??o existe(m) no calend??rio de agendamento "
					+ "de consulta e retorno do paciente!");
		
		verificarDisponibilidadeNoCalendario(periodo.get());
		
		return periodo.get();
	}
	
	
	private void verificarSeDataHorarioSaoValidas(LocalDate data, LocalTime horario) {
		if (!data.isAfter(LocalDate.now()))
			throw new IllegalArgumentException("Data inv??lida. A data deve ser um dia "
					+ "ap??s o dia atual!");
	}
	
	
	private void verificarDisponibilidadeNoCalendario(CalendarioAtendimentoPaciente periodo) {
		if (periodo.getPeriodoDisponivel().equals(RespostaUtils.NAO))
			throw new AtendimentoException("O Per??odo escolhido n??o est?? dispon??vel para Agendamento "
					+ "de Consulta ou Retorno!");
	}
	
	
	public CalendarioAtendimentoPaciente verificarSeExistePeriodoNoCalendarioAtendimento(Long idCalendarioAtendimento) {
		if (Objects.isNull(idCalendarioAtendimento))
			throw new NullPointerException("O ID do calend??rio de atendimento n??o pode ser nulo!");
			
		Optional<CalendarioAtendimentoPaciente> calendarioAtendimento = 
				calendarioAgendamentoRepository.findById(idCalendarioAtendimento);
		
		if (calendarioAtendimento.isEmpty())
			throw new ObjectNotFoundException("Per??odo no calend??rio de atendimento do paciente "
					+ "n??o encontrado!");
		
		return calendarioAtendimento.get();
	}
	
	
	public CalendarioAtendimentoPaciente verificarSeExistePeriodoNoCalendarioAtendimento(LocalDate data, LocalTime horario) {
		if (Objects.isNull(data) || Objects.isNull(horario))
			throw new NullPointerException("Data e/ou hor??rio est??o nulos!");
			
		Optional<CalendarioAtendimentoPaciente> calendarioAtendimento = 
				calendarioAgendamentoRepository.findByDataAndHorario(data, horario);
		
		if (calendarioAtendimento.isEmpty())
			throw new ObjectNotFoundException("Per??odo no calend??rio de atendimento do paciente n??o encontrado!");
		
		return calendarioAtendimento.get();
	}
	
	
	public void alterarPeriodoDoCalendarioParaDisponivel(LocalDate data, LocalTime horario) {
		CalendarioAtendimentoPaciente periodoAgendamento = verificarSeExistePeriodoNoCalendarioAtendimento(data, horario);
		
		periodoAgendamento.setPeriodoDisponivel(RespostaUtils.SIM);
	}
}
