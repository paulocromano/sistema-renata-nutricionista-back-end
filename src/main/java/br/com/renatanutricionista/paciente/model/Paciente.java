package br.com.renatanutricionista.paciente.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
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
import br.com.renatanutricionista.ficha.identificacao.atividade.fisica.model.AtividadeFisica;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.model.QuestionarioFrequenciaAlimentar;
import br.com.renatanutricionista.ficha.identificacao.historico.alimentar.model.HistoricoAlimentar;
import br.com.renatanutricionista.ficha.identificacao.historico.patologia.por.data.familiares.model.HistoricoPatologiaFamiliaresPorData;
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
	
	@OneToMany(mappedBy = "paciente", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private Set<HistoricoPatologiaFamiliaresPorData> historicoPatologiaFamiliaresPorData;
	
	@OneToMany(mappedBy = "paciente", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<QuestionarioFrequenciaAlimentar> questionarioFrequenciaAlimentar;
	
	@OneToMany(mappedBy = "paciente", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<Consulta> consultas;
	
	@Column(name = "data_hora_ultima_atualizacao_dados_paciente")
	@NotNull(message = "A Data e Hora da Última Atualização dos Dados do Paciente não pode estar nula!")
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataHoraUltimaAtualizacaoDadosDoPaciente;
	
	
	private Paciente(String nome, LocalDate dataNascimento, SexoUtils sexo, Etnia etnia, String telefone, 
			String telefoneRecado, Endereco endereco, LocalDateTime dataHoraUltimaAtualizacaoDadosDoPaciente) {
		
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.etnia = etnia;
		this.telefone = telefone;
		this.telefoneRecado = telefoneRecado;
		this.endereco = endereco;
		this.dataHoraUltimaAtualizacaoDadosDoPaciente = dataHoraUltimaAtualizacaoDadosDoPaciente;
	}


	public static class Builder {
		private String nome;
		private LocalDate dataNascimento;
		private SexoUtils sexo;
		private Etnia etnia;
		private String telefone;
		private String telefoneRecado;
		private Endereco endereco;
		private LocalDateTime dataHoraUltimaAtualizacaoDadosDoPaciente;
		
		
		public Builder nome(String nome) {
			this.nome = nome;
			return this;
		}
		
		public Builder dataNascimento(LocalDate dataNascimento) {
			this.dataNascimento = dataNascimento;
			return this;
		}
		
		public Builder sexo(SexoUtils sexo) {
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
			this.telefoneRecado = telefoneRecado;
			return this;
		}
		
		public Builder endereco(Endereco endereco) {
			this.endereco = endereco;
			return this;
		}
		
		public Builder dataHoraUltimaAtualizacaoDadosDoPaciente(LocalDateTime dataHoraUltimaAtualizacaoDadosDoPaciente) {
			this.dataHoraUltimaAtualizacaoDadosDoPaciente = dataHoraUltimaAtualizacaoDadosDoPaciente;
			return this;
		}
		
		
		public Paciente build() {
			return new Paciente(nome, dataNascimento, sexo, etnia, telefone, telefoneRecado, endereco, dataHoraUltimaAtualizacaoDadosDoPaciente);
		}
	}
}
