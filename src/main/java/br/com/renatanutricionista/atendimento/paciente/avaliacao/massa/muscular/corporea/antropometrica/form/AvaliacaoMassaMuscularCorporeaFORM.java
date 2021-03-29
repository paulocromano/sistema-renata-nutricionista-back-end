package br.com.renatanutricionista.atendimento.paciente.avaliacao.massa.muscular.corporea.antropometrica.form;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import br.com.renatanutricionista.atendimento.paciente.avaliacao.massa.muscular.corporea.antropometrica.model.AvaliacaoMassaMuscularCorporea;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AvaliacaoMassaMuscularCorporeaFORM {

	@Digits(integer = 6, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Circunferência da Cintura em Centíemtros deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Circunferência da Cintura em Centíemtros não pode estar nulo!")
	private BigDecimal circunferenciaCinturaCentimetros;
	
	@Digits(integer = 6, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Circunferência do Braço em Centíemtros deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Circunferência do Braço em Centíemtros não pode estar nulo!")
	private BigDecimal circunferenciaBracoCentimentros;
	
	@Digits(integer = 6, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Circunferência da Coxa em Centíemtros deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Circunferência da Coxa em Centíemtros não pode estar nulo!")
	private BigDecimal circunferenciaCoxaCentimetros;
	
	@Digits(integer = 6, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Circunferência da Panturrilha em Centíemtros deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Circunferência da Panturrilha em Centíemtros não pode estar nulo!")
	private BigDecimal circunferenciaPanturrilhaCentimetros;
	
	@Digits(integer = 6, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Circunferência do Punho em Centíemtros deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Circunferência do Punho em Centíemtros não pode estar nulo!")
	private BigDecimal circunferenciaPunhoCentrimetros;
	
	@Digits(integer = 6, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Massa Muscular Kg deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Massa Muscular Kg não pode estar nulo!")
	private BigDecimal massaMuscularKg;
	
	@Digits(integer = 6, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Massa Muscular em Porcentagem deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Massa Muscular em Porcentagem não pode estar nulo!")
	private BigDecimal massaMuscularPorcentagem;
	
	@Digits(integer = 6, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Índice da Massa Muscular em Kg/m² deve ter o valor mínimo {value}")
	@NotNull(message = "O campo Índice da Massa Muscular em Kg/m² não pode estar nulo!")
	private BigDecimal indiceMassaMuscularKgMetroQuadrado;
	
	
	public AvaliacaoMassaMuscularCorporea criarAvaliacaoMassaMuscularCorporea() {
		return new AvaliacaoMassaMuscularCorporea.Builder()
				.circunferenciaCinturaCentimetros(circunferenciaCinturaCentimetros)
				.circunferenciaBracoCentimentros(circunferenciaBracoCentimentros)
				.circunferenciaCoxaCentimetros(circunferenciaCoxaCentimetros)
				.circunferenciaPanturrilhaCentimetros(circunferenciaPanturrilhaCentimetros)
				.circunferenciaPunhoCentrimetros(circunferenciaPunhoCentrimetros)
				.massaMuscularKg(indiceMassaMuscularKgMetroQuadrado)
				.massaMuscularPorcentagem(massaMuscularPorcentagem)
				.indiceMassaMuscularKgMetroQuadrado(indiceMassaMuscularKgMetroQuadrado)
				.build();
	}
}
