package br.com.renatanutricionista.atendimento.paciente.conduta.nutricional.form;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.renatanutricionista.atendimento.paciente.conduta.nutricional.model.CondutaNutricional;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CondutaNutricionalFORM {
	
	@NotEmpty(message = "O campo Prescrição Dietética não pode ser nulo/vazio!")
	@Size(max = 250, message = "O campo Prescrição Dietéica deve ter no máximo {max} caracteres!")
	private String prescricaoDietetica;

	@Digits(integer = 10, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Energia Kcal Total deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Energia Kcal Total não pode estar nulo!")
	private BigDecimal energiaKcalTotal;

	@Digits(integer = 10, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Carboidrato Total em Gramas deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Carboidrato Total em Gramas não pode estar nulo!")
	private BigDecimal carboidratroTotalGramas;

	@Digits(integer = 10, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Carboidrato g/Kg Peso deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Carboidrato g/Kg Peso não pode estar nulo!")
	private BigDecimal carboidratoGramasKgPeso;

	@Digits(integer = 10, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Carboidrato g/Kg Massa Magra deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Carboidrato g/Kg Massa Magra não pode estar nulo!")
	private BigDecimal carboidratoGramasKgMassaMagra;
	
	@Digits(integer = 10, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Proteína Total em Gramas deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Proteína Total em Gramas não pode estar nulo!")
	private BigDecimal proteinaTotalGramas;
	
	@Digits(integer = 10, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Proteína AVB em Gramas deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Proteína Total AVB em Gramas não pode estar nulo!")
	private BigDecimal proteinaAVBGramas;

	@Digits(integer = 10, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Proteína Total AVB g/Kg Peso deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Proteína Total AVB g/Kg Peso não pode estar nulo!")
	private BigDecimal proteinaAVBGramasKgPeso;

	@Digits(integer = 10, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Lipídeo Total deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Lipídeo Total não pode estar nulo!")
	private BigDecimal lipideoTotal;

	@Digits(integer = 10, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Lipídeo Saturado deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Lipídeo Saturado não pode estar nulo!")
	private BigDecimal lipideoSaturado;

	@Digits(integer = 10, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Lipídeo Poliinsaturado deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Lipídeo Poliinsaturado não pode estar nulo!")
	private BigDecimal lipideoPoiinsaturado;

	@Digits(integer = 10, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Lipídeo Monoinsaturado deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Lipídeo Monoinsaturado não pode estar nulo!")
	private BigDecimal lipideoMonoinsaturado;

	@Digits(integer = 10, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Relação Lipídeo Insaturado/Saturado deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Relação Lipídeo Insaturado/Saturado não pode estar nulo!")
	private BigDecimal relacaoLipideoInsaturadoSaturado;
	
	
	public CondutaNutricional converterParaCondutaNutricional() {
		return new CondutaNutricional.Builder()
				.prescricaoDietetica(prescricaoDietetica)
				.energiaKcalTotal(energiaKcalTotal)
				.carboidratroTotalGramas(carboidratroTotalGramas)
				.carboidratoGramasKgPeso(carboidratoGramasKgPeso)
				.carboidratoGramasKgMassaMagra(carboidratoGramasKgMassaMagra)
				.proteinaTotalGramas(proteinaTotalGramas)
				.proteinaAVBGramas(proteinaAVBGramas)
				.proteinaAVBGramasKgPeso(proteinaAVBGramasKgPeso)
				.lipideoTotal(lipideoTotal)
				.lipideoSaturado(lipideoSaturado)
				.lipideoPoiinsaturado(lipideoPoiinsaturado)
				.lipideoMonoinsaturado(lipideoMonoinsaturado)
				.relacaoLipideoInsaturadoSaturado(relacaoLipideoInsaturadoSaturado)
				.build();
	}
}
