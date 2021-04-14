package br.com.renatanutricionista.atendimento.paciente.consulta.dto;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import br.com.renatanutricionista.atendimento.paciente.consulta.enums.TipoAtendimento;
import br.com.renatanutricionista.atendimento.paciente.consulta.model.Consulta;
import br.com.renatanutricionista.utils.ConversaoUtils;
import lombok.Getter;


@Getter
public class InformacoesPreviasConsultaRetornoDTO extends ConsultaDTO {
	
	private String nomePaciente;
	private String possuiRetornoConsulta;
	private TipoAtendimento tipoAtendimento;
	

	public InformacoesPreviasConsultaRetornoDTO(Consulta consulta) {
		id = consulta.getId();
		nomePaciente = consulta.getPaciente().getNome();
		situacaoConsulta = consulta.getSituacaoConsulta().getDescricao();
		data = ConversaoUtils.converterLocalDateParaString(consulta.getDataHorario().toLocalDate());
		horario = consulta.getDataHorario().toLocalTime().toString();
		possuiRetornoConsulta = (Objects.nonNull(consulta.getRetornoConsulta())) ? "Sim" : "Não";
	}
	
	
	public static List<InformacoesPreviasConsultaRetornoDTO> converterParaListaInformacoesPreviasConsultaDTO(List<Consulta> consultas) {
		return consultas.stream()
				.sorted(Comparator.comparing(Consulta::getDataHorario)
				.reversed())
				.map(InformacoesPreviasConsultaRetornoDTO::new)
				.collect(Collectors.toList());
	}
}
