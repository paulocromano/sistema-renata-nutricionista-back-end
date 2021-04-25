package br.com.renatanutricionista.paciente.dto;

import java.util.List;
import java.util.Set;

import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.dto.InformacoesPreviasQuestionarioDTO;
import br.com.renatanutricionista.ficha.identificacao.historico.alimentar.dto.InformacoesPreviasHistoricoAlimentarDTO;
import br.com.renatanutricionista.ficha.identificacao.historico.atividade.fisica.dto.HistoricoAtividadeFisicaDTO;
import br.com.renatanutricionista.ficha.identificacao.historico.patologia.por.data.familiares.dto.PreviaHistoricoPatologiaFamiliaresPorDataDTO;
import br.com.renatanutricionista.ficha.identificacao.historico.social.dto.InformacoesPreviasHistoricoSocialDTO;
import br.com.renatanutricionista.paciente.model.Paciente;
import br.com.renatanutricionista.tabelas.parametro.paciente.model.PacienteParametro;
import lombok.Getter;


@Getter
public class PacientePreviaHistoricosDTO extends PacienteAbstractDTO {

	private List<InformacoesPreviasHistoricoSocialDTO> previaHistoricoSocial;
	private List<InformacoesPreviasHistoricoAlimentarDTO> previaHistoricosAlimentares;
	private List<HistoricoAtividadeFisicaDTO> historicoAtividadeFisica;
	private Set<PreviaHistoricoPatologiaFamiliaresPorDataDTO> previaHistoricoPatologiaFamiliaresPorData;
	private List<InformacoesPreviasQuestionarioDTO> previaQuestionariosFrequenciaAlimentar;
	private DataProximaAtualizacaoHistoricosPacienteDTO dataProximaAtualizacaoHistoricosPaciente;
	
	
	public PacientePreviaHistoricosDTO(Paciente paciente, PacienteParametro pacienteParametro) {
		super(paciente);
		previaHistoricoSocial = InformacoesPreviasHistoricoSocialDTO.converterParaListaInformacoesPreviasHistoricoSocialDTO(paciente.getHistoricoSocial());
		previaHistoricosAlimentares = InformacoesPreviasHistoricoAlimentarDTO.converterParaListaInformacoesPreviasHistoricoAlimentarDTO(paciente.getHistoricoAlimentar());
		historicoAtividadeFisica = HistoricoAtividadeFisicaDTO.converterParaListaAtividadeFisicaDTO(paciente.getHistoricoAtividadeFisica());
		
		previaHistoricoPatologiaFamiliaresPorData = PreviaHistoricoPatologiaFamiliaresPorDataDTO.converterParaSetPreviaHistoricoPatologiaFamiliaresPorDataDTO(
				paciente.getHistoricoPatologiaFamiliaresPorData());
		
		previaQuestionariosFrequenciaAlimentar = InformacoesPreviasQuestionarioDTO.converterParaListaInformacoesPreviasQuestionarioDTO(
				paciente.getQuestionarioFrequenciaAlimentar());
		
		dataProximaAtualizacaoHistoricosPaciente = new DataProximaAtualizacaoHistoricosPacienteDTO(paciente, pacienteParametro);
	}
}
