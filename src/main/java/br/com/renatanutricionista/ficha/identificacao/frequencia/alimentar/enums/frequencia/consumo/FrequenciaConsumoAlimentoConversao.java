package br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.enums.frequencia.consumo;

import java.util.Objects;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


@Converter(autoApply = true)
public class FrequenciaConsumoAlimentoConversao implements AttributeConverter<FrequenciaConsumoAlimento, String> {

	@Override
	public String convertToDatabaseColumn(FrequenciaConsumoAlimento frequenciaConsumoAlimento) {
		return frequenciaConsumoAlimento.getCodigo();
	}

	@Override
	public FrequenciaConsumoAlimento convertToEntityAttribute(String dbData) {
		if (Objects.isNull(dbData)) 
			throw new NullPointerException("O código da Frequência de Consumo do Alimento não pode estar nulo!");
		
		for (FrequenciaConsumoAlimento frequenciaConsumoAlimento : FrequenciaConsumoAlimento.values()) 
			if (dbData.equals(frequenciaConsumoAlimento.getCodigo())) 
				return frequenciaConsumoAlimento;

		throw new IllegalArgumentException("O código da Frequência de Consumo do Alimento é inválido!");
	}

}
