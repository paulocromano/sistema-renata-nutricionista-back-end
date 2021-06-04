package br.com.renatanutricionista.utils.enums.resposta;

import java.util.Objects;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


@Converter(autoApply = true)
public class RespostaConversao implements AttributeConverter<RespostaUtils, String> {

	@Override
	public String convertToDatabaseColumn(RespostaUtils resposta) {
		return Objects.nonNull(resposta) ? resposta.getCodigo() : null;
	}

	
	@Override
	public RespostaUtils convertToEntityAttribute(String dbData) {
		if (Objects.isNull(dbData))
			return null;
		
		if (dbData.equals(RespostaUtils.SIM.getCodigo()))
			return RespostaUtils.SIM;
		
		else if (dbData.equals(RespostaUtils.NAO.getCodigo()))
			return RespostaUtils.NAO;
		
		throw new IllegalArgumentException("O Código da Resposta é inválido!");
	}
}
