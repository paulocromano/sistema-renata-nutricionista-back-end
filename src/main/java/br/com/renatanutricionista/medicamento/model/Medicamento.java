package br.com.renatanutricionista.medicamento.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "medicamento", schema = "sistema_nutricionista")
@Getter
@Setter
@NoArgsConstructor
public class Medicamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = "O campo Nome não pode ser nulo/vazio!")
	@Size(max = 100, message = "O campo Nome deve ter no máximo {max} caracteres!")
	private String nome;

	
	public Medicamento(String nome) {
		this.nome = nome;
	}
}
