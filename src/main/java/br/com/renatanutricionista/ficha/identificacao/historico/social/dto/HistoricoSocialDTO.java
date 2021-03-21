package br.com.renatanutricionista.ficha.identificacao.historico.social.dto;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import br.com.renatanutricionista.ficha.identificacao.historico.patologia.model.PatologiaPaciente;
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
	private String frequenciaUsoBebidasAlcoolicas;
	private String usoCigarro;
	private Integer quantidadeCigarrosPorDia;
	private String habitoIntestinal;
	private String consistenciaFezes;
	private String frequenciaDiurese;
	private String coloracaoDiurese;
	private List<PatologiaPaciente> patologiasPaciente;
	private Integer horasSono;
	private String menstruacaoNormal;
	private String motivoAnormalidadeMenstruacao;
	private String menopausa;
	private String inicioMenopausa;
	private String dataUltimaAtualizacaoDadosDoHistoricoSocial;
	
	
	public HistoricoSocialDTO(HistoricoSocial historicoSocial) {
		id = historicoSocial.getId();
		profissao = historicoSocial.getProfissao();
		estadoCivil = historicoSocial.getEstadoCivil().getDescricao();
		composicaoFamiliar = historicoSocial.getComposicaoFamiliar();
		localRefeicoes = historicoSocial.getLocalRefeicoes();
		frequenciaUsoBebidasAlcoolicas = historicoSocial.getFrequenciaUsoBebidasAlcoolicas().getDescricao();
		usoCigarro = historicoSocial.getUsoCigarro().getDescricao();
		quantidadeCigarrosPorDia = historicoSocial.getQuantidadeCigarrosPorDia();
		habitoIntestinal = historicoSocial.getHabitoIntestinal().getDescricao();
		consistenciaFezes = historicoSocial.getConsistenciaFezes().getDescricao();
		frequenciaDiurese = historicoSocial.getFrequenciaDiurese().getDescricao();
		coloracaoDiurese = historicoSocial.getColoracaoDiurese().getDescricao();
		patologiasPaciente = historicoSocial.getPatologiasPaciente();
		horasSono = historicoSocial.getHorasSono();
		
		if (historicoSocial.getPaciente().getSexo().equals(SexoUtils.FEMININO)) {
			menstruacaoNormal = historicoSocial.getMenstruacaoNormal().getDescricao();
			motivoAnormalidadeMenstruacao = historicoSocial.getMotivoAnormalidadeMenstruacao();
			menopausa = historicoSocial.getMenopausa().getDescricao();
			
			if (Objects.nonNull(inicioMenopausa))
				inicioMenopausa = ConversaoUtils.converterLocalDateParaString(historicoSocial.getInicioMenopausa());
		}
		
		dataUltimaAtualizacaoDadosDoHistoricoSocial = ConversaoUtils.converterLocalDateTimeParaString(historicoSocial.getDataUltimaAtualizacaoDadosDoHistoricoSocial());
	}
	
	
	public static List<HistoricoSocialDTO> converterParaListaHistoricoSocialDTO(List<HistoricoSocial> historicoSocial) {
		return historicoSocial.stream().map(HistoricoSocialDTO::new).collect(Collectors.toList());
	}
}
