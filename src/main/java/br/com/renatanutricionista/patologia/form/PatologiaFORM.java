package br.com.renatanutricionista.patologia.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import br.com.renatanutricionista.patologia.model.Patologia;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PatologiaFORM {

	@NotEmpty(message = "O campo Descrição não pode estar nulo/vazio!")
	@Size(max = 100, message = "O campo Descrição deve ter no máximo {max} caracteres!")
	private String descricao;
	
	
	public Patologia converterParaPatologia() {
		return new Patologia(descricao);
	}
	
	
	public void atualizarPatologia(Patologia patologia) {
		patologia.setDescricao(descricao);
	}
}
