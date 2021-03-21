package br.com.renatanutricionista.paciente.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.renatanutricionista.endereco.dto.EnderecoDTO;
import br.com.renatanutricionista.ficha.identificacao.atividade.fisica.dto.AtividadeFisicaDTO;
import br.com.renatanutricionista.ficha.identificacao.historico.alimentar.dto.HistoricoAlimentarDTO;
import br.com.renatanutricionista.ficha.identificacao.historico.social.dto.HistoricoSocialDTO;
import br.com.renatanutricionista.paciente.model.Paciente;
import br.com.renatanutricionista.utils.ConversaoUtils;
import lombok.Getter;


@Getter
public class PacienteDTO {

	private Long id;
	private String nome;
	private String dataNascimento;
	private String sexo;
	private String etnia;
	private String telefone;
	private String telefoneRecado;
	private EnderecoDTO endereco;
	private List<HistoricoSocialDTO> historicoSocial;
	private List<HistoricoAlimentarDTO> historicoAlimentar;
	private List<AtividadeFisicaDTO> atividadeFisica;
	private String dataUltimaAtualizacaoDadosDoPaciente;
	
	
	public PacienteDTO(Paciente paciente) {
		id = paciente.getId();
		nome = paciente.getNome();
		dataNascimento = ConversaoUtils.converterLocalDateParaString(paciente.getDataNascimento());
		sexo = paciente.getSexo().getDescricao();
		etnia = paciente.getEtnia().getDescricao();
		telefone = paciente.getTelefone();
		telefoneRecado = paciente.getTelefoneRecado();
		endereco = new EnderecoDTO(paciente.getEndereco());
		historicoAlimentar = HistoricoAlimentarDTO.converterParaListaHistoricoAlimentarDTO(paciente.getHistoricoAlimentar());
		atividadeFisica = AtividadeFisicaDTO.converterParaListaAtividadeFisicaDTO(paciente.getAtividadeFisica());
		dataUltimaAtualizacaoDadosDoPaciente = ConversaoUtils.converterLocalDateTimeParaString(paciente.getDataUltimaAtualizacaoDadosDoPaciente());
	}
	
	
	public static List<PacienteDTO> converterParaListaPacienteDTO(List<Paciente> pacientes) {
		return pacientes.stream().map(PacienteDTO::new).collect(Collectors.toList());
	}
}
