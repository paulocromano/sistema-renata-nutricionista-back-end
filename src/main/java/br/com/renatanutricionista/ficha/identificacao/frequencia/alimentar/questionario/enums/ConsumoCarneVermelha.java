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
public enum ConsumoCarneVermelha implements GettersEnum<ConsumoCarneVermelha> {

	COM_GORDURA("0", "Com gordura"),
	SEM_GORDURA("1", "Sem gordura"),
	FRITA_IMERSAO_OLEO("2", "Frita com imersão no óleo"),
	GRELHADA("3", "Grelhada"),
	ASSADA("4", "Assada"),
	COZIDA("5", "Cozida");
	
	
	private String codigo;
	private String descricao;
}
