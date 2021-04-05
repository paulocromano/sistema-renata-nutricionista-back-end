package br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.alimentos.model.AlimentoFrequenciaAlimentar;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.enums.frequencia.consumo.FrequenciaConsumoAlimento;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.model.QuestionarioFrequenciaAlimentar;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "frequencia_alimentar", schema = "sistema_nutricionista")
@Setter
@Getter
@JsonIgnoreProperties(value = { "questionarioFrequenciaAlimentar" })
public class FrequenciaAlimentar {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "alimento_frequencia_alimentar_id")
	private AlimentoFrequenciaAlimentar alimentoFrequenciaAlimentar;
	
	@Column(name = "frequencia_consumo_alimento")
	@NotNull(message = "O campo Frequência do Consumo do Alimento não pode estar nulo!")
	private FrequenciaConsumoAlimento frequenciaConsumoAlimento;
	
	@ManyToMany(mappedBy = "frequenciaConsumoAlimentos")
	private List<QuestionarioFrequenciaAlimentar> questionariosFrequenciaAlimentar;

	
	public FrequenciaAlimentar(AlimentoFrequenciaAlimentar alimentoFrequenciaAlimentar, FrequenciaConsumoAlimento frequenciaConsumoAlimento) {
		this.alimentoFrequenciaAlimentar = alimentoFrequenciaAlimentar;
		this.frequenciaConsumoAlimento = frequenciaConsumoAlimento;
	}
}
