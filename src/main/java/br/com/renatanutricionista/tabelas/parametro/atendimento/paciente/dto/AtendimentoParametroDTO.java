package br.com.renatanutricionista.tabelas.parametro.atendimento.paciente.dto;

import br.com.renatanutricionista.tabelas.parametro.atendimento.paciente.model.AtendimentoParametro;
import lombok.Getter;


@Getter
public class AtendimentoParametroDTO {

	private Integer id;
	private Integer quantidadeParcelas;
	private Integer intervaloMinutosEntreAtendimentos;
	private Integer intervaloDiasEntrePrimeiraConsultaRetorno;
	private Integer intervaloDiasEntreConsultaRetorno;
	private Integer intervaloDiasEntreRetornoConsulta;
	
	
	public AtendimentoParametroDTO(AtendimentoParametro atendimento) {
		id = atendimento.getId();
		quantidadeParcelas = atendimento.getQuantidadeParcelas();
		intervaloMinutosEntreAtendimentos = atendimento.getIntervaloMinutosEntreAtendimentos();
		intervaloDiasEntrePrimeiraConsultaRetorno = atendimento.getIntervaloDiasEntrePrimeiraConsultaRetorno();
		intervaloDiasEntreConsultaRetorno = atendimento.getIntervaloDiasEntreConsultaRetorno();
		intervaloDiasEntreRetornoConsulta = atendimento.getIntervaloDiasEntreRetornoConsulta();
	}
}
