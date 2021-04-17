package br.com.renatanutricionista.patologia.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.renatanutricionista.patologia.model.Patologia;
import br.com.renatanutricionista.utils.FormatacaoUtils;
import lombok.Getter;


@Getter
public class PatologiaDTO implements Comparable<PatologiaDTO> {

	private Integer id;
	private String descricao;

	
	public PatologiaDTO(Patologia patologia) {
		id = patologia.getId();
		descricao = patologia.getDescricao();
	}
	
	
	@Override
	public int compareTo(PatologiaDTO other) {
		return FormatacaoUtils.COLLATOR.compare(descricao, other.getDescricao());
	}
	
	
	public static List<PatologiaDTO> converterParaListaPatologiaDTOEmOrdemAlfabetica(List<Patologia> patologias) {
		return patologias.stream().map(PatologiaDTO::new).sorted().collect(Collectors.toList());
	}
}
