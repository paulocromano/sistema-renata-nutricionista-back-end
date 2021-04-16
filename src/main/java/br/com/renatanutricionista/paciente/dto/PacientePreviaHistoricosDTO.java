package br.com.renatanutricionista.paciente.dto;

import java.util.List;
import java.util.Set;

import br.com.renatanutricionista.endereco.dto.EnderecoDTO;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.dto.InformacoesPreviasQuestionarioDTO;
import br.com.renatanutricionista.ficha.identificacao.historico.alimentar.dto.InformacoesPreviasHistoricoAlimentarDTO;
import br.com.renatanutricionista.ficha.identificacao.historico.atividade.fisica.dto.HistoricoAtividadeFisicaDTO;
import br.com.renatanutricionista.ficha.identificacao.historico.patologia.por.data.familiares.dto.PreviaHistoricoPatologiaFamiliaresPorDataDTO;
import br.com.renatanutricionista.ficha.identificacao.historico.social.dto.InformacoesPreviasHistoricoSocialDTO;
import br.com.renatanutricionista.paciente.model.Paciente;
import br.com.renatanutricionista.tabelas.parametro.paciente.model.PacienteParametro;
import br.com.renatanutricionista.utils.ConversaoUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class PacientePreviaHistoricosDTO extends PacienteAbstractDTO {

	private EnderecoDTO endereco;
	private List<InformacoesPreviasHistoricoSocialDTO> previaHistoricosSociais;
	private List<InformacoesPreviasHistoricoAlimentarDTO> previaHistoricosAlimentares;
	private List<HistoricoAtividadeFisicaDTO> atividadeFisica;
	private Set<PreviaHistoricoPatologiaFamiliaresPorDataDTO> previaHistoricoPatologiaFamiliaresPorData;
	private List<InformacoesPreviasQuestionarioDTO> previaQuestionariosFrequenciaAlimentar;
	private DataProximaAtualizacaoHistoricosPacienteDTO dataProximaAtualizacaoHistoricosPaciente;
	
	
	public PacientePreviaHistoricosDTO(Paciente paciente, PacienteParametro pacieteParametro) {
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
		atividadeFisica = HistoricoAtividadeFisicaDTO.converterParaListaAtividadeFisicaDTO(paciente.getHistoricoAtividadeFisica());
		
		previaHistoricoPatologiaFamiliaresPorData = PreviaHistoricoPatologiaFamiliaresPorDataDTO.converterParaSetPreviaHistoricoPatologiaFamiliaresPorDataDTO(
				paciente.getHistoricoPatologiaFamiliaresPorData());
		
		previaQuestionariosFrequenciaAlimentar = InformacoesPreviasQuestionarioDTO.converterParaListaInformacoesPreviasQuestionarioDTO(
				paciente.getQuestionarioFrequenciaAlimentar());
		
		dataHoraUltimaAtualizacaoDadosDoPaciente = ConversaoUtils.converterLocalDateTimeParaStringDataHoraMinuto(paciente.getDataHoraUltimaAtualizacaoDadosDoPaciente());
		dataProximaAtualizacaoHistoricosPaciente = new DataProximaAtualizacaoHistoricosPacienteDTO(paciente, pacieteParametro);
	}
}
