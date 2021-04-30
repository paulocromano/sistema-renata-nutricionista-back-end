package br.com.renatanutricionista.ficha.identificacao.historico.suplemento.dto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.renatanutricionista.ficha.identificacao.historico.suplemento.model.SuplementoPaciente;
import br.com.renatanutricionista.suplemento.dto.SuplementoDTO;
import br.com.renatanutricionista.utils.FormatacaoUtils;
import lombok.Getter;


@Getter
public class SuplementoPacienteDTO implements Comparable<SuplementoPacienteDTO> {

	private Long id;
	private String dose;
	private String formaPreparo;
	private SuplementoDTO suplemento;
	
	private String mensagemDoseUtilizadaPeloPacienteDiferenteDoSuplemento;
	private String mensagemFormaPreparoUtilizadaPeloPacienteDiferenteDoSuplemento;
	
	
	public SuplementoPacienteDTO(SuplementoPaciente suplementoPaciente) {
		id = suplementoPaciente.getId();
		dose = suplementoPaciente.getDose();
		formaPreparo = suplementoPaciente.getFormaPreparo();
		suplemento = new SuplementoDTO(suplementoPaciente.getSuplemento());
		
		if (!dose.equals(suplemento.getDose()))
			mensagemDoseUtilizadaPeloPacienteDiferenteDoSuplemento = "A Dose utilizada pelo Paciente não é igual a dose "
					+ "contida nas informações do Suplemento.";
		
		if (!formaPreparo.equals(suplemento.getFormaPreparo()))
			mensagemFormaPreparoUtilizadaPeloPacienteDiferenteDoSuplemento = "A Forma de Preparo utilizada pelo Paciente "
					+ "não é igual a Forma de Preaparo contida nas informações do Suplemento.";
	}

	
	@Override
	public int compareTo(SuplementoPacienteDTO other) {
		return FormatacaoUtils.COLLATOR.compare(suplemento.getNome(), other.getSuplemento().getNome());
	}
	
	
	public static List<SuplementoPacienteDTO> converterParaListaSuplementoPacienteDTO(List<SuplementoPaciente> suplementosPaciente) {
		return suplementosPaciente.stream().map(SuplementoPacienteDTO::new).sorted().collect(Collectors.toList());
	}
	
	
	public static Set<SuplementoPacienteDTO> converterParaSetSuplementoPacienteDTO(Set<SuplementoPaciente> suplementosPaciente) {
		return suplementosPaciente.stream().map(SuplementoPacienteDTO::new).sorted().collect(Collectors.toSet());
	}
}
