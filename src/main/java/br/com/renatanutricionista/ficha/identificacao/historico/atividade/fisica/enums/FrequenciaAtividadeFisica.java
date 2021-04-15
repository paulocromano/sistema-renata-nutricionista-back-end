package br.com.renatanutricionista.ficha.identificacao.historico.atividade.fisica.enums;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.renatanutricionista.utils.DesserializacaoEnum;
import br.com.renatanutricionista.utils.conversao.enums.GettersEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonDeserialize(using = DesserializacaoEnum.class)
public enum FrequenciaAtividadeFisica implements GettersEnum<FrequenciaAtividadeFisica> {

	DIARIA("D", "Diária"),
	FREQUENTE("F", "Frequente"),
	AS_VEZES("A", "Às vezes"),
	QUASE_NUNCA("Q", "Quase nunca"),
	NAO_PRATICA("N", "Não pratica");
	
	
	private String codigo;
	private String descricao;
}
