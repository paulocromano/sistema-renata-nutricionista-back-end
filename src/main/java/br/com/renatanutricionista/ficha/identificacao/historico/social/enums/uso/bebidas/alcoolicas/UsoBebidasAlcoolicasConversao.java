package br.com.renatanutricionista.ficha.identificacao.historico.social.enums.uso.bebidas.alcoolicas;

import java.util.Objects;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


@Converter(autoApply = true)
public class UsoBebidasAlcoolicasConversao implements AttributeConverter<UsoBebidasAlcoolicas, String> {

	@Override
	public String convertToDatabaseColumn(UsoBebidasAlcoolicas usoBebidasAlcoolicas) {
		return usoBebidasAlcoolicas.getCodigo();
	}

	@Override
	public UsoBebidasAlcoolicas convertToEntityAttribute(String dbData) {
		if (Objects.isNull(dbData)) 
			throw new NullPointerException("O código do Uso de Bebidas Alcoólicas não pode estar nulo!");
		
		for (UsoBebidasAlcoolicas usoBebidasAlcoolicas : UsoBebidasAlcoolicas.values()) 
			if (dbData.equals(usoBebidasAlcoolicas.getCodigo())) 
				return usoBebidasAlcoolicas;

		throw new IllegalArgumentException("O código do Uso de Bebidas Alcoólicas é inválido!");
	}
}
