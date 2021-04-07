package br.com.renatanutricionista.atendimento.paciente.registro.dieta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renatanutricionista.atendimento.paciente.registro.dieta.model.RegistroDieta;


public interface RegistroDietaRepository extends JpaRepository<RegistroDieta, Long> {

}
