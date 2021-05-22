package br.com.renatanutricionista.atendimento.paciente.consulta.dto;

import java.util.List;
import java.util.Objects;

import br.com.renatanutricionista.atendimento.paciente.avaliacao.composicao.corporal.dto.AvaliacaoComposicaoCorporalDTO;
import br.com.renatanutricionista.atendimento.paciente.avaliacao.consumo.habitual.dto.AvaliacaoConsumoHabitualDTO;
import br.com.renatanutricionista.atendimento.paciente.avaliacao.massa.muscular.corporea.antropometrica.dto.AvaliacaoMassaMuscularCorporeaDTO;
import br.com.renatanutricionista.atendimento.paciente.conduta.nutricional.dto.CondutaNutricionalDTO;
import br.com.renatanutricionista.atendimento.paciente.consulta.model.Consulta;
import br.com.renatanutricionista.atendimento.paciente.registro.dieta.dto.RefeicaoDietaDTO;
import br.com.renatanutricionista.utils.ConversaoUtils;
import br.com.renatanutricionista.utils.FormatacaoUtils;
import lombok.Getter;


@Getter
public class ConsultaDTO {

	private Long id;
	private String situacaoConsulta;
	private String data;
	private String horario;
	private String formaPagamento;
	private Integer numeroParcelas;
	private String valorConsulta;
	private String motivoConsulta;
	private AvaliacaoConsumoHabitualDTO avaliacaoConsumoHabitual;
	private AvaliacaoComposicaoCorporalDTO avaliacaoComposicaoCorporal;
	private AvaliacaoMassaMuscularCorporeaDTO avaliacaoMassaMuscularCorporea;
	private CondutaNutricionalDTO condutaNutricional;
	private List<RefeicaoDietaDTO> refeicoesRegistroDietaHabitual;
	
	
	public ConsultaDTO(Consulta consulta) {
		id = consulta.getId();
		situacaoConsulta = consulta.getSituacaoConsulta().getDescricao();
		data = ConversaoUtils.converterLocalDateParaString(consulta.getData());
		horario = consulta.getHorario().toString();
	
		if (Objects.nonNull(consulta.getFormaPagamento())) {
			formaPagamento = consulta.getFormaPagamento().getDescricao();
			numeroParcelas = consulta.getNumeroParcelas();
			valorConsulta = FormatacaoUtils.substituirPontoPorVirgula(consulta.getValorConsulta());
		}

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
			refeicoesRegistroDietaHabitual = RefeicaoDietaDTO.gerarListaComAsRefeicoesDoRegistroDaDieta(consulta.getRegistroDietaHabitual());
	}
}
