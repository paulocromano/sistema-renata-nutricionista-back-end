package br.com.renatanutricionista.ficha.identificacao.historico.patologia.paciente.dto;

import java.util.Set;
import java.util.stream.Collectors;

import br.com.renatanutricionista.ficha.identificacao.historico.patologia.paciente.model.PatologiaPaciente;
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
	
	
	public static Set<PatologiaPacienteDTO> converterParaSetPatologiaPacienteDTO(Set<PatologiaPaciente> patologiasPaciente) {
		return patologiasPaciente.stream().map(PatologiaPacienteDTO::new).collect(Collectors.toSet());
	}
}
