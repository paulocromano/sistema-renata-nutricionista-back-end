package br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.enums.consumo;

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
		if (Objects.isNull(codigosLeite))
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
		
		return leitesBuilder.substring(0, leitesBuilder.length() - 3).toString();
	}
	
	
	public static void validarCodigo(String codigosConsumoTipoLeite) {
		String[] codigos = codigosConsumoTipoLeite.split(";");
		
		if (codigos.length > 4)
			throw new IllegalArgumentException("Só é permitido escolher no máximo 4 opções!");
		
		boolean encontrouOpçaoQualquerUm = false;
		boolean encontrouCodigoValido = false;
		
		for (String codigoConsumo : codigos) {
			for (ConsumoTipoLeite consumoTipoLeite : ConsumoTipoLeite.values()) {
				if (codigoConsumo.equals(consumoTipoLeite.codigo)) {
					encontrouCodigoValido = true;
					break;
				}	
			}
			
			if (codigoConsumo.equals(QUALQUER_UM.codigo)) 
				encontrouOpçaoQualquerUm = true;
			
			if (!encontrouCodigoValido)
				throw new IllegalArgumentException("Existe(m) código(s) inválido(s)!");
			
			encontrouCodigoValido = false;
		}
		
		if (encontrouOpçaoQualquerUm && codigos.length > 1)
			throw new IllegalArgumentException("Ao selecionar a opção 'Qualquer um' não é permitido "
					+ "escolher as demais opções!");
	}
}
