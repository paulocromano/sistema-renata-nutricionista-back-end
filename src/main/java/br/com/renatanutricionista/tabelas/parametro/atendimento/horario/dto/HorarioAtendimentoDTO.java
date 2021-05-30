package br.com.renatanutricionista.tabelas.parametro.atendimento.horario.dto;

import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.util.StringUtils;

import br.com.renatanutricionista.tabelas.parametro.atendimento.horario.model.HorarioAtendimento;
import br.com.renatanutricionista.utils.ConversaoUtils;
import lombok.Getter;


@Getter
public class HorarioAtendimentoDTO {

	private Integer id;
	private String diaDaSemana;
	private String entradaSaidaAntesDoAlmoco;
	private String entradaSaidaDepoisDoAlmoco;
	
	
	public HorarioAtendimentoDTO(HorarioAtendimento horarioAtendimento) {
		id = horarioAtendimento.getId();
		diaDaSemana = StringUtils.capitalize(horarioAtendimento.getDiaDaSemana().getDisplayName(TextStyle.FULL, new Locale("pt", "BR")));	
		
		if (Objects.nonNull(horarioAtendimento.getHorarioEntradaAntesAlmoco())) {
			entradaSaidaAntesDoAlmoco = ConversaoUtils.converterLocalTimeParaString(horarioAtendimento.getHorarioEntradaAntesAlmoco()) + "h às "
					+ ConversaoUtils.converterLocalTimeParaString(horarioAtendimento.getHorarioSaidaAntesAlmoco()) + "h";
		}
		else {
			entradaSaidaAntesDoAlmoco = "---";
		}

		if (Objects.nonNull(horarioAtendimento.getHorarioEntradaDepoisAlmoco())) {
			entradaSaidaDepoisDoAlmoco = ConversaoUtils.converterLocalTimeParaString(horarioAtendimento.getHorarioEntradaDepoisAlmoco()) + "h às "
					+ ConversaoUtils.converterLocalTimeParaString(horarioAtendimento.getHorarioSaidaDepoisAlmoco()) + "h";
		}
		else {
			entradaSaidaDepoisDoAlmoco = "---";
		}
	}
	
	
	public static List<HorarioAtendimentoDTO> converterParaListaHorarioAtendimentoDTO(List<HorarioAtendimento> horariosAtendimento) {
		return horariosAtendimento.stream().map(HorarioAtendimentoDTO::new).collect(Collectors.toList());
	}
}
