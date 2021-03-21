package br.com.renatanutricionista.suplemento.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.renatanutricionista.suplemento.model.Suplemento;
import lombok.Getter;


@Getter
public class SuplementoDTO {

	private Integer id;
	private String nome;
	private String dose;
	private String formaPreparo;
	
	
	public SuplementoDTO(Suplemento suplemento) {
		id = suplemento.getId();
		nome = suplemento.getNome();
		dose = suplemento.getDose();
		formaPreparo = suplemento.getFormaPreparo();
	}
	
	
	public static List<SuplementoDTO> converterParaListaSuplementoDTO(List<Suplemento> suplementos) {
		return suplementos.stream().map(SuplementoDTO::new).collect(Collectors.toList());
	}
}
