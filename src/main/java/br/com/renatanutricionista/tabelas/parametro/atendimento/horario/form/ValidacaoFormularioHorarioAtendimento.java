package br.com.renatanutricionista.tabelas.parametro.atendimento.horario.form;

import java.util.Objects;
import java.util.regex.Pattern;

import br.com.renatanutricionista.utils.ConversaoUtils;
import br.com.renatanutricionista.utils.RegexUtils;

public abstract class ValidacaoFormularioHorarioAtendimento {

	protected void validarHorarios(String horarioEntradaAntesAlmoco, String horarioSaidaAntesAlmoco,
			String horarioEntradaDepoisAlmoco, String horarioSaidaDepoisAlmoco) {
		
		validarHorarioEntradaSaida(horarioEntradaAntesAlmoco, horarioSaidaAntesAlmoco);
		validarHorarioEntradaSaida(horarioEntradaDepoisAlmoco, horarioSaidaDepoisAlmoco);
		
		validarHorarioEntrePeriodos(horarioSaidaAntesAlmoco, horarioEntradaDepoisAlmoco);
	}
	
	
	private void validarHorarioEntradaSaida(String horarioEntrada, String horarioSaida) {
		if (Objects.nonNull(horarioEntrada) ^ Objects.nonNull(horarioSaida)) 
			throw new IllegalArgumentException("Não pode haver horário de entrada sem saída ou saída sem entrada!");
		
		if (Objects.nonNull(horarioEntrada) && Objects.nonNull(horarioSaida)) {
			if (!Pattern.matches(RegexUtils.HORA_MINUTO, horarioEntrada)) 
				throw new IllegalArgumentException("O formato do horário de entrada está inválido!");
			
			if (!Pattern.matches(RegexUtils.HORA_MINUTO, horarioSaida))
				throw new IllegalArgumentException("O formato do horário de saída está inválido!");
			
			validarSeHorarioEntradaPrecedeHorarioSaida(horarioEntrada, horarioSaida);
		}
	}
	
	
	private void validarSeHorarioEntradaPrecedeHorarioSaida(String horarioEntrada, String horarioSaida) {
		
		if (ConversaoUtils.converterStringParaLocalTimeHoraMinuto(horarioEntrada)
				.isAfter(ConversaoUtils.converterStringParaLocalTimeHoraMinuto(horarioSaida)))
			
			throw new IllegalArgumentException("Horário de entrada deve preceder o horário de saída!");
	}
	
	
	private void validarHorarioEntrePeriodos(String horarioSaidaAntesAlmoco, String horarioEntradaDepoisAlmoco) {
		if (Objects.nonNull(horarioSaidaAntesAlmoco) && Objects.nonNull(horarioEntradaDepoisAlmoco)) {
			
			if (ConversaoUtils.converterStringParaLocalTimeHoraMinuto(horarioSaidaAntesAlmoco)
					.isAfter(ConversaoUtils.converterStringParaLocalTimeHoraMinuto(horarioEntradaDepoisAlmoco)))
				
				throw new IllegalArgumentException("Os horários do período da manhã devem preceder "
						+ "os horários do período da tarde!");
		}
	}
}
