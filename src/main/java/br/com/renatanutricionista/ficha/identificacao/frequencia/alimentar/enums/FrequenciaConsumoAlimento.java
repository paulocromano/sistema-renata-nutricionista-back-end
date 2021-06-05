package br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.enums;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.renatanutricionista.utils.DesserializacaoEnum;
import br.com.renatanutricionista.utils.conversao.enums.GettersEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonDeserialize(using = DesserializacaoEnum.class)
public enum FrequenciaConsumoAlimento implements GettersEnum<FrequenciaConsumoAlimento> {

	NUNCA("N", "Nunca"),
	DIARIO("D", "Diário"),
	SEMANAL("S", "Semanal"),
	MENSAL("M", "Mensal"),
	ANUAL("A", "Anual");
	
	
	private String codigo;
	private String descricao;
}
