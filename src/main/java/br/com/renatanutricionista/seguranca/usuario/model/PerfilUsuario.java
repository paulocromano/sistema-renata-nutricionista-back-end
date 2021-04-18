package br.com.renatanutricionista.seguranca.usuario.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;


@Entity
@Table(name = "perfil", catalog = "sistema_nutricionista_acesso")
@Getter
public class PerfilUsuario {

	@Id
	@Column(name = "usuario_id")
	private Integer usuarioId;
	
	private Integer perfil;
}
