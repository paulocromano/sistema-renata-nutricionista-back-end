package br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.dto;

import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.enums.ConsumoCarneVermelha;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.enums.ConsumoFrango;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.enums.ConsumoPeixe;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.enums.ConsumoTipoBebida;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.enums.ConsumoTipoLeite;


public class InformacoesConsumoQuestionarioParaCadastroDTO {
	
	public ConsumoCarneVermelha[] CONSUMO_CARNE_VEMELHA = ConsumoCarneVermelha.values();
	public ConsumoFrango[] CONSUMO_FRANGO = ConsumoFrango.values();
	public ConsumoPeixe[] CONSUMO_PEIXE = ConsumoPeixe.values();
	public ConsumoTipoBebida[] CONSUMO_TIPO_BEBIDA = ConsumoTipoBebida.values();
	public ConsumoTipoLeite[] CONSUMO_TIPO_LEITE = ConsumoTipoLeite.values();	
	
	
	public InformacoesConsumoQuestionarioParaCadastroDTO() {

	}
}
