package br.com.renatanutricionista.ficha.identificacao.historico.patologia.por.data.familiares.resource;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.renatanutricionista.ficha.identificacao.historico.patologia.por.data.familiares.form.HistoricoPatologiaFamiliaresPorDataFORM;
import br.com.renatanutricionista.ficha.identificacao.historico.patologia.por.data.familiares.service.HistoricoPatologiaFamiliaresPorDataService;


@RestController
@RequestMapping("/historico-patologia-familiares-por-data")
public class HistoricoPatologiaFamiliaresPorDataResource {

	@Autowired
	private HistoricoPatologiaFamiliaresPorDataService historicoPatologiaFamiliaresPorDataService;
	
	
	@PostMapping("/{idPaciente}")
	@Transactional
	public ResponseEntity<Void> cadastrarHistoricoPatologiaFamiliaresPorData(@PathVariable Long idPaciente,
			@RequestBody @Valid HistoricoPatologiaFamiliaresPorDataFORM historicoPatologiaFamiliaresPorDataFORM) {
		
		return historicoPatologiaFamiliaresPorDataService.cadastrarHistoricoPatologiaFamiliaresPorData(idPaciente, 
				historicoPatologiaFamiliaresPorDataFORM);
	}
	
	
	@DeleteMapping("/{idHistoricoPatologiaFamiliaresPorData}")
	@Transactional
	public ResponseEntity<Void> excluirHistoricoPatologiaFamiliaresPorData(@PathVariable Long idHistoricoPatologiaFamiliaresPorData) {
		return historicoPatologiaFamiliaresPorDataService.excluirHistoricoPatologiaFamiliaresPorData(idHistoricoPatologiaFamiliaresPorData);
	}
}
