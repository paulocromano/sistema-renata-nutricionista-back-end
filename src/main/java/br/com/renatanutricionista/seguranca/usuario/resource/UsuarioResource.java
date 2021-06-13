package br.com.renatanutricionista.seguranca.usuario.resource;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.renatanutricionista.seguranca.usuario.dto.UsuarioDTO;
import br.com.renatanutricionista.seguranca.usuario.form.UsuarioFORM;
import br.com.renatanutricionista.seguranca.usuario.service.UsuarioService;


@RestController
@RequestMapping("/usuario")
public class UsuarioResource {

	@Autowired
	private UsuarioService usuarioService;
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> listarUsuariosEmOrdemAlfabetica() {
		return usuarioService.listarUsuariosEmOrdemAlfabetica();
	}
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	@Transactional
	public ResponseEntity<Void> cadastrarUsuario(@RequestBody @Valid UsuarioFORM usuario) {
		return usuarioService.cadastrarUsuario(usuario);
	}
	
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> atualizarUsuario(@PathVariable Integer id, @RequestBody @Valid UsuarioFORM usuarioFORM) {
		return usuarioService.atualizarUsuario(id, usuarioFORM);
	}
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> removerUsuario(@PathVariable Integer id) {
		return usuarioService.removerUsuario(id);
	}
}
