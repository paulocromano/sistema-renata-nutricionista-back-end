package br.com.renatanutricionista.consultas.retornos.consulta.dto;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import br.com.renatanutricionista.calendario.agendamento.paciente.dto.CalendarioAgendamentoPacienteDTO;
import br.com.renatanutricionista.consultas.retornos.avaliacao.consumo.habitual.dto.AvaliacaoConsumoHabitualDTO;
import br.com.renatanutricionista.consultas.retornos.consulta.model.Consulta;
import lombok.Getter;


@Getter
public class ConsultaDTO {

	private Long id;
	private String situacaoConsulta;
	private String formaPagamento;
	private Integer numeroParcelas;
	private Integer porcentagemDesconto;
	private String motivoConsulta;
	private CalendarioAgendamentoPacienteDTO periodoAgendamentoConsulta;
	private AvaliacaoConsumoHabitualDTO avaliacaoConsumoHabitual;
	
	
	public ConsultaDTO(Consulta consulta) {
		id = consulta.getId();
		situacaoConsulta = consulta.getSituacaoConsulta().getDescricao();
	
		if (Objects.nonNull(consulta.getFormaPagamento()))
			formaPagamento = consulta.getFormaPagamento().getDescricao();
		
		numeroParcelas = consulta.getNumeroParcelas();
		porcentagemDesconto = consulta.getPorcentagemDesconto();
		motivoConsulta = consulta.getMotivoConsulta();
		periodoAgendamentoConsulta = new CalendarioAgendamentoPacienteDTO(consulta.getPeriodoAgendamentoConsulta());
		avaliacaoConsumoHabitual = new AvaliacaoConsumoHabitualDTO(consulta.getAvaliacaoConsumoHabitual());
	}
	
	
	public static List<ConsultaDTO> converterParaListaConsultaDTO(List<Consulta> consultas) {
		return consultas.stream().map(ConsultaDTO::new).collect(Collectors.toList());
	}
}
