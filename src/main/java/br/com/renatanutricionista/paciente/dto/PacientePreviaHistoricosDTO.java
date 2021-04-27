package br.com.renatanutricionista.paciente.dto;

import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.questionario.dto.InformacoesPreviasQuestionariosDTO;
import br.com.renatanutricionista.ficha.identificacao.historico.alimentar.dto.InformacoesPreviasHistoricosAlimentaresDTO;
import br.com.renatanutricionista.ficha.identificacao.historico.atividade.fisica.dto.InformacoesHistoricosAtividadesFisicasDTO;
import br.com.renatanutricionista.ficha.identificacao.historico.patologia.por.data.familiares.dto.InformacoesPreviasHistoricosFamiliaresPorDataDTO;
import br.com.renatanutricionista.ficha.identificacao.historico.social.dto.InformacoesPreviasHistoricosSociaisDTO;
import br.com.renatanutricionista.paciente.model.Paciente;
import br.com.renatanutricionista.tabelas.parametro.paciente.model.PacienteParametro;
import lombok.Getter;


@Getter
public class PacientePreviaHistoricosDTO {

	private PacienteDTO paciente;
	private InformacoesPreviasHistoricosSociaisDTO informacoesPreviasHistoricosSociais;
	private InformacoesPreviasHistoricosAlimentaresDTO informacoesPreviasHistoricosAlimentares;
	private InformacoesHistoricosAtividadesFisicasDTO informacoesHistoricosAtividadesFisicas;
	private InformacoesPreviasHistoricosFamiliaresPorDataDTO informacoesPreviasHistoricosPatologiaFamiliaresPorData;
	private InformacoesPreviasQuestionariosDTO informacoesPreviasQuestionariosFrequenciaAlimentar;
	
	
	public PacientePreviaHistoricosDTO(Paciente paciente, PacienteParametro pacienteParametro) {
		this.paciente = new PacienteDTO(paciente);
		informacoesPreviasHistoricosSociais = new InformacoesPreviasHistoricosSociaisDTO(paciente, pacienteParametro);
		informacoesPreviasHistoricosAlimentares = new InformacoesPreviasHistoricosAlimentaresDTO(paciente, pacienteParametro);
		informacoesHistoricosAtividadesFisicas = new InformacoesHistoricosAtividadesFisicasDTO(paciente, pacienteParametro);
		informacoesPreviasHistoricosPatologiaFamiliaresPorData = new InformacoesPreviasHistoricosFamiliaresPorDataDTO(paciente, pacienteParametro);
		informacoesPreviasQuestionariosFrequenciaAlimentar = new InformacoesPreviasQuestionariosDTO(paciente, pacienteParametro);
	}
}
