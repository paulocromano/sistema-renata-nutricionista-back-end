package br.com.renatanutricionista.ficha.identificacao.historico.social.enums.mulher.menopausa;

import java.util.Objects;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


@Converter(autoApply = true)
public class MenopausaConversao implements AttributeConverter<Menopausa, String> {

	@Override
	public String convertToDatabaseColumn(Menopausa menopausa) {
		return menopausa.getCodigo();
	}

	@Override
	public Menopausa convertToEntityAttribute(String dbData) {
		if (Objects.isNull(dbData)) 
			return null;
		
		for (Menopausa menopausa : Menopausa.values()) 
			if (dbData.equals(menopausa.getCodigo())) 
				return menopausa;

		throw new IllegalArgumentException("O código da Menopausa é inválido!");
	}
}
