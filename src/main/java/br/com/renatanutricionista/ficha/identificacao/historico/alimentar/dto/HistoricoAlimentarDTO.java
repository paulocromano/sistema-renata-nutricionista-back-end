package br.com.renatanutricionista.ficha.identificacao.historico.alimentar.dto;

import java.util.Set;

import br.com.renatanutricionista.ficha.identificacao.historico.alimentar.model.HistoricoAlimentar;
import br.com.renatanutricionista.ficha.identificacao.historico.suplemento.dto.SuplementoPacienteDTO;
import br.com.renatanutricionista.medicamento.dto.MedicamentoDTO;
import br.com.renatanutricionista.utils.ConversaoUtils;
import lombok.Getter;


@Getter
public class HistoricoAlimentarDTO {

	private Long id;
	private String intoleranciaAlergiaAlimentosPaciente;
	private String preferenciaAlimentarPaciente;
	private String alimentosPacienteNaoGosta;
	private String alteracoesGastrointestinal;
	private String consumoAgua;
	private Set<SuplementoPacienteDTO> suplementosPaciente;
	private Set<MedicamentoDTO> medicamentosPaciente;
	private String dataHoraCadastroHistoricoAlimentar;
	
	
	public HistoricoAlimentarDTO(HistoricoAlimentar historicoAlimentar) {
		id = historicoAlimentar.getId();
		intoleranciaAlergiaAlimentosPaciente = historicoAlimentar.getIntoleranciaAlergiaAlimentosPaciente();
		preferenciaAlimentarPaciente = historicoAlimentar.getPreferenciaAlimentarPaciente();
		alimentosPacienteNaoGosta = historicoAlimentar.getAlimentosPacienteNaoGosta();
		alteracoesGastrointestinal = historicoAlimentar.getAlteracoesGastrointestinal();
		consumoAgua = historicoAlimentar.getConsumoAgua();
		suplementosPaciente = SuplementoPacienteDTO.converterParaSetSuplementoPacienteDTO(historicoAlimentar.getSuplementosPaciente());
		medicamentosPaciente = MedicamentoDTO.converterParaSetMedicamentoDTOEmOrdemAlfabetica(historicoAlimentar.getMedicamentos());
		dataHoraCadastroHistoricoAlimentar = ConversaoUtils.converterLocalDateTimeParaStringDataHoraMinuto(
				historicoAlimentar.getDataHoraCadastroHistoricoAlimentar());
	}
}
