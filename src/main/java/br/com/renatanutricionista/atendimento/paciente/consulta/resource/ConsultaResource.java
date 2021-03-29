package br.com.renatanutricionista.atendimento.paciente.consulta.resource;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.renatanutricionista.atendimento.paciente.avaliacao.composicao.corporal.form.AvaliacaoComposicaoCorporalFORM;
import br.com.renatanutricionista.atendimento.paciente.avaliacao.consumo.habitual.form.AvaliacaoConsumoHabitualFORM;
import br.com.renatanutricionista.atendimento.paciente.avaliacao.massa.muscular.corporea.antropometrica.form.AvaliacaoMassaMuscularCorporeaFORM;
import br.com.renatanutricionista.atendimento.paciente.consulta.form.AgendamentoConsultaFORM;
import br.com.renatanutricionista.atendimento.paciente.consulta.form.ConfirmacaoConsultaFORM;
import br.com.renatanutricionista.atendimento.paciente.consulta.form.ReagendamentoConsultaFORM;
import br.com.renatanutricionista.atendimento.paciente.consulta.service.ConsultaService;


@RestController
@RequestMapping("/consulta-paciente")
public class ConsultaResource {

	@Autowired
	private ConsultaService consultaService;
	
	
	@PostMapping("/agendar/{idPaciente}")
	@Transactional
	public ResponseEntity<Void> agendarConsulta(@PathVariable Long idPaciente, 
			@RequestBody @Valid AgendamentoConsultaFORM agendamentoConsulta) {
		
		return consultaService.agendarConsulta(idPaciente, agendamentoConsulta);
	}
	
	
	@PutMapping("/reagendar/{idPaciente}/{idConsulta}")
	@Transactional
	public ResponseEntity<Void> reagendarConsulta(@PathVariable Long idPaciente, @PathVariable Long idConsulta, 
			@RequestBody @Valid ReagendamentoConsultaFORM reagendamentoConsulta) {
		
		return consultaService.reagendarConsulta(idPaciente, idConsulta, reagendamentoConsulta);
	}
	
	
	@PutMapping("/confirmar/{idPaciente}/{idConsulta}")
	@Transactional
	public ResponseEntity<Void> confirmarConsulta(@PathVariable Long idPaciente, @PathVariable Long idConsulta, 
			@RequestBody @Valid ConfirmacaoConsultaFORM confirmacaoConsulta) {
		
		return consultaService.confirmarConsulta(idPaciente, idConsulta, confirmacaoConsulta);
	}
	
	
	@DeleteMapping("/cancelar/{idPaciente}/{idConsulta}")
	@Transactional
	public ResponseEntity<Void> cancelarConsulta(@PathVariable Long idPaciente, @PathVariable Long idConsulta) {
		return consultaService.cancelarConsulta(idPaciente, idConsulta);
	}
	
	
	@PutMapping("/iniciar-consulta/{idPaciente}/{idConsulta}")
	@Transactional
	public ResponseEntity<Void> iniciarConsulta(@PathVariable Long idPaciente, @PathVariable Long idConsulta) { 
		return consultaService.iniciarConsulta(idPaciente, idConsulta);
	}
	
	
	@PostMapping("/cadastrar-avaliacao-consumo-habitual/{idPaciente}/{idConsulta}")
	@Transactional
	public ResponseEntity<Void> cadastrarAvaliacaoConsumoHabitual(@PathVariable Long idPaciente, 
			@PathVariable Long idConsulta,
			@RequestBody @Valid AvaliacaoConsumoHabitualFORM avaliacaoConsumoHabitual) {
		
		return consultaService.cadastrarAvaliacaoConsumoHabitual(idPaciente, idConsulta, avaliacaoConsumoHabitual);
	}
	
	
	@PostMapping("/cadastrar-avaliacao-composicao-corporal/{idPaciente}/{idConsulta}")
	@Transactional
	public ResponseEntity<Void> cadastrarAvaliacaoComposicaoCorporal(@PathVariable Long idPaciente, 
			@PathVariable Long idConsulta,
			@RequestBody @Valid AvaliacaoComposicaoCorporalFORM avaliacaoComposicaoCorporal) {
		
		return consultaService.cadastrarAvaliacaoComposicaoCorporal(idPaciente, idConsulta, avaliacaoComposicaoCorporal);
	}
	
	
	@PostMapping("/cadastrar-avaliacao-massa-muscular-corporea/{idPaciente}/{idConsulta}")
	@Transactional
	public ResponseEntity<Void> cadastrarAvaliacaoMassaMuscularCorporeaMedidaAntropometrica(@PathVariable Long idPaciente, 
			@PathVariable Long idConsulta, 
			@RequestBody @Valid AvaliacaoMassaMuscularCorporeaFORM avaliacaoMassaMuscularCorporea) {
		
		return consultaService.cadastrarAvaliacaoMassaMuscularCorporeaMedidaAntropometrica(idPaciente, 
				idConsulta, avaliacaoMassaMuscularCorporea);
	}
}
