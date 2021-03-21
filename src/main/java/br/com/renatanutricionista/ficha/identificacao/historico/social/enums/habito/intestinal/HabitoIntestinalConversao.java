package br.com.renatanutricionista.ficha.identificacao.historico.social.enums.habito.intestinal;

import java.util.Objects;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


@Converter(autoApply = true)
public class HabitoIntestinalConversao implements AttributeConverter<HabitoIntestinal, String> {

	@Override
	public String convertToDatabaseColumn(HabitoIntestinal habitoIntestinal) {
		return habitoIntestinal.getCodigo();
	}

	@Override
	public HabitoIntestinal convertToEntityAttribute(String dbData) {
		if (Objects.isNull(dbData)) 
			throw new NullPointerException("O código do Hábito Intestinal não pode estar nulo!");
		
		for (HabitoIntestinal habitoIntestinal : HabitoIntestinal.values()) 
			if (dbData.equals(habitoIntestinal.getCodigo())) 
				return habitoIntestinal;

		throw new IllegalArgumentException("O código do Hábito Intestinal é inválido!");
	}
}