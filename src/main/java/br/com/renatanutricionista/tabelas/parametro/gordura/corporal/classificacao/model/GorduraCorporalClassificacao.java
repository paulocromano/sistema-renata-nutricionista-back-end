package br.com.renatanutricionista.tabelas.parametro.gordura.corporal.classificacao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.renatanutricionista.tabelas.parametro.gordura.corporal.classificacao.enums.ClassificacaoGorduraCorporal;
import lombok.Getter;


@Entity
@Table(name = "percentual_gordura_coporal_classificacao", schema = "sistema_nutricionista_parametro")
@Getter
public class GorduraCorporalClassificacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "classificacao")
	private ClassificacaoGorduraCorporal classificacaoGorduraCorporal;
	
	private String homem;
	private String mulher;
}
