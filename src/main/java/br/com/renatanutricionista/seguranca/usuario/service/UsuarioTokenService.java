package br.com.renatanutricionista.seguranca.usuario.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import br.com.renatanutricionista.exception.custom.ForbiddenException;
import br.com.renatanutricionista.seguranca.usuario.enums.Perfil;
import br.com.renatanutricionista.seguranca.usuario.model.UsuarioToken;


@Service
public class UsuarioTokenService {

	
	public void usuarioTemPermissaoDeAdmin(HttpServletRequest request) {
		UsuarioToken usuarioToken = new UsuarioToken(request);
		
		if (!usuarioToken.temPermissao(Perfil.ADMIN))
			throw new ForbiddenException("Usuário não tem permissão para fazer a requisição!");
	}
}
