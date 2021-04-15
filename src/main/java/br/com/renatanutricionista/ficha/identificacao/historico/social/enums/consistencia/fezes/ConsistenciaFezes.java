package br.com.renatanutricionista.ficha.identificacao.historico.social.enums.consistencia.fezes;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.renatanutricionista.utils.DesserializacaoEnum;
import br.com.renatanutricionista.utils.conversao.enums.GettersEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonDeserialize(using = DesserializacaoEnum.class)
public enum ConsistenciaFezes implements GettersEnum<ConsistenciaFezes> {

	NORMAL("N", "Normal"),
	AMOLECIDAS("A", "Amolecidas"),
	DURAS("D", "Duras");
	
	
	private String codigo;
	private String descricao;
}
