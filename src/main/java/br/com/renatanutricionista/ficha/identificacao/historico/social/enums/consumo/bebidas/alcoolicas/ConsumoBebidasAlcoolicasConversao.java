package br.com.renatanutricionista.ficha.identificacao.historico.social.enums.consumo.bebidas.alcoolicas;

import java.util.Objects;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


@Converter(autoApply = true)
public class ConsumoBebidasAlcoolicasConversao implements AttributeConverter<ConsumoBebidasAlcoolicas, String> {

	@Override
	public String convertToDatabaseColumn(ConsumoBebidasAlcoolicas consumoBebidasAlcoolicas) {
		return consumoBebidasAlcoolicas.getCodigo();
	}

	@Override
	public ConsumoBebidasAlcoolicas convertToEntityAttribute(String dbData) {
		if (Objects.isNull(dbData)) 
			throw new NullPointerException("O código de Consumo de Bebidas Alcoólicas não pode estar nulo!");
		
		for (ConsumoBebidasAlcoolicas consumoBebidasAlcoolicas : ConsumoBebidasAlcoolicas.values()) 
			if (dbData.equals(consumoBebidasAlcoolicas.getCodigo())) 
				return consumoBebidasAlcoolicas;

		throw new IllegalArgumentException("O código de Consumo de Bebidas Alcoólicas é inválido!");
	}
}
