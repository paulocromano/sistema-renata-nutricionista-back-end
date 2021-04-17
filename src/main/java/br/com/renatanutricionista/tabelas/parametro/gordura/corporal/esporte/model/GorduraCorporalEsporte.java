package br.com.renatanutricionista.tabelas.parametro.gordura.corporal.esporte.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.renatanutricionista.paciente.enums.sexo.Sexo;
import br.com.renatanutricionista.tabelas.parametro.gordura.corporal.esporte.enums.ModalidadeEsporte;
import lombok.Getter;


@Entity
@Table(name = "percentual_gordura_corporal_esporte", catalog = "sistema_nutricionista_parametro")
@Getter
public class GorduraCorporalEsporte {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "modalidade_esporte")
	private ModalidadeEsporte modalidadeEsporte;
	
	private Sexo sexo;
	
	@Column(name = "percentual_minimo")
	private Integer percentualMinimo;
	
	@Column(name = "percentual_maximo")
	private Integer percentualMaximo;
}
