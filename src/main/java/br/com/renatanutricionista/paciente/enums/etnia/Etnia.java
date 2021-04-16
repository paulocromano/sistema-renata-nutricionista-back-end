package br.com.renatanutricionista.paciente.enums.etnia;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.renatanutricionista.utils.DesserializacaoEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonDeserialize(using = DesserializacaoEnum.class)
public enum Etnia {

	BRANCO("B", "Branco"),
	NEGRO("N", "Negro"),
	PARDO("P", "Pardo"),
	ASIATICO("A", "Asiático");
	
	
	private String codigo;
	private String descricao;
}
