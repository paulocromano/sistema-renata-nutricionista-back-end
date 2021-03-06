package br.com.renatanutricionista.ficha.identificacao.historico.patologia.por.data.familiares.dto;

import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.renatanutricionista.ficha.identificacao.historico.patologia.por.data.familiares.model.HistoricoPatologiaFamiliaresPorData;
import br.com.renatanutricionista.utils.ConversaoUtils;
import lombok.Getter;


@Getter
public class PreviaHistoricoPatologiaFamiliaresPorDataDTO {

	private Long id;
	private String dataHoraCadastroPatologiasFamiliaresPorData;
	
	
	public PreviaHistoricoPatologiaFamiliaresPorDataDTO(HistoricoPatologiaFamiliaresPorData historicoPatologiaFamiliaresPorData) {
		id = historicoPatologiaFamiliaresPorData.getId();
		dataHoraCadastroPatologiasFamiliaresPorData = ConversaoUtils.converterLocalDateTimeParaFrontEndEmString(
				historicoPatologiaFamiliaresPorData.getDataHoraCadastroPatologiasFamiliares());
	}
	
	
	public static Set<PreviaHistoricoPatologiaFamiliaresPorDataDTO> converterParaSetPreviaHistoricoPatologiaFamiliaresPorDataDTO(
			Set<HistoricoPatologiaFamiliaresPorData> historicoPatologiaFamiliaresPorDatas) {
		
		return historicoPatologiaFamiliaresPorDatas.stream()
				.sorted(Comparator.comparing(HistoricoPatologiaFamiliaresPorData::getDataHoraCadastroPatologiasFamiliares)
				.reversed())
				.map(PreviaHistoricoPatologiaFamiliaresPorDataDTO::new)
				.collect(Collectors.toSet());
	}
}
