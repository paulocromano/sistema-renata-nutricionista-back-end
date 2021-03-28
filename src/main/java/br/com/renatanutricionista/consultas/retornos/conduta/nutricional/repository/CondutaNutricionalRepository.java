package br.com.renatanutricionista.consultas.retornos.conduta.nutricional.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renatanutricionista.consultas.retornos.conduta.nutricional.model.CondutaNutricional;


public interface CondutaNutricionalRepository extends JpaRepository<CondutaNutricional, Long> {

}
