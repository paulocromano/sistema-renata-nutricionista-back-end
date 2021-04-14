package br.com.renatanutricionista.paciente.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.renatanutricionista.paciente.model.Paciente;
import br.com.renatanutricionista.utils.ConversaoUtils;


public class PacienteDTO extends PacienteAbstractDTO {

	public PacienteDTO(Paciente paciente) {
		id = paciente.getId();
		nome = paciente.getNome();
		dataNascimento = ConversaoUtils.converterLocalDateParaString(paciente.getDataNascimento());
		sexo = paciente.getSexo().getDescricao();
		etnia = paciente.getEtnia().getDescricao();
		telefone = paciente.getTelefone();
		telefoneRecado = paciente.getTelefoneRecado();
		dataHoraUltimaAtualizacaoDadosDoPaciente = ConversaoUtils.converterLocalDateTimeParaStringDataHoraMinuto(paciente.getDataHoraUltimaAtualizacaoDadosDoPaciente());
	}

	
	public static List<PacienteDTO> converterParaListaPacienteDTO(List<Paciente> pacientes) {
		return pacientes.stream().map(PacienteDTO::new).collect(Collectors.toList());
	}
}
