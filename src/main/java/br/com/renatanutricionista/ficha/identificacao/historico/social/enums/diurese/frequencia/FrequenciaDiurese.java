package br.com.renatanutricionista.ficha.identificacao.historico.social.enums.diurese.frequencia;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.renatanutricionista.utils.DesserializacaoEnum;
import br.com.renatanutricionista.utils.conversao.enums.GettersEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonDeserialize(using = DesserializacaoEnum.class)
public enum FrequenciaDiurese implements GettersEnum<FrequenciaDiurese> {
 
	ATE_3_VEZES("A", "Até 3 vezes"),
	DE_4_A_6_VEZES("B", "De 4 a 6 vezes"),
	DE_7_A_10_VEZES("C", "De 7 a 10 vezes"),
	ACIMA_10_VEZES("D", "Acima de 10 vezes");
	
	
	private String codigo;
	private String descricao;
}
