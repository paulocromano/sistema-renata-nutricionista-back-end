package br.com.renatanutricionista.tabelas.parametro.atendimento.paciente.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renatanutricionista.tabelas.parametro.atendimento.paciente.model.AtendimentoParametro;


public interface AtendimentoParametroRepository extends JpaRepository<AtendimentoParametro, Integer> {

}
