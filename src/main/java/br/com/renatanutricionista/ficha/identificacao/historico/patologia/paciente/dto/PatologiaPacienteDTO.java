package br.com.renatanutricionista.ficha.identificacao.historico.patologia.paciente.dto;

import java.util.Set;
import java.util.stream.Collectors;

import br.com.renatanutricionista.ficha.identificacao.historico.patologia.paciente.model.PatologiaPaciente;
import br.com.renatanutricionista.patologia.dto.PatologiaDTO;
import br.com.renatanutricionista.utils.FormatacaoUtils;
import lombok.Getter;


@Getter
public class PatologiaPacienteDTO implements Comparable<PatologiaPacienteDTO> {

	private Long id;
	private Integer quantosAnosPosssuiPatologia;
	private PatologiaDTO patologia;
	
	
	public PatologiaPacienteDTO(PatologiaPaciente patologiaPaciente) {
		id = patologiaPaciente.getId();
		quantosAnosPosssuiPatologia = patologiaPaciente.getQuantosAnosPosssuiPatologia();
		patologia = new PatologiaDTO(patologiaPaciente.getPatologia());
	}
	
	
	@Override
	public int compareTo(PatologiaPacienteDTO other) {
		return FormatacaoUtils.COLLATOR.compare(patologia, other.getPatologia());
	}
	
	
	public static Set<PatologiaPacienteDTO> converterParaSetPatologiaPacienteDTO(Set<PatologiaPaciente> patologiasPaciente) {
		return patologiasPaciente.stream().map(PatologiaPacienteDTO::new).sorted().collect(Collectors.toSet());
	}
}
