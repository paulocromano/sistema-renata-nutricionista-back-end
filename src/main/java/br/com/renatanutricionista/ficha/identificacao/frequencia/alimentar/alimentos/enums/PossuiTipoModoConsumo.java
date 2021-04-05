package br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.alimentos.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum PossuiTipoModoConsumo {

	SIM("S"),
	NAO("N");
	
	
	private String codigo;
}
