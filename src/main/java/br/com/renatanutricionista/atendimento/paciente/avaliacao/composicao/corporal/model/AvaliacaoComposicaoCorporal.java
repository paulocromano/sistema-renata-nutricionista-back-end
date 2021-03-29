package br.com.renatanutricionista.atendimento.paciente.avaliacao.composicao.corporal.model;

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
@Table(name = "avaliacao_composicao_corporal", schema = "sistema_nutricionista")
@Setter
@Getter
@NoArgsConstructor
@JsonIgnoreProperties(value = { "consulta", "retornoConsulta" })
public class AvaliacaoComposicaoCorporal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "peso_atual_kg")
	@Digits(integer = 5, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Peso Atual em Kg deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Peso Atual em Kg não pode estar nulo!")
	private BigDecimal pesoAtualKg;
	
	@Column(name = "estatura_m")
	@Digits(integer = 3, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Estatura em Metros deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Estatura em Metros não pode estar nulo!")
	private BigDecimal estaturaMetros;
	
	@Column(name = "imc_kg_m_quadrado")
	@Digits(integer = 6, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Índice de Massa Corporal kg/m² deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Índice de Massa Corporal kg/m² não pode estar nulo!")
	private BigDecimal indiceMassaCorporalKgMetrosQuadrado;
	
	@Column(name = "porcentagem_gordura_densidade_corporal")
	@Digits(integer = 5, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Porcentagem de Gordura Densidade Corporal deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Porcentagem de Gordura Densidade Corporal não pode estar nulo!")
	private BigDecimal porcentagemGorduraDensidadeCorporal;
	
	@Column(name = "dobra_cutanea_tricipital_mm")
	@Digits(integer = 7, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Dobra Cutânea Tricipital mm deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Dobra Cutânea Tricipital mm não pode estar nulo!")
	private BigDecimal dobraCutaneaTricipitalMilimetros;
	
	@Column(name = "dobra_cutanea_coxa_mm")
	@Digits(integer = 7, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Dobra Cutânea Coxa mm deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Dobra Cutânea Coxa mm não pode estar nulo!")
	private BigDecimal dobraCutaneaCoxaMilimetros;
	
	@Column(name = "dobra_cutanea_panturrilha_mm")
	@Digits(integer = 7, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Dobra Cutânea Panturrilha mm deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Dobra Cutânea Panturrilha mm não pode estar nulo!")
	private BigDecimal dobraCutaneaPanturrilhaMilimetros;
	
	@Column(name = "dobra_cutanea_peitoral_homem_mm")
	@Digits(integer = 7, fraction = 2)
	private BigDecimal dobraCutaneaPeitoralHomemMilimetros;
	
	@Column(name = "dobra_cutanea_abdominal_homem_mm")
	@Digits(integer = 7, fraction = 2)
	private BigDecimal dobraCutaneaAbdominalHomemMilimetros;
	
	@Column(name = "dobra_cutanea_supra_iliaca_mulher_mm")
	@Digits(integer = 7, fraction = 2)
	private BigDecimal dobraCutaneaSupraIliacaMulherMilimetros;
	
	@Column(name = "resultado_calculo_densidade_corporal")
	@Digits(integer = 7, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Dobra Cutânea Panturrilha mm deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Dobra Cutânea Panturrilha mm não pode estar nulo!")
	private BigDecimal resultadoCalculoDensidadeCorporal;
	
	@OneToOne(mappedBy = "avaliacaoComposicaoCorporal")
	private Consulta consulta;
	
	@OneToOne(mappedBy = "avaliacaoComposicaoCorporal")
	private RetornoConsulta retornoConsulta;


	private AvaliacaoComposicaoCorporal(BigDecimal pesoAtualKg, BigDecimal estaturaMetros, BigDecimal indiceMassaCorporalKgMetrosQuadrado,
			BigDecimal porcentagemGorduraDensidadeCorporal, BigDecimal dobraCutaneaTricipitalMilimetros,
			BigDecimal dobraCutaneaCoxaMilimetros, BigDecimal dobraCutaneaPanturrilhaMilimetros,
			BigDecimal dobraCutaneaPeitoralHomemMilimetros, BigDecimal dobraCutaneaAbdominalHomemMilimetros,
			BigDecimal dobraCutaneaSupraIliacaMulherMilimetros, BigDecimal resultadoCalculoDensidadeCorporal) {
		
		this.pesoAtualKg = pesoAtualKg;
		this.estaturaMetros = estaturaMetros;
		this.indiceMassaCorporalKgMetrosQuadrado = indiceMassaCorporalKgMetrosQuadrado;
		this.porcentagemGorduraDensidadeCorporal = porcentagemGorduraDensidadeCorporal;
		this.dobraCutaneaTricipitalMilimetros = dobraCutaneaTricipitalMilimetros;
		this.dobraCutaneaCoxaMilimetros = dobraCutaneaCoxaMilimetros;
		this.dobraCutaneaPanturrilhaMilimetros = dobraCutaneaPanturrilhaMilimetros;
		this.dobraCutaneaPeitoralHomemMilimetros = dobraCutaneaPeitoralHomemMilimetros;
		this.dobraCutaneaAbdominalHomemMilimetros = dobraCutaneaAbdominalHomemMilimetros;
		this.dobraCutaneaSupraIliacaMulherMilimetros = dobraCutaneaSupraIliacaMulherMilimetros;
		this.resultadoCalculoDensidadeCorporal = resultadoCalculoDensidadeCorporal;
	}
	
	
	public static class Builder {
		
		private BigDecimal pesoAtualKg;
		private BigDecimal estaturaMetros;
		private BigDecimal indiceMassaCorporalKgMetrosQuadrado;
		private BigDecimal porcentagemGorduraDensidadeCorporal;
		private BigDecimal dobraCutaneaTricipitalMilimetros;
		private BigDecimal dobraCutaneaCoxaMilimetros;
		private BigDecimal dobraCutaneaPanturrilhaMilimetros;
		private BigDecimal dobraCutaneaPeitoralHomemMilimetros;
		private BigDecimal dobraCutaneaAbdominalHomemMilimetros;
		private BigDecimal dobraCutaneaSupraIliacaMulherMilimetros;
		private BigDecimal resultadoCalculoDensidadeCorporal;
		
		
		public Builder pesoAtualKg(BigDecimal pesoAtualKg) {
			this.pesoAtualKg = pesoAtualKg;
			return this;
		}
		
		public Builder estaturaMetros(BigDecimal estaturaMetros) {
			this.estaturaMetros = estaturaMetros;
			return this;
		}
		
		public Builder indiceMassaCorporalKgMetrosQuadrado(BigDecimal indiceMassaCorporalKgMetrosQuadrado) {
			this.indiceMassaCorporalKgMetrosQuadrado = indiceMassaCorporalKgMetrosQuadrado;
			return this;
		}
		
		public Builder porcentagemGorduraDensidadeCorporal(BigDecimal porcentagemGorduraDensidadeCorporal) {
			this.porcentagemGorduraDensidadeCorporal = porcentagemGorduraDensidadeCorporal;
			return this;
		}
		
		public Builder dobraCutaneaTricipitalMilimetros(BigDecimal dobraCutaneaTricipitalMilimetros) {
			this.dobraCutaneaTricipitalMilimetros = dobraCutaneaTricipitalMilimetros;
			return this;
		}
		
		public Builder dobraCutaneaCoxaMilimetros(BigDecimal dobraCutaneaCoxaMilimetros) {
			this.dobraCutaneaCoxaMilimetros = dobraCutaneaCoxaMilimetros;
			return this;
		}
		
		public Builder dobraCutaneaPanturrilhaMilimetros(BigDecimal dobraCutaneaPanturrilhaMilimetros) {
			this.dobraCutaneaPanturrilhaMilimetros = dobraCutaneaPanturrilhaMilimetros;
			return this;
		}
		
		public Builder dobraCutaneaPeitoralHomemMilimetros(BigDecimal dobraCutaneaPeitoralHomemMilimetros) {
			this.dobraCutaneaPeitoralHomemMilimetros = dobraCutaneaPeitoralHomemMilimetros;
			return this;
		}
		
		public Builder dobraCutaneaAbdominalHomemMilimetros(BigDecimal dobraCutaneaAbdominalHomemMilimetros) {
			this.dobraCutaneaAbdominalHomemMilimetros = dobraCutaneaAbdominalHomemMilimetros;
			return this;
		}
		
		public Builder dobraCutaneaSupraIliacaMulherMilimetros(BigDecimal dobraCutaneaSupraIliacaMulherMilimetros) {
			this.dobraCutaneaSupraIliacaMulherMilimetros = dobraCutaneaSupraIliacaMulherMilimetros;
			return this;
		}
		
		public Builder resultadoCalculoDensidadeCorporal(BigDecimal resultadoCalculoDensidadeCorporal) {
			this.resultadoCalculoDensidadeCorporal = resultadoCalculoDensidadeCorporal;
			return this;
		}
		
		
		public AvaliacaoComposicaoCorporal build() {
			return new AvaliacaoComposicaoCorporal(pesoAtualKg, estaturaMetros, indiceMassaCorporalKgMetrosQuadrado, 
					porcentagemGorduraDensidadeCorporal, dobraCutaneaTricipitalMilimetros, dobraCutaneaCoxaMilimetros, 
					dobraCutaneaPanturrilhaMilimetros, dobraCutaneaPeitoralHomemMilimetros, dobraCutaneaAbdominalHomemMilimetros, 
					dobraCutaneaSupraIliacaMulherMilimetros, resultadoCalculoDensidadeCorporal);		
		}
	}
}
