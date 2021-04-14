package br.com.renatanutricionista.utils.enums.resposta;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum RespostaUtils {

	SIM("S", "Sim"),
	NAO("N", "Não");
	
	
	private String codigo;
	private String descricao;
}
