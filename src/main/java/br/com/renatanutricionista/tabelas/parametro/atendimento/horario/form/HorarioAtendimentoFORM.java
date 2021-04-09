package br.com.renatanutricionista.tabelas.parametro.atendimento.horario.form;

import java.time.DayOfWeek;
import java.time.LocalTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import br.com.renatanutricionista.tabelas.parametro.atendimento.horario.model.HorarioAtendimento;
import br.com.renatanutricionista.utils.ConversaoUtils;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class HorarioAtendimentoFORM extends ValidacaoFormularioHorarioAtendimento {

	@NotEmpty(message = "O campo Dia da Semana não pode estar nulo/vazio!")
	@Pattern(regexp = "[1-7]", message = "O Dia da Semana é inválido!")
	private String diaDaSemana;

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
	
	
	public HorarioAtendimento converterParaHorarioAtendimento() {
		LocalTime horarioEntradaAntesAlmoco = ConversaoUtils.converterStringParaLocalTimeHoraMinuto(this.horarioEntradaAntesAlmoco);
		LocalTime horarioSaidaAntesAlmoco = ConversaoUtils.converterStringParaLocalTimeHoraMinuto(this.horarioSaidaAntesAlmoco);
		LocalTime horarioEntradaDepoisAlmoco = ConversaoUtils.converterStringParaLocalTimeHoraMinuto(this.horarioEntradaDepoisAlmoco);
		LocalTime horarioSaidaDepoisAlmoco = ConversaoUtils.converterStringParaLocalTimeHoraMinuto(this.horarioSaidaDepoisAlmoco);
		
		validarHorarios(horarioEntradaAntesAlmoco, horarioSaidaAntesAlmoco, horarioEntradaDepoisAlmoco, horarioSaidaDepoisAlmoco);

		return new HorarioAtendimento.Builder()
				.diaDaSemana(DayOfWeek.of(Integer.parseInt(diaDaSemana)))
				.horarioEntradaAntesAlmoco(horarioEntradaAntesAlmoco)
				.horarioSaidaAntesAlmoco(horarioSaidaAntesAlmoco)
				.horarioEntradaDepoisAlmoco(horarioEntradaDepoisAlmoco)
				.horarioSaidaDepoisAlmoco(horarioSaidaDepoisAlmoco)
				.build();
	}
}
