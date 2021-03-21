package br.com.renatanutricionista.alimento.model;

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
@Table(name = "alimento", schema = "sistema_nutricionista")
@Getter
@Setter
public class Alimento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = "O campo Nome não pode estar vazio/nulo!")
	@Size(max = 60, message = "O campo Nome deve ter no máximo {max} caracteres!")
	private String nome;
}
