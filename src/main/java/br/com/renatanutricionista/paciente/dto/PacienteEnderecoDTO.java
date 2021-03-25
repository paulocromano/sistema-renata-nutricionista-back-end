package br.com.renatanutricionista.paciente.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.renatanutricionista.endereco.dto.EnderecoDTO;
import br.com.renatanutricionista.paciente.model.Paciente;
import br.com.renatanutricionista.utils.ConversaoUtils;
import lombok.Getter;


@Getter
public class PacienteEnderecoDTO extends PacienteDTO {

	public PacienteEnderecoDTO(Paciente paciente) {
		id = paciente.getId();
		nome = paciente.getNome();
		dataNascimento = ConversaoUtils.converterLocalDateParaString(paciente.getDataNascimento());
		sexo = paciente.getSexo().getDescricao();
		etnia = paciente.getEtnia().getDescricao();
		telefone = paciente.getTelefone();
		telefoneRecado = paciente.getTelefoneRecado();
		endereco = new EnderecoDTO(paciente.getEndereco());
		dataHoraUltimaAtualizacaoDadosDoPaciente = ConversaoUtils.converterLocalDateTimeParaString(paciente.getDataHoraUltimaAtualizacaoDadosDoPaciente());
	}

	
	public static List<PacienteEnderecoDTO> converterParaListaPacienteEnderecoDTO(List<Paciente> pacientes) {
		return pacientes.stream().map(PacienteEnderecoDTO::new).collect(Collectors.toList());
	}
}
