package br.com.renatanutricionista.ficha.identificacao.historico.social.enums.consistencia.fezes;

import java.util.Objects;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


@Converter(autoApply = true)
public class ConsistenciaFezesConversao implements AttributeConverter<ConsistenciaFezes, String> {

	@Override
	public String convertToDatabaseColumn(ConsistenciaFezes consistenciaFezes) {
		return consistenciaFezes.getCodigo();
	}

	@Override
	public ConsistenciaFezes convertToEntityAttribute(String dbData) {
		if (Objects.isNull(dbData)) 
			throw new NullPointerException("O código da Consistência das Fezes não pode estar nulo!");
		
		for (ConsistenciaFezes consistenciaFezes : ConsistenciaFezes.values()) 
			if (dbData.equals(consistenciaFezes.getCodigo())) 
				return consistenciaFezes;

		throw new IllegalArgumentException("O código da Consistência das Fezes é inválido!");
	}
}

