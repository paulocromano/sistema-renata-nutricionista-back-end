package br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.enums.consumo;

import java.util.Objects;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ConsumoCarneVermelha {

	COM_GORDURA("0", "Com gordura"),
	SEM_GORDURA("1", "Sem gordura"),
	FRITA_IMERSAO_OLEO("2", "Frita com imersão no óleo"),
	GRELHADA("3", "Grelhada"),
	ASSADA("4", "Assada"),
	COZIDA("5", "Cozida");
	
	
	private String codigo;
	private String descricao;
	
	
	public static String concatenarConsumoCarneVermelha(String codigosCarne) {
		if (Objects.isNull(codigosCarne))
			return null;

		StringBuilder carneBuilder = new StringBuilder();
		
		for (String codigoCarne : codigosCarne.split(";")) {
			for (ConsumoCarneVermelha consumoCarneVermelha : ConsumoCarneVermelha.values()) {
				if (codigoCarne.equals(consumoCarneVermelha.codigo)) {
					carneBuilder.append(consumoCarneVermelha.descricao);
					carneBuilder.append(", ");
					
					break;
				}
			}
		}
		
		return carneBuilder.substring(0, carneBuilder.length() - 3).toString();
	}
	
	
	public static ConsumoCarneVermelha converterParaEnum(String codigoConsumoCarneVermelha) {
		if (Objects.isNull(codigoConsumoCarneVermelha))
			return null;
		
		for (ConsumoCarneVermelha consumoCarneVermelha : ConsumoCarneVermelha.values()) 
			if (codigoConsumoCarneVermelha.equals(consumoCarneVermelha.codigo))
				return consumoCarneVermelha;
		
		throw new IllegalArgumentException("O código de Consumo de Carne Vermelha é inválido!");
	}
}
