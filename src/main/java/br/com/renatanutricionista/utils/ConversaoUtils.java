package br.com.renatanutricionista.utils;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public final class ConversaoUtils {
	
	public static final String converterLocalDateTimeParaString(LocalDateTime localDateTime) {
		try {
			return localDateTime.format(FormatacaoUtils.FORMATADOR_DATA_HORA);
		}
		catch (DateTimeException e) {
			throw new DateTimeException("Formato de Data e/ou Hora inválido para conversão em String!");
		}
	}
	
	
	public static final String converterLocalDateParaString(LocalDate localDate) {
		try {
			return localDate.format(FormatacaoUtils.FORMATADOR_DATA);
		}
		catch (DateTimeException e) {
			throw new DateTimeException("Formato de Data inválido para conversão em String!");
		}
	}
	
	
	public static final LocalDate converterStringParaLocalDate(String data) {
		try {
			return LocalDate.parse(data, FormatacaoUtils.FORMATADOR_DATA);
		}
		catch (DateTimeParseException e) {
			throw new DateTimeException("Formato de Data inválido para conversão em LocalDate!");
		}
		catch (NullPointerException e) {
			throw new NullPointerException("A Data está nula!");
		}
	}
	
	
	public static final LocalDate converterStringParaLocalDate(String data, DateTimeFormatter formatter) {
		try {
			return LocalDate.parse(data, formatter);
		}
		catch (DateTimeParseException e) {
			throw new DateTimeException("Formato de Data inválido para conversão em LocalDate!");
		}
		catch (NullPointerException e) {
			throw new NullPointerException("A Data está nula!");
		}
	}
	
	
	public static final LocalTime converterStringParaLocalTime(String hora) {
		try {
			return LocalTime.parse(hora, FormatacaoUtils.FORMATADOR_HORA_MINUTO);
		}
		catch (DateTimeParseException e) {
			throw new DateTimeException("Formato de Hora inválido para conversão em LocalTime!");
		}
		catch (NullPointerException e) {
			throw new NullPointerException("A Hora está nula!");
		}
	}
}
