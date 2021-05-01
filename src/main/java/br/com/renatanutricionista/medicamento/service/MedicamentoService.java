package br.com.renatanutricionista.medicamento.service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.renatanutricionista.exception.custom.IntegrityConstraintViolationException;
import br.com.renatanutricionista.exception.custom.ObjectNotFoundException;
import br.com.renatanutricionista.medicamento.dto.MedicamentoDTO;
import br.com.renatanutricionista.medicamento.form.MedicamentoFORM;
import br.com.renatanutricionista.medicamento.model.Medicamento;
import br.com.renatanutricionista.medicamento.repository.MedicamentoRepository;


@Service
public class MedicamentoService {

	@Autowired
	private MedicamentoRepository medicamentoRepository;
	
	
	public ResponseEntity<List<MedicamentoDTO>> listarMedicamentosEmOrdemAlfabetica() {
		return ResponseEntity.ok()
				.body(MedicamentoDTO.converterParaListaMedicamentoDTOEmOrdemAlfabetica(medicamentoRepository.findAll()));
	}
	
	
	public ResponseEntity<Void> cadastrarMedicamento(MedicamentoFORM medicamento) {
		verificarSeExisteMedicamentoComMesmoNome(medicamento.getNome());
		medicamentoRepository.save(medicamento.converterParaMedicamento());
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	public ResponseEntity<Void> alterarMedicamento(Integer idMedicamento, MedicamentoFORM medicamento) {
		verificarSeExisteMedicamentoComMesmoNome(medicamento.getNome());
		medicamento.atualizarMedicamento(verificarSeMedicamentoExiste(idMedicamento));
		
		return ResponseEntity.ok().build();
	}
	
	
	public ResponseEntity<Void> excluirMedicamento(Integer idMedicamento) {
		try {
			medicamentoRepository.delete(verificarSeMedicamentoExiste(idMedicamento));
		}
		catch (Exception e) {
			if (e instanceof SQLIntegrityConstraintViolationException) {
				throw new IntegrityConstraintViolationException("Não é possível excluir o Medicamento pois há "
						+ "histórico de paciente(s) que contém este registro!");
			}
		}
		
		return ResponseEntity.noContent().build();
	}
	
	
	private void verificarSeExisteMedicamentoComMesmoNome(String nomeMedicamento) {
		Optional<Medicamento> medicamento = medicamentoRepository.findByNomeIgnoreCase(nomeMedicamento);
		
		if (medicamento.isPresent()) 
			throw new IllegalArgumentException("Já existe um medicamento com o mesmo nome cadastrado!");
	}
	
	private Medicamento verificarSeMedicamentoExiste(Integer idMedicamento) {
		if (Objects.isNull(idMedicamento))
			throw new NullPointerException("O ID do Medicamento não pode ser nulo!");
		
		Optional<Medicamento> medicamento = medicamentoRepository.findById(idMedicamento);
		
		if (medicamento.isEmpty())
			throw new ObjectNotFoundException("Medicamento não encontrado!");
		
		return medicamento.get();
	}
}
