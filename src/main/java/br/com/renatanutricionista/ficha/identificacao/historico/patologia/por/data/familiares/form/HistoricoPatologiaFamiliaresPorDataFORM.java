package br.com.renatanutricionista.ficha.identificacao.historico.patologia.por.data.familiares.form;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import br.com.renatanutricionista.exception.custom.PacienteException;
import br.com.renatanutricionista.ficha.identificacao.historico.patologia.familiares.enums.PatologiaFamiliares;
import br.com.renatanutricionista.ficha.identificacao.historico.patologia.familiares.form.HistoricoPatologiaFamiliaresFORM;
import br.com.renatanutricionista.ficha.identificacao.historico.patologia.familiares.model.HistoricoPatologiaFamiliares;
import br.com.renatanutricionista.ficha.identificacao.historico.patologia.por.data.familiares.model.HistoricoPatologiaFamiliaresPorData;
import br.com.renatanutricionista.paciente.model.Paciente;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class HistoricoPatologiaFamiliaresPorDataFORM {

	@Size(max = 500, message = "o campo Observação deve ter no máximo {max} caracteres!")
	private String observacao;
	
	@Valid
	private Set<HistoricoPatologiaFamiliaresFORM> patologiasFamiliares;
	
	
	public HistoricoPatologiaFamiliaresPorData converterParaHistoricoPatologiaFamiliaresPorData(Paciente paciente) {
		validarSetPatologiasDosFamiliares();
		
		return new HistoricoPatologiaFamiliaresPorData.HistoricoPatologiaFamiliaresPorDataBuilder()
				.observacao(observacao)
				.dataHoraCadastroPatologiasFamiliares(LocalDateTime.now())
				.paciente(paciente)
				.criarHistoricoPatologiaFamiliaresPorData();
	}
	
	
	private void validarSetPatologiasDosFamiliares() {
		int tamanhoSetPatologiaFamiliares = PatologiaFamiliares.values().length;
		
		if (patologiasFamiliares.size() != tamanhoSetPatologiaFamiliares)
			throw new PacienteException("A Lista deve conter os " + tamanhoSetPatologiaFamiliares 
					+ " tipos de Patologias dos Familiares!");
	}
	
	
	public Set<HistoricoPatologiaFamiliares> gerarListaHistoricoPatologiaFamiliares(HistoricoPatologiaFamiliaresPorData historicoPatologiaFamiliaresPorData) {
		return patologiasFamiliares.stream().map(patologiaFamiliares -> 
			patologiaFamiliares.converterParaHistoricoPatologiaFamiliares(historicoPatologiaFamiliaresPorData))
			.collect(Collectors.toSet());
	}
}
