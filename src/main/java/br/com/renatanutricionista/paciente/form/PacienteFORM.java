package br.com.renatanutricionista.paciente.form;

import java.time.LocalDateTime;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import br.com.renatanutricionista.endereco.form.EnderecoFORM;
import br.com.renatanutricionista.paciente.enums.EtniaConversao;
import br.com.renatanutricionista.paciente.model.Paciente;
import br.com.renatanutricionista.utils.ConversaoUtlis;
import br.com.renatanutricionista.utils.enums.SexoUtlisConversao;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PacienteFORM {

	@NotEmpty(message = "O campo Nome não pode estar nulo/vazio!")
	@Size(max = 150, message = "O campo Nome deve ter no máximo {max} caracteres!")
	private String nome;
	
	@NotNull(message = "A Data de Nascimento não pode estar nula!")
	private String dataNascimento;
	
	@NotNull(message = "O campo Sexo não pode estar nulo!")
	private String sexo;
	
	@NotNull(message = "O campo Etnia não pode estar nulo!")
	private String etnia;
	
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
	
	
	public Paciente converterParaPaciente() {
		return new Paciente.PacienteBuilder()
				.nome(nome)
				.dataNascimento(ConversaoUtlis.converterStringParaLocalDate(dataNascimento))
				.sexo(new SexoUtlisConversao().convertToEntityAttribute(sexo))
				.etnia(new EtniaConversao().convertToEntityAttribute(etnia))
				.telefone(telefone)
				.telefoneRecado(telefoneRecado)
				.endereco(endereco.converterParaEndereco())
				.dataUltimaAtualizacaoDadosDoPaciente(LocalDateTime.now())
				.criarPaciente();
	}
}
