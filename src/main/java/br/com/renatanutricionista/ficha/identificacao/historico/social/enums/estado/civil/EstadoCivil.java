package br.com.renatanutricionista.ficha.identificacao.historico.social.enums.estado.civil;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum EstadoCivil {

	SOLTEIRO("0", "Solteiro(a)"), 
	CASADO("1", "Casado(a)"), 
	SEPARADO("2", "Separado(a)"), 
	VIUVO("3", "Viúvo(a)");

	private String codigo;
	private String descricao;
}
