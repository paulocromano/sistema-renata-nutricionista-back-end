package br.com.renatanutricionista.endereco.dto;

import br.com.renatanutricionista.endereco.model.Endereco;
import lombok.Getter;


@Getter
public class EnderecoDTO {

	private Long id;
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String cidade;
	private String cep;
	private String uf;
	
	
	public EnderecoDTO(Endereco endereco) {
		id = endereco.getId();
		logradouro = endereco.getLogradouro();
		numero = endereco.getNumero();
		complemento = endereco.getComplemento();
		bairro = endereco.getBairro();
		cidade = endereco.getCidade();
		cep = endereco.getCep();
		uf = endereco.getUf();
	}
}
