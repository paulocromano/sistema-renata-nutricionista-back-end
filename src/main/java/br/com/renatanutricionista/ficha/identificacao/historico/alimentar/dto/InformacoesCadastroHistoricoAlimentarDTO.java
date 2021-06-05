package br.com.renatanutricionista.ficha.identificacao.historico.alimentar.dto;

import java.util.List;

import br.com.renatanutricionista.medicamento.dto.MedicamentoDTO;
import br.com.renatanutricionista.medicamento.model.Medicamento;
import br.com.renatanutricionista.suplemento.dto.SuplementoDTO;
import br.com.renatanutricionista.suplemento.model.Suplemento;
import lombok.Getter;


@Getter
public class InformacoesCadastroHistoricoAlimentarDTO {

	private List<MedicamentoDTO> medicamentos;
	private List<SuplementoDTO> suplementos;
	
	
	public InformacoesCadastroHistoricoAlimentarDTO(List<Medicamento> medicamentos, List<Suplemento> suplementos) {
		this.medicamentos = MedicamentoDTO.converterParaListaMedicamentoDTOEmOrdemAlfabetica(medicamentos);
		this.suplementos = SuplementoDTO.converterParaListaSuplementoDTOEmOrdemAlfabetica(suplementos);
	}
}
