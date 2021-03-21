package br.com.renatanutricionista.tabelas.parametro.composicao.corporal.antropometria.massa.muscular.equacao.lee.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ClassificacaoMassaMuscularPelaIdade {

	IDADE_MENOR_40("0", "menor 40 anos"),
	IDADE_MAIOR_IGUAL_40("1", "maior ou igual 40 anos");
	
	
	private String codigo;
	private String descricao;
}
