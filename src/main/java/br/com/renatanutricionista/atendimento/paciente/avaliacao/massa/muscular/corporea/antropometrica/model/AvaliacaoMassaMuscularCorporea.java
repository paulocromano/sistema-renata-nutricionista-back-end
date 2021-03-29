package br.com.renatanutricionista.atendimento.paciente.avaliacao.massa.muscular.corporea.antropometrica.model;

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

import br.com.renatanutricionista.atendimento.paciente.consulta.model.Consulta;
import br.com.renatanutricionista.atendimento.paciente.retorno.model.RetornoConsulta;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "avaliacao_massa_muscular_corporea_medida_antropometrica", schema = "sistema_nutricionista")
@Setter
@Getter
@NoArgsConstructor
@JsonIgnoreProperties(value = { "consulta", "retornoConsulta" })
public class AvaliacaoMassaMuscularCorporea {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "circunferencia_cintura_cm")
	@Digits(integer = 6, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Circunferência da Cintura em Centíemtros deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Circunferência da Cintura em Centíemtros não pode estar nulo!")
	private BigDecimal circunferenciaCinturaCentimetros;
	
	@Column(name = "circunferencia_braco_cm")
	@Digits(integer = 6, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Circunferência do Braço em Centíemtros deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Circunferência do Braço em Centíemtros não pode estar nulo!")
	private BigDecimal circunferenciaBracoCentimentros;
	
	@Column(name = "circunferencia_coxa_cm")
	@Digits(integer = 6, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Circunferência da Coxa em Centíemtros deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Circunferência da Coxa em Centíemtros não pode estar nulo!")
	private BigDecimal circunferenciaCoxaCentimetros;
	
	@Column(name = "circunferencia_panturrilha_cm")
	@Digits(integer = 6, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Circunferência da Panturrilha em Centíemtros deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Circunferência da Panturrilha em Centíemtros não pode estar nulo!")
	private BigDecimal circunferenciaPanturrilhaCentimetros;
	
	@Column(name = "circunferencia_punho_cm")
	@Digits(integer = 6, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Circunferência do Punho em Centíemtros deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Circunferência do Punho em Centíemtros não pode estar nulo!")
	private BigDecimal circunferenciaPunhoCentrimetros;
	
	@Column(name = "massa_muscular_kg")
	@Digits(integer = 6, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Massa Muscular Kg deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Massa Muscular Kg não pode estar nulo!")
	private BigDecimal massaMuscularKg;
	
	@Column(name = "massa_muscular_porcentagem")
	@Digits(integer = 6, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Massa Muscular em Porcentagem deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Massa Muscular em Porcentagem não pode estar nulo!")
	private BigDecimal massaMuscularPorcentagem;
	
	@Column(name = "indice_massa_muscular_kg_m_quadrado")
	@Digits(integer = 6, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Índice da Massa Muscular em Kg/m² deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Índice da Massa Muscular em Kg/m² não pode estar nulo!")
	private BigDecimal indiceMassaMuscularKgMetroQuadrado;
	
	@OneToOne(mappedBy = "avaliacaoMassaMuscularCorporeaAntropometrica")
	private Consulta consulta;
	
	@OneToOne(mappedBy = "avaliacaoMassaMuscularCorporeaAntropometrica")
	private RetornoConsulta retornoConsulta;
	

	private AvaliacaoMassaMuscularCorporea(BigDecimal circunferenciaCinturaCentimetros, BigDecimal circunferenciaBracoCentimentros,
			BigDecimal circunferenciaCoxaCentimetros, BigDecimal circunferenciaPanturrilhaCentimetros, BigDecimal circunferenciaPunhoCentrimetros,
			BigDecimal massaMuscularKg, BigDecimal massaMuscularPorcentagem, BigDecimal indiceMassaMuscularKgMetroQuadrado) {
		
		this.circunferenciaCinturaCentimetros = circunferenciaCinturaCentimetros;
		this.circunferenciaBracoCentimentros = circunferenciaBracoCentimentros;
		this.circunferenciaCoxaCentimetros = circunferenciaCoxaCentimetros;
		this.circunferenciaPanturrilhaCentimetros = circunferenciaPanturrilhaCentimetros;
		this.circunferenciaPunhoCentrimetros = circunferenciaPunhoCentrimetros;
		this.massaMuscularKg = massaMuscularKg;
		this.massaMuscularPorcentagem = massaMuscularPorcentagem;
		this.indiceMassaMuscularKgMetroQuadrado = indiceMassaMuscularKgMetroQuadrado;
	}


	public static class Builder {
		
		private BigDecimal circunferenciaCinturaCentimetros;
		private BigDecimal circunferenciaBracoCentimentros;
		private BigDecimal circunferenciaCoxaCentimetros;
		private BigDecimal circunferenciaPanturrilhaCentimetros;
		private BigDecimal circunferenciaPunhoCentrimetros;
		private BigDecimal massaMuscularKg;
		private BigDecimal massaMuscularPorcentagem;
		private BigDecimal indiceMassaMuscularKgMetroQuadrado;
		
		
		public Builder circunferenciaCinturaCentimetros(BigDecimal circunferenciaCinturaCentimetros) {
			this.circunferenciaCinturaCentimetros = circunferenciaCinturaCentimetros;
			return this;
		}
		
		public Builder circunferenciaBracoCentimentros(BigDecimal circunferenciaBracoCentimentros) {
			this.circunferenciaBracoCentimentros = circunferenciaBracoCentimentros;
			return this;
		}
		
		public Builder circunferenciaCoxaCentimetros(BigDecimal circunferenciaCoxaCentimetros) {
			this.circunferenciaCoxaCentimetros = circunferenciaCoxaCentimetros;
			return this;
		}
		
		public Builder circunferenciaPanturrilhaCentimetros(BigDecimal circunferenciaPanturrilhaCentimetros) {
			this.circunferenciaPanturrilhaCentimetros = circunferenciaPanturrilhaCentimetros;
			return this;
		}
		
		public Builder circunferenciaPunhoCentrimetros(BigDecimal circunferenciaPunhoCentrimetros) {
			this.circunferenciaPunhoCentrimetros = circunferenciaPunhoCentrimetros;
			return this;
		}
		
		public Builder massaMuscularKg(BigDecimal massaMuscularKg) {
			this.massaMuscularKg = massaMuscularKg;
			return this;
		}
		
		public Builder massaMuscularPorcentagem(BigDecimal massaMuscularPorcentagem) {
			this.massaMuscularPorcentagem = massaMuscularPorcentagem;
			return this;
		}
		
		public Builder indiceMassaMuscularKgMetroQuadrado(BigDecimal indiceMassaMuscularKgMetroQuadrado) {
			this.indiceMassaMuscularKgMetroQuadrado = indiceMassaMuscularKgMetroQuadrado;
			return this;
		}
		
		
		public AvaliacaoMassaMuscularCorporea build() {
			return new AvaliacaoMassaMuscularCorporea(circunferenciaCinturaCentimetros, circunferenciaBracoCentimentros, 
					circunferenciaCoxaCentimetros, circunferenciaPanturrilhaCentimetros, circunferenciaPunhoCentrimetros, 
					massaMuscularKg, massaMuscularPorcentagem, indiceMassaMuscularKgMetroQuadrado);
		}
	}
}
