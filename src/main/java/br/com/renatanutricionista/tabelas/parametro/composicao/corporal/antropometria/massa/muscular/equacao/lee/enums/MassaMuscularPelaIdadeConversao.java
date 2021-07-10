package br.com.renatanutricionista.tabelas.parametro.composicao.corporal.antropometria.massa.muscular.equacao.lee.enums;

import java.util.Objects;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


@Converter(autoApply = true)
public class MassaMuscularPelaIdadeConversao implements AttributeConverter<MassaMuscularPelaIdade, String> {

	@Override
	public String convertToDatabaseColumn(MassaMuscularPelaIdade classificacaoMassaMuscularPelaIdade) {
		return classificacaoMassaMuscularPelaIdade.getCodigo();
	}

	@Override
	public MassaMuscularPelaIdade convertToEntityAttribute(String dbData) {
		if (Objects.isNull(dbData)) 
			throw new NullPointerException("O código de Classificação pela Idade não pode estar nulo!");
		
		for (MassaMuscularPelaIdade classificacao : MassaMuscularPelaIdade.values()) 
			if (dbData.equals(classificacao.getCodigo())) 
				return classificacao;

		throw new IllegalArgumentException("O código de Classificação pela Idade é inválido!");
	}
}
