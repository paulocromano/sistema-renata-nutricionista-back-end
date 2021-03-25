package br.com.renatanutricionista.consultas.retornos.consulta.dto;

import java.util.List;
import java.util.stream.Collectors;

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
	
	
	public ConsultaDTO(Consulta consulta) {
		id = consulta.getId();
		situacaoConsulta = consulta.getSituacaoConsulta().getDescricao();
		formaPagamento = consulta.getFormaPagamento().getDescricao();
		numeroParcelas = consulta.getNumeroParcelas();
		porcentagemDesconto = consulta.getPorcentagemDesconto();
		motivoConsulta = consulta.getMotivoConsulta();
	}
	
	
	public static List<ConsultaDTO> converterParaListaConsultaDTO(List<Consulta> consultas) {
		return consultas.stream().map(ConsultaDTO::new).collect(Collectors.toList());
	}
}
