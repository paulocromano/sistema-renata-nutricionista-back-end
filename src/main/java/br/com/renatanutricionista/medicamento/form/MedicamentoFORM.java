package br.com.renatanutricionista.medicamento.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import br.com.renatanutricionista.medicamento.model.Medicamento;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MedicamentoFORM {

	@NotEmpty(message = "O campo Nome não pode ser nulo/vazio!")
	@Size(max = 100, message = "O campo Nome deve ter no máximo {max} caracteres!")
	private String nome;
	
	
	public Medicamento converterParaMedicamento() {
		return new Medicamento(nome);
	}
	
	
	public void atualizarMedicamento(Medicamento medicamento) {
		medicamento.setNome(nome);
	}
}
