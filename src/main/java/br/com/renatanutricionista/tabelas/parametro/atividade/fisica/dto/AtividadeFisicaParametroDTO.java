package br.com.renatanutricionista.tabelas.parametro.atividade.fisica.dto;

import java.math.BigDecimal;

import br.com.renatanutricionista.tabelas.parametro.atividade.fisica.model.AtividadeFisicaParametro;
import lombok.Getter;

@Getter
public abstract class AtividadeFisicaParametroDTO {

	private Integer id;
	private String tipoPessoa;
	private String observacao;
	private String sexoPessoa;
	private BigDecimal sedentario;
	private BigDecimal poucoAtivo;
	private BigDecimal ativo;
	private BigDecimal muitoAtivo;
	
	
	public AtividadeFisicaParametroDTO(AtividadeFisicaParametro atividadeFisicaParametro) {
		id = atividadeFisicaParametro.getId();
		tipoPessoa = atividadeFisicaParametro.getTipoPessoa().getDescricao();
		observacao = atividadeFisicaParametro.getObservacao();
		sexoPessoa = atividadeFisicaParametro.getSexoPessoa().getDescricao();
		
		sedentario = atividadeFisicaParametro.getSedentario();
		poucoAtivo = atividadeFisicaParametro.getPoucoAtivo();
		ativo = atividadeFisicaParametro.getAtivo();
		muitoAtivo = atividadeFisicaParametro.getMuitoAtivo();
	}
}
