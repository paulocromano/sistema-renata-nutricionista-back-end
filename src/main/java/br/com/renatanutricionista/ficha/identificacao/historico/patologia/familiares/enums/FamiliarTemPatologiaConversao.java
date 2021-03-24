package br.com.renatanutricionista.ficha.identificacao.historico.patologia.familiares.enums;

import java.util.Objects;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


@Converter(autoApply = true)
public class FamiliarTemPatologiaConversao implements AttributeConverter<FamiliarTemPatologia, String> {

	@Override
	public String convertToDatabaseColumn(FamiliarTemPatologia familiarTemPatologia) {
		return familiarTemPatologia.getCodigo();
	}

	@Override
	public FamiliarTemPatologia convertToEntityAttribute(String dbData) {
		if (Objects.isNull(dbData)) 
			throw new NullPointerException("O código para Verificar se o(a) Familiar tem Patologia não estar nulo!");
		
		if (dbData.equals(FamiliarTemPatologia.SIM.getCodigo())) 
			return FamiliarTemPatologia.SIM;
		
		else if (dbData.equals(FamiliarTemPatologia.NAO.getCodigo()))
			return FamiliarTemPatologia.NAO;

		throw new IllegalArgumentException("O código para Verificar se o(a) Familiar tem Patologia é inválido!");
	}
}
