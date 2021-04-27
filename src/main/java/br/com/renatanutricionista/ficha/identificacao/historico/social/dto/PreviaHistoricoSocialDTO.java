package br.com.renatanutricionista.ficha.identificacao.historico.social.dto;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import br.com.renatanutricionista.ficha.identificacao.historico.social.model.HistoricoSocial;
import br.com.renatanutricionista.utils.ConversaoUtils;
import lombok.Getter;


@Getter
public class PreviaHistoricoSocialDTO {

	private Long id;
	private String dataHoraCadastroHistoricoSocial;
	
	
	public PreviaHistoricoSocialDTO(HistoricoSocial historicoSocial) {
		id = historicoSocial.getId();
		dataHoraCadastroHistoricoSocial = ConversaoUtils.converterLocalDateTimeParaFrontEndEmString(historicoSocial.getDataHoraCadastroHistoricoSocial());
	}
	
	
	public static List<PreviaHistoricoSocialDTO> converterParaListaPreviasHistoricoSocialDTO(List<HistoricoSocial> historicoSocial) {
		return historicoSocial.stream()
				.sorted(Comparator.comparing(HistoricoSocial::getDataHoraCadastroHistoricoSocial)
				.reversed())
				.map(PreviaHistoricoSocialDTO::new)
				.collect(Collectors.toList());
	}
}
