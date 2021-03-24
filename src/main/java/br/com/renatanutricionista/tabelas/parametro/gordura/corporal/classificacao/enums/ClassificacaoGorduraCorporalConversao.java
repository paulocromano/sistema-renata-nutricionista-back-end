package br.com.renatanutricionista.tabelas.parametro.gordura.corporal.classificacao.enums;

import java.util.Objects;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


@Converter(autoApply = true)
public class ClassificacaoGorduraCorporalConversao implements AttributeConverter<ClassificacaoGorduraCorporal, String> {

	@Override
	public String convertToDatabaseColumn(ClassificacaoGorduraCorporal classificacaoGorduraCorporal) {
		return classificacaoGorduraCorporal.getCodigo();
	}

	@Override
	public ClassificacaoGorduraCorporal convertToEntityAttribute(String dbData) {
		if (Objects.isNull(dbData)) 
			throw new NullPointerException("O código da Etnia não pode estar nulo!");
		
		for (ClassificacaoGorduraCorporal classificacaoGorduraCorporal : ClassificacaoGorduraCorporal.values()) 
			if (dbData.equals(classificacaoGorduraCorporal.getCodigo())) 
				return classificacaoGorduraCorporal;

		throw new IllegalArgumentException("O código da Etnia é inválido!");
	}
}
