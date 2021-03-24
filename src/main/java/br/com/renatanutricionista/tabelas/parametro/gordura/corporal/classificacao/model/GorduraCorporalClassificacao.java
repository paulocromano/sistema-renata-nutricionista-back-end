package br.com.renatanutricionista.tabelas.parametro.gordura.corporal.classificacao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.renatanutricionista.tabelas.parametro.gordura.corporal.classificacao.enums.ClassificacaoGorduraCorporal;
import br.com.renatanutricionista.utils.enums.SexoUtils;
import lombok.Getter;


@Entity
@Table(name = "percentual_gordura_coporal_classificacao", schema = "sistema_nutricionista_parametro")
@Getter
public class GorduraCorporalClassificacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message = "A Classificação da Gordura Corporal não pode estar nula!")
	private ClassificacaoGorduraCorporal classificacaoGorduraCorporal;
	
	@NotNull(message = "O Sexo da Pessoa não pode estar nulo!")
	private SexoUtils sexo;
	
	@Column(name = "percentual_gordura")
	@NotEmpty(message = "O campo Percentual de Gordura não pode estar vazio/nulo!")
	private String percentualGordura;
}
