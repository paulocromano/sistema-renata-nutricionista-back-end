package br.com.renatanutricionista.seguranca.usuario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.renatanutricionista.exception.custom.IntegrityConstraintViolationException;
import br.com.renatanutricionista.exception.custom.ObjectNotFoundException;
import br.com.renatanutricionista.seguranca.usuario.dto.UsuarioDTO;
import br.com.renatanutricionista.seguranca.usuario.enums.Perfil;
import br.com.renatanutricionista.seguranca.usuario.form.UsuarioFORM;
import br.com.renatanutricionista.seguranca.usuario.model.Usuario;
import br.com.renatanutricionista.seguranca.usuario.repository.UsuarioRepository;


@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	public ResponseEntity<List<UsuarioDTO>> listarUsuariosEmOrdemAlfabetica() {
		return ResponseEntity.ok().body(UsuarioDTO.converterParaUsuarioDTOEmOrdemAlfabetica(
				usuarioRepository.findAllByPerfisContains(Perfil.FUNCIONARIO.ordinal())));
	}
	
	
	public ResponseEntity<Void> cadastrarUsuario(UsuarioFORM usuario) {
		verificarSeEmailJaExiste(usuario.getEmail());
		usuarioRepository.save(usuario.converterParaUsuario(bCryptPasswordEncoder));

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	public ResponseEntity<Void> atualizarUsuario(Integer id, UsuarioFORM usuarioFORM) {
		Usuario usuario = verificarSeUsuarioExiste(id);
		
		if (!usuario.getEmail().equals(usuarioFORM.getEmail())) {
			verificarSeEmailJaExiste(usuarioFORM.getEmail());
		}
		
		usuarioFORM.atualizarInformacoesUsuario(usuario, bCryptPasswordEncoder);
		
		return ResponseEntity.ok().build();
	}
	
	
	public ResponseEntity<Void> removerUsuario(Integer id) {
		Usuario usuario = verificarSeUsuarioExiste(id);
		usuarioRepository.delete(usuario);
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	
	private void verificarSeEmailJaExiste(String email) {
		Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
		
		if (usuario.isPresent())
			throw new IntegrityConstraintViolationException("Email já cadastrado!");
	}
	
	
	private Usuario verificarSeUsuarioExiste(Integer id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		
		if (usuario.isEmpty())
			throw new ObjectNotFoundException("Usuário não existe!");
		
		return usuario.get();
	}
}
