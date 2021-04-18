package br.com.renatanutricionista.seguranca.usuario.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.renatanutricionista.exception.custom.IntegrityConstraintViolationException;
import br.com.renatanutricionista.seguranca.usuario.form.UsuarioFORM;
import br.com.renatanutricionista.seguranca.usuario.model.Usuario;
import br.com.renatanutricionista.seguranca.usuario.repository.UsuarioRepository;


@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	public ResponseEntity<Void> cadastrarUsuario(UsuarioFORM usuario) {
		verificarSeEmailJaExiste(usuario.getEmail());
		usuarioRepository.save(usuario.converterParaUsuario(bCryptPasswordEncoder));

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	private void verificarSeEmailJaExiste(String email) {
		Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
		
		if (usuario.isPresent())
			throw new IntegrityConstraintViolationException("Email já cadastrado!");
	}
}
