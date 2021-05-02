package br.com.renatanutricionista.suplemento.service;

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
import br.com.renatanutricionista.suplemento.dto.SuplementoDTO;
import br.com.renatanutricionista.suplemento.form.SuplementoFORM;
import br.com.renatanutricionista.suplemento.model.Suplemento;
import br.com.renatanutricionista.suplemento.repository.SuplementoRepository;


@Service
public class SuplementoService {

	@Autowired
	private SuplementoRepository suplementoRepository;
	
	
	public ResponseEntity<List<SuplementoDTO>> listarSuplementosEmOrdemAlfabetica() {
		return ResponseEntity.ok().body(SuplementoDTO.converterParaListaSuplementoDTOEmOrdemAlfabetica(suplementoRepository.findAll()));
	}
	
	
	public ResponseEntity<Void> cadastrarSuplemento(SuplementoFORM suplemento) {
		verificarSeExisteSuplementoComMesmoNome(suplemento);
		suplementoRepository.save(suplemento.converterParaSuplemento());
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	public ResponseEntity<Void> alterarSuplemento(Integer idSuplemento, SuplementoFORM suplemento) {
		verificarSeExisteSuplementoComMesmoNome(suplemento);
		suplemento.atualizarSuplemento(verificarSeSuplementoExiste(idSuplemento));
		
		return ResponseEntity.ok().build();
	}
	
	
	public ResponseEntity<Void> excluirSuplemento(Integer idSuplemento) {
		try {
			suplementoRepository.delete(verificarSeSuplementoExiste(idSuplemento));
		}
		catch (Exception e) {
			if (e instanceof SQLIntegrityConstraintViolationException) {
				throw new IntegrityConstraintViolationException("Não é possível excluir o Suplemento, pois há "
						+ "histórico de paciente(s) que contém este registro!");
			}
		}
	
		return ResponseEntity.noContent().build();
	}
	
	
	private void verificarSeExisteSuplementoComMesmoNome(SuplementoFORM suplementoFORM) {
		Optional<Suplemento> suplemento = suplementoRepository.findByAllIgnoreCaseNomeAndDoseAndFormaPreparo(
				suplementoFORM.getNome(), suplementoFORM.getDose(), suplementoFORM.getFormaPreparo());
		
		if (suplemento.isPresent())
			throw new IllegalArgumentException("Já existe um suplemento com as mesmas informações cadastradas!");
	}
	
	
	private Suplemento verificarSeSuplementoExiste(Integer idSuplemento) {
		if (Objects.isNull(idSuplemento))
			throw new NullPointerException("O ID do Suplemento não pode ser nulo!");
		
		Optional<Suplemento> suplemento = suplementoRepository.findById(idSuplemento);
		
		if (suplemento.isEmpty())
			throw new ObjectNotFoundException("Suplemento não encontrado!");
		
		return suplemento.get();
	}
}
