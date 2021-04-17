package br.com.renatanutricionista.medicamento.dto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.renatanutricionista.medicamento.model.Medicamento;
import br.com.renatanutricionista.utils.FormatacaoUtils;
import lombok.Getter;


@Getter
public class MedicamentoDTO implements Comparable<MedicamentoDTO> {

	private Integer id;
	private String nome;
	
	
	public MedicamentoDTO(Medicamento medicamento) {
		id = medicamento.getId();
		nome = medicamento.getNome();
	}
	
	
	@Override
	public int compareTo(MedicamentoDTO outroMedicamento) {
		return FormatacaoUtils.COLLATOR.compare(nome, outroMedicamento.getNome());
	}
	
	
	public static List<MedicamentoDTO> converterParaListaMedicamentoDTOEmOrdemAlfabetica(List<Medicamento> medicamentos) {
		return medicamentos.stream().map(MedicamentoDTO::new).sorted().collect(Collectors.toList());
	}
	
	
	public static Set<MedicamentoDTO> converterParaSetMedicamentoDTOEmOrdemAlfabetica(Set<Medicamento> medicamentos) {
		return medicamentos.stream().map(MedicamentoDTO::new).sorted().collect(Collectors.toSet());
	}
}
