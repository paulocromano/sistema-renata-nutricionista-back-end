package br.com.renatanutricionista.tabelas.parametro.gordura.corporal.esporte.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.renatanutricionista.utils.enums.SexoUtils;
import lombok.Getter;

@Entity
@Table(name = "percentual_gordura_corporal_esporte", schema = "sistema_nutricionista_parametro")
@Getter
public class GorduraCorporalEsporte {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique = true)
	@NotEmpty(message = "A modalidade não pode estar nula/vazia!")
	private String modalidade;
	
	@NotNull(message = "O Sexo da Pessoa não pode estar nulo!")
	private SexoUtils sexo;
	
	@Column(name = "percentual_minimo")
	private Integer percentualMinimo;
	
	@Column(name = "percentual_maximo")
	private Integer percentualMaximo;
}
