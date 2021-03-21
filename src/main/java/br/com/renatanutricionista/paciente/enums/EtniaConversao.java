package br.com.renatanutricionista.paciente.enums;

import java.util.Objects;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


@Converter(autoApply = true)
public class EtniaConversao implements AttributeConverter<Etnia, String> {

	@Override
	public String convertToDatabaseColumn(Etnia etnia) {
		return etnia.getCodigo();
	}

	@Override
	public Etnia convertToEntityAttribute(String dbData) {
		if (Objects.isNull(dbData)) 
			throw new NullPointerException("O código da Etnia não pode estar nulo!");
		
		for (Etnia etnia : Etnia.values()) 
			if (dbData.equals(etnia.getCodigo())) 
				return etnia;

		throw new IllegalArgumentException("O código da Etnia é inválido!");
	}
}
