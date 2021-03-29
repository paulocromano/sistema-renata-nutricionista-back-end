package br.com.renatanutricionista.atendimento.paciente.consulta.enums;

import java.util.Objects;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


@Converter
public class FormaPagamentoConversao implements AttributeConverter<FormaPagamento, String> {

	@Override
	public String convertToDatabaseColumn(FormaPagamento formaPagamento) {
		return formaPagamento.getCodigo();
	}

	@Override
	public FormaPagamento convertToEntityAttribute(String dbData) {
		if (Objects.isNull(dbData)) 
			return null;
		
		for (FormaPagamento formaPagamento : FormaPagamento.values()) 
			if (dbData.equals(formaPagamento.getCodigo())) 
				return formaPagamento;

		throw new IllegalArgumentException("O código da Forma de Pagamento é inválido!");
	}
}
