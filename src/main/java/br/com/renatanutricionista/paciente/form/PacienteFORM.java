package br.com.renatanutricionista.paciente.form;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import br.com.renatanutricionista.endereco.form.EnderecoFORM;
import br.com.renatanutricionista.paciente.enums.etnia.Etnia;
import br.com.renatanutricionista.paciente.enums.sexo.Sexo;
import br.com.renatanutricionista.paciente.model.Paciente;
import br.com.renatanutricionista.utils.ConversaoUtils;
import br.com.renatanutricionista.utils.RegexUtils;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PacienteFORM {

	@NotEmpty(message = "O campo Nome não pode estar nulo/vazio!")
	@Size(max = 150, message = "O campo Nome deve ter no máximo {max} caracteres!")
	private String nome;
	
	@NotEmpty(message = "A Data de Nascimento não pode estar nula!")
	@Pattern(regexp = RegexUtils.DATA, message = "O formato da Data de Nascimento é inválida!")
	private String dataNascimento;
	
	@NotNull(message = "O campo Sexo não pode estar nulo!")
	private Sexo sexo;
	
	@NotNull(message = "O campo Etnia não pode estar nulo!")
	private Etnia etnia;
	
	@NotEmpty(message = "O campo Telefone não pode estar nulo/vazio!")
	@Size(max = 15, message = "O campo Telefone deve ter no máximo {max} caracteres!")
	@Pattern(regexp = RegexUtils.TELEFONE, message = "O formato do número de Telefone é inválido!")
	private String telefone;
	
	@Size(max = 15, message = "O campo Telefone deve ter no máximo {max} caracteres!")
	private String telefoneRecado;
	
	@Valid
	@NotNull(message = "As informações do Endereco não podem estar nula!")
	private EnderecoFORM endereco;
	
	
	public Paciente converterParaPaciente() {
		if (Objects.nonNull(telefoneRecado) && telefoneRecado.trim().length() != 0) {
			if (!java.util.regex.Pattern.matches(RegexUtils.TELEFONE, telefoneRecado)) {
				throw new IllegalArgumentException("O formato do Telefone para Recado é inválido!");
			}
		}
		
		return new Paciente.Builder()
				.nome(nome)
				.dataNascimento(ConversaoUtils.converterStringParaLocalDate(dataNascimento))
				.sexo(sexo)
				.etnia(etnia)
				.telefone(telefone)
				.telefoneRecado(telefoneRecado)
				.endereco(endereco.converterParaEndereco())
				.dataHoraUltimaAtualizacaoDadosDoPaciente(LocalDateTime.now())
				.build();
	}
}
