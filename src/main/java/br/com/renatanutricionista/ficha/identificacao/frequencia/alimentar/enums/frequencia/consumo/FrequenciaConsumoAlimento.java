package br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.enums.frequencia.consumo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonSerialize
public enum FrequenciaConsumoAlimento {

	@JsonProperty(value = "N") NUNCA("N", "Nunca"),
	@JsonProperty(value = "D") DIA("D", "Dia"),
	@JsonProperty(value = "S") SEMANA("S", "Semana"),
	@JsonProperty(value = "M") MES("M", "Mês"),
	@JsonProperty(value = "A") ANO("A", "Ano");
	
	
	private String codigo;
	private String descricao;
}
