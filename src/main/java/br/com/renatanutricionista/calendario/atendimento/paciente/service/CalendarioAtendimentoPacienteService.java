package br.com.renatanutricionista.calendario.atendimento.paciente.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.renatanutricionista.calendario.atendimento.paciente.dto.CalendarioAtendimentoPacienteDTO;
import br.com.renatanutricionista.calendario.atendimento.paciente.enums.PeriodoDisponivel;
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


@Service
public class CalendarioAtendimentoPacienteService {

	@Autowired
	private CalendarioAtendimentoPacienteRepository calendarioAgendamentoRepository;
	
	@Autowired
	private HorarioAtendimentoRepository horarioAtendimentoRepository;
	
	@Autowired
	private AtendimentoPacienteParametroService atendimentoPacienteParametroService;
	
	
	public ResponseEntity<List<CalendarioAtendimentoPacienteDTO>> listarHorariosAPartirDoDiaAtual() {
		List<CalendarioAtendimentoPaciente> calendarioAPartirDaDataAtual = calendarioAgendamentoRepository.findAllByDataGreaterThanEqual(LocalDate.now());
		
		return ResponseEntity.ok().body(CalendarioAtendimentoPacienteDTO
				.converterParaListaCalendarioAtendimentoPacienteDTOOrdenadaPorDataHorario(calendarioAPartirDaDataAtual));
	}
	
	
	public ResponseEntity<Void> cadastrarPeriodosAutomaticamenteNoCalendarioParaAtendimentoPaciente() {
		AtendimentoPacienteParametro parametroAtendimento = atendimentoPacienteParametroService.verificarSeExisteAtendimentoPacienteParametro(1);
		
		List<HorarioAtendimento> diasDeAtendimento = horarioAtendimentoRepository.findAll();
		System.out.println("1");
		LocalDate hoje = LocalDate.now();
		List<LocalDate> datasDeAtendimento = gerarListaComDatasDeAtendimento(hoje.plusDays(1L), 
				hoje.plusMonths(parametroAtendimento.getTempoMesesGeracaoAutomaticaHorariosAtendimento()), diasDeAtendimento);
		
		List<CalendarioAtendimentoPaciente> calendario = gerarListaComOsNovosPeriodosDeAtendimento(parametroAtendimento, datasDeAtendimento, diasDeAtendimento);
		calendarioAgendamentoRepository.saveAll(calendario);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	private List<CalendarioAtendimentoPaciente> gerarListaComOsNovosPeriodosDeAtendimento(AtendimentoPacienteParametro parametroAtendimento,
			List<LocalDate> datasDeAtendimento, List<HorarioAtendimento> diasDeAtendimento) {
		
		Map<DayOfWeek, HorarioAtendimento> horarioAtendimentoSeparadoPeloDiaDaSemana = gerarMapComHorarioAtendimentoSeparadoPeloDiaDaSemana(diasDeAtendimento);
		List<CalendarioAtendimentoPaciente> calendarioAtendimento = new ArrayList<>();
		
		int intervaloMinutosEntreAtendimentos = 1;
		
		datasDeAtendimento.forEach(data -> {
			DayOfWeek diaDaSemana = data.getDayOfWeek();
			HorarioAtendimento horario = horarioAtendimentoSeparadoPeloDiaDaSemana.get(diaDaSemana);
			
			for (LocalTime agora = horario.getHorarioEntradaAntesAlmoco(); agora.isBefore(horario.getHorarioSaidaDepoisAlmoco()); 
					agora.plusMinutes(intervaloMinutosEntreAtendimentos)) {
				
				
				if (!agora.isAfter(horario.getHorarioSaidaAntesAlmoco().minusMinutes(intervaloMinutosEntreAtendimentos))
						|| !agora.isAfter(horario.getHorarioSaidaDepoisAlmoco().minusMinutes(intervaloMinutosEntreAtendimentos))) {
					
					calendarioAtendimento.add(new CalendarioAtendimentoPaciente(data, agora));
				}
			}
		});
		
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
			for (LocalDate data = dataInicial; !dataInicial.isAfter(dataFinal); data.plusDays(1)) {
				DayOfWeek diaDaSemana = data.getDayOfWeek();
				
				if (diasDeAtendimentoDaSemana.contains(diaDaSemana))
					datasDeAtendimento.add(data);
			}
		}
		else {
			for (LocalDate data = dataInicial; !dataInicial.isAfter(dataFinal); data.plusDays(1)) {
				DayOfWeek diaDaSemana = data.getDayOfWeek();
				
				if (!datasExistentesNoCalendario.contains(data) && diasDeAtendimentoDaSemana.contains(diaDaSemana))
					datasDeAtendimento.add(data);
			}
		}
		
		return datasDeAtendimento;
	}
	
