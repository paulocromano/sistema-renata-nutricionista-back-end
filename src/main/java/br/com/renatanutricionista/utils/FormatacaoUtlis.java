package br.com.renatanutricionista.utils;

import java.text.Collator;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public final class FormatacaoUtlis {

	public static final DateTimeFormatter FORMATADOR_DATA_HORA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	public static final DateTimeFormatter FORMATADOR_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	public static final DateTimeFormatter FORMATADOR_MES_ANO = DateTimeFormatter.ofPattern("MM/yyyy");
	
	public static final Collator COLLATOR = Collator.getInstance(new Locale("pt", "BR"));
}
