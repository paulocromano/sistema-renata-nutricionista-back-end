package br.com.renatanutricionista.consultas.retornos.avaliacao.consumo.habitual.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.renatanutricionista.consultas.retornos.consulta.model.Consulta;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "avaliacao_consumo_habitual", schema = "sistema_nutricionista")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonIgnoreProperties(value = "consulta")
public class AvaliacaoConsumoHabitual {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "energia_kcal")
	@Digits(integer = 10, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Energia Kcal deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Energia Kcal não pode estar nulo!")
	private BigDecimal energiaKcal;
	
	@Column(name = "proteina_total_g")
	@Digits(integer = 10, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Proteína Total em Gramas deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Proteína Total em Gramas não pode estar nulo!")
	private BigDecimal proteinaTotalGramas;
	
	@Column(name = "proteina_avb_g")
	@Digits(integer = 10, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Proteína AVB em Gramas deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Proteína AVB em Gramas não pode estar nulo!")
	private BigDecimal proteinaAVBGramas;
	
	@Column(name = "carboidrato_g")
	@Digits(integer = 10, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Carboidrato em Gramas deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Carboidrato em Gramas não pode estar nulo!")
	private BigDecimal carboidratoGramas;
	
	@Column(name = "lipideo_total_g")
	@Digits(integer = 10, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Lipídeo Total em Gramas deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Lipídeo Total em Gramas não pode estar nulo!")
	private BigDecimal lipideoTotalGramas;
	
	@Column(name = "lipideo_saturado_g")
	@Digits(integer = 10, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Lipídeo Saturado em Gramas deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Lipídeo Saturado em Gramas não pode estar nulo!")
	private BigDecimal lipideoSaturadoGramas;
	
	@Column(name = "lipideo_poliinsaturado_g")
	@Digits(integer = 10, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Lipídeo Poliinsaturado em Gramas deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Lipídeo Poliinsaturado em Gramas não pode estar nulo!")
	private BigDecimal lipideoPoliinsaturadoGramas;
	
	@Column(name = "lipideo_monoinsaturado_g")
	@Digits(integer = 10, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Lipídeo Monoinsaturado em Gramas deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Lipídeo Monoinsaturado em Gramas não pode estar nulo!")
	private BigDecimal lipideoMonoinsaturadoGramas;
	
	@Column(name = "colesterol_mg")
	@Digits(integer = 10, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Colesterol em Miligramas deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Colesterol em Miligramas não pode estar nulo!")
	private BigDecimal colesterolMiligramas;
	
	@Column(name = "fibra_g")
	@Digits(integer = 10, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Fibra em Gramas  deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Fibra em Gramas não pode estar nulo!")
	private BigDecimal fibraGramas;
	
	@Column(name = "calcio_g")
	@Digits(integer = 10, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Lipídeo Monoinsaturado em Gramas deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Lipídeo Monoinsaturado em Gramas não pode estar nulo!")
	private BigDecimal calcioGramas;
	
	@Column(name = "fosforo_g")
	@Digits(integer = 10, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Fósforo em Gramas deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Fósforo em Gramas não pode estar nulo!")
	private BigDecimal fosforoGramas;
	
	@Column(name = "ferro_mg")
	@Digits(integer = 10, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Ferro em Miligramas deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Ferro em Miligramas não pode estar nulo!")
	private BigDecimal ferroMiligramas;
	
	@Column(name = "zinco_mg")
	@Digits(integer = 10, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Zinco em Miligramas deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Zinco em Miligramas não pode estar nulo!")
	private BigDecimal zincoMiligramas;
	
	@Column(name = "magnesio_mg")
	@Digits(integer = 10, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Magnésio em Miligramas deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Magnésio em Miligramas não pode estar nulo!")
	private BigDecimal magnesioMiligramas;
	
	@Column(name = "potassio_mg")
	@Digits(integer = 10, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Potássio em Miligramas deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Potássio em Miligramas não pode estar nulo!")
	private BigDecimal potassioMiligramas;
	
	@Column(name = "vitamina_b6_mg")
	@Digits(integer = 10, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Vitamina B6 em Miligramas deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Vitamina B6 em Miligramas não pode estar nulo!")
	private BigDecimal vitaminaB6Miligramas;
	
	@Column(name = "vitamina_c_mg")
	@Digits(integer = 10, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Vitamina C em Miligramas deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Vitamina C em Miligramas não pode estar nulo!")
	private BigDecimal vitaminaCMiligramas;
	
	@Column(name = "leucina_mg")
	@Digits(integer = 10, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Leucina em Miligramas deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Leucina em Miligramas não pode estar nulo!")
	private BigDecimal leucinaMiligramas;
	
	@OneToOne(mappedBy = "avaliacaoConsumoHabitual")
	private Consulta consulta;
	
	
	public static class AvaliacaoConsumoHabitualBuilder {
		
		private BigDecimal energiaKcal;
		private BigDecimal proteinaTotalGramas;
		private BigDecimal proteinaAVBGramas;
		private BigDecimal carboidratoGramas;
		private BigDecimal lipideoTotalGramas;
		private BigDecimal lipideoSaturadoGramas;
		private BigDecimal lipideoPoliinsaturadoGramas;
		private BigDecimal lipideoMonoinsaturadoGramas;
		private BigDecimal colesterolMiligramas;
		private BigDecimal fibraGramas;
		private BigDecimal calcioGramas;
		private BigDecimal fosforoGramas;
		private BigDecimal ferroMiligramas;
		private BigDecimal zincoMiligramas;
		private BigDecimal magnesioMiligramas;
		private BigDecimal potassioMiligramas;
		private BigDecimal vitaminaB6Miligramas;
		private BigDecimal vitaminaCMiligramas;
		private BigDecimal leucinaMiligramas;
		
		
		public AvaliacaoConsumoHabitualBuilder energiaKcal(BigDecimal energiaKcal) {
			this.energiaKcal = energiaKcal;
			return this;
		}
		
		public AvaliacaoConsumoHabitualBuilder proteinaTotalGramas(BigDecimal proteinaTotalGramas) {
			this.proteinaTotalGramas = proteinaTotalGramas;
			return this;
		}
		
		public AvaliacaoConsumoHabitualBuilder proteinaAVBGramas(BigDecimal proteinaAVBGramas) {
			this.proteinaAVBGramas = proteinaAVBGramas;
			return this;
		}
		
		public AvaliacaoConsumoHabitualBuilder carboidratoGramas(BigDecimal carboidratoGramas) {
			this.carboidratoGramas = carboidratoGramas;
			return this;
		}
		
		public AvaliacaoConsumoHabitualBuilder lipideoTotalGramas(BigDecimal lipideoTotalGramas) {
			this.lipideoTotalGramas = lipideoTotalGramas;
			return this;
		}
		
		public AvaliacaoConsumoHabitualBuilder lipideoSaturadoGramas(BigDecimal lipideoSaturadoGramas) {
			this.lipideoSaturadoGramas = lipideoSaturadoGramas;
			return this;
		}
		
		public AvaliacaoConsumoHabitualBuilder lipideoPoliinsaturadoGramas(BigDecimal lipideoPoliinsaturadoGramas) {
			this.lipideoPoliinsaturadoGramas = lipideoPoliinsaturadoGramas;
			return this;
		}
		
		public AvaliacaoConsumoHabitualBuilder lipideoMonoinsaturadoGramas(BigDecimal lipideoMonoinsaturadoGramas) {
			this.lipideoMonoinsaturadoGramas = lipideoMonoinsaturadoGramas;
			return this;
		}
		
		public AvaliacaoConsumoHabitualBuilder colesterolMiligramas(BigDecimal colesterolMiligramas) {
			this.colesterolMiligramas = colesterolMiligramas;
			return this;
		}
		
		public AvaliacaoConsumoHabitualBuilder fibraGramas(BigDecimal fibraGramas) {
			this.fibraGramas = fibraGramas;
			return this;
		}
		
		public AvaliacaoConsumoHabitualBuilder calcioGramas(BigDecimal calcioGramas) {
			this.calcioGramas = calcioGramas;
			return this;
		}
		
		public AvaliacaoConsumoHabitualBuilder fosforoGramas(BigDecimal fosforoGramas) {
			this.fosforoGramas = fosforoGramas;
			return this;
		}
		
		public AvaliacaoConsumoHabitualBuilder ferroMiligramas(BigDecimal ferroMiligramas) {
			this.ferroMiligramas = ferroMiligramas;
			return this;
		}
		
		public AvaliacaoConsumoHabitualBuilder zincoMiligramas(BigDecimal zincoMiligramas) {
			this.zincoMiligramas = zincoMiligramas;
			return this;
		}
		
		public AvaliacaoConsumoHabitualBuilder magnesioMiligramas(BigDecimal magnesioMiligramas) {
			this.magnesioMiligramas = magnesioMiligramas;
			return this;
		}
		
		public AvaliacaoConsumoHabitualBuilder potassioMiligramas(BigDecimal potassioMiligramas) {
			this.potassioMiligramas = potassioMiligramas;
			return this;
		}
		
		public AvaliacaoConsumoHabitualBuilder vitaminaB6Miligramas(BigDecimal vitaminaB6Miligramas) {
			this.vitaminaB6Miligramas = vitaminaB6Miligramas;
			return this;
		}
		
		public AvaliacaoConsumoHabitualBuilder vitaminaCMiligramas(BigDecimal vitaminaCMiligramas) {
			this.vitaminaCMiligramas = vitaminaCMiligramas;
			return this;
		}
		
		public AvaliacaoConsumoHabitualBuilder leucinaMiligramas(BigDecimal leucinaMiligramas) {
			this.leucinaMiligramas = leucinaMiligramas;
			return this;
		}
		
		
		public AvaliacaoConsumoHabitual criarAvaliacaoConsumoHabitual() {
			return new AvaliacaoConsumoHabitual(null, energiaKcal, proteinaTotalGramas, proteinaAVBGramas, carboidratoGramas, 
					lipideoTotalGramas, lipideoSaturadoGramas, lipideoPoliinsaturadoGramas, lipideoMonoinsaturadoGramas, 
					colesterolMiligramas, fibraGramas, calcioGramas, fosforoGramas, ferroMiligramas, zincoMiligramas, 
					magnesioMiligramas, potassioMiligramas, vitaminaB6Miligramas, vitaminaCMiligramas, leucinaMiligramas, null);
		}
	}
}
