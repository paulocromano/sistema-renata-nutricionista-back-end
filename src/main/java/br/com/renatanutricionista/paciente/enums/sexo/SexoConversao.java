package br.com.renatanutricionista.paciente.enums.sexo;

import java.util.Objects;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


@Converter(autoApply = true)
public class SexoConversao implements AttributeConverter<Sexo, String> {

	@Override
	public String convertToDatabaseColumn(Sexo sexo) {
		return sexo.getCodigo();
	}

	@Override
	public Sexo convertToEntityAttribute(String dbData) {
		if (Objects.isNull(dbData)) 
			throw new NullPointerException("O código do Sexo não pode estar nulo!");
		
		for (Sexo sexo : Sexo.values()) 
			if (dbData.equals(sexo.getCodigo())) 
				return sexo;

		throw new IllegalArgumentException("O código do Sexo é inválido!");
	}
}
