package br.com.renatanutricionista.suplemento.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renatanutricionista.suplemento.model.Suplemento;

public interface SuplementoRepository extends JpaRepository<Suplemento, Integer> {

	Optional<Suplemento> findByAllIgnoreCaseNomeAndDoseAndFormaPreparo(String nome, String dose, String formaPreparo);
}
