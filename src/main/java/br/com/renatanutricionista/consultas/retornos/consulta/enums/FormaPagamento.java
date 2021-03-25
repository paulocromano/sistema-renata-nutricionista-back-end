package br.com.renatanutricionista.consultas.retornos.consulta.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum FormaPagamento {

	DINHEIRO("0", "Dinheiro"),
	DEBITO("1", "Débito"),
	CREDITO("2", "Crédito");
	
	
	private String codigo;
	private String descricao;
}
