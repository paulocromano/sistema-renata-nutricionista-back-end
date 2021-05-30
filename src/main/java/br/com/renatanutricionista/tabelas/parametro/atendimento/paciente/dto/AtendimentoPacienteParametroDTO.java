package br.com.renatanutricionista.tabelas.parametro.atendimento.paciente.dto;

import br.com.renatanutricionista.tabelas.parametro.atendimento.paciente.model.AtendimentoPacienteParametro;
import br.com.renatanutricionista.utils.FormatacaoUtils;
import lombok.Getter;


@Getter
public class AtendimentoPacienteParametroDTO {

	private Integer id;
	private Integer quantidadeParcelas;
	private Integer tempoMesesGeracaoAutomaticaHorariosAtendimento;
	private Integer intervaloMinutosEntreAtendimentos;
	private Integer intervaloDiasEntrePrimeiraConsultaRetorno;
	private Integer intervaloDiasEntreConsultaRetorno;
	private Integer intervaloDiasEntreRetornoConsulta;
	private String precoConsulta;
	private Integer tempoMesesAtualizarQuestionarioFrequenciaAlimentar;
	private Integer tempoMesesAtualizarHistoricoSocial;
	private Integer tempoMesesAtualizarHistoricoPatologiaFamiliares;
	private Integer tempoMesesAtualizarHistoricoAtividadeFisica;
	private Integer tempoMesesAtualizarHistoricoAlimentar;
	private Integer tempoMesesAtualizarHistoricoFrequenciaAlimentar;
	
	
	public AtendimentoPacienteParametroDTO(AtendimentoPacienteParametro atendimento) {
		id = atendimento.getId();
		quantidadeParcelas = atendimento.getQuantidadeParcelas();
		tempoMesesGeracaoAutomaticaHorariosAtendimento = atendimento.getTempoMesesGeracaoAutomaticaHorariosAtendimento();
		intervaloMinutosEntreAtendimentos = atendimento.getIntervaloMinutosEntreAtendimentos();
		intervaloDiasEntrePrimeiraConsultaRetorno = atendimento.getIntervaloDiasEntrePrimeiraConsultaRetorno();
		intervaloDiasEntreConsultaRetorno = atendimento.getIntervaloDiasEntreConsultaRetorno();
		intervaloDiasEntreRetornoConsulta = atendimento.getIntervaloDiasEntreRetornoConsulta();
		precoConsulta = FormatacaoUtils.substituirPontoPorVirgula(atendimento.getPrecoConsulta());
		
		tempoMesesAtualizarQuestionarioFrequenciaAlimentar = atendimento.getTempoMesesAtualizarQuestionarioFrequenciaAlimentar();
		tempoMesesAtualizarHistoricoSocial = atendimento.getTempoMesesAtualizarHistoricoSocial();
		tempoMesesAtualizarHistoricoPatologiaFamiliares = atendimento.getTempoMesesAtualizarHistoricoPatologiaFamiliares();
		tempoMesesAtualizarHistoricoAtividadeFisica = atendimento.getTempoMesesAtualizarHistoricoAtividadeFisica();
		tempoMesesAtualizarHistoricoAlimentar = atendimento.getTempoMesesAtualizarHistoricoAlimentar();
		tempoMesesAtualizarHistoricoFrequenciaAlimentar = atendimento.getTempoMesesAtualizarHistoricoFrequenciaAlimentar();
	}
}
