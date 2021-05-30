package br.com.renatanutricionista.atendimento.paciente.utils;

import java.time.LocalDate;
import java.util.List;

import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.alimentos.model.AlimentoFrequenciaAlimentar;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.dto.InformacoesCadastroConsumoQuestionarioDTO;
import br.com.renatanutricionista.ficha.identificacao.historico.atividade.fisica.enums.FrequenciaAtividadeFisica;
import br.com.renatanutricionista.ficha.identificacao.historico.patologia.familiares.enums.PatologiaFamiliares;
import br.com.renatanutricionista.ficha.identificacao.historico.social.dto.InformacoesCadastroHistoricoSocialDTO;
import br.com.renatanutricionista.paciente.dto.PacienteDTO;
import br.com.renatanutricionista.paciente.model.Paciente;
import br.com.renatanutricionista.utils.ConversaoUtils;
import br.com.renatanutricionista.utils.conversao.enums.ConversaoDadosEnum;
import br.com.renatanutricionista.utils.conversao.enums.DadosEnum;
import br.com.renatanutricionista.utils.enums.resposta.RespostaUtils;
import lombok.Getter;


@Getter
public abstract class InformacoesCadastroAtendimentoDTO {

	protected PacienteDTO paciente;
	protected String dataAtendimento;
	protected final InformacoesCadastroHistoricoSocialDTO historicoSocial;
	protected final InformacoesCadastroConsumoQuestionarioDTO consumoQuestionarioFrequenciaAlimentar;
	protected final List<DadosEnum> respostaSimNao;
	protected final List<DadosEnum> patologiaFamiliares;
	protected final List<DadosEnum> frequenciaAtividadeFisica;
	
	
	public InformacoesCadastroAtendimentoDTO(Paciente paciente, List<AlimentoFrequenciaAlimentar> alimentosFrequenciaAlimentar) {
		this.paciente = new PacienteDTO(paciente);
		dataAtendimento = ConversaoUtils.converterLocalDateParaString(LocalDate.now());
		historicoSocial = new InformacoesCadastroHistoricoSocialDTO();
		consumoQuestionarioFrequenciaAlimentar = new InformacoesCadastroConsumoQuestionarioDTO(alimentosFrequenciaAlimentar);

		respostaSimNao = ConversaoDadosEnum.converterDadosEnum(RespostaUtils.values());
		patologiaFamiliares = ConversaoDadosEnum.converterDadosEnum(PatologiaFamiliares.values());
		frequenciaAtividadeFisica = ConversaoDadosEnum.converterDadosEnum(FrequenciaAtividadeFisica.values());
	}	
}
 