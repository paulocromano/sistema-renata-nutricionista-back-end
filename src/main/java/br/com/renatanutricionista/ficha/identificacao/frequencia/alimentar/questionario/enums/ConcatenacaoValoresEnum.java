package br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.enums;

import java.util.Objects;

import br.com.renatanutricionista.utils.conversao.enums.GettersEnum;


public final class ConcatenacaoValoresEnum {
	

	public static final <T extends Enum<T> & GettersEnum<T>> String concatenarValoresEnum(String codigosEnum, T[] valoresEnum) {
		if (Objects.isNull(codigosEnum) || codigosEnum.trim().isEmpty())
			return null;
		
		StringBuilder builder = new StringBuilder();
		
		for (String codigoConsumo : codigosEnum.split(";")) {
			for (T consumo : valoresEnum) {
				if (codigoConsumo.equals(consumo.getCodigo())) {
					builder.append(consumo.getDescricao());
					builder.append(", ");
					
					break;
				}
			}
		}
		
		return builder.substring(0, builder.length() - 2).toString();
	}
}


