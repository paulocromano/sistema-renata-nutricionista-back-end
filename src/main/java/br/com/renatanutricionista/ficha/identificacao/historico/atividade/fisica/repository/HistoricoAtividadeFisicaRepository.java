package br.com.renatanutricionista.ficha.identificacao.historico.atividade.fisica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renatanutricionista.ficha.identificacao.historico.atividade.fisica.model.HistoricoAtividadeFisica;


public interface HistoricoAtividadeFisicaRepository extends JpaRepository<HistoricoAtividadeFisica, Long> {

}
