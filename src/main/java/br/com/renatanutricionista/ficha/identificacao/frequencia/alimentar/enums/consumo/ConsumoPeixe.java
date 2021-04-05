package br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.enums.consumo;

import java.util.Objects;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ConsumoPeixe {

	FRITA_IMERSAO_OLEO("0", "Frita com imersão no óleo"),
	GRELHADA("1", "Grelhada"),
	ASSADA("2", "Assada"),
	COZIDA("3", "Cozida");
	
	
	private String codigo;
	private String descricao;
	
	
	public static String concatenarConsumoPeixe(String codigosPeixe) {
		if (Objects.isNull(codigosPeixe))
			return null;

		StringBuilder peixeBuilder = new StringBuilder();
		
		for (String codigoConsumoPeixe : codigosPeixe.split(";")) {
			for (ConsumoPeixe consumoPeixe : ConsumoPeixe.values()) {
				if (codigoConsumoPeixe.equals(consumoPeixe.codigo)) {
					peixeBuilder.append(consumoPeixe.descricao);
					peixeBuilder.append(", ");
					
					break;
				}
			}
		}
		
		return peixeBuilder.substring(0, peixeBuilder.length() - 3).toString();
	}
	
	
	public static ConsumoPeixe converterParaEnum(String codigoPeixe) {
		if (Objects.isNull(codigoPeixe))
			throw new NullPointerException("O Tipo de Consumo de Peixe não pode estar nulo!");
		
		for (ConsumoPeixe consumoPeixe : ConsumoPeixe.values()) 
			if (codigoPeixe.equals(consumoPeixe.codigo))
				return consumoPeixe;
		
		throw new IllegalArgumentException("O código de Consumo de Peixe é inválido!");
	}
}
