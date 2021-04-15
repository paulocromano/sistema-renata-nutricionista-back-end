package br.com.renatanutricionista.atendimento.paciente.consulta.dto;

import java.util.List;

import br.com.renatanutricionista.atendimento.paciente.utils.InformacoesAtendimentoParaCadastroDTO;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.alimentos.model.AlimentoFrequenciaAlimentar;
import br.com.renatanutricionista.paciente.dto.PacientePreviaHistoricosDTO;


public class InformacoesConsultaHistoricoParaCadastroDTO extends InformacoesAtendimentoParaCadastroDTO {

	
	public InformacoesConsultaHistoricoParaCadastroDTO(PacientePreviaHistoricosDTO pacientePreviaHistoricos,
			List<AlimentoFrequenciaAlimentar> alimentosFrequenciaAlimentar) {
		
		super(pacientePreviaHistoricos, alimentosFrequenciaAlimentar);
	}
}
