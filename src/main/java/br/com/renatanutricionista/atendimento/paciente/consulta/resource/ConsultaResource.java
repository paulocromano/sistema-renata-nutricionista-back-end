package br.com.renatanutricionista.atendimento.paciente.consulta.resource;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
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

import br.com.renatanutricionista.atendimento.paciente.consulta.dto.ConfirmacaoAtendimentoDTO;
import br.com.renatanutricionista.atendimento.paciente.consulta.dto.ConsultaDTO;
import br.com.renatanutricionista.atendimento.paciente.consulta.dto.InformacoesCadastroConsultaDTO;
import br.com.renatanutricionista.atendimento.paciente.consulta.dto.InformacoesPreviasConsultaRetornoDTO;
import br.com.renatanutricionista.atendimento.paciente.consulta.form.AgendamentoConsultaFORM;
import br.com.renatanutricionista.atendimento.paciente.consulta.form.ConfirmacaoConsultaFORM;
import br.com.renatanutricionista.atendimento.paciente.consulta.form.ConsultaFORM;
import br.com.renatanutricionista.atendimento.paciente.consulta.form.ReagendamentoConsultaFORM;
import br.com.renatanutricionista.atendimento.paciente.consulta.service.ConsultaService;


@RestController
@RequestMapping("/consulta-paciente")
public class ConsultaResource {

	@Autowired
	private ConsultaService consultaService;
	
	
	@GetMapping("/gerar-relatorio-pagamentos-pendentes")
	public ResponseEntity<byte[]> gerarRelatorioDosPagamentosPendentes() {
		return consultaService.gerarRelatorioDosPagamentosPendentes();
	}
	
	
	@GetMapping("/listar-atendimentos-data-atual-posteriores")
	public ResponseEntity<List<InformacoesPreviasConsultaRetornoDTO>> listarAtendimentosAPartirDaDataAtual() {
		return consultaService.listarAtendimentosAPartirDaDataAtual();
	}
	
	
	@GetMapping("/listar-atendimentos-anteriores-data-atual")
	public ResponseEntity<List<InformacoesPreviasConsultaRetornoDTO>> listarAtendimentosAnterioresAoDiaAtual() {
		return consultaService.listarAtendimentosAnterioresAoDiaAtual();
	}
	
	
	@GetMapping("/proximo-tipo-atendimento/{idPaciente}")
	public ResponseEntity<Integer> verificarProximoTipoDeAtendimentoDoPaciente(@PathVariable Long idPaciente) {
		return consultaService.verificarProximoTipoDeAtendimentoDoPaciente(idPaciente);
	}
	
	
	@GetMapping("/informacoes-confirmacao-atendimento")
	public ResponseEntity<ConfirmacaoAtendimentoDTO> informacoesParaConfirmacaoDeAtendimento() {
		return consultaService.informacoesParaConfirmacaoDeAtendimento();
	}
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/buscar/{tipoAtendimento}/{idConsulta}")
	public ResponseEntity<ConsultaDTO> buscarConsultaDoPaciente(@PathVariable Integer tipoAtendimento, @PathVariable Long idConsulta) {
		return consultaService.buscarConsultaDoPaciente(tipoAtendimento, idConsulta);
	}
	
	
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
	public ResponseEntity<Void> cancelarConsulta(HttpServletRequest request, @PathVariable Long idPaciente, @PathVariable Long idConsulta) {
		return consultaService.cancelarConsulta(request, idPaciente, idConsulta);
	}
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("/iniciar/{idPaciente}/{idConsulta}")
	@Transactional
	public ResponseEntity<Void> iniciarConsulta(@PathVariable Long idPaciente, @PathVariable Long idConsulta) { 
		return consultaService.iniciarConsulta(idPaciente, idConsulta);
	}
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/informacoes-cadastro-consulta/{idPaciente}/{idConsulta}")
	public ResponseEntity<InformacoesCadastroConsultaDTO> informacoesParaCadastrarConsulta(@PathVariable Long idPaciente, @PathVariable Long idConsulta) {
		return consultaService.informacoesParaCadastrarConsulta(idPaciente, idConsulta);
	}
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("/finalizar/{idPaciente}/{idConsulta}")
	@Transactional
	public ResponseEntity<Void> finalizarConsulta(@PathVariable Long idPaciente, @PathVariable Long idConsulta,
			@RequestBody @Valid ConsultaFORM formularioConsulta) {
		return consultaService.finalizarConsulta(idPaciente, idConsulta, formularioConsulta);
	}
}
