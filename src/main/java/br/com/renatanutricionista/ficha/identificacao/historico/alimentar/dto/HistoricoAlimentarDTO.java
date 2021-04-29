package br.com.renatanutricionista.ficha.identificacao.historico.alimentar.dto;

import java.util.Set;

import br.com.renatanutricionista.ficha.identificacao.historico.alimentar.model.HistoricoAlimentar;
import br.com.renatanutricionista.ficha.identificacao.historico.suplemento.dto.SuplementoPacienteDTO;
import br.com.renatanutricionista.medicamento.model.Medicamento;
import br.com.renatanutricionista.utils.ConversaoUtils;
import lombok.Getter;


@Getter
public class HistoricoAlimentarDTO {

	private Long id;
	private String intoleranciaAlergiaAlimentos;
	private String preferenciaAlimentar;
	private String alimentosPacienteNaoGosta;
	private String alteracoesGastrointestinal;
	private String consumoAgua;
	private Set<SuplementoPacienteDTO> suplementosPaciente;
	private String medicamentosPaciente;
	private String dataHoraCadastroHistoricoAlimentar;
	
	
	public HistoricoAlimentarDTO(HistoricoAlimentar historicoAlimentar) {
		id = historicoAlimentar.getId();
		intoleranciaAlergiaAlimentos = historicoAlimentar.getIntoleranciaAlergiaAlimentosPaciente();
		preferenciaAlimentar = historicoAlimentar.getPreferenciaAlimentarPaciente();
		alimentosPacienteNaoGosta = historicoAlimentar.getAlimentosPacienteNaoGosta();
		alteracoesGastrointestinal = historicoAlimentar.getAlteracoesGastrointestinal();
		consumoAgua = historicoAlimentar.getConsumoAgua();
		suplementosPaciente = SuplementoPacienteDTO.converterParaSetSuplementoPacienteDTO(historicoAlimentar.getSuplementosPaciente());
		gerarStringMedicamentosPaciente(historicoAlimentar.getMedicamentos());
		dataHoraCadastroHistoricoAlimentar = ConversaoUtils.converterLocalDateTimeParaFrontEndEmString(
				historicoAlimentar.getDataHoraCadastroHistoricoAlimentar());
	}
	
	
	private void gerarStringMedicamentosPaciente(Set<Medicamento> medicamentos) {
		StringBuilder builder = new StringBuilder();
		
		for (Medicamento medicamento : medicamentos) {
			builder.append(medicamento.getNome());
			builder.append(", ");
		}
		
		medicamentosPaciente = builder.substring(0, builder.length() - 2).toString();
	}
}
