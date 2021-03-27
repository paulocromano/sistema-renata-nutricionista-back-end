package br.com.renatanutricionista.ficha.identificacao.historico.patologia.familiares.enums;

import java.util.Objects;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


@Converter(autoApply = true)
public class PatologiaFamiliaresConversao implements AttributeConverter<PatologiaFamiliares, String> {

	@Override
	public String convertToDatabaseColumn(PatologiaFamiliares patologiaFamiliares) {
		return patologiaFamiliares.getCodigo();
	}

	@Override
	public PatologiaFamiliares convertToEntityAttribute(String dbData) {
		if (Objects.isNull(dbData)) 
			throw new NullPointerException("O código da Patologia dos Familiares não estar nulo!");
		
		for (PatologiaFamiliares patologiaFamiliares : PatologiaFamiliares.values()) 
			if (dbData.equals(patologiaFamiliares.getCodigo())) 
				return patologiaFamiliares;

		throw new IllegalArgumentException("O código da Patologia dos Familiares é inválido!");
	}
}
