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
	
	
	public static ConsumoTipoLeite converterParaEnum(String codigoLeite) {
		if (Objects.isNull(codigoLeite))
			throw new NullPointerException("O Tipo de Leite não pode estar nulo!");
		
		for (ConsumoTipoLeite consumoTipoLeite : ConsumoTipoLeite.values()) 
			if (codigoLeite.equals(consumoTipoLeite.codigo))
				return consumoTipoLeite;
		
		throw new IllegalArgumentException("O código do Tipo de Leite é inválido!");
	}
}
