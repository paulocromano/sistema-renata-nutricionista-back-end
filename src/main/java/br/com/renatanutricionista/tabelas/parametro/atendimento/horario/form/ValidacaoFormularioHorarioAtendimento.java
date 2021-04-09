package br.com.renatanutricionista.tabelas.parametro.atendimento.horario.form;

import java.time.LocalTime;

public abstract class ValidacaoFormularioHorarioAtendimento {

	protected void validarHorarios(LocalTime horarioEntradaAntesAlmoco, LocalTime horarioSaidaAntesAlmoco,
			LocalTime horarioEntradaDepoisAlmoco, LocalTime horarioSaidaDepoisAlmoco) {
		
		validarSeHorarioEntradaPrecedeHorarioSaida(horarioEntradaAntesAlmoco, horarioSaidaAntesAlmoco, 
				"O Horário de Entrada antes do Almoço deve preceder o Horário de Saída antes do almoço!");
		
		validarSeHorarioEntradaPrecedeHorarioSaida(horarioEntradaDepoisAlmoco, horarioSaidaDepoisAlmoco, 
				"O Horário de Entrada depois do Almoço deve preceder o Horário de Saída depois do almoço!");
		
		if (!horarioEntradaAntesAlmoco.isBefore(horarioEntradaDepoisAlmoco) || !horarioSaidaAntesAlmoco.isBefore(horarioEntradaDepoisAlmoco))
			throw new IllegalArgumentException("O(s) horário(s) do período da manhã deve(m) preceder "
					+ "os horários do período da tarde!");
	}
	
	
	private void validarSeHorarioEntradaPrecedeHorarioSaida(LocalTime horarioEntrada, LocalTime horarioSaida, String mensagemErro) {
		if (!horarioEntrada.isBefore(horarioSaida))
			throw new IllegalArgumentException(mensagemErro);
	}
}
