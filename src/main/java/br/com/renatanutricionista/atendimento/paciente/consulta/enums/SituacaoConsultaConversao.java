package br.com.renatanutricionista.atendimento.paciente.consulta.enums;

import java.util.Objects;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


@Converter(autoApply = true)
public class SituacaoConsultaConversao implements AttributeConverter<SituacaoConsulta, String> {

	@Override
	public String convertToDatabaseColumn(SituacaoConsulta situacaoConsulta) {
		return situacaoConsulta.getCodigo();
	}

	@Override
	public SituacaoConsulta convertToEntityAttribute(String dbData) {
		if (Objects.isNull(dbData)) 
			throw new NullPointerException("O código da Situação da Consulta não pode estar nulo!");
		
		for (SituacaoConsulta situacaoConsulta : SituacaoConsulta.values()) 
			if (dbData.equals(situacaoConsulta.getCodigo())) 
				return situacaoConsulta;

		throw new IllegalArgumentException("O código da Situação da Consulta é inválido!");
	}
}
