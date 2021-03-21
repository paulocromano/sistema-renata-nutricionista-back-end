package br.com.renatanutricionista.ficha.identificacao.historico.social.enums.uso.cigarro;

import java.util.Objects;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


@Converter(autoApply = true)
public class UsoCigarroConversao implements AttributeConverter<UsoCigarro, String> {

	@Override
	public String convertToDatabaseColumn(UsoCigarro usoCigarro) {
		return usoCigarro.getCodigo();
	}

	@Override
	public UsoCigarro convertToEntityAttribute(String dbData) {
		if (Objects.isNull(dbData)) 
			throw new NullPointerException("O código do Uso de Cigarro não pode estar nulo!");
		
		for (UsoCigarro usoCigarro : UsoCigarro.values()) 
			if (dbData.equals(usoCigarro.getCodigo())) 
				return usoCigarro;

		throw new IllegalArgumentException("O código do Uso de Cigarro é inválido!");
	}
}
