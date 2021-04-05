package br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.form;

import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.enums.consumo.AlimentoTipoModoConsumo;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.enums.frequencia.consumo.FrequenciaConsumoAlimento;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.form.FrequenciaAlimentarFORM;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.model.FrequenciaAlimentar;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.model.QuestionarioFrequenciaAlimentar;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class QuestionarioFrequenciaAlimentarFORM {

	@Valid
	@NotNull(message = "A lista da Frequência Alimentar não poser ser nula!")
	private List<FrequenciaAlimentarFORM> frequenciaConsumoAlimentos;
	
	@Size(max = 1, message = "O campo Código do Consumo do Tipo de Bebida dever conter somente {max} caracter!")
	@Pattern(regexp = "[NDQ]{1};", message = "Formato dos códigos do Consumo do Tipo de Bebida inválidos!")
	private String codigoConsumoTipoBebida;
	
	@Size(max = 8, message = "O campo Código do Consumo do Tipo de Leite dever conter no máximo {max} caracteres!")
	@Pattern(regexp = "[0-5]{1};", message = "Formato dos códigos do Consumo do Tipo de Leite inválidos!")
	private String codigosConsumoTipoLeite;
	
	@Size(max = 8, message = "O campo Código do Consumo de Carne Vermelha dever conter no máximo {max} caracteres!")
	@Pattern(regexp = "[0-5]{1};", message = "Formato dos códigos do Consumo de Carne Vermelha inválidos!")
	private String codigosConsumoCarneVermelha;
	
	@Size(max = 8, message = "O campo Código do Consumo de Frango dever conter no máximo {max} caracteres!")
	@Pattern(regexp = "[0-5]{1};", message = "Formato dos códigos do Consumo de Frango inválidos!")
	private String codigosConsumoFrango;
	
	@Size(max = 8, message = "O campo Código do Consumo de Peixe dever conter no máximo {max} caracteres!")
	@Pattern(regexp = "[0-3]{1};", message = "Formato dos códigos do Consumo de Peixe inválidos!")
	private String codigosConsumoPeixe;
	
	
	public QuestionarioFrequenciaAlimentar converterParaQuestionarioFrequenciaAlimentar() {

		return null;
	}
	
	
	private void verificarConsumoTipoBebida(List<FrequenciaAlimentar> frequenciaAlimentar) {
		FrequenciaAlimentar sucoArtificial = null;
		FrequenciaAlimentar refrigerante = null;
		
		for (FrequenciaAlimentar alimento : frequenciaAlimentar) {
			String descricaoAlimento = alimento.getAlimentoFrequenciaAlimentar().getDescricao().toLowerCase();
			
			if (descricaoAlimento.contains(AlimentoTipoModoConsumo.SUCO_ARTIFICIAL.getDescricao())) 
				sucoArtificial = alimento;
			
			else if (descricaoAlimento.contains(AlimentoTipoModoConsumo.REFRIGERANTES.getDescricao())) 
				refrigerante = alimento;
		}
		
		if ((Objects.nonNull(sucoArtificial) && !sucoArtificial.getFrequenciaConsumoAlimento().equals(FrequenciaConsumoAlimento.NUNCA))
				|| (Objects.isNull(sucoArtificial) && Objects.nonNull(refrigerante) && 
						!refrigerante.getFrequenciaConsumoAlimento().equals(FrequenciaConsumoAlimento.NUNCA))) {
			
		}
		
		else codigoConsumoTipoBebida = null;
	}
}
