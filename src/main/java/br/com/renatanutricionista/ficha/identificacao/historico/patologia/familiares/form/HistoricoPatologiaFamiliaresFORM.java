package br.com.renatanutricionista.ficha.identificacao.historico.patologia.familiares.form;

import javax.validation.constraints.NotNull;

import br.com.renatanutricionista.ficha.identificacao.historico.patologia.familiares.enums.PatologiaFamiliares;
import br.com.renatanutricionista.ficha.identificacao.historico.patologia.familiares.model.HistoricoPatologiaFamiliares;
import br.com.renatanutricionista.ficha.identificacao.historico.patologia.por.data.familiares.model.HistoricoPatologiaFamiliaresPorData;
import br.com.renatanutricionista.utils.enums.resposta.RespostaUtils;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@EqualsAndHashCode(of = "patologiaFamiliares")
public class HistoricoPatologiaFamiliaresFORM {

	@NotNull(message = "O campo da Patologia dos Familiares não pode ser nulo!")
	private PatologiaFamiliares patologiaFamiliares;
	
	@NotNull(message = "O campo Pai não pode ser nulo!")
	private RespostaUtils pai;
	
	@NotNull(message = "O campo Mãe não pode ser nulo!")
	private RespostaUtils mae;

	@NotNull(message = "O campo Avôs não pode ser nulo!")
	private RespostaUtils avosMasculinos;

	@NotNull(message = "O campo Avós não pode ser nulo!")
	private RespostaUtils avosFemininos;
	
	@NotNull(message = "O campo Tios não pode ser nulo!")
	private RespostaUtils tios;
	
	@NotNull(message = "O campo Tias não pode ser nulo!")
	private RespostaUtils tias;
	
	
	public HistoricoPatologiaFamiliares converterParaHistoricoPatologiaFamiliares(HistoricoPatologiaFamiliaresPorData historicoPatologiaFamiliaresPorData) {
		
		return new HistoricoPatologiaFamiliares.Builder()
				.patologiaFamiliares(patologiaFamiliares)
				.pai(pai)
				.mae(mae)
				.avosMasculinos(avosMasculinos)
				.avosFemininos(avosFemininos)
				.tios(tios)
				.tias(tias)
				.historicoPatologiaFamiliaresPorData(historicoPatologiaFamiliaresPorData)
				.build();
	}
}
