package br.com.renatanutricionista.atendimento.paciente.consulta.dto;

import java.util.List;

import br.com.renatanutricionista.atendimento.paciente.consulta.model.Consulta;
import br.com.renatanutricionista.atendimento.paciente.utils.InformacoesCadastroAtendimentoDTO;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.alimentos.model.AlimentoFrequenciaAlimentar;
import lombok.Getter;


@Getter
public class InformacoesCadastroConsultaDTO extends InformacoesCadastroAtendimentoDTO {

	private String motivoConsulta;
	
	public InformacoesCadastroConsultaDTO(Consulta consulta, List<AlimentoFrequenciaAlimentar> alimentosFrequenciaAlimentar) {
		super(consulta.getPaciente(), alimentosFrequenciaAlimentar);
		
		motivoConsulta = consulta.getMotivoConsulta();	
	}
}
