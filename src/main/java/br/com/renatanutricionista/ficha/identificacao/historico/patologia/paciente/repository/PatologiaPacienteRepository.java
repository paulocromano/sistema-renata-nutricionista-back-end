package br.com.renatanutricionista.ficha.identificacao.historico.patologia.paciente.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renatanutricionista.ficha.identificacao.historico.patologia.paciente.model.PatologiaPaciente;


public interface PatologiaPacienteRepository extends JpaRepository<PatologiaPaciente, Long> {

}
