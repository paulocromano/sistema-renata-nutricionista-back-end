package br.com.renatanutricionista.tabelas.parametro.gordura.corporal.esporte.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.renatanutricionista.tabelas.parametro.gordura.corporal.esporte.model.GorduraCorporalEsporte;


@Transactional(readOnly = true)
public interface GorduraCorporalEsporteRepository extends JpaRepository<GorduraCorporalEsporte, Integer> {

}
