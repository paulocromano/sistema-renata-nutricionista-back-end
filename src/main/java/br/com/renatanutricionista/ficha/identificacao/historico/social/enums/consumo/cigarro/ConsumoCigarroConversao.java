package br.com.renatanutricionista.ficha.identificacao.historico.social.enums.consumo.cigarro;

import java.util.Objects;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


@Converter(autoApply = true)
public class ConsumoCigarroConversao implements AttributeConverter<ConsumoCigarro, String> {

	@Override
	public String convertToDatabaseColumn(ConsumoCigarro consumoCigarro) {
		return consumoCigarro.getCodigo();
	}

	@Override
	public ConsumoCigarro convertToEntityAttribute(String dbData) {
		if (Objects.isNull(dbData)) 
			throw new NullPointerException("O código de Consumo de Cigarro não pode estar nulo!");
		
		for (ConsumoCigarro consumoCigarro : ConsumoCigarro.values()) 
			if (dbData.equals(consumoCigarro.getCodigo())) 
				return consumoCigarro;

		throw new IllegalArgumentException("O código de Consumo de Cigarro é inválido!");
	}
}
