package br.com.renatanutricionista.seguranca.usuario.resource;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.renatanutricionista.seguranca.usuario.form.UsuarioFORM;
import br.com.renatanutricionista.seguranca.usuario.service.UsuarioService;


@RestController
@RequestMapping("/usuario")
public class UsuarioResource {

	@Autowired
	private UsuarioService usuarioService;
	
	
	@PostMapping
	@Transactional
	public ResponseEntity<Void> cadastrarUsuario(@RequestBody @Valid UsuarioFORM usuario) {
		return usuarioService.cadastrarUsuario(usuario);
	}
}
