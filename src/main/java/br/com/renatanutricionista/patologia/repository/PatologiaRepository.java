package br.com.renatanutricionista.patologia.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renatanutricionista.patologia.model.Patologia;


public interface PatologiaRepository extends JpaRepository<Patologia, Integer> {

	List<Patologia> findByIdIn(List<Integer> listaIdPatologia);

	Optional<Patologia> findByDescricaoIgnoreCase(String descricaoPatologia);
}
