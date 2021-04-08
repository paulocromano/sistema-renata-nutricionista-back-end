package br.com.renatanutricionista.tabelas.parametro.atendimento.horario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renatanutricionista.tabelas.parametro.atendimento.horario.model.HorarioAtendimento;


public interface HorarioAtendimentoRepository extends JpaRepository<HorarioAtendimento, Integer> {

	List<HorarioAtendimento> findAllByOrderByDiaDaSemanaAsc();
}
