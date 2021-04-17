package br.com.renatanutricionista.atendimento.paciente.consulta.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.renatanutricionista.atendimento.paciente.consulta.model.Consulta;
import br.com.renatanutricionista.atendimento.paciente.retorno.model.RetornoConsulta;
import br.com.renatanutricionista.atendimento.paciente.utils.TipoAtendimento;
import br.com.renatanutricionista.utils.ConversaoUtils;
import lombok.Getter;


@Getter
public class InformacoesPreviasConsultaRetornoDTO {
	
	private Long idAtendimento;
	private String nomePaciente;
	private String situacaoAtendimento;
	private String tipoAtendimento;
	private String dataAtendimento;
	private String horarioAtendimento;
	
	@JsonIgnore
	private LocalDateTime dataHorarioAtendimento;
	

	private InformacoesPreviasConsultaRetornoDTO(Consulta consulta) {
		idAtendimento = consulta.getId();
		nomePaciente = consulta.getPaciente().getNome();
		tipoAtendimento = TipoAtendimento.CONSULTA.getDescricao();
		situacaoAtendimento = consulta.getSituacaoConsulta().getDescricao();
		dataAtendimento = ConversaoUtils.converterLocalDateParaString(consulta.getData());
		horarioAtendimento = consulta.getHorario().toString();
		dataHorarioAtendimento = consulta.getData().atTime(consulta.getHorario());
	}
	
	private InformacoesPreviasConsultaRetornoDTO(RetornoConsulta retornoConsulta) {
		idAtendimento = retornoConsulta.getId();
		nomePaciente = retornoConsulta.getConsulta().getPaciente().getNome();
		tipoAtendimento = TipoAtendimento.RETORNO_CONSULTA.getDescricao();
		situacaoAtendimento = retornoConsulta.getSituacaoRetorno().getDescricao();
		dataAtendimento = ConversaoUtils.converterLocalDateParaString(retornoConsulta.getData());
		horarioAtendimento = retornoConsulta.getHorario().toString();
		dataHorarioAtendimento = retornoConsulta.getData().atTime(retornoConsulta.getHorario());
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
