package br.com.renatanutricionista.ficha.identificacao.historico.alimentar.model;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.renatanutricionista.ficha.identificacao.historico.suplemento.model.SuplementoPaciente;
import br.com.renatanutricionista.medicamento.model.Medicamento;
import br.com.renatanutricionista.paciente.model.Paciente;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "historico_alimentar", schema = "sistema_nutricionista")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonIgnoreProperties(value = "paciente")
public class HistoricoAlimentar {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "intolerancia_alergia_alimentos")
	@Size(max = 250, message = "O campo Intolerância e/ou Alergia de Alimentos do Paciente deve ter no máximo {max} caracteres!")
	private String intoleranciaAlergiaAlimentosPaciente;
	
	@Column(name = "preferencia_alimentar")
	@Size(max = 250, message = "O campo Preferência Alimentar do Paciente deve ter no máximo {max} caracteres!")
	private String preferenciaAlimentarPaciente;
	
	@Column(name = "alimentos_nao_gosta")
	@Size(max = 250, message = "O campo Alimentos que o Paciente não Gosta deve ter no máximo {max} caracteres!")
	private String alimentosPacienteNaoGosta;
	
	@Column(name = "alteracoes_gastrointestinal")
	@Size(max = 250, message = "O campo Alterações Gastrointestinal deve ter no máximo {max} caracteres!")
	private String alteracoesGastrointestinal;
	
	@Column(name = "consumo_agua")
	@NotEmpty(message = "O campo Consumo de Água não pode estar vazio/nulo!")
	@Size(max = 100, message = "O campo Consumo de Água deve ter no máximo {max} caracteres!")
	private String consumoAgua;
	
	@OneToMany(mappedBy = "historicoAlimentar", cascade = CascadeType.REMOVE)
	private Set<SuplementoPaciente> suplementosPaciente;
	
	@ManyToMany(cascade = { CascadeType.PERSIST }, fetch = FetchType.LAZY)
	@JoinTable(name = "historico_alimentar_has_medicamento", 
		joinColumns = @JoinColumn(name = "historico_alimentar_id"), 
		inverseJoinColumns = @JoinColumn(name = "medicamento_id"))
	private Set<Medicamento> medicamentos;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "paciente_id", referencedColumnName = "id")
	@NotNull(message = "O objeto Paciente não pode estar nulo!")
	private Paciente paciente;
	
	@Column(name = "data_hora_cadastro_historico_alimentar")
	@NotNull(message = "A Data e Hora do Cadastro do Histórico Alimentar não pode estar nula!")
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataHoraCadastroHistoricoAlimentar;
	
	
	public static class Builder {
		
		private String intoleranciaAlergiaAlimentosPaciente;
		private String preferenciaAlimentarPaciente;
		private String alimentosPacienteNaoGosta;
		private String alteracoesGastrointestinal;
		private String consumoAgua;
		private Set<Medicamento> medicamentos;
		private Paciente paciente;
		private LocalDateTime dataHoraCadastroHistoricoAlimentar;
		
		
		public Builder intoleranciaAlergiaAlimentosPaciente(String intoleranciaAlergiaAlimentosPaciente) {
			this.intoleranciaAlergiaAlimentosPaciente = intoleranciaAlergiaAlimentosPaciente;
			return this;
		}
		
		public Builder preferenciaAlimentarPaciente(String preferenciaAlimentarPaciente) {
			this.preferenciaAlimentarPaciente = preferenciaAlimentarPaciente;
			return this;
		}
		
		public Builder alimentosPacienteNaoGosta(String alimentosPacienteNaoGosta) {
			this.alimentosPacienteNaoGosta = alimentosPacienteNaoGosta;
			return this;
		}
		
		public Builder alteracoesGastrointestinal(String alteracoesGastrointestinal) {
			this.alteracoesGastrointestinal = alteracoesGastrointestinal;
			return this;
		}
		
		public Builder consumoAgua(String consumoAgua) {
			this.consumoAgua = consumoAgua;
			return this;
		}
		
		public Builder medicamentos(Set<Medicamento> medicamentos) {
			this.medicamentos = medicamentos;
			return this;
		}
		
		public Builder paciente(Paciente paciente) {
			this.paciente = paciente;
			return this;
		}
		
		public Builder dataHoraCadastroHistoricoAlimentar(LocalDateTime dataHoraCadastroHistoricoAlimentar) {
			this.dataHoraCadastroHistoricoAlimentar = dataHoraCadastroHistoricoAlimentar;
			return this;
		}
		
		
		public HistoricoAlimentar build() {
			return new HistoricoAlimentar(null, intoleranciaAlergiaAlimentosPaciente, preferenciaAlimentarPaciente, 
					alimentosPacienteNaoGosta, alteracoesGastrointestinal, consumoAgua, null, 
					medicamentos, paciente, dataHoraCadastroHistoricoAlimentar);
		}
	}
}
