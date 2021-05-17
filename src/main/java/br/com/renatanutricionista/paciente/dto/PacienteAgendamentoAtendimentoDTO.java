package br.com.renatanutricionista.paciente.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.renatanutricionista.paciente.model.Paciente;
import br.com.renatanutricionista.utils.FormatacaoUtils;
import lombok.Getter;


@Getter
public class PacienteAgendamentoAtendimentoDTO implements Comparable<PacienteAgendamentoAtendimentoDTO> {

	private Long id;
	private String nome;
	
	
	public PacienteAgendamentoAtendimentoDTO(Paciente paciente) {
		id = paciente.getId();
		nome = paciente.getNome();
	}
	
	
	@Override
	public int compareTo(PacienteAgendamentoAtendimentoDTO other) {
		return FormatacaoUtils.COLLATOR.compare(nome, other.getNome());
	}
	
	
	public static List<PacienteAgendamentoAtendimentoDTO> converterParaListaPacienteAgendamentoAtendimentoDTO(List<Paciente> pacientes) {
		return pacientes.stream().map(PacienteAgendamentoAtendimentoDTO::new).sorted().collect(Collectors.toList());
	}
}
