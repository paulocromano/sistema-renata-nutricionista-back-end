package br.com.renatanutricionista.calendario.atendimento.paciente.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renatanutricionista.calendario.atendimento.paciente.model.CalendarioAtendimentoPaciente;


public interface CalendarioAtendimentoPacienteRepository extends JpaRepository<CalendarioAtendimentoPaciente, Long> {

	Optional<CalendarioAtendimentoPaciente> findByDataAndHorario(LocalDate data, LocalTime horario);

	List<CalendarioAtendimentoPaciente> findAllByDataGreaterThanEqual(LocalDate dataAtual);

	List<CalendarioAtendimentoPaciente> findByDataBetween(LocalDate dataInicial, LocalDate dataFinal);
}
