package br.com.renatanutricionista.atendimento.paciente.registro.dieta.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum TipoRegistroDieta {

	HABITUAL("0", "Habitual"),
	PERIODO_24H("1", "24 horas");
	
	
	private String codigo;
	private String descricao;
}
