package br.com.renatanutricionista.consultas.retornos.retorno.enums;

import java.util.Objects;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


@Converter(autoApply = true)
public class SituacaoRetornoConversao implements AttributeConverter<SituacaoRetorno, String> {

	@Override
	public String convertToDatabaseColumn(SituacaoRetorno situacaoRetorno) {
		return situacaoRetorno.getCodigo();
	}

	@Override
	public SituacaoRetorno convertToEntityAttribute(String dbData) {
		if (Objects.isNull(dbData)) 
			throw new NullPointerException("O código da Situação do Retorno não pode estar nulo!");
		
		for (SituacaoRetorno situacaoRetorno : SituacaoRetorno.values()) 
			if (dbData.equals(situacaoRetorno.getCodigo())) 
				return situacaoRetorno;

		throw new IllegalArgumentException("O código da Situação do Retorno é inválido!");
	}
}
