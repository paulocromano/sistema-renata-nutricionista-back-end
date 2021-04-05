package br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.model.FrequenciaAlimentar;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "questionario_frequencia_alimentar", schema = "sistema_nutricionista")
@Setter
@Getter
public class QuestionarioFrequenciaAlimentar {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "data_hora_cadastro_questionario")
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@NotNull(message = "O campo Data e Hora do Cadastro do Questionário não pode estar nulo!")
	private LocalDateTime dataHoraCadastroQuestionario;
	
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinTable(name = "questionario_has_frequencia_alimentar",
			joinColumns = @JoinColumn(name = "questionario_frequencia_alimentar_id"),
			inverseJoinColumns = @JoinColumn(name = "frequencia_alimentar_id"))
	@NotNull(message = "O campo Frequências Alimentares do Questionário não pode estar nulo!")
	private List<FrequenciaAlimentar> frequenciaConsumoAlimentos;
	
	@Column(name = "consumo_tipo_bebida")
	@Size(max = 1, message = "O campo Consumo do Tipo de Bebida dever conter somente {max} caracter!")
	private String consumoTipoBebida;
	
	@Column(name = "consumo_tipo_leite")
	@Size(max = 8, message = "O campo Consumo do Tipo de Leite dever conter no máximo {max} caracteres!")
	private String consumoTipoLeite;
	
	@Column(name = "consumo_carne_vermelha")
	@Size(max = 8, message = "O campo Código do Consumo do Tipo de Leite dever conter no máximo {max} caracteres!")
	private String consumoCarneVermelha;
	
	@Column(name = "consumo_frango")
	@Size(max = 8, message = "O campo Consumo de Frango dever conter no máximo {max} caracteres!")
	private String ConsumoFrango;
	
	@Column(name = "consumo_peixe")
	@Size(max = 8, message = "O campo Código do Consumo de Peixe dever conter no máximo {max} caracteres!")
	private String consumoPeixe;
}
