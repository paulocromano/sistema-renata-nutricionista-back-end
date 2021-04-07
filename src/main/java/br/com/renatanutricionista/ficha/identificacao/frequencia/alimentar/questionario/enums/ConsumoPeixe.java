package br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.enums;

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
	
	
	public static String concatenarModosConsumoPeixe(String codigosPeixe) {
		if (Objects.isNull(codigosPeixe) || codigosPeixe.trim().isEmpty())
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
		
		return peixeBuilder.substring(0, peixeBuilder.length() - 2).toString();
	}
}
