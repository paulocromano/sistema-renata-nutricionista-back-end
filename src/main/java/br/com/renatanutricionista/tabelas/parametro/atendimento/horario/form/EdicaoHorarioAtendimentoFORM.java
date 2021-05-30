package br.com.renatanutricionista.tabelas.parametro.atendimento.horario.form;

import java.time.LocalTime;
import java.util.Objects;

import br.com.renatanutricionista.tabelas.parametro.atendimento.horario.model.HorarioAtendimento;
import br.com.renatanutricionista.utils.ConversaoUtils;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EdicaoHorarioAtendimentoFORM extends ValidacaoFormularioHorarioAtendimento {

	private String horarioEntradaAntesAlmoco;
	private String horarioSaidaAntesAlmoco;
	private String horarioEntradaDepoisAlmoco;
	private String horarioSaidaDepoisAlmoco;
	
	
	public void atualizarHorariosAtendimentoDeUmDiaDaSemana(HorarioAtendimento horarioAtendimento) {		
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

		horarioAtendimento.setHorarioEntradaAntesAlmoco(horarioEntradaAntesAlmoco);
		horarioAtendimento.setHorarioSaidaAntesAlmoco(horarioSaidaAntesAlmoco);
		horarioAtendimento.setHorarioEntradaDepoisAlmoco(horarioEntradaDepoisAlmoco);
		horarioAtendimento.setHorarioSaidaDepoisAlmoco(horarioSaidaDepoisAlmoco);
	}
}
