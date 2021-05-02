package br.com.renatanutricionista.patologia.service;

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
import br.com.renatanutricionista.patologia.dto.PatologiaDTO;
import br.com.renatanutricionista.patologia.form.PatologiaFORM;
import br.com.renatanutricionista.patologia.model.Patologia;
import br.com.renatanutricionista.patologia.repository.PatologiaRepository;


@Service
public class PatologiaService {

	@Autowired
	private PatologiaRepository patologiaRepository;
	
	
	public ResponseEntity<List<PatologiaDTO>> listarPatologiasEmOrdemAlfabetica() {
		return ResponseEntity.ok().body(PatologiaDTO.converterParaListaPatologiaDTOEmOrdemAlfabetica(patologiaRepository.findAll()));
	}
	
	
	public ResponseEntity<Void> cadastrarPatologia(PatologiaFORM patologia) {
		verificarSeExistePatologiaComMesmaDescricao(patologia.getDescricao());
		patologiaRepository.save(patologia.converterParaPatologia());
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	public ResponseEntity<Void> alterarPatologia(Integer idPatologia, PatologiaFORM patologia) {
		verificarSeExistePatologiaComMesmaDescricao(patologia.getDescricao());
		patologia.atualizarPatologia(verificarSePatologiaExiste(idPatologia));
		
		return ResponseEntity.ok().build();
	}
	
	
	public ResponseEntity<Void> excluirPatologia(Integer idPatologia) {
		try {
			patologiaRepository.delete(verificarSePatologiaExiste(idPatologia));
		}
		catch (Exception e) {
			if (e instanceof SQLIntegrityConstraintViolationException) {
				throw new IntegrityConstraintViolationException("Não é possível excluir a Patologia pois há "
						+ "histórico de paciente(s) que contém este registro!");
			}
		}
		
		return ResponseEntity.noContent().build();
	}
	
	
	private void verificarSeExistePatologiaComMesmaDescricao(String descricaoPatologia) {
		Optional<Patologia> patologia = patologiaRepository.findByDescricaoIgnoreCase(descricaoPatologia);
		
		if (patologia.isPresent())
			throw new IllegalArgumentException("Já existe uma patologia com a mesma descrição cadastrada!");
	}
	
	
	private Patologia verificarSePatologiaExiste(Integer idPatologia) {
		if (Objects.isNull(idPatologia))
			throw new NullPointerException("O ID da Patologia não pode ser nulo!");
		
		Optional<Patologia> patologia = patologiaRepository.findById(idPatologia);
		
		if (patologia.isEmpty())
			throw new ObjectNotFoundException("Patologia não encontrada!");
		
		return patologia.get();
	}
}
