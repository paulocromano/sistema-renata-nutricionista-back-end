package br.com.renatanutricionista.tabelas.parametro.gordura.corporal.classificacao.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.renatanutricionista.tabelas.parametro.gordura.corporal.classificacao.model.GorduraCorporalClassificacao;
import lombok.Getter;


@Getter
public class GorduraCorporalClassificacaoDTO {

	private Integer id;
	private String classificacaoGorduraCorporal;
	private String sexo;
	private String percentualGordura;
	
	
	public GorduraCorporalClassificacaoDTO(GorduraCorporalClassificacao gorduraCorporalClassificacao) {
		id = gorduraCorporalClassificacao.getId();
		classificacaoGorduraCorporal = gorduraCorporalClassificacao.getClassificacaoGorduraCorporal().getDescricao();
		sexo = gorduraCorporalClassificacao.getSexo().getDescricao();
		percentualGordura = gorduraCorporalClassificacao.getPercentualGordura();
	}
	
	
	public static List<GorduraCorporalClassificacaoDTO> converterParaListaGorduraCorporalClassificacaoDTO(List<GorduraCorporalClassificacao> gorduraCorporalClassificaca) {
		return gorduraCorporalClassificaca.stream().map(GorduraCorporalClassificacaoDTO::new).collect(Collectors.toList());
	}
}
