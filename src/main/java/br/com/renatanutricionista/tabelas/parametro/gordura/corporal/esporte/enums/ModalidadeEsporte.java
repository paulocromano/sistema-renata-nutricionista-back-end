package br.com.renatanutricionista.tabelas.parametro.gordura.corporal.esporte.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ModalidadeEsporte {

	BASQUETEBOL("A", "Basquetebol"),
	CICLISMO("B", "Ciclismo"),
	FISICULTURISMO("C", "Fisiculturismo"),
	FUTEBOL("D", "Futebol"),
	GINASTICA("E", "Ginástica"),
	LUTA("F", "Luta"),
	MARATONISTA("G", "Maratonista"),
	NATACAO("H", "Natação"),
	REMO("I", "Remo"),
	TENIS("J", "Tênis"),
	TRIATLO("K", "Triatlo"),
	VOLEIBOL("L", "Voleibol");
	
	
	private String codigo;
	private String descricao;
}
