package br.com.renatanutricionista.paciente.enums.etnia;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.renatanutricionista.utils.DesserializacaoEnum;
import br.com.renatanutricionista.utils.conversao.enums.GettersEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonDeserialize(using = DesserializacaoEnum.class)
public enum Etnia implements GettersEnum<Etnia> {

	BRANCO("B", "Branco"),
	NEGRO("N", "Negro"),
	PARDO("P", "Pardo"),
	ASIATICO("A", "Asiático");
	
	
	private String codigo;
	private String descricao;
}
