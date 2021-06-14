package br.com.renatanutricionista.seguranca.usuario.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.renatanutricionista.seguranca.usuario.model.Usuario;
import br.com.renatanutricionista.utils.ConversaoUtils;
import br.com.renatanutricionista.utils.FormatacaoUtils;
import lombok.Getter;


@Getter
public class UsuarioDTO implements Comparable<UsuarioDTO> {

	private Integer id;
	private String nome;
	private String email;
	private String dataCadastro;
	
	
	public UsuarioDTO(Usuario usuario) {
		id = usuario.getId();
		nome = usuario.getNome();
		email = usuario.getEmail();
		dataCadastro = ConversaoUtils.converterLocalDateParaString(usuario.getDataCadastro());
	}
	
	
	@Override
	public int compareTo(UsuarioDTO other) {
		return FormatacaoUtils.COLLATOR.compare(nome, other.getNome());
	}
	
	
	public static List<UsuarioDTO> converterParaUsuarioDTOEmOrdemAlfabetica(List<Usuario> usuarios) {
		return usuarios.stream().map(UsuarioDTO::new).sorted().collect(Collectors.toList());
	}
}
