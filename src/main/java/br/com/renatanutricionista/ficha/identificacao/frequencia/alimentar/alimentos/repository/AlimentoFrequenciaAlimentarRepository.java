package br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.alimentos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.alimentos.model.AlimentoFrequenciaAlimentar;


@Transactional(readOnly = true)
public interface AlimentoFrequenciaAlimentarRepository extends JpaRepository<AlimentoFrequenciaAlimentar, Integer> {

}
