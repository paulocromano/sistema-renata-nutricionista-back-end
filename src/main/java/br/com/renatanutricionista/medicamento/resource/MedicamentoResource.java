package br.com.renatanutricionista.medicamento.resource;

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

import br.com.renatanutricionista.medicamento.dto.MedicamentoDTO;
import br.com.renatanutricionista.medicamento.form.MedicamentoFORM;
import br.com.renatanutricionista.medicamento.service.MedicamentoService;


@RestController
@RequestMapping("/medicamento")
public class MedicamentoResource {

	@Autowired
	private MedicamentoService medicamentoService;
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping
	public ResponseEntity<List<MedicamentoDTO>> listarMedicamentosEmOrdemAlfabetica() {
		return medicamentoService.listarMedicamentosEmOrdemAlfabetica();
	}
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	@Transactional
	public ResponseEntity<Void> cadastrarMedicamento(@RequestBody @Valid MedicamentoFORM medicamento) {
		return medicamentoService.cadastrarMedicamento(medicamento);
	}
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("/{idMedicamento}")
	@Transactional
	public ResponseEntity<Void> alterarMedicamento(@PathVariable Integer idMedicamento, @RequestBody @Valid MedicamentoFORM medicamento) {
		return medicamentoService.alterarMedicamento(idMedicamento, medicamento);
	}
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/{idMedicamento}")
	@Transactional
	public ResponseEntity<Void> excluirMedicamento(@PathVariable Integer idMedicamento) {
		return medicamentoService.excluirMedicamento(idMedicamento);
	}
}
