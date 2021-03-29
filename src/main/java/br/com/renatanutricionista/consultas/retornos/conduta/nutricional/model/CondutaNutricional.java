package br.com.renatanutricionista.consultas.retornos.conduta.nutricional.model;

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
@Table(name = "conduta_nutricional", schema = "sistema_nutricionista")
@Setter
@Getter
@NoArgsConstructor
@JsonIgnoreProperties(value = { "consulta", "retornoConsulta" })
public class CondutaNutricional {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "energia_kcal_total")
	@Digits(integer = 10, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Energia Kcal Total deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Energia Kcal Total não pode estar nulo!")
	private BigDecimal energiaKcalTotal;
	
	@Column(name = "carboidrato_total_g")
	@Digits(integer = 10, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Carboidrato Total em Gramas deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Carboidrato Total em Gramas não pode estar nulo!")
	private BigDecimal carboidratroTotalGramas;
	
	@Column(name = "carboidrato_g_kg_peso")
	@Digits(integer = 10, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Carboidrato g/Kg Peso deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Carboidrato g/Kg Peso não pode estar nulo!")
	private BigDecimal carboidratoGramasKgPeso;
	
	@Column(name = "carboidrato_g_kg_mm")
	@Digits(integer = 10, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Carboidrato g/Kg Massa Magra deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Carboidrato g/Kg Massa Magra não pode estar nulo!")
	private BigDecimal carboidratoGramasKgMassaMagra;
	
	@Column(name = "proteina_total_g")
	@Digits(integer = 10, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Proteína Total em Gramas deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Proteína Total em Gramas não pode estar nulo!")
	private BigDecimal proteinaTotalGramas;
	
	@Column(name = "proteina_avb_g")
	@Digits(integer = 10, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Proteína Total AVB em Gramas deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Proteína Total AVB em Gramas não pode estar nulo!")
	private BigDecimal proteinaAVBGramas;
	
	@Column(name = "proteina_avb_g_kg_peso")
	@Digits(integer = 10, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Proteína Total AVB g/Kg Peso deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Proteína Total AVB g/Kg Peso não pode estar nulo!")
	private BigDecimal proteinaAVBGramasKgPeso;
	
	@Column(name = "lipideo_total")
	@Digits(integer = 10, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Lipídeo Total deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Lipídeo Total não pode estar nulo!")
	private BigDecimal lipideoTotal;
	
	@Column(name = "lipideo_saturado")
	@Digits(integer = 10, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Lipídeo Saturado deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Lipídeo Saturado não pode estar nulo!")
	private BigDecimal lipideoSaturado;
	
	@Column(name = "lipideo_poliinsaturado")
	@Digits(integer = 10, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Lipídeo Poliinsaturado deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Lipídeo Poliinsaturado não pode estar nulo!")
	private BigDecimal lipideoPoiinsaturado;
	
	@Column(name = "lipideo_monoinsaturado")
	@Digits(integer = 10, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Lipídeo Monoinsaturado deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Lipídeo Monoinsaturado não pode estar nulo!")
	private BigDecimal lipideoMonoinsaturado;
	
	@Column(name = "relacao_lipideo_insaturado_saturado")
	@Digits(integer = 10, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Relação Lipídeo Insaturado/Saturado deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Relação Lipídeo Insaturado/Saturado não pode estar nulo!")
	private BigDecimal relacaoLipideoInsaturadoSaturado;
	
	@OneToOne(mappedBy = "condutaNutricional")
	private Consulta consulta;
	
	@OneToOne(mappedBy = "condutaNutricional")
	private RetornoConsulta retornoConsulta;
	
	
	private CondutaNutricional(BigDecimal energiaKcalTotal, BigDecimal carboidratroTotalGramas, BigDecimal carboidratoGramasKgPeso,
			BigDecimal carboidratoGramasKgMassaMagra, BigDecimal proteinaTotalGramas, BigDecimal proteinaAVBGramas,
			BigDecimal proteinaAVBGramasKgPeso, BigDecimal lipideoTotal, BigDecimal lipideoSaturado, BigDecimal lipideoPoiinsaturado,
			BigDecimal lipideoMonoinsaturado, BigDecimal relacaoLipideoInsaturadoSaturado) {
		
		this.energiaKcalTotal = energiaKcalTotal;
		this.carboidratroTotalGramas = carboidratroTotalGramas;
		this.carboidratoGramasKgPeso = carboidratoGramasKgPeso;
		this.carboidratoGramasKgMassaMagra = carboidratoGramasKgMassaMagra;
		this.proteinaTotalGramas = proteinaTotalGramas;
		this.proteinaAVBGramas = proteinaAVBGramas;
		this.proteinaAVBGramasKgPeso = proteinaAVBGramasKgPeso;
		this.lipideoTotal = lipideoTotal;
		this.lipideoSaturado = lipideoSaturado;
		this.lipideoPoiinsaturado = lipideoPoiinsaturado;
		this.lipideoMonoinsaturado = lipideoMonoinsaturado;
		this.relacaoLipideoInsaturadoSaturado = relacaoLipideoInsaturadoSaturado;
	}


	public static class Builder {
		
		private BigDecimal energiaKcalTotal;
		private BigDecimal carboidratroTotalGramas;
		private BigDecimal carboidratoGramasKgPeso;
		private BigDecimal carboidratoGramasKgMassaMagra;
		private BigDecimal proteinaTotalGramas;
		private BigDecimal proteinaAVBGramas;
		private BigDecimal proteinaAVBGramasKgPeso;
		private BigDecimal lipideoTotal;
		private BigDecimal lipideoSaturado;
		private BigDecimal lipideoPoiinsaturado;
		private BigDecimal lipideoMonoinsaturado;
		private BigDecimal relacaoLipideoInsaturadoSaturado;
		
		
		public Builder energiaKcalTotal(BigDecimal energiaKcalTotal) {
			this.energiaKcalTotal = energiaKcalTotal;
			return this;
		}
		
		public Builder carboidratroTotalGramas(BigDecimal carboidratroTotalGramas) {
			this.carboidratroTotalGramas = carboidratroTotalGramas;
			return this;
		}
		
		public Builder carboidratoGramasKgPeso(BigDecimal carboidratoGramasKgPeso) {
			this.carboidratoGramasKgPeso = carboidratoGramasKgPeso;
			return this;
		}
		
		public Builder carboidratoGramasKgMassaMagra(BigDecimal carboidratoGramasKgMassaMagra) {
			this.carboidratoGramasKgMassaMagra = carboidratoGramasKgMassaMagra;
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
		
		public Builder proteinaAVBGramasKgPeso(BigDecimal proteinaAVBGramasKgPeso) {
			this.proteinaAVBGramasKgPeso = proteinaAVBGramasKgPeso;
			return this;
		}
		
		public Builder lipideoTotal(BigDecimal lipideoTotal) {
			this.lipideoTotal = lipideoTotal;
			return this;
		}
		
		public Builder lipideoSaturado(BigDecimal lipideoSaturado) {
			this.lipideoSaturado = lipideoSaturado;
			return this;
		}
		
		public Builder lipideoPoiinsaturado(BigDecimal lipideoPoiinsaturado) {
			this.lipideoPoiinsaturado = lipideoPoiinsaturado;
			return this;
		}
		
		public Builder lipideoMonoinsaturado(BigDecimal lipideoMonoinsaturado) {
			this.lipideoMonoinsaturado = lipideoMonoinsaturado;
			return this;
		}
		
		public Builder relacaoLipideoInsaturadoSaturado(BigDecimal relacaoLipideoInsaturadoSaturado) {
			this.relacaoLipideoInsaturadoSaturado = relacaoLipideoInsaturadoSaturado;
			return this;
		}
		
		
		public CondutaNutricional build() {
			return new CondutaNutricional(energiaKcalTotal, carboidratroTotalGramas, carboidratoGramasKgPeso, 
					carboidratoGramasKgMassaMagra, proteinaTotalGramas, proteinaAVBGramas, proteinaAVBGramasKgPeso, 
					lipideoTotal, lipideoSaturado, lipideoPoiinsaturado, lipideoMonoinsaturado, 
					relacaoLipideoInsaturadoSaturado);
		}
	}
}
