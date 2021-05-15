package br.com.renatanutricionista.atendimento.paciente.consulta.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.renatanutricionista.atendimento.paciente.consulta.model.Consulta;
import br.com.renatanutricionista.atendimento.paciente.retorno.model.RetornoConsulta;
import br.com.renatanutricionista.atendimento.paciente.utils.TipoAtendimento;
import br.com.renatanutricionista.utils.ConversaoUtils;
import br.com.renatanutricionista.utils.FormatacaoUtils;
import lombok.Getter;


@Getter
public class InformacoesPreviasConsultaRetornoDTO {
	
	private Long idAtendimento;
	private String nomePaciente;
	private String situacaoAtendimento;
	private Integer codigoTipoAtendimento;
	private String descricaoTipoAtendimento;
	private String dataAtendimento;
	private String horarioAtendimento;
	private String formaPagamentoConsulta;
	private Integer numeroParcelasConsulta;
	private String valorConsulta;
	private String motivoConsulta;
	
	@JsonIgnore
	private LocalDateTime dataHorarioAtendimento;
	

	private InformacoesPreviasConsultaRetornoDTO(Consulta consulta) {
		idAtendimento = consulta.getId();
		nomePaciente = consulta.getPaciente().getNome();
		informacoesTipoAtendimento(TipoAtendimento.CONSULTA);
		situacaoAtendimento = consulta.getSituacaoConsulta().getDescricao();
		dataAtendimento = ConversaoUtils.converterLocalDateParaString(consulta.getData());
		horarioAtendimento = consulta.getHorario().toString();
		dataHorarioAtendimento = consulta.getData().atTime(consulta.getHorario());
		informacoesParaConsulta(consulta);

	}
	
	private InformacoesPreviasConsultaRetornoDTO(RetornoConsulta retornoConsulta) {
		idAtendimento = retornoConsulta.getId();
		nomePaciente = retornoConsulta.getConsulta().getPaciente().getNome();
		informacoesTipoAtendimento(TipoAtendimento.RETORNO_CONSULTA);
		situacaoAtendimento = retornoConsulta.getSituacaoRetorno().getDescricao();
		dataAtendimento = ConversaoUtils.converterLocalDateParaString(retornoConsulta.getData());
		horarioAtendimento = retornoConsulta.getHorario().toString();
		dataHorarioAtendimento = retornoConsulta.getData().atTime(retornoConsulta.getHorario());
		informacoesParaConsulta(retornoConsulta.getConsulta());
	}
	
	
	private void informacoesTipoAtendimento(TipoAtendimento tipoAtendimento) {
		codigoTipoAtendimento = tipoAtendimento.ordinal();
		descricaoTipoAtendimento = tipoAtendimento.getDescricao();
	}
	
	
	private void informacoesParaConsulta(Consulta consulta) {
		if (Objects.nonNull(consulta.getFormaPagamento()))
			formaPagamentoConsulta = consulta.getFormaPagamento().getDescricao();
		
		numeroParcelasConsulta = consulta.getNumeroParcelas();
		valorConsulta = FormatacaoUtils.substituirPontoPorVirgula(consulta.getValorConsulta());
		motivoConsulta = consulta.getMotivoConsulta();
	}
	
	
	public static List<InformacoesPreviasConsultaRetornoDTO> converterParaListaInformacoesPreviasConsultaRetornoDTO(List<Consulta> atendimentos,
			LocalDate dataInicial, LocalDate dataFinal) {
		
		Predicate<Consulta> predicateConsulta = consulta -> !consulta.getData().isBefore(dataInicial) && !consulta.getData().isAfter(dataFinal);
		
		return atendimentos.stream()
				.map(atendimento -> (predicateConsulta.test(atendimento)) 
						? new InformacoesPreviasConsultaRetornoDTO(atendimento) 
						: new InformacoesPreviasConsultaRetornoDTO(atendimento.getRetornoConsulta()))
				.sorted(Comparator.comparing(InformacoesPreviasConsultaRetornoDTO::getDataHorarioAtendimento))
				.collect(Collectors.toList());
	}
}
