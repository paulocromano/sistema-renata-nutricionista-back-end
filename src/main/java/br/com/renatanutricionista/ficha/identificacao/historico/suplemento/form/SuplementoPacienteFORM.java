package br.com.renatanutricionista.ficha.identificacao.historico.suplemento.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SuplementoPacienteFORM {

	@NotEmpty(message = "O campo Dose não pode estar nulo/vazio!")
	@Size(max = 100, message = "O campo Dose deve ter no máximo {max} caracteres!")
	private String dose;
	
	@NotEmpty(message = "O campo Forma de Preparo não pode estar nulo/vazio!")
	@Size(max = 250, message = "O campo Forma de Preparo deve ter no máximo {max} caracteres!")
	private String formaPreparo;
	
	@NotNull(message = "O campo ID do Suplemento não pode estar nulo!")
	private Integer idSuplemento;
}
