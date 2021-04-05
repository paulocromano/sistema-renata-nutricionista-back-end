package br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.alimentos.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.alimentos.enums.PossuiTipoModoConsumo;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.alimentos.model.AlimentoFrequenciaAlimentar;
import lombok.Getter;


@Getter
public class AlimentoFrequenciaAlimentarDTO {

	private Integer id;
	private String descricao;
	private PossuiTipoModoConsumo possuiTipoModoConsumo;
	
	
	public AlimentoFrequenciaAlimentarDTO(AlimentoFrequenciaAlimentar alimentoFrequenciaAlimentar) {
		id = alimentoFrequenciaAlimentar.getId();
		descricao = alimentoFrequenciaAlimentar.getDescricao();
		possuiTipoModoConsumo = alimentoFrequenciaAlimentar.getPossuiTipoModoConsumo();
	}
	
	
	public static List<AlimentoFrequenciaAlimentarDTO> converterParaListaAlimentoFrequenciaAlimentarDTO(List<AlimentoFrequenciaAlimentar> alimentos) {
		return alimentos.stream().map(AlimentoFrequenciaAlimentarDTO::new).collect(Collectors.toList());
	}
}
