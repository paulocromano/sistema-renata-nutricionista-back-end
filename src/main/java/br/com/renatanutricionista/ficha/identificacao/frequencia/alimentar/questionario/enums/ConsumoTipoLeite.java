package br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.enums;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.renatanutricionista.utils.DesserializacaoEnum;
import br.com.renatanutricionista.utils.conversao.enums.GettersEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonDeserialize(using = DesserializacaoEnum.class)
public enum ConsumoTipoLeite implements GettersEnum<ConsumoTipoLeite> {

	INTEGRAL("0", "Integral"),
	SEMIDESNATADO("1", "Semidesnatado"),
	DESNATADO("2", "Desnatado"),
	SOJA("3", "Soja"),
	SEM_LACTOSE("4", "Sem lactose"),
	QUALQUER_UM("5", "Qualquer um");
	
	
	private String codigo;
	private String descricao;
	
	
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
