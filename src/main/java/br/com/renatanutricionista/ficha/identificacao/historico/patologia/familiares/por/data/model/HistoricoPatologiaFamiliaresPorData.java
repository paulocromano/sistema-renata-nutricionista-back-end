package br.com.renatanutricionista.ficha.identificacao.historico.patologia.familiares.por.data.model;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.renatanutricionista.ficha.identificacao.historico.patologia.familiares.model.HistoricoPatologiaFamiliares;
import br.com.renatanutricionista.paciente.model.Paciente;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "historico_patologia_familiares_por_data", schema = "sistema_nutricionista")
@Getter
@Setter
@JsonIgnoreProperties(value = "paciente")
public class HistoricoPatologiaFamiliaresPorData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(max = 500, message = "o campo Observação deve ter no máximo {max} caracteres!")
	private String observacao;
	
	@Column(name = "data_hora_cadastro_patologias_familiares")
	@NotNull(message = "O campo Data e Hora do Cadastro das Patologias dos Familiares não pode estar nulo!")
	private LocalDateTime dataHoraCadastroPatologiasFamiliares;
	
	@OneToMany(mappedBy = "historicoPatologiaFamiliaresPorData", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<HistoricoPatologiaFamiliares> patologiasFamiliares;
	
	@ManyToOne
	@JoinColumn(name = "paciente_id")
	private Paciente paciente;
}
