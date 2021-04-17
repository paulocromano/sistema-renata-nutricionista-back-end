package br.com.renatanutricionista.paciente.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.renatanutricionista.paciente.model.Paciente;
import br.com.renatanutricionista.utils.FormatacaoUtils;


public class PacienteDTO extends PacienteAbstractDTO implements Comparable<PacienteDTO> {

	public PacienteDTO(Paciente paciente) {
		super(paciente);
	}

	
	@Override
	public int compareTo(PacienteDTO other) {
		return FormatacaoUtils.COLLATOR.compare(nome, other.getNome());
	}
	
	
	public static List<PacienteDTO> converterParaListaPacienteDTOEmOrdemAlfabetica(List<Paciente> pacientes) {
		return pacientes.stream().map(PacienteDTO::new).sorted().collect(Collectors.toList());
	}
}
