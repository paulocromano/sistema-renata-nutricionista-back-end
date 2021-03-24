package br.com.renatanutricionista.tabelas.parametro.valor.normalidade.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.renatanutricionista.tabelas.parametro.valor.normalidade.enums.ComposicaoCorporal;
import lombok.Getter;


@Entity
@Table(name = "valor_normalidade", schema = "sistema_nutricionista_parametro")
@Getter
public class ValorNormalidade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "composicao_corporal")
	private ComposicaoCorporal composicaoCorporal;
	
	@Column(name = "idade_parametro")
	private String idadeParametro;
	
	private String mulher;
	private String homem;
}
