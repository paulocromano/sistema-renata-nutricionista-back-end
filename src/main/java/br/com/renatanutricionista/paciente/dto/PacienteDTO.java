package br.com.renatanutricionista.paciente.dto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.renatanutricionista.consultas.retornos.consulta.dto.ConsultaDTO;
import br.com.renatanutricionista.endereco.dto.EnderecoDTO;
import br.com.renatanutricionista.ficha.identificacao.atividade.fisica.dto.AtividadeFisicaDTO;
import br.com.renatanutricionista.ficha.identificacao.historico.alimentar.dto.HistoricoAlimentarDTO;
import br.com.renatanutricionista.ficha.identificacao.historico.patologia.por.data.familiares.dto.HistoricoPatologiaFamiliaresPorDataDTO;
import br.com.renatanutricionista.ficha.identificacao.historico.social.dto.HistoricoSocialDTO;
import br.com.renatanutricionista.paciente.model.Paciente;
import br.com.renatanutricionista.utils.ConversaoUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class PacienteDTO {

	protected Long id;
	protected String nome;
	protected String dataNascimento;
	protected String sexo;
	protected String etnia;
	protected String telefone;
	protected String telefoneRecado;
	protected EnderecoDTO endereco;
	private List<HistoricoSocialDTO> historicoSocial;
	private List<HistoricoAlimentarDTO> historicoAlimentar;
	private List<AtividadeFisicaDTO> atividadeFisica;
	private Set<HistoricoPatologiaFamiliaresPorDataDTO> historicoPatologiaFamiliaresPorData;
	private List<ConsultaDTO> consultas;
	protected String dataHoraUltimaAtualizacaoDadosDoPaciente;
	
	
	public PacienteDTO(Paciente paciente) {
		id = paciente.getId();
		nome = paciente.getNome();
		dataNascimento = ConversaoUtils.converterLocalDateParaString(paciente.getDataNascimento());
		sexo = paciente.getSexo().getDescricao();
		etnia = paciente.getEtnia().getDescricao();
		telefone = paciente.getTelefone();
		telefoneRecado = paciente.getTelefoneRecado();
		endereco = new EnderecoDTO(paciente.getEndereco());
		historicoSocial = HistoricoSocialDTO.converterParaListaHistoricoSocialDTO(paciente.getHistoricoSocial());
		historicoAlimentar = HistoricoAlimentarDTO.converterParaListaHistoricoAlimentarDTO(paciente.getHistoricoAlimentar());
		atividadeFisica = AtividadeFisicaDTO.converterParaListaAtividadeFisicaDTO(paciente.getAtividadeFisica());
		
		historicoPatologiaFamiliaresPorData = HistoricoPatologiaFamiliaresPorDataDTO.converterParaSetHistoricoPatologiaFamiliaresPorDataDTO(
				paciente.getHistoricoPatologiaFamiliaresPorData());
		
		consultas = ConsultaDTO.converterParaListaConsultaDTO(paciente.getConsultas());
		
		dataHoraUltimaAtualizacaoDadosDoPaciente = ConversaoUtils.converterLocalDateTimeParaStringDataHoraMinuto(paciente.getDataHoraUltimaAtualizacaoDadosDoPaciente());
	}
	
	
	public static List<PacienteDTO> converterParaListaPacienteDTO(List<Paciente> pacientes) {
		return pacientes.stream().map(PacienteDTO::new).collect(Collectors.toList());
	}
}
