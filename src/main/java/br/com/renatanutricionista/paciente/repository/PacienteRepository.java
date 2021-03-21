package br.com.renatanutricionista.paciente.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renatanutricionista.paciente.model.Paciente;


public interface PacienteRepository extends JpaRepository<Paciente, Long> {

}
