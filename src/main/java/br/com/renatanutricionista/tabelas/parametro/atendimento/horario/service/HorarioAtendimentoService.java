package br.com.renatanutricionista.tabelas.parametro.atendimento.horario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.renatanutricionista.tabelas.parametro.atendimento.horario.dto.HorarioAtendimentoDTO;
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
}
