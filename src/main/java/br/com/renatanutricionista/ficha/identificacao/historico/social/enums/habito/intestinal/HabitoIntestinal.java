package br.com.renatanutricionista.ficha.identificacao.historico.social.enums.habito.intestinal;

import br.com.renatanutricionista.utils.conversao.enums.GettersEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum HabitoIntestinal implements GettersEnum<HabitoIntestinal> {

	DIARIO("A", "Diário"),
	ATE_3_DIAS("B", "Até 3 dias"),
	MAIS_3_DIAS("C", "Mais que 3 dias");
	
	
	private String codigo;
	private String descricao;
}
