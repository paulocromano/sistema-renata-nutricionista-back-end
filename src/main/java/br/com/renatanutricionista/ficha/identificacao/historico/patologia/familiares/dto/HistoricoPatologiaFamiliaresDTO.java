package br.com.renatanutricionista.ficha.identificacao.historico.patologia.familiares.dto;

import java.util.Set;
import java.util.stream.Collectors;

import br.com.renatanutricionista.ficha.identificacao.historico.patologia.familiares.model.HistoricoPatologiaFamiliares;
import br.com.renatanutricionista.utils.FormatacaoUtils;
import lombok.Getter;


@Getter
public class HistoricoPatologiaFamiliaresDTO implements Comparable<HistoricoPatologiaFamiliaresDTO> {

	private Long id;
	private String patologiaFamiliares;
	private String pai;
	private String mae;
	private String avosMasculinos;
	private String avosFemininos;
	private String tios;
	private String tias;
	
	
	public HistoricoPatologiaFamiliaresDTO(HistoricoPatologiaFamiliares historicoPatologiaFamiliares) {
		id = historicoPatologiaFamiliares.getId();
		patologiaFamiliares = historicoPatologiaFamiliares.getPatologiaFamiliares().getDescricao();
		pai = historicoPatologiaFamiliares.getPai().getDescricao();
		mae = historicoPatologiaFamiliares.getMae().getDescricao();
		avosMasculinos = historicoPatologiaFamiliares.getAvosMasculinos().getDescricao();
		avosFemininos = historicoPatologiaFamiliares.getAvosFemininos().getDescricao();
		tios = historicoPatologiaFamiliares.getTios().getDescricao();
		tias = historicoPatologiaFamiliares.getTias().getDescricao();
	}
	
	
	@Override
	public int compareTo(HistoricoPatologiaFamiliaresDTO other) {
		return FormatacaoUtils.COLLATOR.compare(patologiaFamiliares, other.getPatologiaFamiliares());
	}
	
	
	public static Set<HistoricoPatologiaFamiliaresDTO> converterParaSetHistoricoPatologiaFamiliaresDTO(Set<HistoricoPatologiaFamiliares> historicoPatologiaFamiliares) {
		return historicoPatologiaFamiliares.stream().map(HistoricoPatologiaFamiliaresDTO::new).sorted().collect(Collectors.toSet());
	}
}
