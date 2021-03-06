package br.com.renatanutricionista.atendimento.paciente.consulta.enums.situacao.consulta;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SituacaoConsulta {

	AGUARDANDO_CONFIRMACAO("0", "Aguardando confirmação"),
	AGUARDANDO_ATENDIMENTO("1", "Aguardando atendimento"),
	CONSULTA_INICIADA("2", "Iniciada"),
	CONSULTA_FINALIZADA("3", "Finalizada");
	
	
	private String codigo;
	private String descricao;
}
