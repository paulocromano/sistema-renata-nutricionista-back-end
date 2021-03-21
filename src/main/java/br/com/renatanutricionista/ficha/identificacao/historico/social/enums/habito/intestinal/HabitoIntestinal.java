package br.com.renatanutricionista.ficha.identificacao.historico.social.enums.habito.intestinal;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum HabitoIntestinal {

	DIARIO("A", "Diário"),
	ATE_3_DIAS("B", "Até 3 dias"),
	MAIS_3_DIAS("C", "Mais que 3 dias");
	
	
	private String codigo;
	private String descricao;
}
