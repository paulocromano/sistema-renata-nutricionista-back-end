package br.com.renatanutricionista.ficha.identificacao.historico.social.enums.consumo.cigarro;

import br.com.renatanutricionista.utils.conversao.enums.GettersEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ConsumoCigarro implements GettersEnum<ConsumoCigarro> {

	FUMA("0", "Fuma"),
	FUMOU("1", "Fumou"),
	NUNCA_FUMOU("2", "Nunca fumou");
	
	
	private String codigo;
	private String descricao;
}
