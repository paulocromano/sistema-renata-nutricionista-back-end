package br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@EqualsAndHashCode(of = "idAlimentoFrequenciaAlimentar")
public class FrequenciaAlimentarFORM {

	@NotNull(message = "O campo ID do Alimento utilizado no questionário da Frequência Alimentar não pode ser nulo!")
	private Integer idAlimentoFrequenciaAlimentar;
	
	@NotEmpty(message = "O campo Frequência do Consumo do Alimento não pode estar nulo/vazio!")
	@Size(max = 1, message = "O campo Frequência do Consumo do Alimento deve ter somente {max} caracter!")
	@Pattern(regexp = "N|D|S|M|A", message = "O Código da Frequência do Consumo de Alimento é inválido!")
	private String frequenciaConsumoAlimento;
}
