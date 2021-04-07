package br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.enums;

import java.util.Objects;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ConsumoTipoLeite {

	INTEGRAL("0", "Integral"),
	SEMIDESNATADO("1", "Semidesnatado"),
	DESNATADO("2", "Desnatado"),
	SOJA("3", "Soja"),
	SEM_LACTOSE("4", "Sem lactose"),
	QUALQUER_UM("5", "Qualquer um");
	
	
	private String codigo;
	private String descricao;
	
	
	public static String concatenarTiposLeiteConsumidos(String codigosLeite) {
		if (Objects.isNull(codigosLeite) || codigosLeite.trim().isEmpty())
			return null;

		StringBuilder leitesBuilder = new StringBuilder();
		
		for (String codigoLeite : codigosLeite.split(";")) {
			for (ConsumoTipoLeite consumoTipoLeite : ConsumoTipoLeite.values()) {
				if (codigoLeite.equals(consumoTipoLeite.codigo)) {
					leitesBuilder.append(consumoTipoLeite.descricao);
					leitesBuilder.append(", ");
					
					break;
				}
			}
		}
		
		return leitesBuilder.substring(0, leitesBuilder.length() - 2).toString();
	}
	
	
	public static void validarCodigo(String codigosConsumoTipoLeite) {
		String[] codigos = codigosConsumoTipoLeite.split(";");
		
		if (codigos.length > 4)
			throw new IllegalArgumentException("Só é permitido escolher no máximo 4 opções!");
		
		boolean encontrouOpçaoQualquerUm = false;
		
		for (String codigoConsumo : codigos) {
			if (codigoConsumo.equals(QUALQUER_UM.codigo)) {
				encontrouOpçaoQualquerUm = true;
				break;
			}
		}
		
		if (encontrouOpçaoQualquerUm && codigos.length > 1)
			throw new IllegalArgumentException("Ao selecionar a opção 'Qualquer um' não é permitido "
					+ "escolher as demais opções!");
	}
}
