package br.com.renatanutricionista.ficha.identificacao.historico.social.enums.mulher.menstruacao.normal;

import java.util.Objects;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


@Converter(autoApply = true)
public class MenstruacaoNormalConversao implements AttributeConverter<MenstruacaoNormal, String> {

	@Override
	public String convertToDatabaseColumn(MenstruacaoNormal menstruacaoNormal) {
		return menstruacaoNormal.getCodigo();
	}

	@Override
	public MenstruacaoNormal convertToEntityAttribute(String dbData) {
		if (Objects.isNull(dbData)) 
			return null;
		
		for (MenstruacaoNormal menstruacaoNormal : MenstruacaoNormal.values()) 
			if (dbData.equals(menstruacaoNormal.getCodigo())) 
				return menstruacaoNormal;

		throw new IllegalArgumentException("O código da Menstruação Normal é inválido!");
	}
}

