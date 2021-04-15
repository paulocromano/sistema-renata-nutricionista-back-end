package br.com.renatanutricionista.ficha.identificacao.historico.social.enums.consumo.bebidas.alcoolicas;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.renatanutricionista.utils.DesserializacaoEnum;
import br.com.renatanutricionista.utils.conversao.enums.GettersEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonDeserialize(using = DesserializacaoEnum.class)
public enum ConsumoBebidasAlcoolicas implements GettersEnum<ConsumoBebidasAlcoolicas> {

	DIARIAMENTE("D", "Diariamente"),
	FREQUENTEMENTE("F", "Frequentemente"),
	AS_VEZES("A", "Às vezes"),
	RARAMENTE("R", "Raramente"),
	BEBIA("B", "Bebia"),
	NUNCA("N", "Nunca");
	
	
	private String codigo;
	private String descricao;
}
