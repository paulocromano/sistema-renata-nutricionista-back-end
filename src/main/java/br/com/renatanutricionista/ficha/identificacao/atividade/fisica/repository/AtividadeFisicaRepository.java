package br.com.renatanutricionista.ficha.identificacao.atividade.fisica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renatanutricionista.ficha.identificacao.atividade.fisica.model.AtividadeFisica;


public interface AtividadeFisicaRepository extends JpaRepository<AtividadeFisica, Long> {

}
