package br.com.renatanutricionista.ficha.identificacao.historico.social.enums.diurese.coloracao;

import java.util.Objects;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


@Converter(autoApply = true)
public class ColoracaoDiureseConversao implements AttributeConverter<ColoracaoDiurese, String> {

	@Override
	public String convertToDatabaseColumn(ColoracaoDiurese coloracaoDiurese) {
		return coloracaoDiurese.getCodigo();
	}

	@Override
	public ColoracaoDiurese convertToEntityAttribute(String dbData) {
		if (Objects.isNull(dbData)) 
			throw new NullPointerException("O código da Coloração da Diurese não pode estar nulo!");
		
		for (ColoracaoDiurese coloracaoDiurese : ColoracaoDiurese.values()) 
			if (dbData.equals(coloracaoDiurese.getCodigo())) 
				return coloracaoDiurese;

		throw new IllegalArgumentException("O código da Coloração da Diurese é inválido!");
	}
}
