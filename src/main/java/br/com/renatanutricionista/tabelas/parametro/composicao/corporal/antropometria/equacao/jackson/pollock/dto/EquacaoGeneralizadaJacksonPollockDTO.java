package br.com.renatanutricionista.tabelas.parametro.composicao.corporal.antropometria.equacao.jackson.pollock.dto;

import br.com.renatanutricionista.tabelas.parametro.composicao.corporal.antropometria.equacao.jackson.pollock.model.EquacaoPollock;
import lombok.Getter;


@Getter
public class EquacaoGeneralizadaJacksonPollockDTO {

	private Integer id;
	private String descricao;
	private String sexoPessoa;
	private Integer gorduraEssencialMinimaEmPorcentagem;
	private Integer gorduraEssencialMaximaEmPorcentagem;
	private Integer maioriaAtletasMinimaEmPorcentagem;
	private Integer maioriaAtletasMaximaEmPorcentagem;
	private Integer saudeOtimaMinimaEmPorcentagem;
	private Integer saudeOtimaMaximaEmPorcentagem;
	private Integer obesidadeLimitrofeMinimaEmPorcentagem;
	private Integer obesidadeLimitrofeMaximaEmPorcentagem;
	
	
	public EquacaoGeneralizadaJacksonPollockDTO(EquacaoPollock equacao) {
		id = equacao.getId();
		descricao = equacao.getDescricao();
		sexoPessoa = equacao.getSexoPessoa().getDescricao();
		
		gorduraEssencialMinimaEmPorcentagem = equacao.getGorduraEssencialMinimaEmPorcentagem();
		gorduraEssencialMaximaEmPorcentagem = equacao.getGorduraEssencialMaximaEmPorcentagem();
		
		maioriaAtletasMinimaEmPorcentagem = equacao.getMaioriaAtletasMinimaEmPorcentagem();
		maioriaAtletasMaximaEmPorcentagem = equacao.getMaioriaAtletasMaximaEmPorcentagem();
		
		saudeOtimaMinimaEmPorcentagem = equacao.getSaudeOtimaMinimaEmPorcentagem();
		saudeOtimaMaximaEmPorcentagem = equacao.getSaudeOtimaMaximaEmPorcentagem();
		
		obesidadeLimitrofeMinimaEmPorcentagem = equacao.getObesidadeLimitrofeMinimaEmPorcentagem();
		obesidadeLimitrofeMaximaEmPorcentagem = equacao.getObesidadeLimitrofeMaximaEmPorcentagem();
	}
}
