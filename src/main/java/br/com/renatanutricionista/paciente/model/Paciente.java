package br.com.renatanutricionista.paciente.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

import br.com.renatanutricionista.endereco.model.Endereco;
import br.com.renatanutricionista.ficha.identificacao.atividade.fisica.model.AtividadeFisica;
import br.com.renatanutricionista.ficha.identificacao.historico.alimentar.model.HistoricoAlimentar;
import br.com.renatanutricionista.ficha.identificacao.historico.social.model.HistoricoSocial;
import br.com.renatanutricionista.paciente.enums.Etnia;
import br.com.renatanutricionista.utils.enums.SexoUtils;
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
	
	@Column(name = "sexo")
	@NotNull(message = "O campo Sexo não pode estar nulo!")
	private SexoUtils sexo;
	
	@NotNull(message = "O campo Etnia não pode estar nulo!")
	private Etnia etnia;
	
	@NotEmpty(message = "O campo Telefone não pode estar nulo/vazio!")
	@Size(max = 15, message = "O campo Telefone deve ter no máximo {max} caracteres!")
	@Pattern(regexp = "(\\(?\\d{2}\\)?\\s)?(\\d{4,5}\\-\\d{4})", message = "O formato do número de Telefone é inválido!")
	private String telefone;
	
	@Column(name = "telefone_recado")
	@Size(max = 15, message = "O campo Telefone deve ter no máximo {max} caracteres!")
	@Pattern(regexp = "(\\(?\\d{2}\\)?\\s)?(\\d{4,5}\\-\\d{4})", message = "O formato do número do Telefone para Recado é inválido!")
	private String telefoneRecado;
	
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinColumn(name = "endereco_id")
	@NotNull(message = "O objeto Endereço do Paciente não pode estar nulo!")
	private Endereco endereco;
	
	@OneToMany(mappedBy = "paciente", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<HistoricoSocial> historicoSocial;
	
	@OneToMany(mappedBy = "paciente", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<HistoricoAlimentar> historicoAlimentar;
	
	@OneToMany(mappedBy = "paciente", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<AtividadeFisica> atividadeFisica;
	
	@Column(name = "data_ultima_atualizacao_dados_paciente")
	@NotNull(message = "A Data da Última Atualização dos Dados do Paciente não pode estar nula!")
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime dataUltimaAtualizacaoDadosDoPaciente;
	
	
	private Paciente(String nome, LocalDate dataNascimento, SexoUtils sexo, Etnia etnia, String telefone, 
			String telefoneRecado, Endereco endereco, LocalDateTime dataUltimaAtualizacaoDadosDoPaciente) {
		
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.etnia = etnia;
		this.telefone = telefone;
		this.telefoneRecado = telefoneRecado;
		this.endereco = endereco;
		this.dataUltimaAtualizacaoDadosDoPaciente = dataUltimaAtualizacaoDadosDoPaciente;
	}


	public static class PacienteBuilder {
		private String nome;
		private LocalDate dataNascimento;
		private SexoUtils sexo;
		private Etnia etnia;
		private String telefone;
		private String telefoneRecado;
		private Endereco endereco;
		private LocalDateTime dataUltimaAtualizacaoDadosDoPaciente;
		
		
		public PacienteBuilder nome(String nome) {
			this.nome = nome;
			return this;
		}
		
		public PacienteBuilder dataNascimento(LocalDate dataNascimento) {
			this.dataNascimento = dataNascimento;
			return this;
		}
		
		public PacienteBuilder sexo(SexoUtils sexo) {
			this.sexo = sexo;
			return this;
		}
		
		public PacienteBuilder etnia(Etnia etnia) {
			this.etnia = etnia;
			return this;
		}
		
		public PacienteBuilder telefone(String telefone) {
			this.telefone = telefone;
			return this;
		}
		
		public PacienteBuilder telefoneRecado(String telefoneRecado) {
			this.telefoneRecado = telefoneRecado;
			return this;
		}
		
		public PacienteBuilder endereco(Endereco endereco) {
			this.endereco = endereco;
			return this;
		}
		
		public PacienteBuilder dataUltimaAtualizacaoDadosDoPaciente(LocalDateTime dataUltimaAtualizacaoDadosDoPaciente) {
			this.dataUltimaAtualizacaoDadosDoPaciente = dataUltimaAtualizacaoDadosDoPaciente;
			return this;
		}
		
		
		public Paciente criarPaciente() {
			return new Paciente(nome, dataNascimento, sexo, etnia, telefone, telefoneRecado, endereco, dataUltimaAtualizacaoDadosDoPaciente);
		}
	}
}
