package br.com.renatanutricionista.calendario.agendamento.paciente.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renatanutricionista.calendario.agendamento.paciente.model.CalendarioAgendamentoPaciente;


public interface CalendarioAgendamentoPacienteRepository extends JpaRepository<CalendarioAgendamentoPaciente, Long> {

	Optional<CalendarioAgendamentoPaciente> findByDataAndHorario(LocalDate data, LocalTime horario);
}
