package br.com.renatanutricionista.suplemento.resource;

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

import br.com.renatanutricionista.suplemento.dto.SuplementoDTO;
import br.com.renatanutricionista.suplemento.form.SuplementoFORM;
import br.com.renatanutricionista.suplemento.service.SuplementoService;


@RestController
@RequestMapping("/suplemento")
public class SuplementoResource {

	@Autowired
	private SuplementoService suplementoService;
	
	
	@GetMapping
	public ResponseEntity<List<SuplementoDTO>> listarSuplementosEmOrdemAlfabetica() {
		return suplementoService.listarSuplementosEmOrdemAlfabetica();
	}
	
	
	@PostMapping
	@Transactional
	public ResponseEntity<Void> cadastrarSuplemento(@RequestBody @Valid SuplementoFORM suplemento) {
		return suplementoService.cadastrarSuplemento(suplemento);
	}
	
	
	@PutMapping("/{idSuplemento}")
	@Transactional
	public ResponseEntity<Void> alterarSuplemento(@PathVariable Integer idSuplemento, @RequestBody @Valid SuplementoFORM suplemento) {
		return suplementoService.alterarSuplemento(idSuplemento, suplemento);
	}
	
	
	@DeleteMapping("/{idSuplemento}")
	@Transactional
	public ResponseEntity<Void> excluirSuplemento(@PathVariable Integer idSuplemento) {
		return suplementoService.excluirSuplemento(idSuplemento);
	}
}
