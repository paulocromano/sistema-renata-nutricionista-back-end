package br.com.renatanutricionista.ficha.identificacao.historico.social.enums.consumo.bebidas.alcoolicas;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ConsumoBebidasAlcoolicas {

	DIARIAMENTE("D", "Diariamente"),
	FREQUENTEMENTE("F", "Frequentemente"),
	AS_VEZES("A", "As vezes"),
	RARAMENTE("R", "Raramente"),
	BEBIA("B", "Bebia"),
	NUNCA("N", "Nunca");
	
	
	private String codigo;
	private String descricao;
}
