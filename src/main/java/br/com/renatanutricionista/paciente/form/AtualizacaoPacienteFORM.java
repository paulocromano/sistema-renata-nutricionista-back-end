package br.com.renatanutricionista.paciente.form;

import java.time.LocalDateTime;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import br.com.renatanutricionista.endereco.form.EnderecoFORM;
import br.com.renatanutricionista.paciente.model.Paciente;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AtualizacaoPacienteFORM {

	@NotEmpty(message = "O campo Telefone não pode estar nulo/vazio!")
	@Size(max = 15, message = "O campo Telefone deve ter no máximo {max} caracteres!")
	@Pattern(regexp = "(\\(?\\d{2}\\)?\\s)?(\\d{4,5}\\-\\d{4})", message = "O formato do número de Telefone é inválido!")
	private String telefone;
	
	@Size(max = 15, message = "O campo Telefone deve ter no máximo {max} caracteres!")
	@Pattern(regexp = "(\\(?\\d{2}\\)?\\s)?(\\d{4,5}\\-\\d{4})", message = "O formato do número do Telefone para Recado é inválido!")
	private String telefoneRecado;
	
	@Valid
	@NotNull(message = "O objeto Endereco não pode estar nulo!")
	private EnderecoFORM endereco;
	
	
	public void atualizarPacienteEndereco(Paciente paciente) {
		paciente.setTelefone(telefone);
		paciente.setTelefoneRecado(telefoneRecado);
		paciente.setEndereco(endereco.converterParaEndereco());
		paciente.setDataHoraUltimaAtualizacaoDadosDoPaciente(LocalDateTime.now());
	}
}
