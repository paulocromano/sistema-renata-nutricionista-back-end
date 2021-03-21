package br.com.renatanutricionista.ficha.identificacao.historico.social.enums.diurese.frequencia;

import java.util.Objects;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


@Converter(autoApply = true)
public class FrequenciaDiureseConversao implements AttributeConverter<FrequenciaDiurese, String> {

	@Override
	public String convertToDatabaseColumn(FrequenciaDiurese frequenciaDiurese) {
		return frequenciaDiurese.getCodigo();
	}

	@Override
	public FrequenciaDiurese convertToEntityAttribute(String dbData) {
		if (Objects.isNull(dbData)) 
			throw new NullPointerException("O código da Frequência da Diurese não pode estar nulo!");
		
		for (FrequenciaDiurese frequenciaDiurese : FrequenciaDiurese.values()) 
			if (dbData.equals(frequenciaDiurese.getCodigo())) 
				return frequenciaDiurese;

		throw new IllegalArgumentException("O código da Frequência da Diurese é inválido!");
	}
}


