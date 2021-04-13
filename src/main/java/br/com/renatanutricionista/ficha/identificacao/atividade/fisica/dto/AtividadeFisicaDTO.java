package br.com.renatanutricionista.ficha.identificacao.atividade.fisica.dto;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import br.com.renatanutricionista.ficha.identificacao.atividade.fisica.model.AtividadeFisica;
import br.com.renatanutricionista.utils.ConversaoUtils;
import lombok.Getter;


@Getter
public class AtividadeFisicaDTO {

	private Long id;
	private String atividadePraticada;
	private String frequencia;
	private String duracao;
	private String dataHoraUltimaAtualizacaoDadosDaAtividadeFisica;
	
	
	public AtividadeFisicaDTO(AtividadeFisica atividadeFisica) {
		id = atividadeFisica.getId();
		atividadePraticada = atividadeFisica.getAtividadePraticada();
		frequencia = atividadeFisica.getFrequencia().getDescricao();
		duracao = atividadeFisica.getDuracao();
		dataHoraUltimaAtualizacaoDadosDaAtividadeFisica = ConversaoUtils.converterLocalDateTimeParaStringDataHoraMinuto(
				atividadeFisica.getDataHoraUltimaAtualizacaoDadosDaAtividadeFisica());
	}
	
	
	public static List<AtividadeFisicaDTO> converterParaListaAtividadeFisicaDTO(List<AtividadeFisica> atividadesFisica) {
		return atividadesFisica.stream()
				.sorted(Comparator.comparing(AtividadeFisica::getDataHoraUltimaAtualizacaoDadosDaAtividadeFisica)
				.reversed())
				.map(AtividadeFisicaDTO::new)
				.collect(Collectors.toList());
	}
}
