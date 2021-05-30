package br.com.renatanutricionista.tabelas.parametro.atendimento.horario.service;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.com.renatanutricionista.exception.custom.IntegrityConstraintViolationException;
import br.com.renatanutricionista.exception.custom.ObjectNotFoundException;
import br.com.renatanutricionista.tabelas.parametro.atendimento.horario.dto.HorarioAtendimentoDTO;
import br.com.renatanutricionista.tabelas.parametro.atendimento.horario.form.EdicaoHorarioAtendimentoFORM;
import br.com.renatanutricionista.tabelas.parametro.atendimento.horario.form.HorarioAtendimentoFORM;
import br.com.renatanutricionista.tabelas.parametro.atendimento.horario.model.HorarioAtendimento;
import br.com.renatanutricionista.tabelas.parametro.atendimento.horario.repository.HorarioAtendimentoRepository;
import br.com.renatanutricionista.utils.conversao.enums.DadosEnum;


@Service
public class HorarioAtendimentoService {

	@Autowired
	private HorarioAtendimentoRepository horarioAtendimentoRepository;
	
	
	public ResponseEntity<List<HorarioAtendimentoDTO>> listarHorariosAtendimento() {
		List<HorarioAtendimento> horariosAtendimento = horarioAtendimentoRepository.findAllByOrderByDiaDaSemanaAsc();
		
		return ResponseEntity.ok().body(HorarioAtendimentoDTO.converterParaListaHorarioAtendimentoDTO(horariosAtendimento));
	}
	
	
	public ResponseEntity<List<DadosEnum>> listarDiasDaSemanaDisponiveisParaCadastro() {
		List<DayOfWeek> diasDeAtendimento = horarioAtendimentoRepository.findAll().stream().map(HorarioAtendimento::getDiaDaSemana).collect(Collectors.toList());
		List<DayOfWeek> diasDaSemana = Arrays.asList(DayOfWeek.values());
		
		List<DadosEnum> diasNaoCadastrados = diasDaSemana.stream().filter(diaDaSemana -> !diasDeAtendimento.contains(diaDaSemana))
			.map(diaAtendimento -> new DadosEnum(String.valueOf(diaAtendimento.ordinal() + 1), 
					StringUtils.capitalize(diaAtendimento.getDisplayName(TextStyle.FULL, new Locale("pt", "BR")))))		
		.collect(Collectors.toList());
		
		return ResponseEntity.ok().body(diasNaoCadastrados);
	}
	
	
	public ResponseEntity<Void> cadastrarDiaDeAtendimento(HorarioAtendimentoFORM horarioAtendimento) {
		verificarSeJaExisteDiaDeAtendimento(Integer.parseInt(horarioAtendimento.getDiaDaSemana()));		
		HorarioAtendimento diaAtendimento = horarioAtendimento.converterParaHorarioAtendimento();
		
		horarioAtendimentoRepository.save(diaAtendimento);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	private void verificarSeJaExisteDiaDeAtendimento(Integer diaDaSemana) {
		Optional<HorarioAtendimento> diaAtendimento = horarioAtendimentoRepository.findByDiaDaSemana(DayOfWeek.of(diaDaSemana));
		
		if (diaAtendimento.isPresent()) 
			throw new IntegrityConstraintViolationException("O dia especificado de Horários de Atendimento já existe!");
	}
	
	
	public ResponseEntity<Void> atualizarHorariosDeUmDiaDaSamana(Integer idHorarioAtendimento,
			EdicaoHorarioAtendimentoFORM edicaoHorarioAtendimento) {
		
		HorarioAtendimento horarioAtendimento = verificarSeExisteHorarioAtendimento(idHorarioAtendimento);
		edicaoHorarioAtendimento.atualizarHorariosAtendimentoDeUmDiaDaSemana(horarioAtendimento);
		
		return ResponseEntity.ok().build();
	}
	
	
	public ResponseEntity<Void> excluirUmDiaDeAtendimento(Integer idHorarioAtendimento) {
		verificarSeExisteHorarioAtendimento(idHorarioAtendimento);
		horarioAtendimentoRepository.deleteById(idHorarioAtendimento);
		
		return ResponseEntity.noContent().build();
	}
	
	
	private HorarioAtendimento verificarSeExisteHorarioAtendimento(Integer idHorarioAtendimento) {
		if (Objects.isNull(idHorarioAtendimento))
			throw new NullPointerException("O ID do Horário de Atendimento não pode ser nulo!");
		
		Optional<HorarioAtendimento> horarioAtendimento = horarioAtendimentoRepository.findById(idHorarioAtendimento);
		
		if (horarioAtendimento.isEmpty())
			throw new ObjectNotFoundException("Horário de Atendimento não encontrado!");
		
		return horarioAtendimento.get();
	}
}
