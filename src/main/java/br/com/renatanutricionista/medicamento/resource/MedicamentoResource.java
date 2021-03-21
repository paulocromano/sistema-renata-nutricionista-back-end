package br.com.renatanutricionista.medicamento.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.renatanutricionista.medicamento.dto.MedicamentoDTO;
import br.com.renatanutricionista.medicamento.service.MedicamentoService;


@RestController
@RequestMapping("/medicamento")
public class MedicamentoResource {

	@Autowired
	private MedicamentoService medicamentoService;
	
	
	public ResponseEntity<List<MedicamentoDTO>> listarMedicamentosEmOrdemAlfabetica() {
		return medicamentoService.listarMedicamentosEmOrdemAlfabetica();
	}
}
