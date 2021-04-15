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
public enum ConsumoPeixe implements GettersEnum<ConsumoPeixe> {

	FRITA_IMERSAO_OLEO("0", "Frita com imersão no óleo"),
	GRELHADA("1", "Grelhada"),
	ASSADA("2", "Assada"),
	COZIDA("3", "Cozida");
	
	
	private String codigo;
	private String descricao;
}
