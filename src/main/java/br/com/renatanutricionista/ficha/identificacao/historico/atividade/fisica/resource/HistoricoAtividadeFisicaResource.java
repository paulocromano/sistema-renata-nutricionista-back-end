package br.com.renatanutricionista.ficha.identificacao.historico.atividade.fisica.resource;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.renatanutricionista.ficha.identificacao.historico.atividade.fisica.dto.InformacoesHistoricosAtividadeFisicaDTO;
import br.com.renatanutricionista.ficha.identificacao.historico.atividade.fisica.form.HistoricoAtividadeFisicaFORM;
import br.com.renatanutricionista.ficha.identificacao.historico.atividade.fisica.service.HistoricoAtividadeFisicaService;


@RestController
@RequestMapping("/atividade-fisica")
public class HistoricoAtividadeFisicaResource {
	
	@Autowired
	private HistoricoAtividadeFisicaService historicoAtividadeFisicaService;

	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/informacoes-paciente/{idPaciente}")
	public ResponseEntity<InformacoesHistoricosAtividadeFisicaDTO> buscarInformacoesHistoricosAtividadeFisicaDoPaciente(@PathVariable Long idPaciente) {
		return historicoAtividadeFisicaService.buscarInformacoesHistoricosAtividadeFisicaDoPaciente(idPaciente);
	}
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping("/{idPaciente}")
	@Transactional
	public ResponseEntity<Void> cadastrarAtividadeFisicaDoPaciente(@PathVariable Long idPaciente, 
			@RequestBody @Valid HistoricoAtividadeFisicaFORM historicoAtividadeFisicaFORM) {
		
		return historicoAtividadeFisicaService.cadastrarAtividadeFisicaDoPaciente(idPaciente, historicoAtividadeFisicaFORM);
	}
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/{idHistoricoAtividadeFisica}")
	@Transactional
	public ResponseEntity<Void> excluirHistoricoAtividadeFisica(@PathVariable Long idHistoricoAtividadeFisica) {
		return historicoAtividadeFisicaService.excluirHistoricoAtividadeFisica(idHistoricoAtividadeFisica);
	}
}
