package br.com.renatanutricionista.consultas.retornos.retorno.dto;

import java.util.Objects;

import br.com.renatanutricionista.calendario.atendimento.paciente.dto.CalendarioAtendimentoPacienteDTO;
import br.com.renatanutricionista.consultas.retornos.avaliacao.composicao.corporal.dto.AvaliacaoComposicaoCorporalDTO;
import br.com.renatanutricionista.consultas.retornos.avaliacao.consumo.habitual.dto.AvaliacaoConsumoHabitualDTO;
import br.com.renatanutricionista.consultas.retornos.avaliacao.massa.muscular.corporea.antropometrica.dto.AvaliacaoMassaMuscularCorporeaDTO;
import br.com.renatanutricionista.consultas.retornos.conduta.nutricional.dto.CondutaNutricionalDTO;
import br.com.renatanutricionista.consultas.retornos.retorno.model.RetornoConsulta;
import lombok.Getter;


@Getter
public class RetornoConsultaDTO {

	private Long id;
	private String situacaoRetorno;
	private String dificuldadesParaSeguirOrientacoes;
	private String alteracoesSintomas;
	private String alteracoesQueimacoes;
	private String alteracoesMedicamentos;
	private CalendarioAtendimentoPacienteDTO periodoRetorno;
	private AvaliacaoConsumoHabitualDTO avaliacaoConsumoHabitual;
	private AvaliacaoComposicaoCorporalDTO avaliacaoComposicaoCorporal;
	private AvaliacaoMassaMuscularCorporeaDTO avaliacaoMassaMuscularCorporea;
	private CondutaNutricionalDTO condutaNutricional;
	
	
	public RetornoConsultaDTO(RetornoConsulta retornoConsulta) {
		id = retornoConsulta.getId();
		situacaoRetorno = retornoConsulta.getSituacaoRetorno().getDescricao();
		dificuldadesParaSeguirOrientacoes = retornoConsulta.getDificuldadesParaSeguirOrientacoes();
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
	}
}