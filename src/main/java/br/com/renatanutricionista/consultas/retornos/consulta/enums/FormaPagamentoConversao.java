package br.com.renatanutricionista.consultas.retornos.consulta.enums;

import java.util.Objects;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


@Converter(autoApply = true)
public class FormaPagamentoConversao implements AttributeConverter<FormaPagamento, String> {

	@Override
	public String convertToDatabaseColumn(FormaPagamento formaPagamento) {
		return formaPagamento.getCodigo();
	}

	@Override
	public FormaPagamento convertToEntityAttribute(String dbData) {
		if (Objects.isNull(dbData)) 
			throw new NullPointerException("O código da Forma de Pagamento não pode estar nulo!");
		
		for (FormaPagamento formaPagamento : FormaPagamento.values()) 
			if (dbData.equals(formaPagamento.getCodigo())) 
				return formaPagamento;

		throw new IllegalArgumentException("O código da Forma de Pagamento é inválido!");
	}
}
