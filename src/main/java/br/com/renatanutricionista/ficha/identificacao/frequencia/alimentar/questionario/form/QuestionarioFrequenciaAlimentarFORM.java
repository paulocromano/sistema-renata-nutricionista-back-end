package br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.form;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.enums.FrequenciaConsumoAlimento;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.form.FrequenciaAlimentarFORM;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.model.FrequenciaAlimentar;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.enums.AlimentoTipoModoConsumo;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.enums.ConsumoTipoLeite;
import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.model.QuestionarioFrequenciaAlimentar;
import br.com.renatanutricionista.paciente.model.Paciente;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class QuestionarioFrequenciaAlimentarFORM {

	@NotNull(message = "A lista da Frequência Alimentar não poser ser nula!")
	private Set<FrequenciaAlimentarFORM> frequenciaConsumoAlimentos;
	
	@Size(max = 1, message = "O campo Código do Consumo do Tipo de Bebida dever conter somente {max} caracter!")
	private String consumoTipoBebida;
	
	@Size(max = 8, message = "O campo Código do Consumo do Tipo de Leite dever conter no máximo {max} caracteres!")
	private String consumoTipoLeite;
	
	@Size(max = 12, message = "O campo Código do Consumo de Carne Vermelha dever conter no máximo {max} caracteres!")
	private String consumoCarneVermelha;
	
	@Size(max = 12, message = "O campo Código do Consumo de Frango dever conter no máximo {max} caracteres!")
	private String consumoFrango;
	
	@Size(max = 8, message = "O campo Código do Consumo de Peixe dever conter no máximo {max} caracteres!")
	private String consumoPeixe;
	
	
	public QuestionarioFrequenciaAlimentar converterParaQuestionarioFrequenciaAlimentar(Paciente paciente, Set<FrequenciaAlimentar> frequenciaAlimentar) {
		validacoesConsumos(frequenciaAlimentar);

		return new QuestionarioFrequenciaAlimentar.Builder()
				.dataHoraCadastroQuestionario(LocalDateTime.now())
				.frequenciaConsumoAlimentos(frequenciaAlimentar)
				.consumoTipoBebida(consumoTipoBebida)
				.consumoTipoLeite(consumoTipoLeite)
				.consumoCarneVermelha(consumoCarneVermelha)
				.consumoFrango(consumoFrango)
				.consumoPeixe(consumoPeixe)
				.paciente(paciente)
				.build();
	}
	
	
	private void validacoesConsumos(Set<FrequenciaAlimentar> frequenciaAlimentar) {
		verificarConsumoTipoBebida(frequenciaAlimentar);
		verificarConsumoTipoLeite(frequenciaAlimentar);
		verificarConsumoCarneVermelha(frequenciaAlimentar);
		verificarConsumoFrango(frequenciaAlimentar);
		validarConsumoPeixe(frequenciaAlimentar);
	}
	
	
	private void verificarConsumoTipoBebida(Set<FrequenciaAlimentar> frequenciaAlimentar) {
		FrequenciaAlimentar sucoArtificial = null;
		FrequenciaAlimentar refrigerante = null;
		
		for (FrequenciaAlimentar alimento : frequenciaAlimentar) {
			String descricaoAlimento = alimento.getAlimentoFrequenciaAlimentar().getDescricao().toLowerCase();
			
			if (descricaoAlimento.contains(AlimentoTipoModoConsumo.SUCO_ARTIFICIAL.getDescricao())) {
				sucoArtificial = alimento;
			}
			
			else if (descricaoAlimento.contains(AlimentoTipoModoConsumo.REFRIGERANTES.getDescricao())) {
				refrigerante = alimento;
			}
		}
		
		if (Objects.isNull(sucoArtificial) || Objects.isNull(refrigerante)) {
			throw new NullPointerException("O(s) alimento(s) Suco Artificial e/ou Refrigerante não pode(m) estar vazio(s)!");
		}
		
		if (!sucoArtificial.getFrequenciaConsumoAlimento().equals(FrequenciaConsumoAlimento.NUNCA)
				|| !refrigerante.getFrequenciaConsumoAlimento().equals(FrequenciaConsumoAlimento.NUNCA)) {
			
			if (!Pattern.matches("N|D|Q|", consumoTipoBebida)) {
				throw new IllegalArgumentException("Formato dos códigos do Consumo do Tipo de Bebida inválidos!");
			}		
		}	
		
		else {
			consumoTipoBebida = null;
		}
	}
	
	
	private void verificarConsumoTipoLeite(Set<FrequenciaAlimentar> frequenciaAlimentar) {
		FrequenciaAlimentar leite = verificarSeAlimentoExiste(frequenciaAlimentar, AlimentoTipoModoConsumo.LEITE);

		if (!leite.getFrequenciaConsumoAlimento().equals(FrequenciaConsumoAlimento.NUNCA)) {

			if (!Pattern.matches("(?:([0-5];)(?!.*\\1))+", consumoTipoLeite)) {
				throw new IllegalArgumentException("Formato dos códigos do Consumo do Tipo de Leite inválidos!");
			}

			ConsumoTipoLeite.validarCodigo(consumoTipoLeite);
		}	
		
		else {
			 consumoTipoLeite = null;
		}
	}
	
	
	private void verificarConsumoCarneVermelha(Set<FrequenciaAlimentar> frequenciaAlimentar) {
		FrequenciaAlimentar carneVermelha = verificarSeAlimentoExiste(frequenciaAlimentar, AlimentoTipoModoConsumo.CARNE_VERMELHA);
		
		if (!carneVermelha.getFrequenciaConsumoAlimento().equals(FrequenciaConsumoAlimento.NUNCA)) {
			
			if (!Pattern.matches("(?:([0-5];)(?!.*\\1))+", consumoCarneVermelha)) {
				throw new IllegalArgumentException("Formato dos códigos do Consumo de Carne Vermelha inválidos!");
			}	
		}

		else {
			consumoCarneVermelha = null;
		}
	}
	
	
	private void verificarConsumoFrango(Set<FrequenciaAlimentar> frequenciaAlimentar) {
		FrequenciaAlimentar frango = verificarSeAlimentoExiste(frequenciaAlimentar, AlimentoTipoModoConsumo.FRANGO);
		
		if (!frango.getFrequenciaConsumoAlimento().equals(FrequenciaConsumoAlimento.NUNCA)) {
			
			if (!Pattern.matches("(?:([0-5];)(?!.*\\1))+", consumoFrango)) {
				throw new IllegalArgumentException("Formato dos códigos do Consumo de Frango inválidos!");
			}
		}

		else {
			consumoFrango = null;
		}
	}
	
	
	private void validarConsumoPeixe(Set<FrequenciaAlimentar> frequenciaAlimentar) {
		FrequenciaAlimentar peixe = verificarSeAlimentoExiste(frequenciaAlimentar, AlimentoTipoModoConsumo.PEIXE);
		
		if (!peixe.getFrequenciaConsumoAlimento().equals(FrequenciaConsumoAlimento.NUNCA)) {
			
			if (!Pattern.matches("(?:([0-3];)(?!.*\\1))+", consumoPeixe)) {
				throw new IllegalArgumentException("Formato dos códigos do Consumo de Peixe inválidos!");
			}
		}
		
		else {
			consumoPeixe = null;
		}
	}
	
	
	private FrequenciaAlimentar verificarSeAlimentoExiste(Set<FrequenciaAlimentar> frequenciaAlimentar, AlimentoTipoModoConsumo alimentoTipoModoConsumo) {
		
		for (FrequenciaAlimentar alimento : frequenciaAlimentar) {
			String descricaoAlimento = alimento.getAlimentoFrequenciaAlimentar().getDescricao().toLowerCase();
			
			if (descricaoAlimento.contains(alimentoTipoModoConsumo.getDescricao())) {
				return alimento;
			}	
		}
		
		throw new NullPointerException("O alimento " + alimentoTipoModoConsumo + "não pode estar vazio!");
	}
}
