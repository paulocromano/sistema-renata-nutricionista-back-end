package br.com.renatanutricionista.tabelas.parametro.atividade.fisica.enums;

import java.util.Objects;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


@Converter(autoApply = true)
public class TipoPessoaAtividadeFisicaConversao implements AttributeConverter<TipoPessoaAtividadeFisica, String> {

	@Override
	public String convertToDatabaseColumn(TipoPessoaAtividadeFisica tipoPessoaAtividadeFisica) {
		return tipoPessoaAtividadeFisica.getCodigo();
	}

	@Override
	public TipoPessoaAtividadeFisica convertToEntityAttribute(String dbData) {
		if (Objects.isNull(dbData)) 
			throw new NullPointerException("O código do Tipo de Pessoa da Atividade Física não pode estar nulo!");
		
		for (TipoPessoaAtividadeFisica tipoPessoa : TipoPessoaAtividadeFisica.values()) 
			if (dbData.equals(tipoPessoa.getCodigo())) 
				return tipoPessoa;

		throw new IllegalArgumentException("O código do Tipo de Pessoa da Atividade Física é inválido!");
	}
}
