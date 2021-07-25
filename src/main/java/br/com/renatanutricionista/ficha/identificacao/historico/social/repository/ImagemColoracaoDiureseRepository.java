package br.com.renatanutricionista.ficha.identificacao.historico.social.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renatanutricionista.ficha.identificacao.historico.social.enums.diurese.coloracao.ColoracaoDiurese;
import br.com.renatanutricionista.ficha.identificacao.historico.social.model.ImagemColoracaoDiurese;


public interface ImagemColoracaoDiureseRepository extends JpaRepository<ImagemColoracaoDiurese, Integer> {

	Optional<ImagemColoracaoDiurese> findByColoracaoDiurese(ColoracaoDiurese coloracaoDiurese);

	Set<ImagemColoracaoDiurese> findByIdIn(Set<Integer> coloracoesDiurese);
}
