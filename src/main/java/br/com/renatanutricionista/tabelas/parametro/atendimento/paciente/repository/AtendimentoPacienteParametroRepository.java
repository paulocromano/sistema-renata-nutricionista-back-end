package br.com.renatanutricionista.tabelas.parametro.atendimento.paciente.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renatanutricionista.tabelas.parametro.atendimento.paciente.model.AtendimentoPacienteParametro;


public interface AtendimentoPacienteParametroRepository extends JpaRepository<AtendimentoPacienteParametro, Integer> {

}
