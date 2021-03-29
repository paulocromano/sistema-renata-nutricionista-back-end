package br.com.renatanutricionista.atendimento.paciente.consulta.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renatanutricionista.atendimento.paciente.consulta.enums.SituacaoConsulta;
import br.com.renatanutricionista.atendimento.paciente.consulta.model.Consulta;
import br.com.renatanutricionista.paciente.model.Paciente;


public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

	Optional<Consulta> findByPacienteAndSituacaoConsultaNot(Paciente paciente, SituacaoConsulta situacaoConsulta);
}
