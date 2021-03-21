package br.com.renatanutricionista.ficha.identificacao.historico.patologia.dto;

import br.com.renatanutricionista.ficha.identificacao.historico.patologia.model.PatologiaPaciente;
import br.com.renatanutricionista.patologia.dto.PatologiaDTO;
import lombok.Getter;


@Getter
public class PatologiaPacienteDTO {

	private Long id;
	private Integer quantosAnosPosssuiPatologia;
	private PatologiaDTO patologia;
	
	
	public PatologiaPacienteDTO(PatologiaPaciente patologiaPaciente) {
		id = patologiaPaciente.getId();
		quantosAnosPosssuiPatologia = patologiaPaciente.getQuantosAnosPosssuiPatologia();
		patologia = new PatologiaDTO(patologiaPaciente.getPatologia());
	}
}
