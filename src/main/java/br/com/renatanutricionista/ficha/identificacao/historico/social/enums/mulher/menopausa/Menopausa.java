package br.com.renatanutricionista.ficha.identificacao.historico.social.enums.mulher.menopausa;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Menopausa {

	SIM("S", "Sim"),
	NAO("N", "Não");
	
	
	private String codigo;
	private String descricao;
}
