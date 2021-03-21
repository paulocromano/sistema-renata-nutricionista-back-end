package br.com.renatanutricionista.alimento.dto;

import br.com.renatanutricionista.alimento.model.Alimento;
import lombok.Getter;


@Getter
public class AlimentoDTO {

	private Integer id;
	private String nome;
	
	
	public AlimentoDTO(Alimento alimento) {
		id = alimento.getId();
		nome = alimento.getNome();
	}
}
