package br.com.renatanutricionista.utils.conversao.enums;

public interface GettersEnum<T extends Enum<T> & GettersEnum<T>> {

	String getCodigo();
	String getDescricao();
}
