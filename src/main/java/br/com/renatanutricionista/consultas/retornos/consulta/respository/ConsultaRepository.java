package br.com.renatanutricionista.consultas.retornos.consulta.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renatanutricionista.consultas.retornos.consulta.model.Consulta;


public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

}
