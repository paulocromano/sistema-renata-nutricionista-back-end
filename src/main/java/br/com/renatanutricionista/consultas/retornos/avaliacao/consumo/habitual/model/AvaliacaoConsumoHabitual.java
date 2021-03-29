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
import br.com.renatanutricionista.consultas.retornos.retorno.model.RetornoConsulta;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "avaliacao_consumo_habitual", schema = "sistema_nutricionista")
@Setter
@Getter
@NoArgsConstructor
@JsonIgnoreProperties(value = { "consulta", "retornoConsulta" })
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
	
	@OneToOne(mappedBy = "avaliacaoConsumoHabitual")
	private RetornoConsulta retornoConsulta;
	

	private AvaliacaoConsumoHabitual(BigDecimal energiaKcal, BigDecimal proteinaTotalGramas, BigDecimal proteinaAVBGramas, 
			BigDecimal carboidratoGramas, BigDecimal lipideoTotalGramas, BigDecimal lipideoSaturadoGramas, 
			BigDecimal lipideoPoliinsaturadoGramas, BigDecimal lipideoMonoinsaturadoGramas, BigDecimal colesterolMiligramas, 
			BigDecimal fibraGramas, BigDecimal calcioGramas, BigDecimal fosforoGramas, BigDecimal ferroMiligramas, 
			BigDecimal zincoMiligramas, BigDecimal magnesioMiligramas, BigDecimal potassioMiligramas, BigDecimal vitaminaB6Miligramas, 
			BigDecimal vitaminaCMiligramas, BigDecimal leucinaMiligramas) {
		
		this.energiaKcal = energiaKcal;
		this.proteinaTotalGramas = proteinaTotalGramas;
		this.proteinaAVBGramas = proteinaAVBGramas;
		this.carboidratoGramas = carboidratoGramas;
		this.lipideoTotalGramas = lipideoTotalGramas;
		this.lipideoSaturadoGramas = lipideoSaturadoGramas;
		this.lipideoPoliinsaturadoGramas = lipideoPoliinsaturadoGramas;
		this.lipideoMonoinsaturadoGramas = lipideoMonoinsaturadoGramas;
		this.colesterolMiligramas = colesterolMiligramas;
		this.fibraGramas = fibraGramas;
		this.calcioGramas = calcioGramas;
		this.fosforoGramas = fosforoGramas;
		this.ferroMiligramas = ferroMiligramas;
		this.zincoMiligramas = zincoMiligramas;
		this.magnesioMiligramas = magnesioMiligramas;
		this.potassioMiligramas = potassioMiligramas;
		this.vitaminaB6Miligramas = vitaminaB6Miligramas;
		this.vitaminaCMiligramas = vitaminaCMiligramas;
		this.leucinaMiligramas = leucinaMiligramas;
	}


	public static class Builder {
		
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
		
		
		public Builder energiaKcal(BigDecimal energiaKcal) {
			this.energiaKcal = energiaKcal;
			return this;
		}
		
		public Builder proteinaTotalGramas(BigDecimal proteinaTotalGramas) {
			this.proteinaTotalGramas = proteinaTotalGramas;
			return this;
		}
		
		public Builder proteinaAVBGramas(BigDecimal proteinaAVBGramas) {
			this.proteinaAVBGramas = proteinaAVBGramas;
			return this;
		}
		
		public Builder carboidratoGramas(BigDecimal carboidratoGramas) {
			this.carboidratoGramas = carboidratoGramas;
			return this;
		}
		
		public Builder lipideoTotalGramas(BigDecimal lipideoTotalGramas) {
			this.lipideoTotalGramas = lipideoTotalGramas;
			return this;
		}
		
		public Builder lipideoSaturadoGramas(BigDecimal lipideoSaturadoGramas) {
			this.lipideoSaturadoGramas = lipideoSaturadoGramas;
			return this;
		}
		
		public Builder lipideoPoliinsaturadoGramas(BigDecimal lipideoPoliinsaturadoGramas) {
			this.lipideoPoliinsaturadoGramas = lipideoPoliinsaturadoGramas;
			return this;
		}
		
		public Builder lipideoMonoinsaturadoGramas(BigDecimal lipideoMonoinsaturadoGramas) {
			this.lipideoMonoinsaturadoGramas = lipideoMonoinsaturadoGramas;
			return this;
		}
		
		public Builder colesterolMiligramas(BigDecimal colesterolMiligramas) {
			this.colesterolMiligramas = colesterolMiligramas;
			return this;
		}
		
		public Builder fibraGramas(BigDecimal fibraGramas) {
			this.fibraGramas = fibraGramas;
			return this;
		}
		
		public Builder calcioGramas(BigDecimal calcioGramas) {
			this.calcioGramas = calcioGramas;
			return this;
		}
		
		public Builder fosforoGramas(BigDecimal fosforoGramas) {
			this.fosforoGramas = fosforoGramas;
			return this;
		}
		
		public Builder ferroMiligramas(BigDecimal ferroMiligramas) {
			this.ferroMiligramas = ferroMiligramas;
			return this;
		}
		
		public Builder zincoMiligramas(BigDecimal zincoMiligramas) {
			this.zincoMiligramas = zincoMiligramas;
			return this;
		}
		
		public Builder magnesioMiligramas(BigDecimal magnesioMiligramas) {
			this.magnesioMiligramas = magnesioMiligramas;
			return this;
		}
		
		public Builder potassioMiligramas(BigDecimal potassioMiligramas) {
			this.potassioMiligramas = potassioMiligramas;
			return this;
		}
		
		public Builder vitaminaB6Miligramas(BigDecimal vitaminaB6Miligramas) {
			this.vitaminaB6Miligramas = vitaminaB6Miligramas;
			return this;
		}
		
		public Builder vitaminaCMiligramas(BigDecimal vitaminaCMiligramas) {
			this.vitaminaCMiligramas = vitaminaCMiligramas;
			return this;
		}
		
		public Builder leucinaMiligramas(BigDecimal leucinaMiligramas) {
			this.leucinaMiligramas = leucinaMiligramas;
			return this;
		}
		
		
		public AvaliacaoConsumoHabitual build() {
			return new AvaliacaoConsumoHabitual(energiaKcal, proteinaTotalGramas, proteinaAVBGramas, carboidratoGramas, 
					lipideoTotalGramas, lipideoSaturadoGramas, lipideoPoliinsaturadoGramas, lipideoMonoinsaturadoGramas, 
					colesterolMiligramas, fibraGramas, calcioGramas, fosforoGramas, ferroMiligramas, zincoMiligramas, 
					magnesioMiligramas, potassioMiligramas, vitaminaB6Miligramas, vitaminaCMiligramas, leucinaMiligramas);
		}
	}
}
