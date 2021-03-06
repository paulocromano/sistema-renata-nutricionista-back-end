package br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.form;

import javax.validation.constraints.NotNull;

import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.enums.FrequenciaConsumoAlimento;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@EqualsAndHashCode(of = "idAlimentoFrequenciaAlimentar")
public class FrequenciaAlimentarFORM {

	@NotNull(message = "O campo ID do Alimento utilizado no questionário da Frequência Alimentar não pode ser nulo!")
	private Integer idAlimentoFrequenciaAlimentar;
	
	@NotNull(message = "O campo da Frequência de Consumo do Alimento não pode ser nula!")
	private FrequenciaConsumoAlimento frequenciaConsumoAlimento;
}
