package br.com.renatanutricionista.atendimento.paciente.consulta.dto;

import br.com.renatanutricionista.atendimento.paciente.utils.InformacoesAtendimentoParaCadastroDTO;
import br.com.renatanutricionista.paciente.dto.PacientePreviaHistoricosDTO;


public class InformacoesConsultaHistoricoParaCadastroDTO extends InformacoesAtendimentoParaCadastroDTO {

	
	public InformacoesConsultaHistoricoParaCadastroDTO(PacientePreviaHistoricosDTO pacientePreviaHistoricos) {
		super(pacientePreviaHistoricos);
	}
}
