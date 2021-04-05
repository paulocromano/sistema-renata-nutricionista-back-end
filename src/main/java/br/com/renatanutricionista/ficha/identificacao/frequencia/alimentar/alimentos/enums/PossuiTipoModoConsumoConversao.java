package br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.alimentos.enums;

import java.util.Objects;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


@Converter(autoApply = true)
public class PossuiTipoModoConsumoConversao implements AttributeConverter<PossuiTipoModoConsumo, String> {

	@Override
	public String convertToDatabaseColumn(PossuiTipoModoConsumo possuiTipoModoConsumo) {
		return possuiTipoModoConsumo.getCodigo();
	}

	@Override
	public PossuiTipoModoConsumo convertToEntityAttribute(String dbData) {
		if (Objects.isNull(dbData)) 
			throw new NullPointerException("O código para Alimentos que Possui um Tipo ou Modo de Consumo não pode estar nulo!");
		
		if (dbData.equals(PossuiTipoModoConsumo.SIM.getCodigo()))
			return PossuiTipoModoConsumo.SIM;
		
		else if (dbData.equals(PossuiTipoModoConsumo.NAO.getCodigo()))
			return PossuiTipoModoConsumo.NAO;

		throw new IllegalArgumentException("O código para Alimentos que Possui um Tipo ou Modo de Consumo é inválido!");
	}
}
