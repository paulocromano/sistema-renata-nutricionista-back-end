package br.com.renatanutricionista.ficha.identificacao.historico.patologia.familiares.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum PatologiaFamiliares {

	DIABETES("0", "Diabetes"),
	DISLIPIDEMIA("1", "Dislipidemia"),
	OBESIDADE("2", "Obesidade"),
	CANCER("3", "Câncer"),
	NEFROPATIA("4", "Nefropatia");
	
	
	private String codigo;
	private String descricao;
}
