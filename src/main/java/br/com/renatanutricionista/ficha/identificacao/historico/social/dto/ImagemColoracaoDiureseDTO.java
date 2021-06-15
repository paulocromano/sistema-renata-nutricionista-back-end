package br.com.renatanutricionista.ficha.identificacao.historico.social.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.renatanutricionista.ficha.identificacao.historico.social.model.ImagemColoracaoDiurese;
import br.com.renatanutricionista.utils.conversao.enums.DadosEnum;
import lombok.Getter;


@Getter
public class ImagemColoracaoDiureseDTO {

	private Integer id;
	private DadosEnum coloracaoDiurese;
	private String uuidImagemCorBase64;
	
	
	public ImagemColoracaoDiureseDTO(ImagemColoracaoDiurese imagemColoracaoDiurese) {
		id = imagemColoracaoDiurese.getId();
		coloracaoDiurese = new DadosEnum(imagemColoracaoDiurese.getColoracaoDiurese().getCodigo(), 
				imagemColoracaoDiurese.getColoracaoDiurese().getDescricao());
		
		uuidImagemCorBase64 = "data:image/png;base64," + imagemColoracaoDiurese.getUuidImagem();
	}
	
	
	public static List<ImagemColoracaoDiureseDTO> converterParaListaImagemCorDiureseDTO(List<ImagemColoracaoDiurese> imagens) {
		return imagens.stream().map(ImagemColoracaoDiureseDTO::new).collect(Collectors.toList());
	}
}
