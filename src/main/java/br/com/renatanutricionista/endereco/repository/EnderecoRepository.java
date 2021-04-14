package br.com.renatanutricionista.endereco.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renatanutricionista.endereco.model.Endereco;


public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

	Optional<Endereco> findByPaciente_Id(Long idPaciente);
}
