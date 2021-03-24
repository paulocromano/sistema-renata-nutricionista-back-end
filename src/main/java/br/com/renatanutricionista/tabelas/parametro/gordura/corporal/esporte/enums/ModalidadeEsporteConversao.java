package br.com.renatanutricionista.tabelas.parametro.gordura.corporal.esporte.enums;

import java.util.Objects;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ModalidadeEsporteConversao implements AttributeConverter<ModalidadeEsporte, String> {

	@Override
	public String convertToDatabaseColumn(ModalidadeEsporte modalidadeEsporte) {
		return modalidadeEsporte.getCodigo();
	}

	@Override
	public ModalidadeEsporte convertToEntityAttribute(String dbData) {
		if (Objects.isNull(dbData)) 
			throw new NullPointerException("O código da Modalidade do Esporte não pode estar nulo!");
		
		for (ModalidadeEsporte modalidadeEsporte : ModalidadeEsporte.values()) 
			if (dbData.equals(modalidadeEsporte.getCodigo())) 
				return modalidadeEsporte;

		throw new IllegalArgumentException("O código da Modalidade do Esporte é inválido!");
	}
}
