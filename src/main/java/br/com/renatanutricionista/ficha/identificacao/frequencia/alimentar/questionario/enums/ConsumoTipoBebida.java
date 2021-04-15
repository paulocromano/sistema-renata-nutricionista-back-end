package br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.enums;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.renatanutricionista.utils.DesserializacaoEnum;
import br.com.renatanutricionista.utils.conversao.enums.GettersEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonDeserialize(using = DesserializacaoEnum.class)
public enum ConsumoTipoBebida implements GettersEnum<ConsumoTipoBebida> {

	NORMAL("N", "Normal"),
	DIET_LIGHT_ZERO("D", "Diet. Light/zero"),
	QUALQUER_UM("Q", "Qualquer um");
	
	
	private String codigo;
	private String descricao;
}
