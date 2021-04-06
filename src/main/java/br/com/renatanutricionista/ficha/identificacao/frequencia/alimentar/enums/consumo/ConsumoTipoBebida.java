package br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.enums.consumo;

import java.util.Objects;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ConsumoTipoBebida {

	NORMAL("N", "Normal"),
	DIET_LIGHT_ZERO("D", "Diet. Light/zero"),
	QUALQUER_UM("Q", "Qualquer um");
	
	
	private String codigo;
	private String descricao;
	
	
	public static String converterParaDescricao(String codigoConsumoTipoBebida) {
		if (Objects.isNull(codigoConsumoTipoBebida) || codigoConsumoTipoBebida.trim().isEmpty())
			return null;

		for (ConsumoTipoBebida consumoTipoBebida : ConsumoTipoBebida.values()) 
			if (codigoConsumoTipoBebida.equals(consumoTipoBebida.codigo))
				return consumoTipoBebida.descricao;
		
		throw new IllegalArgumentException("O código de Consumo do Tipo de Bebida é inválido!");
	}
}
