package br.com.renatanutricionista.consultas.retornos.retorno.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renatanutricionista.consultas.retornos.retorno.model.RetornoConsulta;


public interface RetornoConsultaRepository extends JpaRepository<RetornoConsulta, Long> {

}
