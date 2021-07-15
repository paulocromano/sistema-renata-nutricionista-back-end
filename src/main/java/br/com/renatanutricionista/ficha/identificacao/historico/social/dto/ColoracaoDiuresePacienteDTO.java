package br.com.renatanutricionista.ficha.identificacao.historico.social.dto;

import br.com.renatanutricionista.ficha.identificacao.historico.social.model.ColoracaoDiuresePaciente;
import lombok.Getter;

@Getter
public class ColoracaoDiuresePacienteDTO {

	private Long id;
	private ImagemColoracaoDiureseDTO imagemColoracaoDiurese;
	
	
	public ColoracaoDiuresePacienteDTO(ColoracaoDiuresePaciente coloracaoDiuresePaciente) {
		id = coloracaoDiuresePaciente.getId();
		imagemColoracaoDiurese = new ImagemColoracaoDiureseDTO(
				coloracaoDiuresePaciente.getColoracaoDiurese());
	}
}
