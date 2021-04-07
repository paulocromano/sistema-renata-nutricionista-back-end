package br.com.renatanutricionista.atendimento.paciente.registro.dieta.enums;

import java.util.Objects;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


@Converter(autoApply = true)
public class TipoRegistroDietaConversao implements AttributeConverter<TipoRegistroDieta, String> {

	@Override
	public String convertToDatabaseColumn(TipoRegistroDieta tipoRegistroDieta) {
		return tipoRegistroDieta.getCodigo();
	}

	@Override
	public TipoRegistroDieta convertToEntityAttribute(String dbData) {
		if (Objects.isNull(dbData)) 
			throw new NullPointerException("O código do Tipo de Registro de Dieta não pode estar nulo!");
		
		if (dbData.equals(TipoRegistroDieta.HABITUAL.getCodigo())) {
			return TipoRegistroDieta.HABITUAL;
		}

		else if (dbData.equals(TipoRegistroDieta.PERIODO_24H.getCodigo())) {
			return TipoRegistroDieta.PERIODO_24H;
		}

		throw new IllegalArgumentException("O código do Tipo de Registro de Dieta é inválido!");
	}
}
