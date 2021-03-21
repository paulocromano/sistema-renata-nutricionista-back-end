package br.com.renatanutricionista.ficha.identificacao.atividade.fisica.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum FrequenciaAtividadeFisica {

	DIARIA("D", "Diária"),
	FREQUENTE("F", "Frequente"),
	AS_VEZES("A", "Às vezes"),
	QUASE_NUNCA("Q", "Quase nunca"),
	NAO("N", "Não");
	
	
	private String codigo;
	private String descricao;
}
