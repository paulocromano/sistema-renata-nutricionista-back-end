package br.com.renatanutricionista.atendimento.paciente.retorno.dto;

import java.util.Objects;

import br.com.renatanutricionista.atendimento.paciente.avaliacao.composicao.corporal.dto.AvaliacaoComposicaoCorporalDTO;
import br.com.renatanutricionista.atendimento.paciente.avaliacao.consumo.habitual.dto.AvaliacaoConsumoHabitualDTO;
import br.com.renatanutricionista.atendimento.paciente.avaliacao.massa.muscular.corporea.antropometrica.dto.AvaliacaoMassaMuscularCorporeaDTO;
import br.com.renatanutricionista.atendimento.paciente.conduta.nutricional.dto.CondutaNutricionalDTO;
import br.com.renatanutricionista.atendimento.paciente.registro.dieta.dto.RegistroDietaDTO;
import br.com.renatanutricionista.atendimento.paciente.retorno.model.RetornoConsulta;
import br.com.renatanutricionista.calendario.atendimento.paciente.dto.CalendarioAtendimentoPacienteDTO;
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
	private RegistroDietaDTO registroDietaHabitual;
	private RegistroDietaDTO registroDieta24Horas;
	
	
	public RetornoConsultaDTO(RetornoConsulta retornoConsulta) {
		id = retornoConsulta.getId();
		situacaoRetorno = retornoConsulta.getSituacaoRetorno().getDescricao();
		dificuldadesParaSeguirOrientacoes = retornoConsulta.getDificuldadesParaSeguirOrientacoes();
		alteracoesSintomas = retornoConsulta.getAlteracoesSintomas();
		alteracoesQueimacoes = retornoConsulta.getAlteracoesQueimacoes();
		alteracoesMedicamentos = retornoConsulta.getAlteracoesMedicamentos();
		periodoRetorno = new CalendarioAtendimentoPacienteDTO(retornoConsulta.getPeriodoRetorno());
		
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
			registroDietaHabitual = new RegistroDietaDTO(retornoConsulta.getRegistroDietaHabitual());
		
		if (Objects.nonNull(retornoConsulta.getRegistroDieta24Horas()))
			registroDieta24Horas = new RegistroDietaDTO(retornoConsulta.getRegistroDieta24Horas());
	}
}
