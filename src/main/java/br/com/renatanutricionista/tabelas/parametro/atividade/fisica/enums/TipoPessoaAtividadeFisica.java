package br.com.renatanutricionista.tabelas.parametro.atividade.fisica.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum TipoPessoaAtividadeFisica {

	ADULTOS_EUTROFICOS("AE", "Adultos eutróficos"),
	ADULTOS_EXCESSO_PESO("AEP", "Adultos com excesso de peso"),
	CRIANCAS_ADOLESCENTES_EUTROFICOS("CAE", "Crianças e adolescentes eutróficos"),
	CRIANCAS_ADOLESCENTES_EXCESSO_PESO("CAEP", "Crianças e adolescentes com excesso de peso"),
	GESTANTES_MENINAS("GMEN", "Gestantes Meninas"),
	GESTANTES_MULHERES("GMUL", "Gestantes Mulheres"),
	NUTRIZES("NUT", "Nutrizes");
	
	
	private String codigo;
	private String descricao;
}

