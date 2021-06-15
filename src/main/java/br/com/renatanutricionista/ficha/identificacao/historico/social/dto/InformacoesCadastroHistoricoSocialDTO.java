package br.com.renatanutricionista.ficha.identificacao.historico.social.dto;

import java.util.List;

import br.com.renatanutricionista.ficha.identificacao.historico.social.enums.consistencia.fezes.ConsistenciaFezes;
import br.com.renatanutricionista.ficha.identificacao.historico.social.enums.consumo.bebidas.alcoolicas.ConsumoBebidasAlcoolicas;
import br.com.renatanutricionista.ficha.identificacao.historico.social.enums.consumo.cigarro.ConsumoCigarro;
import br.com.renatanutricionista.ficha.identificacao.historico.social.enums.diurese.frequencia.FrequenciaDiurese;
import br.com.renatanutricionista.ficha.identificacao.historico.social.enums.estado.civil.EstadoCivil;
import br.com.renatanutricionista.ficha.identificacao.historico.social.enums.habito.intestinal.HabitoIntestinal;
import br.com.renatanutricionista.patologia.dto.PatologiaDTO;
import br.com.renatanutricionista.patologia.model.Patologia;
import br.com.renatanutricionista.utils.conversao.enums.ConversaoDadosEnum;
import br.com.renatanutricionista.utils.conversao.enums.DadosEnum;
import lombok.Getter;


@Getter
public class InformacoesCadastroHistoricoSocialDTO {

	private List<PatologiaDTO> patologias;
	private List<DadosEnum> estadoCivil;
	private List<DadosEnum> consumoBebidasAlcoolicas;
	private List<DadosEnum> consumoCigarro;
	private List<DadosEnum> habitoIntestinal;
	private List<DadosEnum> consistenciaFezes;
	private List<DadosEnum> frequenciaDiurese;
	private List<ImagemColoracaoDiureseDTO> imagensColoracaoDiurese;
	
	
	public InformacoesCadastroHistoricoSocialDTO(List<Patologia> patologias, List<ImagemColoracaoDiureseDTO> imagensColoracaoDiurese) {
		this.patologias = PatologiaDTO.converterParaListaPatologiaDTOEmOrdemAlfabetica(patologias);
		estadoCivil = ConversaoDadosEnum.converterDadosEnum(EstadoCivil.values());
		consumoBebidasAlcoolicas = ConversaoDadosEnum.converterDadosEnum(ConsumoBebidasAlcoolicas.values());
		consumoCigarro = ConversaoDadosEnum.converterDadosEnum(ConsumoCigarro.values());
		habitoIntestinal = ConversaoDadosEnum.converterDadosEnum(HabitoIntestinal.values());
		consistenciaFezes = ConversaoDadosEnum.converterDadosEnum(ConsistenciaFezes.values());
		frequenciaDiurese = ConversaoDadosEnum.converterDadosEnum(FrequenciaDiurese.values());
		this.imagensColoracaoDiurese = imagensColoracaoDiurese;
	}
}
