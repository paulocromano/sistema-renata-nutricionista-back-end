package br.com.renatanutricionista.ficha.identificacao.historico.patologia.familiares.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renatanutricionista.ficha.identificacao.historico.patologia.familiares.model.HistoricoPatologiaFamiliares;


public interface HistoricoPatologiaFamiliaresRepository extends JpaRepository<HistoricoPatologiaFamiliares, Long> {

}
