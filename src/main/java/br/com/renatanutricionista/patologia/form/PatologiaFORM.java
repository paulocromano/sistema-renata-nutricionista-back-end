package br.com.renatanutricionista.patologia.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PatologiaFORM {

	@NotEmpty(message = "O campo Descrição não pode estar nulo/vazio!")
	@Size(max = 100, message = "O campo Descrição deve ter no máximo {max} caracteres!")
	private String descricao;
}
