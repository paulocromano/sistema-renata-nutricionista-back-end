package br.com.renatanutricionista.ficha.identificacao.historico.atividade.fisica.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.renatanutricionista.ficha.identificacao.historico.atividade.fisica.enums.FrequenciaAtividadeFisica;
import br.com.renatanutricionista.paciente.model.Paciente;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "historico_atividade_fisica", schema = "sistema_nutricionista")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonIgnoreProperties(value = "paciente")
public class HistoricoAtividadeFisica {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "atividade_praticada")
	@Size(max = 100, message = "O campo Atividades Praticadas deve ter no máximo {max} caracteres!")
	private String atividadePraticada;
	
	@NotNull(message = "O campo Frequência de Atividade Física não pode estar nulo!")
	private FrequenciaAtividadeFisica frequencia;
	
	@Size(max = 5, message = "O campo Duração deve ter no máximo {max} caracteres!")
	private String duracao;
	
	@ManyToOne
	@JoinColumn(name = "paciente_id", referencedColumnName = "id")
	@NotNull(message = "O objeto Paciente não pode estar nulo!")
	private Paciente paciente;
	
	@Column(name = "data_hora_cadastro_atividade_fisica")
	@NotNull(message = "A Data e Hora do Cadastro da Atividade Física não pode estar nula!")
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataHoraCadastroAtividadeFisica;

	
	public static class Builder {
		
		private String atividadePraticada;
		private FrequenciaAtividadeFisica frequencia;
		private String duracao;
		private Paciente paciente;
		private LocalDateTime dataHoraCadastroAtividadeFisica;
		
		
		public Builder atividadePraticada(String atividadePraticada) {
			this.atividadePraticada = atividadePraticada;
			return this;
		}
		
		public Builder frequencia(FrequenciaAtividadeFisica frequencia) {
			this.frequencia = frequencia;
			return this;
		}
		
		public Builder duracao(String duracao) {
			this.duracao = duracao;
			return this;
		}
		
		public Builder paciente(Paciente paciente) {
			this.paciente = paciente;
			return this;
		}
		
		public Builder dataHoraCadastroAtividadeFisica(LocalDateTime dataHoraCadastroAtividadeFisica) {
			this.dataHoraCadastroAtividadeFisica = dataHoraCadastroAtividadeFisica;
			return this;
		}
		
		
		public HistoricoAtividadeFisica build() {
			return new HistoricoAtividadeFisica(null, atividadePraticada, frequencia, duracao, paciente, dataHoraCadastroAtividadeFisica);
		}
	}
}
