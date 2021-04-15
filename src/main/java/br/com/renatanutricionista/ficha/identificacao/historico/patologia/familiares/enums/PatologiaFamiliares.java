package br.com.renatanutricionista.ficha.identificacao.historico.patologia.familiares.enums;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.renatanutricionista.utils.DesserializacaoEnum;
import br.com.renatanutricionista.utils.conversao.enums.GettersEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonDeserialize(using = DesserializacaoEnum.class)
public enum PatologiaFamiliares implements GettersEnum<PatologiaFamiliares> {

	DIABETES("0", "Diabetes"),
	DISLIPIDEMIA("1", "Dislipidemia"),
	OBESIDADE("2", "Obesidade"),
	CANCER("3", "Câncer"),
	NEFROPATIA("4", "Nefropatia");
	
	
	private String codigo;
	private String descricao;
}
