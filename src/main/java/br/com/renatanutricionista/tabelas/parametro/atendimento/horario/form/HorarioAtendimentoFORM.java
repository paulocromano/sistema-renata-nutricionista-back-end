package br.com.renatanutricionista.tabelas.parametro.atendimento.horario.form;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Objects;

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

	private String horarioEntradaAntesAlmoco;
	private String horarioSaidaAntesAlmoco;
	private String horarioEntradaDepoisAlmoco;
	private String horarioSaidaDepoisAlmoco;
	
	
	public HorarioAtendimento converterParaHorarioAtendimento() {	
		validarHorarios(horarioEntradaAntesAlmoco, horarioSaidaAntesAlmoco, horarioEntradaDepoisAlmoco, horarioSaidaDepoisAlmoco);
		
		LocalTime horarioEntradaAntesAlmoco = null;
		LocalTime horarioSaidaAntesAlmoco = null;
		LocalTime horarioEntradaDepoisAlmoco = null;
		LocalTime horarioSaidaDepoisAlmoco = null;
		
		if (Objects.nonNull(this.horarioEntradaAntesAlmoco)) {
			horarioEntradaAntesAlmoco = ConversaoUtils.converterStringParaLocalTimeHoraMinuto(this.horarioEntradaAntesAlmoco);
			horarioSaidaAntesAlmoco = ConversaoUtils.converterStringParaLocalTimeHoraMinuto(this.horarioSaidaAntesAlmoco);
		}
		
		if (Objects.nonNull(this.horarioEntradaDepoisAlmoco)) {
			horarioEntradaDepoisAlmoco = ConversaoUtils.converterStringParaLocalTimeHoraMinuto(this.horarioEntradaDepoisAlmoco);
			horarioSaidaDepoisAlmoco = ConversaoUtils.converterStringParaLocalTimeHoraMinuto(this.horarioSaidaDepoisAlmoco);
		}

		return new HorarioAtendimento.Builder()
				.diaDaSemana(DayOfWeek.of(Integer.parseInt(diaDaSemana)))			
				.horarioEntradaAntesAlmoco(horarioEntradaAntesAlmoco)
				.horarioSaidaAntesAlmoco(horarioSaidaAntesAlmoco)
				.horarioEntradaDepoisAlmoco(horarioEntradaDepoisAlmoco)
				.horarioSaidaDepoisAlmoco(horarioSaidaDepoisAlmoco)
				.build();
	}
}
