package br.com.renatanutricionista.ficha.identificacao.atividade.fisica.enums;

import java.util.Objects;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


@Converter
public class FrequenciaAtividadeFisicaConversao implements AttributeConverter<FrequenciaAtividadeFisica, String> {

	@Override
	public String convertToDatabaseColumn(FrequenciaAtividadeFisica frequenciaAtividadeFisica) {
		return frequenciaAtividadeFisica.getCodigo();
	}

	@Override
	public FrequenciaAtividadeFisica convertToEntityAttribute(String dbData) {
		if (Objects.isNull(dbData)) 
			throw new NullPointerException("O código da Frequência da Atividade Física não pode estar nulo!");
		
		for (FrequenciaAtividadeFisica frequencia : FrequenciaAtividadeFisica.values()) 
			if (dbData.equals(frequencia.getCodigo())) 
				return frequencia;

		throw new IllegalArgumentException("O código da Frequência da Atividade Física é inválido!");
	}
}
