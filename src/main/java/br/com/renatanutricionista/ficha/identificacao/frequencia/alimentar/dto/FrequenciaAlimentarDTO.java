package br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.dto;

import java.util.List;
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
	
	
	public static List<FrequenciaAlimentarDTO> converterParaListaFrequenciaAlimentarDTO(
			List<FrequenciaAlimentar> frequenciaAlimentar) {
		
		return frequenciaAlimentar.stream().map(FrequenciaAlimentarDTO::new).collect(Collectors.toList());
	}
}
