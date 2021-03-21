package br.com.renatanutricionista.ficha.identificacao.historico.alimentar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renatanutricionista.ficha.identificacao.historico.alimentar.model.HistoricoAlimentar;


public interface HistoricoAlimentarRepository extends JpaRepository<HistoricoAlimentar, Long> {

}
