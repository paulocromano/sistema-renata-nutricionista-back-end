package br.com.renatanutricionista.utils.enums.sexo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SexoUtils {

	MASCULINO("M", "Masculino"),
	FEMININO("F", "Feminino");
	
	
	private String codigo;
	private String descricao;
}
