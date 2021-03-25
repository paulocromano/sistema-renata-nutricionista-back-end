package br.com.renatanutricionista.consultas.retornos.consulta.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SituacaoConsulta {

	AGUARDANDO_CONFIRMACAO("0", "Aguardando confirmação"),
	CONSULTA_CANCELADA("1", "Consulta cancelada"),
	AGUARDANDO_ATENDIMENTO("2", "aguardando atendimento"),
	CONSULTA_INICIADA("3", "Consulta iniciada"),
	CONSULTA_FINALIZADA("4", "Consulta finalizada");
	
	
	private String codigo;
	private String descricao;
}
