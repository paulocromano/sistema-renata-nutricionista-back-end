package br.com.renatanutricionista.ficha.identificacao.historico.patologia.paciente.dto;

import java.util.Set;
import java.util.stream.Collectors;

import br.com.renatanutricionista.ficha.identificacao.historico.patologia.paciente.model.PatologiaPaciente;
import br.com.renatanutricionista.utils.FormatacaoUtils;
import lombok.Getter;


@Getter
public class PatologiaPacienteDTO implements Comparable<PatologiaPacienteDTO> {

	private Long id;
	private Integer quantosAnosPossuiPatologia;
	private String descricaoPatologia;
	
	
	public PatologiaPacienteDTO(PatologiaPaciente patologiaPaciente) {
		id = patologiaPaciente.getId();
		quantosAnosPossuiPatologia = patologiaPaciente.getQuantosAnosPosssuiPatologia();
		descricaoPatologia = patologiaPaciente.getPatologia().getDescricao();
	}
	
	
	@Override
	public int compareTo(PatologiaPacienteDTO other) {
		return FormatacaoUtils.COLLATOR.compare(descricaoPatologia, other.getDescricaoPatologia());
	}
	
	
	public static Set<PatologiaPacienteDTO> converterParaSetPatologiaPacienteDTO(Set<PatologiaPaciente> patologiasPaciente) {
		return patologiasPaciente.stream().map(PatologiaPacienteDTO::new).sorted().collect(Collectors.toSet());
	}
}
