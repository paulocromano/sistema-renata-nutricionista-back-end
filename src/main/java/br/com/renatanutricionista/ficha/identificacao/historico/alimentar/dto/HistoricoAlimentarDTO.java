package br.com.renatanutricionista.ficha.identificacao.historico.alimentar.dto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.renatanutricionista.ficha.identificacao.historico.alimentar.model.HistoricoAlimentar;
import br.com.renatanutricionista.ficha.identificacao.historico.suplemento.dto.SuplementoPacienteDTO;
import br.com.renatanutricionista.medicamento.model.Medicamento;
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
	private List<SuplementoPacienteDTO> suplementosPaciente;
	private Set<Medicamento> medicamentosPaciente;
	private String dataHoraUltimaAtualizacaoDadosDoHistoricoAlimentar;
	
	
	public HistoricoAlimentarDTO(HistoricoAlimentar historicoAlimentar) {
		id = historicoAlimentar.getId();
		intoleranciaAlergiaAlimentosPaciente = historicoAlimentar.getIntoleranciaAlergiaAlimentosPaciente();
		preferenciaAlimentarPaciente = historicoAlimentar.getPreferenciaAlimentarPaciente();
		alimentosPacienteNaoGosta = historicoAlimentar.getAlimentosPacienteNaoGosta();
		alteracoesGastrointestinal = historicoAlimentar.getAlteracoesGastrointestinal();
		consumoAgua = historicoAlimentar.getConsumoAgua();
		suplementosPaciente = SuplementoPacienteDTO.converterParaListaSuplementoPacienteDTO(historicoAlimentar.getSuplementosPaciente());
		medicamentosPaciente = historicoAlimentar.getMedicamentos();
		dataHoraUltimaAtualizacaoDadosDoHistoricoAlimentar = ConversaoUtils.converterLocalDateTimeParaStringDataHoraMinuto(
				historicoAlimentar.getDataHoraUltimaAtualizacaoDadosDoHistoricoAlimentar());
	}
	
	
	public static List<HistoricoAlimentarDTO> converterParaListaHistoricoAlimentarDTO(List<HistoricoAlimentar> historicoAlimentar) {
		return historicoAlimentar.stream().map(HistoricoAlimentarDTO::new).collect(Collectors.toList());
	}
}
