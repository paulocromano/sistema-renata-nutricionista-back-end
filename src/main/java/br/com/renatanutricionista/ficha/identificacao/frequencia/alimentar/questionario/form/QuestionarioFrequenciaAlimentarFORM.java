package br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.form;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.enums.consumo.AlimentoTipoModoConsumo;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.enums.consumo.ConsumoCarneVermelha;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.enums.consumo.ConsumoFrango;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.enums.consumo.ConsumoPeixe;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.enums.consumo.ConsumoTipoBebida;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.enums.consumo.ConsumoTipoLeite;
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
	private Set<FrequenciaAlimentarFORM> frequenciaConsumoAlimentos;
	
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
	
	
	private Set<FrequenciaAlimentar> gerarListaFrequenciaAlimentar() {
		return frequenciaConsumoAlimentos.stream().map(frequencia -> 
				new FrequenciaAlimentar(frequencia.getIdAlimentoFrequenciaAlimentar(), frequencia.getFrequenciaConsumoAlimento()))
				.collect(Collectors.toSet());
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
		
		if (Objects.isNull(sucoArtificial) || Objects.isNull(refrigerante))
			throw new NullPointerException("O(s) alimento(s) Suco Artificial e/ou Refrigerante não pode(m) estar vazio(s)!");
		
		if (!sucoArtificial.getFrequenciaConsumoAlimento().equals(FrequenciaConsumoAlimento.NUNCA)
				|| !refrigerante.getFrequenciaConsumoAlimento().equals(FrequenciaConsumoAlimento.NUNCA))
			
			codigoConsumoTipoBebida = ConsumoTipoBebida.validarCodigo(codigoConsumoTipoBebida);
		
		else codigoConsumoTipoBebida = null;
	}
	
	
	private void verificarConsumoTipoLeite(List<FrequenciaAlimentar> frequenciaAlimentar) {
		FrequenciaAlimentar leite = verificarSeAlimentoExiste(frequenciaAlimentar, AlimentoTipoModoConsumo.LEITE);
		
		if (!leite.getFrequenciaConsumoAlimento().equals(FrequenciaConsumoAlimento.NUNCA))
			ConsumoTipoLeite.validarCodigo(codigosConsumoTipoLeite);
		
		else codigosConsumoTipoLeite = null;
	}
	
	
	private void verificarConsumoCarneVermelha(List<FrequenciaAlimentar> frequenciaAlimentar) {
		verificarSeAlimentoExiste(frequenciaAlimentar, AlimentoTipoModoConsumo.CARNE_VERMELHA);
		ConsumoCarneVermelha.validarCodigo(codigosConsumoCarneVermelha);
	}
	
	
	private void verificarConsumoFrango(List<FrequenciaAlimentar> frequenciaAlimentar) {
		verificarSeAlimentoExiste(frequenciaAlimentar, AlimentoTipoModoConsumo.FRANGO);
		ConsumoFrango.validarCodigo(codigosConsumoFrango);
	}
	
	
	private void validarConsumoPeixe(List<FrequenciaAlimentar> frequenciaAlimentar) {
		verificarSeAlimentoExiste(frequenciaAlimentar, AlimentoTipoModoConsumo.PEIXE);
		ConsumoPeixe.validarCodigo(codigosConsumoPeixe);
	}
	
	
	private FrequenciaAlimentar verificarSeAlimentoExiste(List<FrequenciaAlimentar> frequenciaAlimentar, AlimentoTipoModoConsumo alimentoTipoModoConsumo) {
		FrequenciaAlimentar alimentoBuscado = null;
		
		for (FrequenciaAlimentar alimento : frequenciaAlimentar) {
			String descricaoAlimento = alimento.getAlimentoFrequenciaAlimentar().getDescricao().toLowerCase();
			
			if (descricaoAlimento.contains(alimentoTipoModoConsumo.getDescricao()))
				return alimentoBuscado;
		}
		
		throw new NullPointerException("O alimento " + alimentoTipoModoConsumo + "não pode estar vazio!");
	}
}
