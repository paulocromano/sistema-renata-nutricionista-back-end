package br.com.renatanutricionista.tabelas.parametro.atividade.fisica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.renatanutricionista.tabelas.parametro.atividade.fisica.model.AtividadeFisicaParametro;

@Transactional(readOnly = true)
public interface AtividadeFisicaParametroRepository extends JpaRepository<AtividadeFisicaParametro, Integer> {

}
