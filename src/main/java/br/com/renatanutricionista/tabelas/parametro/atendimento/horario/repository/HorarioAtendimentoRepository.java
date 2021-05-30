package br.com.renatanutricionista.tabelas.parametro.atendimento.horario.repository;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renatanutricionista.tabelas.parametro.atendimento.horario.model.HorarioAtendimento;


public interface HorarioAtendimentoRepository extends JpaRepository<HorarioAtendimento, Integer> {

	List<HorarioAtendimento> findAllByOrderByDiaDaSemanaAsc();

	Optional<HorarioAtendimento> findByDiaDaSemana(DayOfWeek diaDaSemana);
}
