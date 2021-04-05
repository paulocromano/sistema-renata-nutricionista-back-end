package br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.enums.consumo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum AlimentoTipoModoConsumo {

	SUCO_ARTIFICIAL("suco artificial"),
	REFRIGERANTES("refrigerantes"),
	LEITE("leite"),
	CARNE_VERMELHA("carne vermelha"),
	FRANGO("frango"),
	PEIXE("peixe");
	
	
	private String descricao;
}
