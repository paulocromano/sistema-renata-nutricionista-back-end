package br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.enums;

import java.util.Objects;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ConsumoFrango {

	COM_PELE("0", "Com pele"),
	SEM_PELE("1", "Sem pele"),
	FRITA_IMERSAO_OLEO("2", "Frita com imersão no óleo"),
	GRELHADA("3", "Grelhada"),
	ASSADA("4", "Assada"),
	COZIDA("5", "Cozida");
	
	
	private String codigo;
	private String descricao;
	
	
	public static String concatenarModosConsumoFrango(String codigosFrango) {	
		if (Objects.isNull(codigosFrango) || codigosFrango.trim().isEmpty())
			return null;

		StringBuilder frangoBuilder = new StringBuilder();
		
		for (String codigoConsumoFrango : codigosFrango.split(";")) {
			for (ConsumoFrango consumoFrango : ConsumoFrango.values()) {
				if (codigoConsumoFrango.equals(consumoFrango.codigo)) {
					frangoBuilder.append(consumoFrango.descricao);
					frangoBuilder.append(", ");
					
					break;
				}
			}
		}
		
		return frangoBuilder.substring(0, frangoBuilder.length() - 2).toString();
	}
}
