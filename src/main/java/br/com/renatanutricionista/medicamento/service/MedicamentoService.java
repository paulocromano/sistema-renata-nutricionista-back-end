package br.com.renatanutricionista.medicamento.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.renatanutricionista.medicamento.dto.MedicamentoDTO;
import br.com.renatanutricionista.medicamento.repository.MedicamentoRepository;


@Service
public class MedicamentoService {

	@Autowired
	private MedicamentoRepository medicamentoRepository;
	
	
	public ResponseEntity<List<MedicamentoDTO>> listarMedicamentosEmOrdemAlfabetica() {
		return ResponseEntity.ok()
				.body(MedicamentoDTO.converterParaListaMedicamentoDTOEmOrdemAlfabetica(medicamentoRepository.findAll()));
	}
}
