package br.com.renatanutricionista.atendimento.paciente.consulta.dto;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import br.com.renatanutricionista.atendimento.paciente.consulta.enums.TipoAtendimento;
import br.com.renatanutricionista.atendimento.paciente.consulta.model.Consulta;
import br.com.renatanutricionista.atendimento.paciente.retorno.model.RetornoConsulta;
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
	

	private InformacoesPreviasConsultaRetornoDTO(Consulta consulta) {
		idAtendimento = consulta.getId();
		nomePaciente = consulta.getPaciente().getNome();
		tipoAtendimento = TipoAtendimento.CONSULTA.getDescricao();
		situacaoAtendimento = consulta.getSituacaoConsulta().getDescricao();
		dataAtendimento = ConversaoUtils.converterLocalDateParaString(consulta.getDataHorario().toLocalDate());
		horarioAtendimento = consulta.getDataHorario().toLocalTime().toString();
	}
	
	private InformacoesPreviasConsultaRetornoDTO(RetornoConsulta retornoConsulta) {
		idAtendimento = retornoConsulta.getId();
		nomePaciente = retornoConsulta.getConsulta().getPaciente().getNome();
		tipoAtendimento = TipoAtendimento.RETORNO_CONSULTA.getDescricao();
		situacaoAtendimento = retornoConsulta.getSituacaoRetorno().getDescricao();
		dataAtendimento = ConversaoUtils.converterLocalDateParaString(retornoConsulta.getDataHorario().toLocalDate());
		horarioAtendimento = ConversaoUtils.converterLocalTimeParaString(retornoConsulta.getDataHorario().toLocalTime());
	}
	
	
	public static List<InformacoesPreviasConsultaRetornoDTO> converterParaListaInformacoesPreviasConsultaRetornoDTO(List<Consulta> atendimentos,
			LocalDate dataInicial, LocalDate dataFinal) {
		
		Predicate<Consulta> predicateConsulta = consulta -> !consulta.getDataHorario().toLocalDate().isBefore(dataInicial) 
				&& !consulta.getDataHorario().toLocalDate().isAfter(dataFinal);
		
		return atendimentos.stream()
				.sorted(Comparator.comparing(Consulta::getDataHorario)
				.reversed())
				.map(atendimento -> (predicateConsulta.test(atendimento)) 
						? new InformacoesPreviasConsultaRetornoDTO(atendimento) 
						: new InformacoesPreviasConsultaRetornoDTO(atendimento.getRetornoConsulta()))
				.collect(Collectors.toList());
	}
}
