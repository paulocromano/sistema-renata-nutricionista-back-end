package br.com.renatanutricionista.atendimento.paciente.retorno.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renatanutricionista.atendimento.paciente.retorno.model.RetornoConsulta;


public interface RetornoConsultaRepository extends JpaRepository<RetornoConsulta, Long> {

}
