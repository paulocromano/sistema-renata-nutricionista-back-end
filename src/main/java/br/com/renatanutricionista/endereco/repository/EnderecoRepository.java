package br.com.renatanutricionista.endereco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renatanutricionista.endereco.model.Endereco;


public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

}
