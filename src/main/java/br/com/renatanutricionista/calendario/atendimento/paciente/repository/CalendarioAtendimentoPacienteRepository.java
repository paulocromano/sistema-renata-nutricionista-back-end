package br.com.renatanutricionista.calendario.atendimento.paciente.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renatanutricionista.calendario.atendimento.paciente.model.CalendarioAtendimentoPaciente;
import br.com.renatanutricionista.utils.enums.resposta.RespostaUtils;


public interface CalendarioAtendimentoPacienteRepository extends JpaRepository<CalendarioAtendimentoPaciente, Long> {

	Optional<CalendarioAtendimentoPaciente> findByDataAndHorario(LocalDate data, LocalTime horario);

	List<CalendarioAtendimentoPaciente> findByDataBetween(LocalDate dataInicial, LocalDate dataFinal);

	List<CalendarioAtendimentoPaciente> findAllByDataAndHorarioBetween(LocalDate dataPeriodo, LocalTime horarioAnteriorConformeIntervaloEntreConsultas,
			LocalTime horarioPosteriorConformeIntervaloEntreConsultas);

	void deleteByDataLessThan(LocalDate data);

	List<CalendarioAtendimentoPaciente> findByDataBetweenAndPeriodoDisponivel(LocalDate dataInicial, LocalDate dataFinal, RespostaUtils disponibilidadePeriodo);

	List<CalendarioAtendimentoPaciente> findByDataOrderByHorarioAsc(LocalDate data);

	List<CalendarioAtendimentoPaciente> findByDataAndPeriodoDisponivelOrderByHorarioAsc(LocalDate dataConvertida, RespostaUtils disponibilidadePeriodo);
}
