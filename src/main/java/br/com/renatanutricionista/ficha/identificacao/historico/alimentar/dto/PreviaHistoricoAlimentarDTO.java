package br.com.renatanutricionista.ficha.identificacao.historico.alimentar.dto;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import br.com.renatanutricionista.ficha.identificacao.historico.alimentar.model.HistoricoAlimentar;
import br.com.renatanutricionista.utils.ConversaoUtils;
import lombok.Getter;


@Getter
public class PreviaHistoricoAlimentarDTO {

	private Long id;
	private String dataHoraCadastroHistoricoAlimentar;
	
	
	public PreviaHistoricoAlimentarDTO(HistoricoAlimentar historicoAlimentar) {
		id = historicoAlimentar.getId();
		dataHoraCadastroHistoricoAlimentar = ConversaoUtils.converterLocalDateTimeParaFrontEndEmString(historicoAlimentar.getDataHoraCadastroHistoricoAlimentar());
	}
	
	
	public static List<PreviaHistoricoAlimentarDTO> converterParaListaInformacoesPreviasHistoricoAlimentarDTO(List<HistoricoAlimentar> historicoAlimentar) {
		return historicoAlimentar.stream()
				.sorted(Comparator.comparing(HistoricoAlimentar::getDataHoraCadastroHistoricoAlimentar)
				.reversed())
				.map(PreviaHistoricoAlimentarDTO::new)
				.collect(Collectors.toList());
	}
}
