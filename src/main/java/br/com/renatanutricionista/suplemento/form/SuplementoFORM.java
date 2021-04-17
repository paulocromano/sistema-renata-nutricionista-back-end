package br.com.renatanutricionista.suplemento.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import br.com.renatanutricionista.suplemento.model.Suplemento;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SuplementoFORM {

	@NotEmpty(message = "O campo Nome não pode estar vazio/nulo!")
	@Size(max = 100, message = "O campo Nome deve ter no máximo {max} caracteres!")
	private String nome;
	
	@NotEmpty(message = "O campo Dose não pode estar vazio/nulo!")
	@Size(max = 100, message = "O campo Dose deve ter no máximo {max} caracteres!")
	private String dose;
	
	@NotEmpty(message = "O campo Forma de Preparo não pode estar vazio/nulo!")
	@Size(max = 250, message = "O campo Forma de Preparo deve ter no máximo {max} caracteres!")
	private String formaPreparo;
	
	
	public Suplemento converterParaSuplemento() {
		return new Suplemento(nome, dose, formaPreparo);
	}
	
	
	public void atualizarSuplemento(Suplemento suplemento) {
		suplemento.setNome(nome);
		suplemento.setDose(dose);
		suplemento.setFormaPreparo(formaPreparo);
	}
}
