package br.com.renatanutricionista.paciente.dto;

import java.util.List;

import br.com.renatanutricionista.paciente.enums.etnia.Etnia;
import br.com.renatanutricionista.paciente.model.Paciente;
import br.com.renatanutricionista.utils.conversao.enums.ConversaoDadosEnum;
import br.com.renatanutricionista.utils.conversao.enums.DadosEnum;
import lombok.Getter;


@Getter
public class ListagemCadastroPacienteDTO {

	private List<PacienteDTO> pacientes;
	private List<DadosEnum> etniasPaciente;
	
	
	public ListagemCadastroPacienteDTO(List<Paciente> pacientes) {
		this.pacientes = PacienteDTO.converterParaListaPacienteDTOEmOrdemAlfabetica(pacientes);
		etniasPaciente = ConversaoDadosEnum.converterDadosEnum(Etnia.values());
	}
}
