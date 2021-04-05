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
		if (Objects.isNull(codigoConsumoTipoBebida))
			return null;

		for (ConsumoTipoBebida consumoTipoBebida : ConsumoTipoBebida.values()) 
			if (codigoConsumoTipoBebida.equals(consumoTipoBebida.codigo))
				return consumoTipoBebida.descricao;
		
		throw new IllegalArgumentException("O código de Consumo do Tipo de Bebida é inválido!");
	}
	
	
	public static String validarCodigo(String codigoConsumoTipoBebida) {
		String[] codigos = codigoConsumoTipoBebida.split(";");
		
		if (codigos.length > 1)
			throw new IllegalArgumentException("Só é permitido escolher uma opção!");
		
		if (!(codigos[0].equals(NORMAL.codigo) || codigos[0].equals(DIET_LIGHT_ZERO.codigo) 
				|| codigos[0].equals(QUALQUER_UM.codigo)))
			
			throw new IllegalArgumentException("O Código do Consumo do Tipo de Bebida é inválido!");
		
		return codigos[0];
	}
}
