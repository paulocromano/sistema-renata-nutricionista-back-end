package br.com.renatanutricionista.atendimento.paciente.retorno.resource;

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
import br.com.renatanutricionista.atendimento.paciente.conduta.nutricional.form.CondutaNutricionalFORM;
import br.com.renatanutricionista.atendimento.paciente.registro.dieta.form.RegistroDietaFORM;
import br.com.renatanutricionista.atendimento.paciente.retorno.form.AgendamentoRetornoFORM;
import br.com.renatanutricionista.atendimento.paciente.retorno.form.ReagendamentoRetornoFORM;
import br.com.renatanutricionista.atendimento.paciente.retorno.service.RetornoConsultaService;


@RestController
@RequestMapping("/retorno-consulta-paciente")
public class RetornoConsultaResource {

	@Autowired
	private RetornoConsultaService retornoConsultaService;
	
	
	@PostMapping("/agendar/{idPaciente}/{idConsulta}")
	@Transactional
	public ResponseEntity<Void> agendarRetorno(@PathVariable Long idPaciente, @PathVariable Long idConsulta, 
			@RequestBody @Valid AgendamentoRetornoFORM agendamentoRetorno) {
		
		return retornoConsultaService.agendarRetorno(idPaciente, idConsulta, agendamentoRetorno);
	}
	
	
	@PutMapping("/reagendar/{idPaciente}/{idRetornoConsulta}")
	@Transactional
	public ResponseEntity<Void> reagendarRetorno(@PathVariable Long idPaciente, @PathVariable Long idRetornoConsulta, 
			@RequestBody @Valid ReagendamentoRetornoFORM reagendamentoRetorno) {
		
		return retornoConsultaService.reagendarRetorno(idPaciente, idRetornoConsulta, reagendamentoRetorno);
	}
	
	
	@PutMapping("/confirmar/{idPaciente}/{idRetornoConsulta}")
	@Transactional
	public ResponseEntity<Void> confirmarRetornoConsulta(@PathVariable Long idPaciente, @PathVariable Long idRetornoConsulta) {
		return retornoConsultaService.confirmarRetornoConsulta(idPaciente, idRetornoConsulta);
	}
	
	
	@DeleteMapping("/cancelar/{idPaciente}/{idRetornoConsulta}")
	@Transactional
	public ResponseEntity<Void> cancelarRetornoConsulta(@PathVariable Long idPaciente, @PathVariable Long idRetornoConsulta) {
		return retornoConsultaService.cancelarRetornoConsulta(idPaciente, idRetornoConsulta);
	}
	
	
	@PutMapping("/iniciar/{idPaciente}/{idRetornoConsulta}")
	@Transactional
	public ResponseEntity<Void> iniciarRetornoConsulta(@PathVariable Long idPaciente, @PathVariable Long idRetornoConsulta) {
		return retornoConsultaService.iniciarRetornoConsulta(idPaciente, idRetornoConsulta);
	}
	
	
	@PutMapping("/finalizar/{idPaciente}/{idRetornoConsulta}")
	@Transactional
	public ResponseEntity<Void> finalizarRetornoConsulta(@PathVariable Long idPaciente, @PathVariable Long idRetornoConsulta) {
		return retornoConsultaService.finalizarRetornoConsulta(idPaciente, idRetornoConsulta);
	}
	
	
	@PostMapping("/cadastrar-avaliacao-consumo-habitual/{idPaciente}/{idRetornoConsulta}")
	@Transactional
	public ResponseEntity<Void> cadastrarAvaliacaoConsumoHabitual(@PathVariable Long idPaciente, @PathVariable Long idRetornoConsulta,
			@RequestBody @Valid AvaliacaoConsumoHabitualFORM avaliacaoConsumoHabitual) {
		
		return retornoConsultaService.cadastrarAvaliacaoConsumoHabitual(idPaciente, idRetornoConsulta, avaliacaoConsumoHabitual);
	}
	
	
	@PostMapping("/cadastrar-avaliacao-composicao-corporal/{idPaciente}/{idRetornoConsulta}")
	@Transactional
	public ResponseEntity<Void> cadastrarAvaliacaoComposicaoCorporal(@PathVariable Long idPaciente, @PathVariable Long idRetornoConsulta,
			@RequestBody @Valid AvaliacaoComposicaoCorporalFORM avaliacaoComposicaoCorporal) {
		
		return retornoConsultaService.cadastrarAvaliacaoComposicaoCorporal(idPaciente, idRetornoConsulta, avaliacaoComposicaoCorporal);
	}
	
	
	@PostMapping("/cadastrar-avaliacao-massa-muscular-corporea/{idPaciente}/{idRetornoConsulta}")
	@Transactional
	public ResponseEntity<Void> cadastrarAvaliacaoMassaMuscularCorporeaMedidaAntropometrica(@PathVariable Long idPaciente, 
			@PathVariable Long idRetornoConsulta, 
			@RequestBody @Valid AvaliacaoMassaMuscularCorporeaFORM avaliacaoMassaMuscularCorporea) {
		
		return retornoConsultaService.cadastrarAvaliacaoMassaMuscularCorporeaMedidaAntropometrica(idPaciente, idRetornoConsulta, 
				avaliacaoMassaMuscularCorporea);
	}
	
	
	@PostMapping("/cadastrar-conduta-nutricional/{idPaciente}/{idRetornoConsulta}")
	@Transactional
	public ResponseEntity<Void> cadastrarCondutaNutricional(@PathVariable Long idPaciente, @PathVariable Long idRetornoConsulta,
			@RequestBody @Valid CondutaNutricionalFORM condutaNutricional) {
		
		return retornoConsultaService.cadastrarCondutaNutricional(idPaciente, idRetornoConsulta, condutaNutricional);
	}
	
	
	@PostMapping("/cadastrar-registro-dieta-habitual/{idPaciente}/{idRetornoConsulta}")
	@Transactional
	public ResponseEntity<Void> cadastrarRegistroDietaHabitual(@PathVariable Long idPaciente, @PathVariable Long idRetornoConsulta,
			@RequestBody @Valid RegistroDietaFORM registroDietaHabitual) {
		
		return retornoConsultaService.cadastrarRegistroDietaHabitual(idPaciente, idRetornoConsulta, registroDietaHabitual);
	}
	
	
	@PostMapping("/cadastrar-registro-dieta-24horas/{idPaciente}/{idRetornoConsulta}")
	@Transactional
	public ResponseEntity<Void> cadastrarRegistroDieta24Horas(@PathVariable Long idPaciente, @PathVariable Long idRetornoConsulta,
			@RequestBody @Valid RegistroDietaFORM registroDieta24Horas) {
		
		return retornoConsultaService.cadastrarRegistroDieta24Horas(idPaciente, idRetornoConsulta, registroDieta24Horas);
	}
}
