package br.com.renatanutricionista.atendimento.paciente.utils;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum TipoAtendimento {

	CONSULTA("Consulta"),
	RETORNO_CONSULTA("Retorno");
	
	
	private String descricao;
}
