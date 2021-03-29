package br.com.renatanutricionista.atendimento.paciente.conduta.nutricional.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renatanutricionista.atendimento.paciente.conduta.nutricional.model.CondutaNutricional;


public interface CondutaNutricionalRepository extends JpaRepository<CondutaNutricional, Long> {

}
