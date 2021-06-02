package br.com.renatanutricionista.atendimento.paciente.consulta.dto;

import java.util.List;

import br.com.renatanutricionista.atendimento.paciente.consulta.model.Consulta;
import br.com.renatanutricionista.atendimento.paciente.utils.InformacoesCadastroAtendimentoDTO;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.alimentos.model.AlimentoFrequenciaAlimentar;
import br.com.renatanutricionista.patologia.model.Patologia;
import lombok.Getter;


@Getter
public class InformacoesCadastroConsultaDTO extends InformacoesCadastroAtendimentoDTO {

	private String motivoConsulta;
	
	public InformacoesCadastroConsultaDTO(Consulta consulta, List<AlimentoFrequenciaAlimentar> alimentosFrequenciaAlimentar,
			List<Patologia> patologias) {
		
		super(consulta.getPaciente(), alimentosFrequenciaAlimentar, patologias);
		
		motivoConsulta = consulta.getMotivoConsulta();	
	}
}
