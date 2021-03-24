package br.com.renatanutricionista.tabelas.parametro.valor.normalidade.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ComposicaoCorporal {

	CIRCUNFERENCIA_CINTURA("0", "Circunferência cintura (cm)"),
	GORDURA_CORPORAL("1", "Gordura corporal (%)"),
	MASSA_MUSCULAR("2", "Massa muscular (%)"),
	IMM("3", "IMM (Kg/m²)"),
	IMC("4", "IMC (Kg/m²)");
	
	
	private String codigo;
	private String descricao;
}
