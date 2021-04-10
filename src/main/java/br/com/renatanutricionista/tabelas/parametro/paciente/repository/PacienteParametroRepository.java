package br.com.renatanutricionista.tabelas.parametro.paciente.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renatanutricionista.tabelas.parametro.paciente.model.PacienteParametro;


public interface PacienteParametroRepository extends JpaRepository<PacienteParametro, Integer> {

}
