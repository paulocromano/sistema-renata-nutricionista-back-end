package br.com.renatanutricionista.paciente.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.renatanutricionista.atendimento.paciente.consulta.model.Consulta;
import br.com.renatanutricionista.endereco.model.Endereco;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.model.QuestionarioFrequenciaAlimentar;
import br.com.renatanutricionista.ficha.identificacao.historico.alimentar.model.HistoricoAlimentar;
import br.com.renatanutricionista.ficha.identificacao.historico.atividade.fisica.model.HistoricoAtividadeFisica;
import br.com.renatanutricionista.ficha.identificacao.historico.patologia.por.data.familiares.model.HistoricoPatologiaFamiliaresPorData;
import br.com.renatanutricionista.ficha.identificacao.historico.social.model.HistoricoSocial;
import br.com.renatanutricionista.paciente.enums.etnia.Etnia;
import br.com.renatanutricionista.paciente.enums.sexo.Sexo;
import br.com.renatanutricionista.utils.RegexUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "paciente", schema = "sistema_nutricionista")
@Setter
@Getter
@NoArgsConstructor
public class Paciente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	@NotEmpty(message = "O campo Nome não pode estar nulo/vazio!")
	@Size(max = 150, message = "O campo Nome deve ter no máximo {max} caracteres!")
	private String nome;
	
	@NotNull(message = "A Data de Nascimento não pode estar nula!")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;
	
	@NotNull(message = "O campo Sexo não pode estar nulo!")
	private Sexo sexo;
	
	@NotNull(message = "O campo Etnia não pode estar nulo!")
	private Etnia etnia;
	
	@NotEmpty(message = "O campo Telefone não pode estar nulo/vazio!")
	@Size(max = 15, message = "O campo Telefone deve ter no máximo {max} caracteres!")
	@Pattern(regexp = "(\\(?\\d{2}\\)?\\s)?(\\d{4,5}\\-\\d{4})", message = "O formato do número de Telefone é inválido!")
	private String telefone;
	
	@Column(name = "telefone_recado")
	@Size(max = 15, message = "O campo Telefone deve ter no máximo {max} caracteres!")
	private String telefoneRecado;
	
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "endereco_id")
	@NotNull(message = "O objeto Endereço do Paciente não pode estar nulo!")
	private Endereco endereco;
	
	@OneToMany(mappedBy = "paciente", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<HistoricoSocial> historicosSociais;
	
	@OneToMany(mappedBy = "paciente", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<HistoricoAlimentar> historicosAlimentares;
	
	@OneToMany(mappedBy = "paciente", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<HistoricoAtividadeFisica> historicosAtividadeFisica;
	
	@OneToMany(mappedBy = "paciente", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private Set<HistoricoPatologiaFamiliaresPorData> historicosPatologiaFamiliaresPorData;
	
	@OneToMany(mappedBy = "paciente", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<QuestionarioFrequenciaAlimentar> questionariosFrequenciaAlimentar;
	
	@OneToMany(mappedBy = "paciente", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<Consulta> consultas;
	
	@Column(name = "data_cadastro")
	@NotNull(message = "A Data de Cadastro do Paciente não pode estar nula!")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataCadastro;
	
	
	private Paciente(String nome, LocalDate dataNascimento, Sexo sexo, Etnia etnia, String telefone, 
			String telefoneRecado, Endereco endereco, LocalDate dataCadastro) {
		
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.etnia = etnia;
		this.telefone = telefone;
		this.telefoneRecado = telefoneRecado;
		this.endereco = endereco;
		this.dataCadastro = dataCadastro;
	}


	public static class Builder {
		private String nome;
		private LocalDate dataNascimento;
		private Sexo sexo;
		private Etnia etnia;
		private String telefone;
		private String telefoneRecado;
		private Endereco endereco;
		private LocalDate dataCadastro;
		
		
		public Builder nome(String nome) {
			this.nome = nome;
			return this;
		}
		
		public Builder dataNascimento(LocalDate dataNascimento) {
			this.dataNascimento = dataNascimento;
			return this;
		}
		
		public Builder sexo(Sexo sexo) {
			this.sexo = sexo;
			return this;
		}
		
		public Builder etnia(Etnia etnia) {
			this.etnia = etnia;
			return this;
		}
		
		public Builder telefone(String telefone) {
			this.telefone = telefone;
			return this;
		}
		
		public Builder telefoneRecado(String telefoneRecado) {
			if (Objects.nonNull(telefoneRecado) && telefoneRecado.trim().length() != 0) {
				if (!java.util.regex.Pattern.matches(RegexUtils.TELEFONE, telefoneRecado)) {
					throw new IllegalArgumentException("O formato do Telefone para Recado é inválido!");
				}
			}
			
			this.telefoneRecado = telefoneRecado;	
			return this;
		}
		
		public Builder endereco(Endereco endereco) {
			this.endereco = endereco;
			return this;
		}
		
		public Builder dataCadastro(LocalDate dataCadastro) {
			this.dataCadastro = dataCadastro;
			return this;
		}
		
		
		public Paciente build() {
			return new Paciente(nome, dataNascimento, sexo, etnia, telefone, telefoneRecado, endereco, dataCadastro);
		}
	}
}
