package br.com.renatanutricionista.tabelas.parametro.valor.normalidade.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.renatanutricionista.tabelas.parametro.valor.normalidade.model.ValorNormalidade;
import lombok.Getter;


@Getter
public class ValorNormalidadeDTO {

	private Integer id;
	private String composicaoCorporal;
	private String idadeParametro;
	private String mulher;
	private String homem;
	
	
	public ValorNormalidadeDTO(ValorNormalidade valorNormalidade) {
		id = valorNormalidade.getId();
		composicaoCorporal = valorNormalidade.getComposicaoCorporal().getDescricao();
		idadeParametro = valorNormalidade.getIdadeParametro();
		mulher = valorNormalidade.getMulher();
		homem = valorNormalidade.getHomem();
	}
	
	
	public static List<ValorNormalidadeDTO> converterParaListaValorNormalidadeDTO(List<ValorNormalidade> valoresNormalidade) {
		return valoresNormalidade.stream().map(ValorNormalidadeDTO::new).collect(Collectors.toList());
	}
}
