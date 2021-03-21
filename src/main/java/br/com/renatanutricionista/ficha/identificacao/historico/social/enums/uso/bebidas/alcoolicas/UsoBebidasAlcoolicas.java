package br.com.renatanutricionista.ficha.identificacao.historico.social.enums.uso.bebidas.alcoolicas;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum UsoBebidasAlcoolicas {

	DIARIAMENTE("D", "Diariamente"),
	FREQUENTEMENTE("F", "Frequentemente"),
	AS_VEZES("A", "As vezes"),
	RARAMENTE("R", "Raramente"),
	NUNCA("N", "Nunca");
	
	
	private String codigo;
	private String descricao;
}
