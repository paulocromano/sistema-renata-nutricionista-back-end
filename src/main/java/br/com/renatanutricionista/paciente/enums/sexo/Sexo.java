package br.com.renatanutricionista.paciente.enums.sexo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.renatanutricionista.utils.DesserializacaoEnum;
import br.com.renatanutricionista.utils.conversao.enums.GettersEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonDeserialize(using = DesserializacaoEnum.class)
public enum Sexo implements GettersEnum<Sexo> {

	MASCULINO("M", "Masculino"),
	FEMININO("F", "Feminino");
	
	
	private String codigo;
	private String descricao;
}
