package br.com.renatanutricionista.atendimento.paciente.retorno.dto;

import java.util.List;

import br.com.renatanutricionista.atendimento.paciente.consulta.model.Consulta;
import br.com.renatanutricionista.atendimento.paciente.utils.InformacoesCadastroAtendimentoDTO;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.alimentos.model.AlimentoFrequenciaAlimentar;
import br.com.renatanutricionista.ficha.identificacao.historico.social.dto.ImagemColoracaoDiureseDTO;
import br.com.renatanutricionista.medicamento.model.Medicamento;
import br.com.renatanutricionista.patologia.model.Patologia;
import br.com.renatanutricionista.suplemento.model.Suplemento;
import br.com.renatanutricionista.utils.ConversaoUtils;
import lombok.Getter;


@Getter
public class InformacoesCadastroRetornoConsultaDTO extends InformacoesCadastroAtendimentoDTO {
	
	private String dataHorarioConsultaDoRetorno;
	private String motivoConsultaDoRetorno;

	public InformacoesCadastroRetornoConsultaDTO(Consulta consulta, List<AlimentoFrequenciaAlimentar> alimentosFrequenciaAlimentar,
			List<Patologia> patologias, List<Medicamento> medicamentos, List<Suplemento> suplementos,
			List<ImagemColoracaoDiureseDTO> imagensColoracaoDiurese) {
		
		super(consulta.getPaciente(), alimentosFrequenciaAlimentar, patologias, medicamentos, suplementos, imagensColoracaoDiurese);

		dataHorarioConsultaDoRetorno = "Consulta realizada na data de " + ConversaoUtils.converterLocalDateParaString(consulta.getData())
			+ " às " + consulta.getHorario() + "h";
		
		motivoConsultaDoRetorno = consulta.getMotivoConsulta();
	}
}
