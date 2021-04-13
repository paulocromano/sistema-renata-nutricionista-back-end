package br.com.renatanutricionista.ficha.identificacao.historico.patologia.por.data.familiares.model;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.renatanutricionista.ficha.identificacao.historico.patologia.familiares.model.HistoricoPatologiaFamiliares;
import br.com.renatanutricionista.paciente.model.Paciente;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "historico_patologia_familiares_por_data", schema = "sistema_nutricionista")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonIgnoreProperties(value = "paciente")
public class HistoricoPatologiaFamiliaresPorData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(max = 500, message = "o campo Observação deve ter no máximo {max} caracteres!")
	private String observacao;
	
	@Column(name = "data_hora_cadastro_patologias_familiares")
	@NotNull(message = "O campo Data e Hora do Cadastro das Patologias dos Familiares não pode estar nulo!")
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataHoraCadastroPatologiasFamiliares;
	
	@OneToMany(mappedBy = "historicoPatologiaFamiliaresPorData", 
			cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, 
			fetch = FetchType.LAZY)
	private Set<HistoricoPatologiaFamiliares> patologiasFamiliares;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "paciente_id")
	@NotNull(message = "O objeto Paciente não pode estar nulo!")
	private Paciente paciente;
	
	
	public static class Builder {
		
		private String observacao;
		private LocalDateTime dataHoraCadastroPatologiasFamiliares;
		private Paciente paciente;
		
		
		public Builder observacao(String observacao) {
			this.observacao = observacao;
			return this;
		}
		
		public Builder dataHoraCadastroPatologiasFamiliares(LocalDateTime dataHoraCadastroPatologiasFamiliares) {
			this.dataHoraCadastroPatologiasFamiliares = dataHoraCadastroPatologiasFamiliares;
			return this;
		}
		
		
		public Builder paciente(Paciente paciente) {
			this.paciente = paciente;
			return this;
		}
		
		
		public HistoricoPatologiaFamiliaresPorData build() {
			return new HistoricoPatologiaFamiliaresPorData(null, observacao, dataHoraCadastroPatologiasFamiliares, null, paciente);
		}
	}
}
