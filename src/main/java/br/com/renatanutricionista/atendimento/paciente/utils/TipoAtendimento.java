package br.com.renatanutricionista.atendimento.paciente.utils;

import java.util.Objects;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum TipoAtendimento {

	CONSULTA("Consulta"),
	RETORNO_CONSULTA("Retorno");
	
	
	private String descricao;
	
	
	public static TipoAtendimento converterParaEnum(Integer codigo) {
		if (Objects.isNull(codigo))
			throw new NullPointerException("O código do Tipo de Atendimento não pode ser nulo!");
		
		if (codigo > TipoAtendimento.values().length - 1) 
			throw new IllegalArgumentException("O código do Tipo de Atendimento é inválido!");
		
		return TipoAtendimento.values()[codigo];
	}
}
