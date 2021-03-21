package br.com.renatanutricionista.patologia.model;

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
@Table(name = "patologia", schema = "sistema_nutricionista")
@Getter
@Setter
public class Patologia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = "O campo Descrição não pode estar nulo/vazio!")
	@Size(max = 100, message = "O campo Descrição deve ter no máximo {max} caracteres!")
	private String descricao;
}
