package br.com.renatanutricionista.tabelas.parametro.gordura.corporal.classificacao.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ClassificacaoGorduraCorporal {

	MAGRO("M", "Magro"),
	OTIMO("O", "Ótimo"),
	SOBREPESO("S", "Sobrepeso"),
	GORDO("G", "Gordo");
	
	
	private String codigo;
	private String descricao;
}
