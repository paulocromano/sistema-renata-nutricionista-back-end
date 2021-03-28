package br.com.renatanutricionista.consultas.retornos.avaliacao.consumo.habitual.form;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import br.com.renatanutricionista.consultas.retornos.avaliacao.consumo.habitual.model.AvaliacaoConsumoHabitual;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AvaliacaoConsumoHabitualFORM {

	@Digits(integer = 10, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Energia Kcal deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Energia Kcal não pode estar nulo!")
	private BigDecimal energiaKcal;
	
	@Digits(integer = 10, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Proteína Total em Gramas deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Proteína Total em Gramas não pode estar nulo!")
	private BigDecimal proteinaTotalGramas;
	
	@Digits(integer = 10, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Proteína AVB em Gramas deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Proteína AVB em Gramas não pode estar nulo!")
	private BigDecimal proteinaAVBGramas;
	
	@Digits(integer = 10, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Carboidrato em Gramas deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Carboidrato em Gramas não pode estar nulo!")
	private BigDecimal carboidratoGramas;
	
	@Digits(integer = 10, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Lipídeo Total em Gramas deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Lipídeo Total em Gramas não pode estar nulo!")
	private BigDecimal lipideoTotalGramas;
	
	@Digits(integer = 10, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Lipídeo Saturado em Gramas deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Lipídeo Saturado em Gramas não pode estar nulo!")
	private BigDecimal lipideoSaturadoGramas;
	
	@Digits(integer = 10, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Lipídeo Poliinsaturado em Gramas deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Lipídeo Poliinsaturado em Gramas não pode estar nulo!")
	private BigDecimal lipideoPoliinsaturadoGramas;
	
	@Digits(integer = 10, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Lipídeo Monoinsaturado em Gramas deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Lipídeo Monoinsaturado em Gramas não pode estar nulo!")
	private BigDecimal lipideoMonoinsaturadoGramas;
	
	@Digits(integer = 10, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Colesterol em Miligramas deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Colesterol em Miligramas não pode estar nulo!")
	private BigDecimal colesterolMiligramas;
	
	@Digits(integer = 10, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Fibra em Gramas  deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Fibra em Gramas não pode estar nulo!")
	private BigDecimal fibraGramas;
	
	@Digits(integer = 10, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Lipídeo Monoinsaturado em Gramas deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Lipídeo Monoinsaturado em Gramas não pode estar nulo!")
	private BigDecimal calcioGramas;
	
	@Digits(integer = 10, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Fósforo em Gramas deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Fósforo em Gramas não pode estar nulo!")
	private BigDecimal fosforoGramas;
	
	@Digits(integer = 10, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Ferro em Miligramas deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Ferro em Miligramas não pode estar nulo!")
	private BigDecimal ferroMiligramas;
	
	@Digits(integer = 10, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Zinco em Miligramas deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Zinco em Miligramas não pode estar nulo!")
	private BigDecimal zincoMiligramas;
	
	@Digits(integer = 10, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Magnésio em Miligramas deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Magnésio em Miligramas não pode estar nulo!")
	private BigDecimal magnesioMiligramas;
	
	@Digits(integer = 10, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Potássio em Miligramas deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Potássio em Miligramas não pode estar nulo!")
	private BigDecimal potassioMiligramas;
	
	@Digits(integer = 10, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Vitamina B6 em Miligramas deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Vitamina B6 em Miligramas não pode estar nulo!")
	private BigDecimal vitaminaB6Miligramas;
	
	@Digits(integer = 10, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Vitamina C em Miligramas deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Vitamina C em Miligramas não pode estar nulo!")
	private BigDecimal vitaminaCMiligramas;
	
	@Digits(integer = 10, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Leucina em Miligramas deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Leucina em Miligramas não pode estar nulo!")
	private BigDecimal leucinaMiligramas;
	
	
	public AvaliacaoConsumoHabitual criarAvaliacaoConsumoHabitual() {
		return new AvaliacaoConsumoHabitual.Builder()
				.energiaKcal(energiaKcal)
				.proteinaTotalGramas(proteinaTotalGramas)
				.proteinaAVBGramas(proteinaAVBGramas)
				.carboidratoGramas(carboidratoGramas)
				.lipideoTotalGramas(lipideoTotalGramas)
				.lipideoSaturadoGramas(lipideoSaturadoGramas)
				.lipideoPoliinsaturadoGramas(lipideoPoliinsaturadoGramas)
				.lipideoMonoinsaturadoGramas(lipideoMonoinsaturadoGramas)
				.colesterolMiligramas(colesterolMiligramas)
				.fibraGramas(fibraGramas)
				.calcioGramas(calcioGramas)
				.fosforoGramas(fosforoGramas)
				.ferroMiligramas(ferroMiligramas)
				.zincoMiligramas(zincoMiligramas)
				.magnesioMiligramas(magnesioMiligramas)
				.potassioMiligramas(potassioMiligramas)
				.vitaminaB6Miligramas(vitaminaB6Miligramas)
				.vitaminaCMiligramas(vitaminaCMiligramas)
				.leucinaMiligramas(leucinaMiligramas)
				.build();
	}
}
