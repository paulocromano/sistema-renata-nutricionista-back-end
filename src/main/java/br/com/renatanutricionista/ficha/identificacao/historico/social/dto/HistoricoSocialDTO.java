package br.com.renatanutricionista.ficha.identificacao.historico.social.dto;

import java.util.Objects;
import java.util.Set;

import br.com.renatanutricionista.ficha.identificacao.historico.patologia.paciente.dto.PatologiaPacienteDTO;
import br.com.renatanutricionista.ficha.identificacao.historico.social.model.HistoricoSocial;
import br.com.renatanutricionista.paciente.enums.sexo.Sexo;
import br.com.renatanutricionista.utils.ConversaoUtils;
import br.com.renatanutricionista.utils.ConverterToDTOCollection;
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
	private Set<ColoracaoDiuresePacienteDTO> coloracoesDiurese;
	private Set<PatologiaPacienteDTO> patologiasPaciente;
	private Integer horasSono;
	private String menstruacaoNormal;
	private String motivoAnormalidadeMenstruacao;
	private String menopausa;
	private Integer quantosAnosEstaNaMenopausa;
	private String dataHoraCadastroHistoricoSocial;
	
	
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
		coloracoesDiurese = ConverterToDTOCollection.convertToSet(
				historicoSocial.getColoracoesDiuresePaciente(), ColoracaoDiuresePacienteDTO::new);
		patologiasPaciente = PatologiaPacienteDTO.converterParaSetPatologiaPacienteDTO(historicoSocial.getPatologiasPaciente());
		horasSono = historicoSocial.getHorasSono();
		
		if (historicoSocial.getPaciente().getSexo().equals(Sexo.FEMININO)) {
			if (Objects.nonNull(historicoSocial.getMenstruacaoNormal())) {
				menstruacaoNormal = historicoSocial.getMenstruacaoNormal().getDescricao();
				motivoAnormalidadeMenstruacao = historicoSocial.getMotivoAnormalidadeMenstruacao();
			}
			else if (Objects.nonNull(historicoSocial.getMenopausa())) {
				menopausa = historicoSocial.getMenopausa().getDescricao();
				quantosAnosEstaNaMenopausa = historicoSocial.getQuantosAnosEstaNaMenopausa();
			}
		}
		
		dataHoraCadastroHistoricoSocial = ConversaoUtils.converterLocalDateTimeParaFrontEndEmString(historicoSocial.getDataHoraCadastroHistoricoSocial());
	}
}
