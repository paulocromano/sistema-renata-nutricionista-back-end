package br.com.renatanutricionista.patologia.dto;

import java.text.Collator;

import br.com.renatanutricionista.patologia.model.Patologia;
import br.com.renatanutricionista.utils.FormatacaoUtils;
import lombok.Getter;


@Getter
public class PatologiaDTO implements Comparable<PatologiaDTO> {

	private Integer id;
	private String descricao;
	
	private static final Collator COLLATOR = FormatacaoUtils.COLLATOR;
	
	public PatologiaDTO(Patologia patologia) {
		id = patologia.getId();
		descricao = patologia.getDescricao();
	}
	
	
	@Override
	public int compareTo(PatologiaDTO outraPatologia) {
		return COLLATOR.compare(descricao, outraPatologia.getDescricao());
	}
}
