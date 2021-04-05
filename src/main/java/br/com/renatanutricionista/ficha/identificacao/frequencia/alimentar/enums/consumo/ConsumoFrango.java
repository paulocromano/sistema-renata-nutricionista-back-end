package br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.enums.consumo;

import java.util.Objects;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ConsumoFrango {

	COM_PELE("0", "Com pele"),
	SEM_PELE("1", "Sem pele"),
	FRITA_IMERSAO_OLEO("2", "Frita com imersão no óleo"),
	GRELHADA("3", "Grelhada"),
	ASSADA("4", "Assada"),
	COZIDA("5", "Cozida");
	
	
	private String codigo;
	private String descricao;
	
	
	public static String concatenarConsumoFrango(String codigosFrango) {
		if (Objects.isNull(codigosFrango))
			return null;

		StringBuilder frangoBuilder = new StringBuilder();
		
		for (String codigoConsumoFrango : codigosFrango.split(";")) {
			for (ConsumoFrango consumoFrango : ConsumoFrango.values()) {
				if (codigoConsumoFrango.equals(consumoFrango.codigo)) {
					frangoBuilder.append(consumoFrango.descricao);
					frangoBuilder.append(", ");
					
					break;
				}
			}
		}
		
		return frangoBuilder.substring(0, frangoBuilder.length() - 3).toString();
	}
	
	
	public static void validarCodigo(String codigosConsumoFrango) {
		String[] codigos = codigosConsumoFrango.split(";");
		int quantidadeOpcoesEnum = ConsumoPeixe.values().length;
		
		if (codigos.length > quantidadeOpcoesEnum)
			throw new IllegalArgumentException("Só é permitido escolher no máximo " + quantidadeOpcoesEnum + " opções!");
		
		boolean encontrouCodigoValido = false;
		
		for (String codigoConsumo : codigos) {
			for (ConsumoFrango consumoFrango : ConsumoFrango.values()) {
				if (codigoConsumo.equals(consumoFrango.codigo)) {
					encontrouCodigoValido = true;
					break;
				}
			}
			
			if (!encontrouCodigoValido)
				throw new IllegalArgumentException("Existe(m) código(s) inválido(s)!");
			
			encontrouCodigoValido = false;
		}
	}
}
