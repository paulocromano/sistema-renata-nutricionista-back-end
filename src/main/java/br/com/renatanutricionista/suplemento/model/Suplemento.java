package br.com.renatanutricionista.suplemento.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "suplemento", schema = "sistema_nutricionista")
@Getter
@Setter
public class Suplemento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique = true)
	@NotEmpty(message = "O campo Nome não pode estar vazio/nulo!")
	@Size(max = 100, message = "O campo Nome deve ter no máximo {max} caracteres!")
	private String nome;
	
	@NotEmpty(message = "O campo Dose não pode estar vazio/nulo!")
	@Size(max = 100, message = "O campo Dose deve ter no máximo {max} caracteres!")
	private String dose;
	
	@NotEmpty(message = "O campo Forma de Preparo não pode estar vazio/nulo!")
	@Size(max = 250, message = "O campo Forma de Preparo deve ter no máximo {max} caracteres!")
	private String formaPreparo;
}
