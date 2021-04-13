package br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.model.FrequenciaAlimentar;
import br.com.renatanutricionista.paciente.model.Paciente;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "questionario_frequencia_alimentar", schema = "sistema_nutricionista")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonIgnoreProperties(value = { "paciente" })
public class QuestionarioFrequenciaAlimentar {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "data_hora_cadastro_questionario")
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@NotNull(message = "O campo Data e Hora do Cadastro do Questionário não pode estar nulo!")
	private LocalDateTime dataHoraCadastroQuestionario;
	
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinTable(name = "questionario_has_frequencia_alimentar",
			joinColumns = @JoinColumn(name = "questionario_frequencia_alimentar_id"),
			inverseJoinColumns = @JoinColumn(name = "frequencia_alimentar_id"))
	@NotNull(message = "O campo Frequências Alimentares do Questionário não pode estar nulo!")
	private Set<FrequenciaAlimentar> frequenciaConsumoAlimentos;
	
	@Column(name = "consumo_tipo_bebida")
	@Size(max = 1, message = "O campo Consumo do Tipo de Bebida dever conter somente {max} caracter!")
	private String consumoTipoBebida;
	
	@Column(name = "consumo_tipo_leite")
	@Size(max = 8, message = "O campo Consumo do Tipo de Leite dever conter no máximo {max} caracteres!")
	private String consumoTipoLeite;
	
	@Column(name = "consumo_carne_vermelha")
	@Size(max = 12, message = "O campo Código do Consumo do Tipo de Leite dever conter no máximo {max} caracteres!")
	private String consumoCarneVermelha;
	
	@Column(name = "consumo_frango")
	@Size(max = 12, message = "O campo Consumo de Frango dever conter no máximo {max} caracteres!")
	private String consumoFrango;
	
	@Column(name = "consumo_peixe")
	@Size(max = 8, message = "O campo Código do Consumo de Peixe dever conter no máximo {max} caracteres!")
	private String consumoPeixe;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "paciente_id")
	private Paciente paciente;
	
	
	public static class Builder {
		private LocalDateTime dataHoraCadastroQuestionario;
		private Set<FrequenciaAlimentar> frequenciaConsumoAlimentos;
		private String consumoTipoBebida;
		private String consumoTipoLeite;
		private String consumoCarneVermelha;
		private String consumoFrango;
		private String consumoPeixe;
		private Paciente paciente;
		
		
		public Builder dataHoraCadastroQuestionario(LocalDateTime dataHoraCadastroQuestionario) {
			this.dataHoraCadastroQuestionario = dataHoraCadastroQuestionario;
			return this;
		}
		
		public Builder frequenciaConsumoAlimentos(Set<FrequenciaAlimentar> frequenciaConsumoAlimentos) {
			this.frequenciaConsumoAlimentos = frequenciaConsumoAlimentos;
			return this;
		}
		
		public Builder consumoTipoBebida(String consumoTipoBebida) {
			this.consumoTipoBebida = consumoTipoBebida;
			return this;
		}
		
		public Builder consumoTipoLeite(String consumoTipoLeite) {
			this.consumoTipoLeite = consumoTipoLeite;
			return this;
		}
		
		public Builder consumoCarneVermelha(String consumoCarneVermelha) {
			this.consumoCarneVermelha = consumoCarneVermelha;
			return this;
		}
		
		public Builder consumoFrango(String consumoFrango) {
			this.consumoFrango = consumoFrango;
			return this;
		}
		
		public Builder consumoPeixe(String consumoPeixe) {
			this.consumoPeixe = consumoPeixe;
			return this;
		}
		
		public Builder paciente(Paciente paciente) {
			this.paciente = paciente;
			return this;
		}
		
		
		public QuestionarioFrequenciaAlimentar build() {
			return new QuestionarioFrequenciaAlimentar(null, dataHoraCadastroQuestionario, frequenciaConsumoAlimentos, 
					consumoTipoBebida, consumoTipoLeite, consumoCarneVermelha, consumoFrango, consumoPeixe, paciente);
		}
	}
}
