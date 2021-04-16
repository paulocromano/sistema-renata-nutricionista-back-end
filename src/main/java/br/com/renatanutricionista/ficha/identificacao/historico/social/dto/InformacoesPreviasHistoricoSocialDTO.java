package br.com.renatanutricionista.ficha.identificacao.historico.social.dto;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import br.com.renatanutricionista.ficha.identificacao.historico.social.model.HistoricoSocial;
import br.com.renatanutricionista.utils.ConversaoUtils;
import lombok.Getter;


@Getter
public class InformacoesPreviasHistoricoSocialDTO {

	private Long id;
	private String dataHoraCadastroHistoricoSocial;
	
	
	public InformacoesPreviasHistoricoSocialDTO(HistoricoSocial historicoSocial) {
		id = historicoSocial.getId();
		dataHoraCadastroHistoricoSocial = ConversaoUtils.converterLocalDateTimeParaStringDataHoraMinuto(historicoSocial.getDataHoraCadastroHistoricoSocial());
	}
	
	
	public static List<InformacoesPreviasHistoricoSocialDTO> converterParaListaInformacoesPreviasHistoricoSocialDTO(List<HistoricoSocial> historicoSocial) {
		return historicoSocial.stream()
				.sorted(Comparator.comparing(HistoricoSocial::getDataHoraCadastroHistoricoSocial)
				.reversed())
				.map(InformacoesPreviasHistoricoSocialDTO::new)
				.collect(Collectors.toList());
	}
}
