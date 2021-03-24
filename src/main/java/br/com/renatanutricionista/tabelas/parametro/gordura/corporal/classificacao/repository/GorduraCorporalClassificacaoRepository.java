package br.com.renatanutricionista.tabelas.parametro.gordura.corporal.classificacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.renatanutricionista.tabelas.parametro.gordura.corporal.classificacao.model.GorduraCorporalClassificacao;


@Transactional(readOnly = true)
public interface GorduraCorporalClassificacaoRepository extends JpaRepository<GorduraCorporalClassificacao, Integer> {

}
