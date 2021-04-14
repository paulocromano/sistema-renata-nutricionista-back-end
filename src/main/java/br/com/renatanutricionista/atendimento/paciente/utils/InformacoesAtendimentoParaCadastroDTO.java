package br.com.renatanutricionista.atendimento.paciente.utils;

import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.dto.InformacoesConsumoQuestionarioParaCadastroDTO;
import br.com.renatanutricionista.ficha.identificacao.historico.atividade.fisica.enums.FrequenciaAtividadeFisica;
import br.com.renatanutricionista.ficha.identificacao.historico.patologia.familiares.enums.PatologiaFamiliares;
import br.com.renatanutricionista.ficha.identificacao.historico.social.dto.InformacoesHistoricoSocialParaCadastroDTO;
import br.com.renatanutricionista.paciente.dto.PacientePreviaHistoricosDTO;
import br.com.renatanutricionista.utils.enums.resposta.RespostaUtils;
import lombok.Getter;


@Getter
public abstract class InformacoesAtendimentoParaCadastroDTO {

	protected PacientePreviaHistoricosDTO pacientePreviaHistoricos;
	protected final InformacoesHistoricoSocialParaCadastroDTO informacoesHistoricoSocialParaCadastro;
	protected final InformacoesConsumoQuestionarioParaCadastroDTO informacoesConsumoQuestionarioFrequenciaAlimentarParaCadastro;
	protected final RespostaUtils[] RESPOSTA_SIM_NAO = RespostaUtils.values();
	protected final PatologiaFamiliares[] PATOLOGIA_FAMILIARES = PatologiaFamiliares.values();
	protected final FrequenciaAtividadeFisica[] FREQUENCIA_ATIVIDADE_FISICA = FrequenciaAtividadeFisica.values();
	
	
	public InformacoesAtendimentoParaCadastroDTO(PacientePreviaHistoricosDTO pacientePreviaHistoricos) {
		this.pacientePreviaHistoricos = pacientePreviaHistoricos;
		informacoesHistoricoSocialParaCadastro = new InformacoesHistoricoSocialParaCadastroDTO();
		informacoesConsumoQuestionarioFrequenciaAlimentarParaCadastro = new InformacoesConsumoQuestionarioParaCadastroDTO();
	}	
}
 