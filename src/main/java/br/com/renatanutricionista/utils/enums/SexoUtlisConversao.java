package br.com.renatanutricionista.utils.enums;

import java.util.Objects;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


@Converter(autoApply = true)
public class SexoUtlisConversao implements AttributeConverter<SexoUtils, String> {

	@Override
	public String convertToDatabaseColumn(SexoUtils sexoUtils) {
		return sexoUtils.getCodigo();
	}

	@Override
	public SexoUtils convertToEntityAttribute(String dbData) {
		if (Objects.isNull(dbData)) 
			throw new NullPointerException("O código do Sexo não pode estar nulo!");
		
		for (SexoUtils sexoUtils : SexoUtils.values()) 
			if (dbData.equals(sexoUtils.getCodigo())) 
				return sexoUtils;

		throw new IllegalArgumentException("O código do Sexo é inválido!");
	}
}
