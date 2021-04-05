package br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.dto;

import java.util.Set;
import java.util.stream.Collectors;

import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.alimentos.dto.AlimentoFrequenciaAlimentarDTO;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.enums.frequencia.consumo.FrequenciaConsumoAlimento;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.model.FrequenciaAlimentar;
import lombok.Getter;


@Getter
public class FrequenciaAlimentarDTO {

	private Long id;
	private AlimentoFrequenciaAlimentarDTO alimentoQuestionarioFrequenciaAlimentar;
	private FrequenciaConsumoAlimento frequenciaConsumoAlimento;
	
	
	public FrequenciaAlimentarDTO(FrequenciaAlimentar questionarioFrequenciaAlimentar) {
		id = questionarioFrequenciaAlimentar.getId();
		alimentoQuestionarioFrequenciaAlimentar = new AlimentoFrequenciaAlimentarDTO(
				questionarioFrequenciaAlimentar.getAlimentoFrequenciaAlimentar());
		
		frequenciaConsumoAlimento = questionarioFrequenciaAlimentar.getFrequenciaConsumoAlimento();
	}
	
	
	public static Set<FrequenciaAlimentarDTO> converterParaListaFrequenciaAlimentarDTO(Set<FrequenciaAlimentar> frequenciaAlimentar) {
		return frequenciaAlimentar.stream().map(FrequenciaAlimentarDTO::new).collect(Collectors.toSet());
	}
}
