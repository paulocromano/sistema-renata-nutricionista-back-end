package br.com.renatanutricionista.atendimento.paciente.retorno.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SituacaoRetorno {

	AGUARDANDO_CONFIRMACAO("0", "Aguardando confirmação"),
	AGUARDANDO_ATENDIMENTO("1", "Aguardando atendimento"),
	RETORNO_INICIADO("2", "Retorno iniciado"),
	RETORNO_FINALIZADO("3", "Retorno finalizado");
	
	private String codigo;
	private String descricao;
}
