package br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.dto;

import java.util.Set;
import java.util.stream.Collectors;

import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.model.FrequenciaAlimentar;
import lombok.Getter;


@Getter
public class FrequenciaAlimentarDTO {

	private String descricaoAlimento;
	private String frequenciaConsumoAlimento;
	
	
	public FrequenciaAlimentarDTO(FrequenciaAlimentar frequenciaAlimentar) {
		descricaoAlimento = frequenciaAlimentar.getAlimentoFrequenciaAlimentar().getDescricao();
		frequenciaConsumoAlimento = frequenciaAlimentar.getFrequenciaConsumoAlimento().getDescricao();
	}
	
	
	public static Set<FrequenciaAlimentarDTO> converterParaListaFrequenciaAlimentarDTO(Set<FrequenciaAlimentar> frequenciaAlimentar) {
		return frequenciaAlimentar.stream().map(FrequenciaAlimentarDTO::new).collect(Collectors.toSet());
	}
}
