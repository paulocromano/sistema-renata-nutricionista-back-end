package br.com.renatanutricionista.paciente.enums.sexo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Sexo {

	MASCULINO("M", "Masculino"),
	FEMININO("F", "Feminino");
	
	
	private String codigo;
	private String descricao;
}
