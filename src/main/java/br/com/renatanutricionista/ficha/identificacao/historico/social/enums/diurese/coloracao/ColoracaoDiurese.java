package br.com.renatanutricionista.ficha.identificacao.historico.social.enums.diurese.coloracao;

import br.com.renatanutricionista.utils.conversao.enums.GettersEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ColoracaoDiurese implements GettersEnum<ColoracaoDiurese> {

	AMARELO_BEM_CLARO("0", "Amarelo bem claro"),
	AMARELO_CLARO("1", "Amarelo claro"),
	AMARELO_FLUORESCENTE("2", "Amarelo fluorescente"),
	AMBAR("3", "Âmbar"),
	AMARELO_ESCURO("4", "Amarelo escuro"),
	LARANJA("5", "Laranja"),
	ROSA("6", "Rosa"),
	VERMELHA("7", "Vermelha"),
	ROXA("8", "Roxa"),
	AZUL("9", "Azul"),
	VERDE("10", "Verde"),
	CASTANHA("11", "Castanha"),
	PRETA("12", "Preta"),
	BRANCA("13", "Branca");
	
	
	private String codigo;
	private String descricao;
}
