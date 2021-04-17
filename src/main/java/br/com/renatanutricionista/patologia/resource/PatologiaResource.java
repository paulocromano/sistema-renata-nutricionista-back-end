package br.com.renatanutricionista.patologia.resource;

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

import br.com.renatanutricionista.patologia.dto.PatologiaDTO;
import br.com.renatanutricionista.patologia.form.PatologiaFORM;
import br.com.renatanutricionista.patologia.service.PatologiaService;


@RestController
@RequestMapping("/patologia")
public class PatologiaResource {

	@Autowired
	private PatologiaService patologiaService;
	
	
	@GetMapping
	public ResponseEntity<List<PatologiaDTO>> listarPatologiasEmOrdemAlfabetica() {
		return patologiaService.listarPatologiasEmOrdemAlfabetica();
	}
	
	
	@PostMapping
	@Transactional
	public ResponseEntity<Void> cadastrarPatologia(@RequestBody @Valid PatologiaFORM patologia) {
		return patologiaService.cadastrarPatologia(patologia);
	}
	
	
	@PutMapping("/{idPatologia}")
	@Transactional
	public ResponseEntity<Void> alterarPatologia(@PathVariable Integer idPatologia, @RequestBody @Valid PatologiaFORM patologia) {
		return patologiaService.alterarPatologia(idPatologia, patologia);
	}
	
	
	@DeleteMapping("/{idPatologia}")
	@Transactional
	public ResponseEntity<Void> excluirPatologia(@PathVariable Integer idPatologia) {
		return patologiaService.excluirPatologia(idPatologia);
	}
}
