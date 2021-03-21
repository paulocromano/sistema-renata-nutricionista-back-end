package br.com.renatanutricionista.tabelas.parametro.composicao.corporal.antropometria.equacao.generalizada.jackson.pollock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.renatanutricionista.tabelas.parametro.composicao.corporal.antropometria.equacao.generalizada.jackson.pollock.model.EquacaoPollock;


@Transactional(readOnly = true)
public interface EquacaoPollockRepository extends JpaRepository<EquacaoPollock, Integer> {

}
