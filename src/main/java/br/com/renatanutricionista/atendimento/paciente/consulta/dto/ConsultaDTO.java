package br.com.renatanutricionista.atendimento.paciente.consulta.dto;

import java.math.BigDecimal;
import java.util.Objects;

import br.com.renatanutricionista.atendimento.paciente.avaliacao.composicao.corporal.dto.AvaliacaoComposicaoCorporalDTO;
import br.com.renatanutricionista.atendimento.paciente.avaliacao.consumo.habitual.dto.AvaliacaoConsumoHabitualDTO;
import br.com.renatanutricionista.atendimento.paciente.avaliacao.massa.muscular.corporea.antropometrica.dto.AvaliacaoMassaMuscularCorporeaDTO;
import br.com.renatanutricionista.atendimento.paciente.conduta.nutricional.dto.CondutaNutricionalDTO;
import br.com.renatanutricionista.atendimento.paciente.consulta.model.Consulta;
import br.com.renatanutricionista.atendimento.paciente.registro.dieta.dto.RegistroDietaDTO;
import br.com.renatanutricionista.atendimento.paciente.retorno.dto.RetornoConsultaDTO;
import br.com.renatanutricionista.paciente.dto.PacientePreviaHistoricosDTO;
import br.com.renatanutricionista.tabelas.parametro.paciente.model.PacienteParametro;
import br.com.renatanutricionista.utils.ConversaoUtils;
import lombok.Getter;


@Getter
public class ConsultaDTO {

	private Long id;
	private PacientePreviaHistoricosDTO pacientePreviaHistoricos;
	private String situacaoConsulta;
	private String data;
	private String horario;
	private String formaPagamento;
	private Integer numeroParcelas;
	private BigDecimal valorConsulta;
	private String motivoConsulta;
	private AvaliacaoConsumoHabitualDTO avaliacaoConsumoHabitual;
	private AvaliacaoComposicaoCorporalDTO avaliacaoComposicaoCorporal;
	private AvaliacaoMassaMuscularCorporeaDTO avaliacaoMassaMuscularCorporea;
	private CondutaNutricionalDTO condutaNutricional;
	private RegistroDietaDTO registroDietaHabitual;
	private RetornoConsultaDTO retornoConsulta;
	
	
	public ConsultaDTO(Consulta consulta, PacienteParametro pacienteParametro) {
		id = consulta.getId();
		pacientePreviaHistoricos = new PacientePreviaHistoricosDTO(consulta.getPaciente(), pacienteParametro);
		situacaoConsulta = consulta.getSituacaoConsulta().getDescricao();
		data = ConversaoUtils.converterLocalDateParaString(consulta.getDataHorario().toLocalDate());
		horario = consulta.getDataHorario().toLocalTime().toString();
	
		if (Objects.nonNull(consulta.getFormaPagamento()))
			formaPagamento = consulta.getFormaPagamento().getDescricao();
		
		numeroParcelas = consulta.getNumeroParcelas();
		valorConsulta = consulta.getValorConsulta();
		motivoConsulta = consulta.getMotivoConsulta();
		
		if (Objects.nonNull(consulta.getAvaliacaoConsumoHabitual()))
			avaliacaoConsumoHabitual = new AvaliacaoConsumoHabitualDTO(consulta.getAvaliacaoConsumoHabitual());
		
		if (Objects.nonNull(consulta.getAvaliacaoComposicaoCorporal()))
			avaliacaoComposicaoCorporal = new AvaliacaoComposicaoCorporalDTO(consulta.getAvaliacaoComposicaoCorporal());
		
		if (Objects.nonNull(consulta.getAvaliacaoMassaMuscularCorporeaAntropometrica()))
			avaliacaoMassaMuscularCorporea = new AvaliacaoMassaMuscularCorporeaDTO(
					consulta.getAvaliacaoMassaMuscularCorporeaAntropometrica());
		
		if (Objects.nonNull(consulta.getCondutaNutricional()))
			condutaNutricional = new CondutaNutricionalDTO(consulta.getCondutaNutricional());
		
		if (Objects.nonNull(consulta.getRegistroDietaHabitual()))
			registroDietaHabitual = new RegistroDietaDTO(consulta.getRegistroDietaHabitual());
		
		if (Objects.nonNull(consulta.getRetornoConsulta()))
			retornoConsulta = new RetornoConsultaDTO(consulta.getRetornoConsulta());
	}
}
