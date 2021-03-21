package br.com.renatanutricionista.alimento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renatanutricionista.alimento.model.Alimento;


public interface AlimentoRepository extends JpaRepository<Alimento, Integer> {

}
