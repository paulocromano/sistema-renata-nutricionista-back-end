package br.com.renatanutricionista.ficha.identificacao.historico.patologia.paciente.form;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PatologiaPacienteFORM {

	@NotNull(message = "O campo Ano em que a Patologia foi Adquirida não pode estar nulo!")
	private Integer quantosAnosPossuiPatologia;

	@NotNull(message = "O campo ID Patologia que foi Adquirida não pode estar nulo!")
	private Integer idPatologia;
}
