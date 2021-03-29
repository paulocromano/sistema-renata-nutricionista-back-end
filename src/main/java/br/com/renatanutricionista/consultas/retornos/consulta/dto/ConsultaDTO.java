package br.com.renatanutricionista.consultas.retornos.consulta.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import br.com.renatanutricionista.calendario.atendimento.paciente.dto.CalendarioAtendimentoPacienteDTO;
import br.com.renatanutricionista.consultas.retornos.avaliacao.composicao.corporal.dto.AvaliacaoComposicaoCorporalDTO;
import br.com.renatanutricionista.consultas.retornos.avaliacao.consumo.habitual.dto.AvaliacaoConsumoHabitualDTO;
import br.com.renatanutricionista.consultas.retornos.avaliacao.massa.muscular.corporea.antropometrica.dto.AvaliacaoMassaMuscularCorporeaDTO;
import br.com.renatanutricionista.consultas.retornos.conduta.nutricional.dto.CondutaNutricionalDTO;
import br.com.renatanutricionista.consultas.retornos.consulta.model.Consulta;
import br.com.renatanutricionista.consultas.retornos.retorno.dto.RetornoConsultaDTO;
import lombok.Getter;


@Getter
public class ConsultaDTO {

	private Long id;
	private String situacaoConsulta;
	private String formaPagamento;
	private Integer numeroParcelas;
	private BigDecimal valorConsulta;
	private String motivoConsulta;
	private CalendarioAtendimentoPacienteDTO periodoConsulta;
	private AvaliacaoConsumoHabitualDTO avaliacaoConsumoHabitual;
	private AvaliacaoComposicaoCorporalDTO avaliacaoComposicaoCorporal;
	private AvaliacaoMassaMuscularCorporeaDTO avaliacaoMassaMuscularCorporea;
	private CondutaNutricionalDTO condutaNutricional;
	private RetornoConsultaDTO retornoConsulta;
	
	
	public ConsultaDTO(Consulta consulta) {
		id = consulta.getId();
		situacaoConsulta = consulta.getSituacaoConsulta().getDescricao();
	
		if (Objects.nonNull(consulta.getFormaPagamento()))
			formaPagamento = consulta.getFormaPagamento().getDescricao();
		
		numeroParcelas = consulta.getNumeroParcelas();
		valorConsulta = consulta.getValorConsulta();
		motivoConsulta = consulta.getMotivoConsulta();
		periodoConsulta = new CalendarioAtendimentoPacienteDTO(consulta.getPeriodoConsulta());
		
		if (Objects.nonNull(consulta.getAvaliacaoConsumoHabitual()))
			avaliacaoConsumoHabitual = new AvaliacaoConsumoHabitualDTO(consulta.getAvaliacaoConsumoHabitual());
		
		if (Objects.nonNull(consulta.getAvaliacaoComposicaoCorporal()))
			avaliacaoComposicaoCorporal = new AvaliacaoComposicaoCorporalDTO(consulta.getAvaliacaoComposicaoCorporal());
		
		if (Objects.nonNull(consulta.getAvaliacaoMassaMuscularCorporeaAntropometrica()))
			avaliacaoMassaMuscularCorporea = new AvaliacaoMassaMuscularCorporeaDTO(
					consulta.getAvaliacaoMassaMuscularCorporeaAntropometrica());
		
		if (Objects.nonNull(consulta.getCondutaNutricional()))
			condutaNutricional = new CondutaNutricionalDTO(consulta.getCondutaNutricional());
		
		if (Objects.nonNull(consulta.getRetornoConsulta()))
			retornoConsulta = new RetornoConsultaDTO(consulta.getRetornoConsulta());
	}
	
	
	public static List<ConsultaDTO> converterParaListaConsultaDTO(List<Consulta> consultas) {
		return consultas.stream().map(ConsultaDTO::new).collect(Collectors.toList());
	}
}
