package br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum FrequenciaConsumoAlimento {

	NUNCA("N", "Nunca"),
	DIA("D", "Dia"),
	SEMANA("S", "Semana"),
	MES("M", "Mês"),
	ANO("A", "Ano");
	
	
	private String codigo;
	private String descricao;
}
