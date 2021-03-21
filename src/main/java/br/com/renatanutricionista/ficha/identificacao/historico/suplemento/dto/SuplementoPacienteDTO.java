package br.com.renatanutricionista.ficha.identificacao.historico.suplemento.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.renatanutricionista.ficha.identificacao.historico.suplemento.model.SuplementoPaciente;
import br.com.renatanutricionista.suplemento.dto.SuplementoDTO;
import lombok.Getter;


@Getter
public class SuplementoPacienteDTO {

	private Long id;
	private String dose;
	private String formaPreparo;
	private SuplementoDTO suplemento;
	
	private String mensagemDoseUtilizadaPeloPacienteForDiferenteDoSuplemento;
	private String mensagemFormaPreparoUtilizadaPeloPacienteForDiferenteDoSuplemento;
	
	
	public SuplementoPacienteDTO(SuplementoPaciente suplementoPaciente) {
		id = suplementoPaciente.getId();
		dose = suplementoPaciente.getDose();
		formaPreparo = suplementoPaciente.getFormaPreparo();
		suplemento = new SuplementoDTO(suplementoPaciente.getSuplemento());
		
		if (!dose.equals(suplemento.getDose()))
			mensagemDoseUtilizadaPeloPacienteForDiferenteDoSuplemento = "A Dose utilizada pelo Paciente não é igual a dose "
					+ "contida nas informações do Suplemento";
		
		if (!formaPreparo.equals(suplemento.getFormaPreparo()))
			mensagemFormaPreparoUtilizadaPeloPacienteForDiferenteDoSuplemento = "A Forma de Preparo utilizada pelo Paciente "
					+ "não é igual a Forma de Preaparo contida nas informações do Suplemento";
	}
	
	
	public static List<SuplementoPacienteDTO> converterParaListaSuplementoPacienteDTO(List<SuplementoPaciente> suplementosPaciente) {
		return suplementosPaciente.stream().map(SuplementoPacienteDTO::new).collect(Collectors.toList());
	}
}
