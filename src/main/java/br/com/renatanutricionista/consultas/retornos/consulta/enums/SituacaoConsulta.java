package br.com.renatanutricionista.consultas.retornos.consulta.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SituacaoConsulta {

	AGUARDANDO_CONFIRMACAO("0", "Aguardando confirmação"),
	AGUARDANDO_ATENDIMENTO("1", "Aguardando atendimento"),
	CONSULTA_INICIADA("2", "Consulta iniciada"),
	CONSULTA_FINALIZADA("3", "Consulta finalizada");
	
	
	private String codigo;
	private String descricao;
}
