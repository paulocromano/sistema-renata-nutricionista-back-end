package br.com.renatanutricionista.utils.enums.resposta;

import br.com.renatanutricionista.utils.conversao.enums.GettersEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum RespostaUtils implements GettersEnum<RespostaUtils> {

	SIM("S", "Sim"),
	NAO("N", "Não");
	
	
	private String codigo;
	private String descricao;
}
