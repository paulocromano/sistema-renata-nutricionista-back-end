package br.com.renatanutricionista.ficha.identificacao.historico.atividade.fisica.dto;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import br.com.renatanutricionista.ficha.identificacao.historico.atividade.fisica.model.HistoricoAtividadeFisica;
import br.com.renatanutricionista.utils.ConversaoUtils;
import lombok.Getter;


@Getter
public class HistoricoAtividadeFisicaDTO {

	private Long id;
	private String atividadePraticada;
	private String frequencia;
	private String duracao;
	private String dataHoraCadastroAtividadeFisica;
	
	
	public HistoricoAtividadeFisicaDTO(HistoricoAtividadeFisica historicoAtividadeFisica) {
		id = historicoAtividadeFisica.getId();
		atividadePraticada = historicoAtividadeFisica.getAtividadePraticada();
		frequencia = historicoAtividadeFisica.getFrequencia().getDescricao();
		duracao = historicoAtividadeFisica.getDuracao() + "h";
		dataHoraCadastroAtividadeFisica = ConversaoUtils.converterLocalDateTimeParaFrontEndEmString(historicoAtividadeFisica.getDataHoraCadastroAtividadeFisica());
	}
	
	
	public static List<HistoricoAtividadeFisicaDTO> converterParaListaAtividadeFisicaDTO(List<HistoricoAtividadeFisica> atividadesFisica) {
		return atividadesFisica.stream()
				.sorted(Comparator.comparing(HistoricoAtividadeFisica::getDataHoraCadastroAtividadeFisica)
				.reversed())
				.map(HistoricoAtividadeFisicaDTO::new)
				.collect(Collectors.toList());
	}
}
