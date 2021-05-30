package br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.dto;

import java.util.List;

import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.alimentos.dto.AlimentoFrequenciaAlimentarDTO;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.alimentos.model.AlimentoFrequenciaAlimentar;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.enums.FrequenciaConsumoAlimento;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.enums.ConsumoCarneVermelha;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.enums.ConsumoFrango;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.enums.ConsumoPeixe;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.enums.ConsumoTipoBebida;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.enums.ConsumoTipoLeite;
import br.com.renatanutricionista.utils.conversao.enums.ConversaoDadosEnum;
import br.com.renatanutricionista.utils.conversao.enums.DadosEnum;
import lombok.Getter;


@Getter
public class InformacoesCadastroConsumoQuestionarioDTO {
	
	private List<AlimentoFrequenciaAlimentarDTO> alimentosFrequenciaAlimentar;
	private List<DadosEnum> consumoCarneVermelha;
	private List<DadosEnum> consumoFrango;
	private List<DadosEnum> consumoPeixe;
	private List<DadosEnum> consumoTipoBebida;
	private List<DadosEnum> consumoTipoLeite;	
	private List<DadosEnum> frequenciaConsumoAlimento;
	
	
	public InformacoesCadastroConsumoQuestionarioDTO(List<AlimentoFrequenciaAlimentar> alimentosFrequenciaAlimentar) {
		this.alimentosFrequenciaAlimentar = AlimentoFrequenciaAlimentarDTO.converterParaListaAlimentoFrequenciaAlimentarDTO(alimentosFrequenciaAlimentar);

		consumoCarneVermelha = ConversaoDadosEnum.converterDadosEnum(ConsumoCarneVermelha.values());
		consumoFrango = ConversaoDadosEnum.converterDadosEnum(ConsumoFrango.values());
		consumoPeixe = ConversaoDadosEnum.converterDadosEnum(ConsumoPeixe.values());
		consumoTipoBebida = ConversaoDadosEnum.converterDadosEnum(ConsumoTipoBebida.values());
		consumoTipoLeite = ConversaoDadosEnum.converterDadosEnum(ConsumoTipoLeite.values());
		frequenciaConsumoAlimento = ConversaoDadosEnum.converterDadosEnum(FrequenciaConsumoAlimento.values()); 
	}
}
