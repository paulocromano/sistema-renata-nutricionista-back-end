package br.com.renatanutricionista.tabelas.parametro.atendimento.horario.resource;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import br.com.renatanutricionista.utils.conversao.enums.DadosEnum;


@RestController
@RequestMapping("/horario-atendimento")
public class HorarioAtendimentoResource {

	@Autowired
	private HorarioAtendimentoService horarioAtendimentoService;
	
	
	@GetMapping
	public ResponseEntity<List<HorarioAtendimentoDTO>> listarHorariosAtendimento() {
		return horarioAtendimentoService.listarHorariosAtendimento();
	}
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/dias-semana-para-cadastro")
	public ResponseEntity<List<DadosEnum>> listarDiasDaSemanaDisponiveisParaCadastro() {
		return horarioAtendimentoService.listarDiasDaSemanaDisponiveisParaCadastro();
	}
	

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	@Transactional
	public ResponseEntity<Void> cadastrarDiaDeAtendimento(@RequestBody @Valid HorarioAtendimentoFORM horarioAtendimento) {
		return horarioAtendimentoService.cadastrarDiaDeAtendimento(horarioAtendimento);
	}
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("/{idHorarioAtendimento}")
	@Transactional
	public ResponseEntity<Void> atualizarHorariosDeUmDiaDaSamana(@PathVariable Integer idHorarioAtendimento,
			@RequestBody @Valid EdicaoHorarioAtendimentoFORM edicaoHorarioAtendimento) {
		
		return horarioAtendimentoService.atualizarHorariosDeUmDiaDaSamana(idHorarioAtendimento, edicaoHorarioAtendimento);
	}
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/{idHorarioAtendimento}")
	@Transactional
	public ResponseEntity<Void> excluirUmDiaDeAtendimento(@PathVariable Integer idHorarioAtendimento) {
		return horarioAtendimentoService.excluirUmDiaDeAtendimento(idHorarioAtendimento);
	}
}
