package br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.form;

import javax.validation.constraints.NotNull;

import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.enums.frequencia.consumo.FrequenciaConsumoAlimento;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class FrequenciaAlimentarFORM {

	@NotNull(message = "O campo ID do Alimento utilizado no questionário da Frequência Alimentar não pode ser nulo!")
	private Integer idAlimentoFrequenciaAlimentar;
	
	@NotNull(message = "O campo Frequência do Consumo do Alimento não pode estar nulo!")
	private FrequenciaConsumoAlimento frequenciaConsumoAlimento;
}
