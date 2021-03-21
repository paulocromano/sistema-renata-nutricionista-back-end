package br.com.renatanutricionista.ficha.identificacao.historico.social.enums.consistencia.fezes;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ConsistenciaFezes {

	NORMAL("N", "Normal"),
	AMOLECIDAS("A", "Amolecidas"),
	DURAS("D", "Duras");
	
	
	private String codigo;
	private String descricao;
}
