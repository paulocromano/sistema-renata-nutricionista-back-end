package br.com.renatanutricionista.ficha.identificacao.historico.social.enums.estado.civil;

import java.util.Objects;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class EstadoCivilConversao implements AttributeConverter<EstadoCivil, String> {

	@Override
	public String convertToDatabaseColumn(EstadoCivil estadoCivil) {
		return estadoCivil.getCodigo();
	}

	@Override
	public EstadoCivil convertToEntityAttribute(String dbData) {
		if (Objects.isNull(dbData))
			throw new NullPointerException("O código do Estado Civil não pode estar nulo!");

		for (EstadoCivil estadoCivil : EstadoCivil.values())
			if (dbData.equals(estadoCivil.getCodigo()))
				return estadoCivil;

		throw new IllegalArgumentException("O código do Estado Civil é inválido!");
	}
}
