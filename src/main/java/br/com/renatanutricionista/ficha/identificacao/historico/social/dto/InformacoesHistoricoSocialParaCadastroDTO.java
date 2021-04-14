package br.com.renatanutricionista.ficha.identificacao.historico.social.dto;

import java.util.List;

import br.com.renatanutricionista.ficha.identificacao.historico.social.enums.consistencia.fezes.ConsistenciaFezes;
import br.com.renatanutricionista.ficha.identificacao.historico.social.enums.consumo.bebidas.alcoolicas.ConsumoBebidasAlcoolicas;
import br.com.renatanutricionista.ficha.identificacao.historico.social.enums.consumo.cigarro.ConsumoCigarro;
import br.com.renatanutricionista.ficha.identificacao.historico.social.enums.diurese.coloracao.ColoracaoDiurese;
import br.com.renatanutricionista.ficha.identificacao.historico.social.enums.diurese.frequencia.FrequenciaDiurese;
import br.com.renatanutricionista.ficha.identificacao.historico.social.enums.estado.civil.EstadoCivil;
import br.com.renatanutricionista.ficha.identificacao.historico.social.enums.habito.intestinal.HabitoIntestinal;
import br.com.renatanutricionista.utils.conversao.enums.ConversaoDadosEnum;
import br.com.renatanutricionista.utils.conversao.enums.DadosEnum;


public class InformacoesHistoricoSocialParaCadastroDTO {

	public final List<DadosEnum> estadoCivil;
	public final List<DadosEnum> consumoBebidasAlcoolicas;
	public final List<DadosEnum> consumoCigarro;
	public final List<DadosEnum> habitoIntestinal;
	public final List<DadosEnum> consistenciaFezes;
	public final List<DadosEnum> frequenciaDiurese;
	public final List<DadosEnum> coloracaoDiurese;
	
	
	public InformacoesHistoricoSocialParaCadastroDTO() {
		ConversaoDadosEnum conversao = new ConversaoDadosEnum();
		
		estadoCivil = conversao.converterDadosEnum(EstadoCivil.values());
		consumoBebidasAlcoolicas = conversao.converterDadosEnum(ConsumoBebidasAlcoolicas.values());
		consumoCigarro = conversao.converterDadosEnum(ConsumoCigarro.values());
		habitoIntestinal = conversao.converterDadosEnum(HabitoIntestinal.values());
		consistenciaFezes = conversao.converterDadosEnum(ConsistenciaFezes.values());
		frequenciaDiurese = conversao.converterDadosEnum(FrequenciaDiurese.values());
		coloracaoDiurese = conversao.converterDadosEnum(ColoracaoDiurese.values());
	}
}
