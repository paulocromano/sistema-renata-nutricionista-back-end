package br.com.renatanutricionista.atendimento.paciente.avaliacao.composicao.corporal.form;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import br.com.renatanutricionista.atendimento.paciente.avaliacao.composicao.corporal.model.AvaliacaoComposicaoCorporal;
import br.com.renatanutricionista.atendimento.paciente.avaliacao.composicao.corporal.model.AvaliacaoComposicaoCorporal.Builder;
import br.com.renatanutricionista.utils.enums.SexoUtils;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AvaliacaoComposicaoCorporalFORM {

	@Digits(integer = 5, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Peso Atual em Kg deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Peso Atual em Kg não pode estar nulo!")
	private BigDecimal pesoAtualKg;
	
	@Digits(integer = 3, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Estatura em Metros deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Estatura em Metros não pode estar nulo!")
	private BigDecimal estaturaMetros;
	
	@Digits(integer = 6, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Índice de Massa Corporal kg/m² deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Índice de Massa Corporal kg/m² não pode estar nulo!")
	private BigDecimal indiceMassaCorporalKgMetrosQuadrado;
	
	@Digits(integer = 5, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Porcentagem de Gordura Densidade Corporal deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Porcentagem de Gordura Densidade Corporal não pode estar nulo!")
	private BigDecimal porcentagemGorduraDensidadeCorporal;

	@Digits(integer = 7, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Dobra Cutânea Tricipital mm deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Dobra Cutânea Tricipital mm não pode estar nulo!")
	private BigDecimal dobraCutaneaTricipitalMilimetros;
	
	@Digits(integer = 7, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Dobra Cutânea Coxa mm deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Dobra Cutânea Coxa mm não pode estar nulo!")
	private BigDecimal dobraCutaneaCoxaMilimetros;
	
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
	
	@Digits(integer = 7, fraction = 2)
	private BigDecimal dobraCutaneaSupraIliacaMulherMilimetros;
	
	@Digits(integer = 7, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Dobra Cutânea Panturrilha mm deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Dobra Cutânea Panturrilha mm não pode estar nulo!")
	private BigDecimal resultadoCalculoDensidadeCorporal;
	
	
	public AvaliacaoComposicaoCorporal criarAvaliacaoComposicaoCorporal(SexoUtils sexoPaciente) {
		
		Builder avaliacaoComposicaoCorporalBuilder = 
				new AvaliacaoComposicaoCorporal.Builder()
				.pesoAtualKg(pesoAtualKg)
				.estaturaMetros(estaturaMetros)
				.indiceMassaCorporalKgMetrosQuadrado(indiceMassaCorporalKgMetrosQuadrado)
				.porcentagemGorduraDensidadeCorporal(porcentagemGorduraDensidadeCorporal)
				.dobraCutaneaTricipitalMilimetros(dobraCutaneaTricipitalMilimetros)
				.dobraCutaneaCoxaMilimetros(dobraCutaneaCoxaMilimetros)
				.dobraCutaneaPanturrilhaMilimetros(dobraCutaneaPanturrilhaMilimetros);	
		
		if (sexoPaciente.equals(SexoUtils.MASCULINO)) {
			avaliacaoComposicaoCorporalBuilder = validarComposicaoCorporalHomem(avaliacaoComposicaoCorporalBuilder);
		}
		else
			avaliacaoComposicaoCorporalBuilder = validarComposicaoCorporalMulher(avaliacaoComposicaoCorporalBuilder);
		
		return avaliacaoComposicaoCorporalBuilder.resultadoCalculoDensidadeCorporal(resultadoCalculoDensidadeCorporal).build();
	}
	
	
	private Builder validarComposicaoCorporalHomem(
			Builder avaliacaoComposicaoCorporalBuilder) {
		
		if (Objects.isNull(dobraCutaneaPeitoralHomemMilimetros))
			throw new NullPointerException("O campo Dobra Cutânea Peitoral do Homem não pode estar nulo!");
		
		if (Objects.isNull(dobraCutaneaAbdominalHomemMilimetros))
			throw new NullPointerException("O campo Dobra Cutânea Abdominal do Homem não pode estar nulo!");
		
		return avaliacaoComposicaoCorporalBuilder
				.dobraCutaneaPeitoralHomemMilimetros(dobraCutaneaPeitoralHomemMilimetros)
				.dobraCutaneaAbdominalHomemMilimetros(dobraCutaneaAbdominalHomemMilimetros)
				.dobraCutaneaSupraIliacaMulherMilimetros(null);
	}
	
	
	private Builder validarComposicaoCorporalMulher(
			Builder avaliacaoComposicaoCorporalBuilder) {
		
		if (Objects.isNull(dobraCutaneaSupraIliacaMulherMilimetros))
			throw new NullPointerException("O campo Dobra Cutânea Supra Iliaca da Mulher não pode ser nulo!");
		
		return avaliacaoComposicaoCorporalBuilder
				.dobraCutaneaSupraIliacaMulherMilimetros(dobraCutaneaSupraIliacaMulherMilimetros)
				.dobraCutaneaPeitoralHomemMilimetros(null)
				.dobraCutaneaAbdominalHomemMilimetros(null);
	}
}
