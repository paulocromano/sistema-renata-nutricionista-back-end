package br.com.renatanutricionista.calendario.agendamento.paciente.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renatanutricionista.calendario.agendamento.paciente.model.CalendarioAgendamento;


public interface CalendarioAgendamentoRepository extends JpaRepository<CalendarioAgendamento, Long> {

}
