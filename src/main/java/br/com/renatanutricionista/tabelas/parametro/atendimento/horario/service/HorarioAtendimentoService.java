package br.com.renatanutricionista.tabelas.parametro.atendimento.horario.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.renatanutricionista.exception.custom.IntegrityConstraintViolationException;
import br.com.renatanutricionista.exception.custom.ObjectNotFoundException;
import br.com.renatanutricionista.tabelas.parametro.atendimento.horario.dto.HorarioAtendimentoDTO;
import br.com.renatanutricionista.tabelas.parametro.atendimento.horario.form.EdicaoHorarioAtendimentoFORM;
import br.com.renatanutricionista.tabelas.parametro.atendimento.horario.form.HorarioAtendimentoFORM;
import br.com.renatanutricionista.tabelas.parametro.atendimento.horario.model.HorarioAtendimento;
import br.com.renatanutricionista.tabelas.parametro.atendimento.horario.repository.HorarioAtendimentoRepository;


@Service
public class HorarioAtendimentoService {

	@Autowired
	private HorarioAtendimentoRepository horarioAtendimentoRepository;
	
	
	public ResponseEntity<List<HorarioAtendimentoDTO>> listarHorariosAtendimento() {
		List<HorarioAtendimento> horariosAtendimento = horarioAtendimentoRepository.findAllByOrderByDiaDaSemanaAsc();
		
		return ResponseEntity.ok().body(HorarioAtendimentoDTO.converterParaListaHorarioAtendimentoDTO(horariosAtendimento));
	}
	
	
	public ResponseEntity<List<HorarioAtendimentoDTO>> cadastrarDiaDeAtendimento(HorarioAtendimentoFORM horarioAtendimento) {
		try {
			horarioAtendimentoRepository.save(horarioAtendimento.converterParaHorarioAtendimento());
		}
		catch (DataIntegrityViolationException e) {
			throw new IntegrityConstraintViolationException("O dia especificado de Horários de Atendimento já existe!");
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(listarHorariosAtendimento().getBody());
	}
	
	
	public ResponseEntity<List<HorarioAtendimentoDTO>> atualizarHorariosDeUmDiaDaSamana(Integer idHorarioAtendimento,
			EdicaoHorarioAtendimentoFORM edicaoHorarioAtendimento) {
		
		HorarioAtendimento horarioAtendimento = verificarSeExisteHorarioAtendimento(idHorarioAtendimento);
		edicaoHorarioAtendimento.atualizarHorariosAtendimentoDeUmDiaDaSemana(horarioAtendimento);
		
		return ResponseEntity.ok().body(listarHorariosAtendimento().getBody());
	}
	
	
	public ResponseEntity<List<HorarioAtendimentoDTO>> removerUmDiaDeAtendimento(Integer idHorarioAtendimento) {
		verificarSeExisteHorarioAtendimento(idHorarioAtendimento);
		horarioAtendimentoRepository.deleteById(idHorarioAtendimento);
		
		return ResponseEntity.ok().body(listarHorariosAtendimento().getBody());
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
