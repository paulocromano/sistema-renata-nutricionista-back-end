package br.com.renatanutricionista.calendario.atendimento.paciente.enums;

import java.util.Objects;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


@Converter(autoApply = true)
public class PeriodoDisponivelConversao implements AttributeConverter<PeriodoDisponivel, String> {

	@Override
	public String convertToDatabaseColumn(PeriodoDisponivel periodoDisponivel) {
		return periodoDisponivel.getCodigo();
	}

	@Override
	public PeriodoDisponivel convertToEntityAttribute(String dbData) {
		if (Objects.isNull(dbData)) 
			throw new NullPointerException("O código do Período Disponível não pode estar nulo!");
		
		for (PeriodoDisponivel periodoDisponivel : PeriodoDisponivel.values()) 
			if (dbData.equals(periodoDisponivel.getCodigo())) 
				return periodoDisponivel;

		throw new IllegalArgumentException("O código do Período Disponível é inválido!");
	}
}
