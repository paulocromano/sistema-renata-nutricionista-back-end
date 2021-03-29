package br.com.renatanutricionista.atendimento.paciente.avaliacao.consumo.habitual.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renatanutricionista.atendimento.paciente.avaliacao.consumo.habitual.model.AvaliacaoConsumoHabitual;


public interface AvaliacaoConsumoHabitualRepository extends JpaRepository<AvaliacaoConsumoHabitual, Long> {

}
