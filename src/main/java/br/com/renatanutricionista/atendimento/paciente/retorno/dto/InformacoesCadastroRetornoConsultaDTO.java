package br.com.renatanutricionista.atendimento.paciente.retorno.dto;

import java.util.List;

import br.com.renatanutricionista.atendimento.paciente.consulta.model.Consulta;
import br.com.renatanutricionista.atendimento.paciente.utils.InformacoesCadastroAtendimentoDTO;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.alimentos.model.AlimentoFrequenciaAlimentar;
import br.com.renatanutricionista.utils.ConversaoUtils;
import lombok.Getter;


@Getter
public class InformacoesCadastroRetornoConsultaDTO extends InformacoesCadastroAtendimentoDTO {
	
	private String dataHorarioConsultaDoRetorno;
	private String motivoConsultaDoRetorno;

	public InformacoesCadastroRetornoConsultaDTO(Consulta consulta, List<AlimentoFrequenciaAlimentar> alimentosFrequenciaAlimentar) {
		super(consulta.getPaciente(), alimentosFrequenciaAlimentar);

		dataHorarioConsultaDoRetorno = "Consulta realizada na data de " + ConversaoUtils.converterLocalDateParaString(consulta.getData())
			+ " às " + consulta.getHorario() + "h";
		
		motivoConsultaDoRetorno = consulta.getMotivoConsulta();
	}
}
