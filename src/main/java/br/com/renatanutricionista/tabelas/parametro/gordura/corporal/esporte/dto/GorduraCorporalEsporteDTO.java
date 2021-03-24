package br.com.renatanutricionista.tabelas.parametro.gordura.corporal.esporte.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.renatanutricionista.tabelas.parametro.gordura.corporal.esporte.model.GorduraCorporalEsporte;
import lombok.Getter;


@Getter
public class GorduraCorporalEsporteDTO {

	private Integer id;
	private String modalidade;
	private String sexo;
	private Integer percentualMinimo;
	private Integer percentualMaximo;
	
	
	public GorduraCorporalEsporteDTO(GorduraCorporalEsporte gorduraCorporalEsporte) {
		id = gorduraCorporalEsporte.getId();
		modalidade = gorduraCorporalEsporte.getModalidade();
		sexo = gorduraCorporalEsporte.getSexo().getDescricao();
		percentualMinimo = gorduraCorporalEsporte.getPercentualMinimo();
		percentualMaximo = gorduraCorporalEsporte.getPercentualMaximo();
	}
	
	
	public static List<GorduraCorporalEsporteDTO> converterParaListaGorduraCorporalEsporteDTO(List<GorduraCorporalEsporte> gorduraCorporalEsportes) {
		return gorduraCorporalEsportes.stream().map(GorduraCorporalEsporteDTO::new).collect(Collectors.toList());
	}
}
