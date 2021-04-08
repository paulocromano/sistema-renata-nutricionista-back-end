package br.com.renatanutricionista.tabelas.parametro.atendimento.horario.dto;

import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.util.StringUtils;

import br.com.renatanutricionista.tabelas.parametro.atendimento.horario.model.HorarioAtendimento;
import br.com.renatanutricionista.utils.ConversaoUtils;
import lombok.Getter;


@Getter
public class HorarioAtendimentoDTO {

	private Integer id;
	private String diaDaSemana;
	private String horarioEntradaAntesAlmoco;
	private String horarioSaidaAntesAlmoco;
	private String horarioEntradaDepoisAlmoco;
	private String horarioSaidaDepoisAlmoco;
	
	
	public HorarioAtendimentoDTO(HorarioAtendimento horarioAtendimento) {
		id = horarioAtendimento.getId();
		diaDaSemana = StringUtils.capitalize(horarioAtendimento.getDiaDaSemana().getDisplayName(TextStyle.FULL, new Locale("pt", "BR")));	
		horarioEntradaAntesAlmoco = ConversaoUtils.converterLocalTimeParaString(horarioAtendimento.getHorarioEntradaAntesAlmoco());
		horarioSaidaAntesAlmoco = ConversaoUtils.converterLocalTimeParaString(horarioAtendimento.getHorarioSaidaAntesAlmoco());
		horarioEntradaDepoisAlmoco = ConversaoUtils.converterLocalTimeParaString(horarioAtendimento.getHorarioEntradaDepoisAlmoco());
		horarioSaidaDepoisAlmoco = ConversaoUtils.converterLocalTimeParaString(horarioAtendimento.getHorarioSaidaDepoisAlmoco());
	}
	
	
	public static List<HorarioAtendimentoDTO> converterParaListaHorarioAtendimentoDTO(List<HorarioAtendimento> horariosAtendimento) {
		return horariosAtendimento.stream().map(HorarioAtendimentoDTO::new).collect(Collectors.toList());
	}
}
