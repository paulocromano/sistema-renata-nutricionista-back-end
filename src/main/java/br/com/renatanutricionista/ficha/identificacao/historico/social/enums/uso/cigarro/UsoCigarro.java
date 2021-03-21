package br.com.renatanutricionista.ficha.identificacao.historico.social.enums.uso.cigarro;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum UsoCigarro {

	FUMA("0", "Fuma"),
	FUMOU("1", "Fumou"),
	NUNCA_FUMOU("2", "Nunca fumou");
	
	
	private String codigo;
	private String descricao;
}
