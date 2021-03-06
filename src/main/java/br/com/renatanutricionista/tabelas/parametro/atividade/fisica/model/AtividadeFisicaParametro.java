package br.com.renatanutricionista.tabelas.parametro.atividade.fisica.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;

import br.com.renatanutricionista.paciente.enums.sexo.Sexo;
import br.com.renatanutricionista.tabelas.parametro.atividade.fisica.enums.TipoPessoaAtividadeFisica;
import lombok.Getter;


@Entity
@Table(name = "atividade_fisica_parametro", catalog = "sistema_nutricionista_parametro")
@Getter
public class AtividadeFisicaParametro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "tipo_pessoa")
	private TipoPessoaAtividadeFisica tipoPessoa;
	
	private String observacao;
	
	@Column(name = "sexo")
	private Sexo sexoPessoa;
	
	@Digits(integer = 2, fraction = 2)
	private BigDecimal sedentario;
	
	@Column(name = "pouco_ativo")
	@Digits(integer = 2, fraction = 2)
	private BigDecimal poucoAtivo;
	
	@Digits(integer = 2, fraction = 2)
	private BigDecimal ativo;
	
	@Column(name = "muito_ativo")
	@Digits(integer = 2, fraction = 2)
	private BigDecimal muitoAtivo;
}
