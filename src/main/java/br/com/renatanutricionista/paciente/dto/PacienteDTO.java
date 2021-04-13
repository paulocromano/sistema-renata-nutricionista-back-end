package br.com.renatanutricionista.paciente.dto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.renatanutricionista.atendimento.paciente.consulta.dto.InformacoesPreviasConsultaDTO;
import br.com.renatanutricionista.endereco.dto.EnderecoDTO;
import br.com.renatanutricionista.ficha.identificacao.atividade.fisica.dto.AtividadeFisicaDTO;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.dto.InformacoesPreviasQuestionarioDTO;
import br.com.renatanutricionista.ficha.identificacao.historico.alimentar.dto.InformacoesPreviasHistoricoAlimentarDTO;
import br.com.renatanutricionista.ficha.identificacao.historico.patologia.por.data.familiares.dto.PreviaHistoricoPatologiaFamiliaresPorDataDTO;
import br.com.renatanutricionista.ficha.identificacao.historico.social.dto.InformacoesPreviasHistoricoSocialDTO;
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
	private List<InformacoesPreviasHistoricoSocialDTO> previaHistoricosSociais;
	private List<InformacoesPreviasHistoricoAlimentarDTO> previaHistoricosAlimentares;
	private List<AtividadeFisicaDTO> atividadeFisica;
	private Set<PreviaHistoricoPatologiaFamiliaresPorDataDTO> previaHistoricoPatologiaFamiliaresPorData;
	private List<InformacoesPreviasQuestionarioDTO> previasQuestionariosFrequenciaAlimentar;
	private List<InformacoesPreviasConsultaDTO> previasConsultas;
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
		previaHistoricosSociais = InformacoesPreviasHistoricoSocialDTO.converterParaListaInformacoesPreviasHistoricoSocialDTO(paciente.getHistoricoSocial());
		previaHistoricosAlimentares = InformacoesPreviasHistoricoAlimentarDTO.converterParaListaInformacoesPreviasHistoricoAlimentarDTO(paciente.getHistoricoAlimentar());
		atividadeFisica = AtividadeFisicaDTO.converterParaListaAtividadeFisicaDTO(paciente.getAtividadeFisica());
		
		previaHistoricoPatologiaFamiliaresPorData = PreviaHistoricoPatologiaFamiliaresPorDataDTO.converterParaSetPreviaHistoricoPatologiaFamiliaresPorDataDTO(
				paciente.getHistoricoPatologiaFamiliaresPorData());
		
		previasQuestionariosFrequenciaAlimentar = InformacoesPreviasQuestionarioDTO.converterParaListaInformacoesPreviasQuestionarioDTO(
				paciente.getQuestionarioFrequenciaAlimentar());
		
		previasConsultas = InformacoesPreviasConsultaDTO.converterParaListaInformacoesPreviasConsultaDTO(paciente.getConsultas());
		
		dataHoraUltimaAtualizacaoDadosDoPaciente = ConversaoUtils.converterLocalDateTimeParaStringDataHoraMinuto(paciente.getDataHoraUltimaAtualizacaoDadosDoPaciente());
	}
	
	
	public static List<PacienteDTO> converterParaListaPacienteDTO(List<Paciente> pacientes) {
		return pacientes.stream().map(PacienteDTO::new).collect(Collectors.toList());
	}
}
