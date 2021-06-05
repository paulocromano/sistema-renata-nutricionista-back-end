package br.com.renatanutricionista.atendimento.paciente.consulta.dto;

import java.util.List;

import br.com.renatanutricionista.atendimento.paciente.consulta.model.Consulta;
import br.com.renatanutricionista.atendimento.paciente.utils.InformacoesCadastroAtendimentoDTO;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.alimentos.model.AlimentoFrequenciaAlimentar;
import br.com.renatanutricionista.medicamento.model.Medicamento;
import br.com.renatanutricionista.patologia.model.Patologia;
import br.com.renatanutricionista.suplemento.model.Suplemento;
import lombok.Getter;


@Getter
public class InformacoesCadastroConsultaDTO extends InformacoesCadastroAtendimentoDTO {

	private String motivoConsulta;
	
	public InformacoesCadastroConsultaDTO(Consulta consulta, List<AlimentoFrequenciaAlimentar> alimentosFrequenciaAlimentar,
			List<Patologia> patologias, List<Medicamento> medicamentos, List<Suplemento> suplementos) {
		
		super(consulta.getPaciente(), alimentosFrequenciaAlimentar, patologias, medicamentos, suplementos);
		
		motivoConsulta = consulta.getMotivoConsulta();	
	}
}
