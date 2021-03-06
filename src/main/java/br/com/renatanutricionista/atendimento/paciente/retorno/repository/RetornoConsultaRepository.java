package br.com.renatanutricionista.atendimento.paciente.retorno.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renatanutricionista.atendimento.paciente.retorno.enums.SituacaoRetorno;
import br.com.renatanutricionista.atendimento.paciente.retorno.model.RetornoConsulta;
import br.com.renatanutricionista.paciente.model.Paciente;


public interface RetornoConsultaRepository extends JpaRepository<RetornoConsulta, Long> {

	Optional<RetornoConsulta> findByConsulta_PacienteAndSituacaoRetornoNot(Paciente paciente, SituacaoRetorno retornoFinalizado);

	List<RetornoConsulta> findByDataGreaterThanEqual(LocalDate periodoAtual);

	List<RetornoConsulta> findByDataLessThan(LocalDate periodoAtual);

}
