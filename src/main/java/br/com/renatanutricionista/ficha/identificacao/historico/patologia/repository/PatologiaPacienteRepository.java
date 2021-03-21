package br.com.renatanutricionista.ficha.identificacao.historico.patologia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renatanutricionista.ficha.identificacao.historico.patologia.model.PatologiaPaciente;


public interface PatologiaPacienteRepository extends JpaRepository<PatologiaPaciente, Long> {

}
