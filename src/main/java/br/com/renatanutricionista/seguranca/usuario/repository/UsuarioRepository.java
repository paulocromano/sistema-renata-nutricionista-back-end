package br.com.renatanutricionista.seguranca.usuario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renatanutricionista.seguranca.usuario.model.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	Optional<Usuario> findByEmail(String email);
}
