package br.com.renatanutricionista.ficha.identificacao.historico.social.dto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.renatanutricionista.ficha.identificacao.historico.patologia.paciente.dto.PatologiaPacienteDTO;
import br.com.renatanutricionista.ficha.identificacao.historico.social.model.HistoricoSocial;
import br.com.renatanutricionista.utils.ConversaoUtils;
import br.com.renatanutricionista.utils.enums.SexoUtils;
import lombok.Getter;


@Getter
public class HistoricoSocialDTO {

	private Long id;
	private String profissao;
	private String estadoCivil;
	private String composicaoFamiliar;
	private String localRefeicoes;
	private String frequenciaConsumoBebidasAlcoolicas;
	private String consumoCigarro;
	private Integer quantidadeCigarrosPorDia;
	private String habitoIntestinal;
	private String consistenciaFezes;
	private String frequenciaDiurese;
	private String coloracaoDiurese;
	private Set<PatologiaPacienteDTO> patologiasPaciente;
	private Integer horasSono;
	private String menstruacaoNormal;
	private String motivoAnormalidadeMenstruacao;
	private String menopausa;
	private Integer quantosAnosEstaNaMenopausa;
	private String dataHoraUltimaAtualizacaoDadosDoHistoricoSocial;
	
	
	public HistoricoSocialDTO(HistoricoSocial historicoSocial) {
		id = historicoSocial.getId();
		profissao = historicoSocial.getProfissao();
		estadoCivil = historicoSocial.getEstadoCivil().getDescricao();
		composicaoFamiliar = historicoSocial.getComposicaoFamiliar();
		localRefeicoes = historicoSocial.getLocalRefeicoes();
		frequenciaConsumoBebidasAlcoolicas = historicoSocial.getFrequenciaConsumoBebidasAlcoolicas().getDescricao();
		consumoCigarro = historicoSocial.getConsumoCigarro().getDescricao();
		quantidadeCigarrosPorDia = historicoSocial.getQuantidadeCigarrosPorDia();
		habitoIntestinal = historicoSocial.getHabitoIntestinal().getDescricao();
		consistenciaFezes = historicoSocial.getConsistenciaFezes().getDescricao();
		frequenciaDiurese = historicoSocial.getFrequenciaDiurese().getDescricao();
		coloracaoDiurese = historicoSocial.getColoracaoDiurese().getDescricao();
		patologiasPaciente = PatologiaPacienteDTO.converterParaSetPatologiaPacienteDTO(historicoSocial.getPatologiasPaciente());
		horasSono = historicoSocial.getHorasSono();
		
		if (historicoSocial.getPaciente().getSexo().equals(SexoUtils.FEMININO)) {
			menstruacaoNormal = historicoSocial.getMenstruacaoNormal().getDescricao();
			motivoAnormalidadeMenstruacao = historicoSocial.getMotivoAnormalidadeMenstruacao();
			menopausa = historicoSocial.getMenopausa().getDescricao();
			quantosAnosEstaNaMenopausa = historicoSocial.getQuantosAnosEstaNaMenopausa();
		}
		
		dataHoraUltimaAtualizacaoDadosDoHistoricoSocial = ConversaoUtils.converterLocalDateTimeParaStringDataHoraMinuto(historicoSocial.getDataHoraUltimaAtualizacaoDadosDoHistoricoSocial());
	}
	
	
	public static List<HistoricoSocialDTO> converterParaListaHistoricoSocialDTO(List<HistoricoSocial> historicoSocial) {
		return historicoSocial.stream().map(HistoricoSocialDTO::new).collect(Collectors.toList());
	}
}
