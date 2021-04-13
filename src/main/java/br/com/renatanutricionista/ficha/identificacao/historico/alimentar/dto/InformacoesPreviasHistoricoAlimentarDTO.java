package br.com.renatanutricionista.ficha.identificacao.historico.alimentar.dto;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import br.com.renatanutricionista.ficha.identificacao.historico.alimentar.model.HistoricoAlimentar;
import br.com.renatanutricionista.utils.ConversaoUtils;
import lombok.Getter;


@Getter
public class InformacoesPreviasHistoricoAlimentarDTO {

	private Long id;
	private String dataHoraUltimaAtualizacaoDadosDoHistoricoAlimentar;
	
	
	public InformacoesPreviasHistoricoAlimentarDTO(HistoricoAlimentar historicoAlimentar) {
		id = historicoAlimentar.getId();
		dataHoraUltimaAtualizacaoDadosDoHistoricoAlimentar = ConversaoUtils.converterLocalDateTimeParaStringDataHoraMinuto(
				historicoAlimentar.getDataHoraUltimaAtualizacaoDadosDoHistoricoAlimentar());
	}
	
	
	public static List<InformacoesPreviasHistoricoAlimentarDTO> converterParaListaInformacoesPreviasHistoricoAlimentarDTO(List<HistoricoAlimentar> historicoAlimentar) {
		return historicoAlimentar.stream()
				.sorted(Comparator.comparing(HistoricoAlimentar::getDataHoraUltimaAtualizacaoDadosDoHistoricoAlimentar)
				.reversed())
				.map(InformacoesPreviasHistoricoAlimentarDTO::new)
				.collect(Collectors.toList());
	}
}
