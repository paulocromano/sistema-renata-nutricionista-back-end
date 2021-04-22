package br.com.renatanutricionista.endereco.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.renatanutricionista.paciente.model.Paciente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "endereco", schema = "sistema_nutricionista")
@Setter
@Getter
@NoArgsConstructor
@JsonIgnoreProperties(value = "paciente")
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "O campo Logradouro não pode estar nulo/vazio!")
	@Size(max = 100, message = "O campo Endereço deve ter no máximo {max} caracteres!")
	private String logradouro;
	
	@NotEmpty(message = "O campo Número não pode estar nulo/vazio!")
	@Size(max = 5, message = "O campo Número deve ter no máximo {max} caracteres!")
	private String numero;
	
	@Size(max = 60, message = "O campo Complemento deve ter no máximo {max} caracteres!")
	private String complemento;
	
	@NotEmpty(message = "O campo Bairro não pode estar nulo/vazio!")
	@Size(max = 60, message = "O campo Bairro deve ter no máximo {max} caracteres!")
	private String bairro;
	
	@NotEmpty(message = "O campo Cidade não pode estar nulo/vazio!")
	@Size(max = 60, message = "O campo Cidade deve ter no máximo {max} caracteres!")
	private String cidade;
	
	@NotEmpty(message = "O campo CEP não pode estar nulo/vazio!")
	@Size(max = 9, message = "O campo CEP deve ter no máximo {max} caracteres!")
	@Pattern(regexp = "[0-9]{5}-[0-9]{3}", message = "O formato do CEP é inválido!")
	private String cep;
	
	@NotEmpty(message = "O campo UF não pode estar nulo/vazio!")
	@Size(max = 2, message = "O campo UF deve ter no máximo {max} caracteres!")
	private String uf;
	
	@OneToOne(mappedBy = "endereco", fetch = FetchType.LAZY)
	private Paciente paciente;
	
	
	private Endereco(String logradouro, String numero, String complemento, String bairro, String cidade, 
			String cep, String uf, Paciente paciente) {

		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.cep = cep;
		this.uf = uf;
		this.paciente = paciente;
	}


	public static class Builder {
		
		private String logradouro;
		private String numero;
		private String complemento;
		private String bairro;
		private String cidade;
		private String cep;
		private String uf;
		private Paciente paciente;
		
		
		public Builder logradouro(String logradouro) {
			this.logradouro = logradouro;
			return this;
		}
		
		public Builder numero(String numero) {
			this.numero = numero;
			return this;
		}
		
		public Builder complemento(String complemento) {
			this.complemento = complemento;
			return this;
		}
		
		public Builder bairro(String bairro) {
			this.bairro = bairro;
			return this;
		}
		
		public Builder cidade(String cidade) {
			this.cidade = cidade;
			return this;
		}

		public Builder cep(String cep) {
			this.cep = cep;
			return this;
		}
		
		public Builder uf(String uf) {
			this.uf = uf;
			return this;
		}
		
		public Builder paciente(Paciente paciente) {
			this.paciente = paciente;
			return this;
		}
		
		
		public Endereco build() {
			return new Endereco(logradouro, numero, complemento, bairro, cidade, cep, uf, paciente);
		}
	}
}
