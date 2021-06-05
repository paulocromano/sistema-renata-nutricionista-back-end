package br.com.renatanutricionista.atendimento.paciente.utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.alimentos.model.AlimentoFrequenciaAlimentar;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.dto.InformacoesCadastroQuestionarioDTO;
import br.com.renatanutricionista.ficha.identificacao.historico.alimentar.dto.InformacoesCadastroHistoricoAlimentarDTO;
import br.com.renatanutricionista.ficha.identificacao.historico.atividade.fisica.enums.FrequenciaAtividadeFisica;
import br.com.renatanutricionista.ficha.identificacao.historico.patologia.familiares.enums.PatologiaFamiliares;
import br.com.renatanutricionista.ficha.identificacao.historico.social.dto.InformacoesCadastroHistoricoSocialDTO;
import br.com.renatanutricionista.medicamento.model.Medicamento;
import br.com.renatanutricionista.paciente.dto.PacienteDTO;
import br.com.renatanutricionista.paciente.model.Paciente;
import br.com.renatanutricionista.patologia.model.Patologia;
import br.com.renatanutricionista.suplemento.model.Suplemento;
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
	protected final InformacoesCadastroQuestionarioDTO questionarioFrequenciaAlimentar;
	protected final InformacoesCadastroHistoricoAlimentarDTO historicoAlimentar;
	protected final List<String> tiposDeRefeicoesParaCadastroRegistroDieta = new ArrayList<>();
	protected final List<DadosEnum> respostaSimNao;
	protected final List<DadosEnum> patologiasFamiliares;
	protected final List<DadosEnum> frequenciaAtividadeFisica;
	
	
	public InformacoesCadastroAtendimentoDTO(Paciente paciente, List<AlimentoFrequenciaAlimentar> alimentosFrequenciaAlimentar,
			List<Patologia> patologias, List<Medicamento> medicamentos, List<Suplemento> suplementos) {
		
		this.paciente = new PacienteDTO(paciente);
		dataAtendimento = ConversaoUtils.converterLocalDateParaString(LocalDate.now());
		historicoSocial = new InformacoesCadastroHistoricoSocialDTO(patologias);
		questionarioFrequenciaAlimentar = new InformacoesCadastroQuestionarioDTO(alimentosFrequenciaAlimentar);
		historicoAlimentar = new InformacoesCadastroHistoricoAlimentarDTO(medicamentos, suplementos);

		gerarListaComAsRefeicoesDoDiaParaRegistroDeDieta();
		respostaSimNao = ConversaoDadosEnum.converterDadosEnum(RespostaUtils.values());
		patologiasFamiliares = ConversaoDadosEnum.converterDadosEnum(PatologiaFamiliares.values());
		frequenciaAtividadeFisica = ConversaoDadosEnum.converterDadosEnum(FrequenciaAtividadeFisica.values());
	}
	
	
	private void gerarListaComAsRefeicoesDoDiaParaRegistroDeDieta() {
		tiposDeRefeicoesParaCadastroRegistroDieta.addAll(Arrays.asList("Desjejum", "Lanche da manhã", "Almoço", "Lanche da tarde", 
				"Jantar", "Ceia", "Final de semana"));
	}
}
 