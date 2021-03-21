package br.com.renatanutricionista.paciente.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Etnia {

	BRANCO("B", "Branco"),
	NEGRO("N", "Negro"),
	PARDO("P", "Pardo"),
	ASIATICO("A", "Asiático");
	
	
	private String codigo;
	private String descricao;
}
