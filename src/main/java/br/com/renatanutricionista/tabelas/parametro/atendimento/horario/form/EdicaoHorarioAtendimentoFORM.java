package br.com.renatanutricionista.tabelas.parametro.atendimento.horario.form;

import java.time.LocalTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import br.com.renatanutricionista.tabelas.parametro.atendimento.horario.model.HorarioAtendimento;
import br.com.renatanutricionista.utils.ConversaoUtils;

public class EdicaoHorarioAtendimentoFORM extends ValidacaoFormularioHorarioAtendimento {

	@NotEmpty(message = "O Horário de Entrada antes do Almoço não pode estar nulo/vazio!")
	@Pattern(regexp = "(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]", message = "O formato do Horário de Entrada antes do Almoço é inválido!")
	private String horarioEntradaAntesAlmoco;
	
	@NotEmpty(message = "O Horário de Saída antes do Almoço não pode estar nulo/vazio!")
	@Pattern(regexp = "(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]", message = "O formato do Horário de Saída antes do Almoço é inválido!")
	private String horarioSaidaAntesAlmoco;
	
	@NotEmpty(message = "O Horário de Entrada depois do Almoço não pode estar nulo/vazio!")
	@Pattern(regexp = "(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]", message = "O formato do Horário de Entrada depois do Almoço é inválido!")
	private String horarioEntradaDepoisAlmoco;
	
	@NotEmpty(message = "O Horário de Saída depois do Almoço não pode estar nulo/vazio!")
	@Pattern(regexp = "(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]", message = "O formato do Horário de Saída depois do Almoço é inválido!")
	private String horarioSaidaDepoisAlmoco;
	
	
	public void atualizarHorariosAtendimentoDeUmDiaDaSemana(HorarioAtendimento horarioAtendimento) {
		LocalTime horarioEntradaAntesAlmoco = ConversaoUtils.converterStringParaLocalTimeHoraMinuto(this.horarioEntradaAntesAlmoco);
		LocalTime horarioSaidaAntesAlmoco = ConversaoUtils.converterStringParaLocalTimeHoraMinuto(this.horarioSaidaAntesAlmoco);
		LocalTime horarioEntradaDepoisAlmoco = ConversaoUtils.converterStringParaLocalTimeHoraMinuto(this.horarioEntradaDepoisAlmoco);
		LocalTime horarioSaidaDepoisAlmoco = ConversaoUtils.converterStringParaLocalTimeHoraMinuto(this.horarioSaidaDepoisAlmoco);
		
		validarHorarios(horarioEntradaAntesAlmoco, horarioSaidaAntesAlmoco, horarioEntradaDepoisAlmoco, horarioSaidaDepoisAlmoco);
		
		horarioAtendimento.setHorarioEntradaAntesAlmoco(horarioEntradaAntesAlmoco);
		horarioAtendimento.setHorarioSaidaAntesAlmoco(horarioSaidaAntesAlmoco);
		horarioAtendimento.setHorarioEntradaDepoisAlmoco(horarioEntradaDepoisAlmoco);
		horarioAtendimento.setHorarioSaidaDepoisAlmoco(horarioSaidaDepoisAlmoco);
	}
}
