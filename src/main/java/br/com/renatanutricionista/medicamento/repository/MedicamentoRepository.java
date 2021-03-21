package br.com.renatanutricionista.medicamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renatanutricionista.medicamento.model.Medicamento;


public interface MedicamentoRepository extends JpaRepository<Medicamento, Integer> {

	List<Medicamento> findByIdIn(List<Integer> idMedicamentos);
}
