package br.com.renatanutricionista.tabelas.parametro.valor.normalidade.enums;

import java.util.Objects;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


@Converter(autoApply = true)
public class ComposicaoCorporalConversao implements AttributeConverter<ComposicaoCorporal, String> {

	@Override
	public String convertToDatabaseColumn(ComposicaoCorporal composicaoCorporal) {
		return composicaoCorporal.getCodigo();
	}

	@Override
	public ComposicaoCorporal convertToEntityAttribute(String dbData) {
		if (Objects.isNull(dbData)) 
			throw new NullPointerException("O código da Composição Corporal não pode estar nulo!");
		
		for (ComposicaoCorporal composicaoCorporal : ComposicaoCorporal.values()) 
			if (dbData.equals(composicaoCorporal.getCodigo())) 
				return composicaoCorporal;

		throw new IllegalArgumentException("O código da Composição Corporal é inválido!");
	}
}
