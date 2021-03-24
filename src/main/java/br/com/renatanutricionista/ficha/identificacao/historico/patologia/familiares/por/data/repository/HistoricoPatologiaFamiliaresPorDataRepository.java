package br.com.renatanutricionista.ficha.identificacao.historico.patologia.familiares.por.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renatanutricionista.ficha.identificacao.historico.patologia.familiares.por.data.model.HistoricoPatologiaFamiliaresPorData;


public interface HistoricoPatologiaFamiliaresPorDataRepository extends JpaRepository<HistoricoPatologiaFamiliaresPorData, Long> {

}
