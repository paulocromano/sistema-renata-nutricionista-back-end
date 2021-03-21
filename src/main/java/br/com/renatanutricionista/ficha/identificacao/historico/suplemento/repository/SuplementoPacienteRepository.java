package br.com.renatanutricionista.ficha.identificacao.historico.suplemento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renatanutricionista.ficha.identificacao.historico.suplemento.model.SuplementoPaciente;


public interface SuplementoPacienteRepository extends JpaRepository<SuplementoPaciente, Long> {

}
