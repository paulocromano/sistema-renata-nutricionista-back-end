package br.com.renatanutricionista.medicamento.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renatanutricionista.medicamento.model.Medicamento;


public interface MedicamentoRepository extends JpaRepository<Medicamento, Integer> {

	Set<Medicamento> findByIdIn(Set<Integer> idMedicamentos);
}