	private Set<DayOfWeek> gerarListaComOsDiasDeAtendimentoDaSemana(List<HorarioAtendimento> diasDeAtendimento) {
		if (diasDeAtendimento.isEmpty())
			throw new EmptyResultDataAccessException("Não foi encontrado nenhum horário de atendimento "
					+ "para geração de períodos de Atendimento!");
		
		return diasDeAtendimento.stream().map(HorarioAtendimento::getDiaDaSemana).collect(Collectors.toSet()); 
	}
	
	
	public ResponseEntity<Void> excluirPeriodo(Long idCalendarioAtendimento) {
		CalendarioAtendimentoPaciente horarioAtendimento = verificarSeExistePeriodoNoCalendarioAtendimento(idCalendarioAtendimento);
		
		if (horarioAtendimento.getPeriodoDisponivel().equals(PeriodoDisponivel.NAO))
			throw new AtendimentoException("Não é possível remover um período que não está disponível!");
		
		calendarioAgendamentoRepository.delete(horarioAtendimento);
		
		return ResponseEntity.noContent().build();
	}
	
	
	public CalendarioAtendimentoPaciente verificarPossibilidadeDeAgendarConsultaRetorno(String data, String horario) {
		LocalDate dataAgendamento = ConversaoUtils.converterStringParaLocalDate(data);
		LocalTime horarioAgendamento = ConversaoUtils.converterStringParaLocalTime(horario + ":00");
		
		verificarSeDataHorarioSaoValidas(dataAgendamento, horarioAgendamento);
		
		Optional<CalendarioAtendimentoPaciente> periodo = calendarioAgendamentoRepository.findByDataAndHorario(dataAgendamento, horarioAgendamento);
		
		if (periodo.isEmpty()) 
			throw new ObjectNotFoundException("A Data e/ou Horário não existe(m) no Calendário de Agendamento "
					+ "de Consulta e Retorno do Paciente!");
		
		verificarDisponibilidadeNoCalendario(periodo.get());
		
		return periodo.get();
	}
	
	
	private void verificarSeDataHorarioSaoValidas(LocalDate data, LocalTime horario) {
		if (!data.isAfter(LocalDate.now()))
			throw new IllegalArgumentException("Data inválida. A data deve ser um dia "
					+ "após o dia atual!");
	}
	
	
	private void verificarDisponibilidadeNoCalendario(CalendarioAtendimentoPaciente periodo) {
		if (periodo.getPeriodoDisponivel().equals(PeriodoDisponivel.NAO))
			throw new AtendimentoException("O Período escolhido não está disponível para Agendamento "
					+ "de Consulta ou Retorno!");
	}
	
	
	public CalendarioAtendimentoPaciente verificarSeExistePeriodoNoCalendarioAtendimento(Long idCalendarioAtendimento) {
		if (Objects.isNull(idCalendarioAtendimento))
			throw new NullPointerException("O ID do Calnedário de Atendimento não pode ser nulo!");
			
		Optional<CalendarioAtendimentoPaciente> calendarioAtendimento = 
				calendarioAgendamentoRepository.findById(idCalendarioAtendimento);
		
		if (calendarioAtendimento.isEmpty())
			throw new ObjectNotFoundException("Período no Calendário de Atendimento do Paciente "
					+ "não encontrado!");
		
		return calendarioAtendimento.get();
	}
	
	
	public CalendarioAtendimentoPaciente verificarSeExistePeriodoNoCalendarioAtendimento(LocalDate data, LocalTime horario) {
		if (Objects.isNull(data) || Objects.isNull(horario))
			throw new NullPointerException("Data e/ou Horário estão nulos!");
			
		Optional<CalendarioAtendimentoPaciente> calendarioAtendimento = 
				calendarioAgendamentoRepository.findByDataAndHorario(data, horario);
		
		if (calendarioAtendimento.isEmpty())
			throw new ObjectNotFoundException("Período no Calendário de Atendimento do Paciente encontrado!");
		
		return calendarioAtendimento.get();
	}
	
	
	public void alterarPeriodoDoCalendarioParaDisponivel(LocalDateTime dataHorario) {
		CalendarioAtendimentoPaciente periodoAgendamento = verificarSeExistePeriodoNoCalendarioAtendimento(
				dataHorario.toLocalDate(), dataHorario.toLocalTime());
		
		periodoAgendamento.setPeriodoDisponivel(PeriodoDisponivel.SIM);
	}
}
