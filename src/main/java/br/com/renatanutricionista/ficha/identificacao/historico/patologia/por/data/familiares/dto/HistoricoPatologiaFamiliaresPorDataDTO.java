package br.com.renatanutricionista.ficha.identificacao.historico.patologia.por.data.familiares.dto;

import java.util.Set;
import java.util.stream.Collectors;

import br.com.renatanutricionista.ficha.identificacao.historico.patologia.familiares.dto.HistoricoPatologiaFamiliaresDTO;
import br.com.renatanutricionista.ficha.identificacao.historico.patologia.por.data.familiares.model.HistoricoPatologiaFamiliaresPorData;
import br.com.renatanutricionista.utils.ConversaoUtils;
import lombok.Getter;


@Getter
public class HistoricoPatologiaFamiliaresPorDataDTO {

	private Long id;
	private String observacao;
	private String dataHoraCadastroPatologiasFamiliares;
	private Set<HistoricoPatologiaFamiliaresDTO> patologiasFamiliares;
	
	
	public HistoricoPatologiaFamiliaresPorDataDTO(HistoricoPatologiaFamiliaresPorData historicoPatologiaFamiliaresPorData) {
		id = historicoPatologiaFamiliaresPorData.getId();
		observacao= historicoPatologiaFamiliaresPorData.getObservacao();
		
		dataHoraCadastroPatologiasFamiliares = ConversaoUtils.converterLocalDateTimeParaStringDataHoraMinuto(
				historicoPatologiaFamiliaresPorData.getDataHoraCadastroPatologiasFamiliares());
		
		patologiasFamiliares = HistoricoPatologiaFamiliaresDTO.converterParaListaHistoricoPatologiaFamiliaresDTO(
				historicoPatologiaFamiliaresPorData.getPatologiasFamiliares());
	}
	
	
	public static Set<HistoricoPatologiaFamiliaresPorDataDTO> converterParaSetHistoricoPatologiaFamiliaresPorDataDTO(
			Set<HistoricoPatologiaFamiliaresPorData> historicoPatologiaFamiliaresPorDatas) {
		
		return historicoPatologiaFamiliaresPorDatas.stream().map(HistoricoPatologiaFamiliaresPorDataDTO::new).collect(Collectors.toSet());
	}
}
