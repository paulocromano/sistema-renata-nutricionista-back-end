package br.com.renatanutricionista.atendimento.paciente.utils;

import java.util.List;

import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.alimentos.model.AlimentoFrequenciaAlimentar;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.dto.InformacoesConsumoQuestionarioParaCadastroDTO;
import br.com.renatanutricionista.ficha.identificacao.historico.atividade.fisica.enums.FrequenciaAtividadeFisica;
import br.com.renatanutricionista.ficha.identificacao.historico.patologia.familiares.enums.PatologiaFamiliares;
import br.com.renatanutricionista.ficha.identificacao.historico.social.dto.InformacoesHistoricoSocialParaCadastroDTO;
import br.com.renatanutricionista.paciente.dto.PacientePreviaHistoricosDTO;
import br.com.renatanutricionista.utils.conversao.enums.ConversaoDadosEnum;
import br.com.renatanutricionista.utils.conversao.enums.DadosEnum;
import br.com.renatanutricionista.utils.enums.resposta.RespostaUtils;
import lombok.Getter;


@Getter
public abstract class InformacoesAtendimentoParaCadastroDTO {

	protected PacientePreviaHistoricosDTO pacientePreviaHistoricos;
	protected final InformacoesHistoricoSocialParaCadastroDTO informacoesHistoricoSocialParaCadastro;
	protected final InformacoesConsumoQuestionarioParaCadastroDTO informacoesConsumoQuestionarioFrequenciaAlimentarParaCadastro;
	protected final List<DadosEnum> respostaSimNao;
	protected final List<DadosEnum> patologiaFamiliares;
	protected final List<DadosEnum> frequenciaAtividadeFisica;
	
	
	public InformacoesAtendimentoParaCadastroDTO(PacientePreviaHistoricosDTO pacientePreviaHistoricos, List<AlimentoFrequenciaAlimentar> alimentosFrequenciaAlimentar) {
		this.pacientePreviaHistoricos = pacientePreviaHistoricos;
		informacoesHistoricoSocialParaCadastro = new InformacoesHistoricoSocialParaCadastroDTO();
		informacoesConsumoQuestionarioFrequenciaAlimentarParaCadastro = new InformacoesConsumoQuestionarioParaCadastroDTO(alimentosFrequenciaAlimentar);
		
		ConversaoDadosEnum conversao = new ConversaoDadosEnum();
		respostaSimNao = conversao.converterDadosEnum(RespostaUtils.values());
		patologiaFamiliares = conversao.converterDadosEnum(PatologiaFamiliares.values());
		frequenciaAtividadeFisica = conversao.converterDadosEnum(FrequenciaAtividadeFisica.values());
	}	
}
 