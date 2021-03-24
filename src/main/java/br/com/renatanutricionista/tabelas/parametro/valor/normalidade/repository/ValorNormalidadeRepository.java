package br.com.renatanutricionista.tabelas.parametro.valor.normalidade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.renatanutricionista.tabelas.parametro.valor.normalidade.model.ValorNormalidade;


@Transactional(readOnly = true)
public interface ValorNormalidadeRepository extends JpaRepository<ValorNormalidade, Integer> {

}
