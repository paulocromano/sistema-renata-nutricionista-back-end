package br.com.renatanutricionista.atendimento.paciente.consulta.respository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renatanutricionista.atendimento.paciente.consulta.enums.situacao.consulta.SituacaoConsulta;
import br.com.renatanutricionista.atendimento.paciente.consulta.model.Consulta;
import br.com.renatanutricionista.paciente.model.Paciente;


public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

	Optional<Consulta> findByPacienteAndSituacaoConsultaNot(Paciente paciente, SituacaoConsulta situacaoConsulta);

	Optional<Consulta> findFirstByPacienteOrderByDataDesc(Paciente paciente);

	List<Consulta> findByDataGreaterThanEqual(LocalDate periodoAtual);

	List<Consulta> findByDataLessThan(LocalDate periodoAtual);
}
