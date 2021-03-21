package br.com.renatanutricionista.tabelas.parametro.composicao.corporal.antropometria.massa.muscular.equacao.lee.dto;

import br.com.renatanutricionista.tabelas.parametro.composicao.corporal.antropometria.massa.muscular.equacao.lee.model.MassaMuscularEquacaoLee;
import lombok.Getter;


@Getter
public class MassaMuscularEquacaoLeeDTO {

	private Integer id;
	private String classificacaoPelaIdade;
	private Integer mulheresPorcentagemMaior;
	private Integer homensPorcentagemMaior;
	
	
	public MassaMuscularEquacaoLeeDTO(MassaMuscularEquacaoLee massaMuscular) {
		id = massaMuscular.getId();
		classificacaoPelaIdade = massaMuscular.getClassificacaoPelaIdade().getDescricao();
		mulheresPorcentagemMaior = massaMuscular.getMulheresPorcentagemMaior();
		homensPorcentagemMaior = massaMuscular.getHomensPorcentagemMaior();
	}
}
