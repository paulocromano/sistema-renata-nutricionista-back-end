package br.com.renatanutricionista.tabelas.parametro.atendimento.horario.resource;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.renatanutricionista.tabelas.parametro.atendimento.horario.dto.HorarioAtendimentoDTO;
import br.com.renatanutricionista.tabelas.parametro.atendimento.horario.form.EdicaoHorarioAtendimentoFORM;
import br.com.renatanutricionista.tabelas.parametro.atendimento.horario.form.HorarioAtendimentoFORM;
import br.com.renatanutricionista.tabelas.parametro.atendimento.horario.service.HorarioAtendimentoService;


@RestController
@RequestMapping("/horario-atendimento")
public class HorarioAtendimentoResource {

	@Autowired
	private HorarioAtendimentoService horarioAtendimentoService;
	
	
	@GetMapping
	public ResponseEntity<List<HorarioAtendimentoDTO>> listarHorariosAtendimento() {
		return horarioAtendimentoService.listarHorariosAtendimento();
	}
	
	
	@PostMapping
	@Transactional
	public ResponseEntity<List<HorarioAtendimentoDTO>> cadastrarDiaDeAtendimento(
			@RequestBody @Valid HorarioAtendimentoFORM horarioAtendimento) {
		
		return horarioAtendimentoService.cadastrarDiaDeAtendimento(horarioAtendimento);
	}
	
	
	@PutMapping("/{idHorarioAtendimento}")
	@Transactional
	public ResponseEntity<List<HorarioAtendimentoDTO>> atualizarHorariosDeUmDiaDaSamana(@PathVariable Integer idHorarioAtendimento,
			@RequestBody @Valid EdicaoHorarioAtendimentoFORM edicaoHorarioAtendimento) {
		
		return horarioAtendimentoService.atualizarHorariosDeUmDiaDaSamana(idHorarioAtendimento, edicaoHorarioAtendimento);
	}
	
	
	@DeleteMapping("/{idHorarioAtendimento}")
	@Transactional
	public ResponseEntity<List<HorarioAtendimentoDTO>> removerUmDiaDeAtendimento(Integer idHorarioAtendimento) {
		return horarioAtendimentoService.removerUmDiaDeAtendimento(idHorarioAtendimento);
	}
}
