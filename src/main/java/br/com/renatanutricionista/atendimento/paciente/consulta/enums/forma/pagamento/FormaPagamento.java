package br.com.renatanutricionista.atendimento.paciente.consulta.enums.forma.pagamento;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.renatanutricionista.utils.DesserializacaoEnum;
import br.com.renatanutricionista.utils.conversao.enums.GettersEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonDeserialize(using = DesserializacaoEnum.class)
public enum FormaPagamento implements GettersEnum<FormaPagamento> {

	DINHEIRO("0", "Dinheiro"),
	DEBITO("1", "Débito"),
	CREDITO("2", "Crédito");
	
	
	private String codigo;
	private String descricao;
}
