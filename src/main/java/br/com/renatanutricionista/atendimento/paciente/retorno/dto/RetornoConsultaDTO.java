package br.com.renatanutricionista.atendimento.paciente.retorno.dto;

import java.util.List;
import java.util.Objects;

import br.com.renatanutricionista.atendimento.paciente.avaliacao.composicao.corporal.dto.AvaliacaoComposicaoCorporalDTO;
import br.com.renatanutricionista.atendimento.paciente.avaliacao.consumo.habitual.dto.AvaliacaoConsumoHabitualDTO;
import br.com.renatanutricionista.atendimento.paciente.avaliacao.massa.muscular.corporea.antropometrica.dto.AvaliacaoMassaMuscularCorporeaDTO;
import br.com.renatanutricionista.atendimento.paciente.conduta.nutricional.dto.CondutaNutricionalDTO;
import br.com.renatanutricionista.atendimento.paciente.registro.dieta.dto.RefeicaoDietaDTO;
import br.com.renatanutricionista.atendimento.paciente.retorno.model.RetornoConsulta;
import br.com.renatanutricionista.utils.ConversaoUtils;
import lombok.Getter;


@Getter
public class RetornoConsultaDTO {

	private Long id;
	private String situacaoRetorno;
	private String data;
	private String horario;
	private String dificuldadesParaSeguirOrientacoes;
	private String alteracoesSintomas;
	private String alteracoesQueimacoes;
	private String alteracoesMedicamentos;
	private AvaliacaoConsumoHabitualDTO avaliacaoConsumoHabitual;
	private AvaliacaoComposicaoCorporalDTO avaliacaoComposicaoCorporal;
	private AvaliacaoMassaMuscularCorporeaDTO avaliacaoMassaMuscularCorporea;
	private CondutaNutricionalDTO condutaNutricional;
	private List<RefeicaoDietaDTO> refeicoesRegistroDietaHabitual;
	private List<RefeicaoDietaDTO> refeicoesRegistroDieta24Horas;
	
	
	public RetornoConsultaDTO(RetornoConsulta retornoConsulta) {
		id = retornoConsulta.getId();
		situacaoRetorno = retornoConsulta.getSituacaoRetorno().getDescricao();
		dificuldadesParaSeguirOrientacoes = retornoConsulta.getDificuldadesParaSeguirOrientacoes();
		data = ConversaoUtils.converterLocalDateParaString(retornoConsulta.getData());
		horario = retornoConsulta.getHorario().toString();
		alteracoesSintomas = retornoConsulta.getAlteracoesSintomas();
		alteracoesQueimacoes = retornoConsulta.getAlteracoesQueimacoes();
		alteracoesMedicamentos = retornoConsulta.getAlteracoesMedicamentos();
		
		if (Objects.nonNull(retornoConsulta.getAvaliacaoConsumoHabitual()))
			avaliacaoConsumoHabitual = new AvaliacaoConsumoHabitualDTO(retornoConsulta.getAvaliacaoConsumoHabitual());
		
		if (Objects.nonNull(retornoConsulta.getAvaliacaoComposicaoCorporal()))
			avaliacaoComposicaoCorporal = new AvaliacaoComposicaoCorporalDTO(retornoConsulta.getAvaliacaoComposicaoCorporal());
		
		if (Objects.nonNull(retornoConsulta.getAvaliacaoMassaMuscularCorporeaAntropometrica()))
			avaliacaoMassaMuscularCorporea = new AvaliacaoMassaMuscularCorporeaDTO(
					retornoConsulta.getAvaliacaoMassaMuscularCorporeaAntropometrica());
		
		if (Objects.nonNull(retornoConsulta.getCondutaNutricional()))
			condutaNutricional = new CondutaNutricionalDTO(retornoConsulta.getCondutaNutricional());
		
		if (Objects.nonNull(retornoConsulta.getRegistroDietaHabitual()))
			refeicoesRegistroDietaHabitual = RefeicaoDietaDTO.gerarListaComAsRefeicoesDoRegistroDaDieta(retornoConsulta.getRegistroDietaHabitual());
		
		if (Objects.nonNull(retornoConsulta.getRegistroDieta24Horas()))
			refeicoesRegistroDieta24Horas = RefeicaoDietaDTO.gerarListaComAsRefeicoesDoRegistroDaDieta(retornoConsulta.getRegistroDieta24Horas());
	}
}
