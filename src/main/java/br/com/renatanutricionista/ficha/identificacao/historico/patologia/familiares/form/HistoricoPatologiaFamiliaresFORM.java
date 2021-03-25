package br.com.renatanutricionista.ficha.identificacao.historico.patologia.familiares.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import br.com.renatanutricionista.ficha.identificacao.historico.patologia.familiares.enums.FamiliarTemPatologiaConversao;
import br.com.renatanutricionista.ficha.identificacao.historico.patologia.familiares.enums.PatologiaFamiliaresConversao;
import br.com.renatanutricionista.ficha.identificacao.historico.patologia.familiares.model.HistoricoPatologiaFamiliares;
import br.com.renatanutricionista.ficha.identificacao.historico.patologia.familiares.por.data.model.HistoricoPatologiaFamiliaresPorData;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class HistoricoPatologiaFamiliaresFORM {

	@NotEmpty(message = "O campo Patologia dos Familiares não pode estar nulo/vazio!")
	@Size(max = 20, message = "O campo Patologia Familiares deve ter no máximo {max} caracteres")
	private String patologiaFamiliares;
	
	@NotEmpty(message = "O campo Pai não pode estar nulo/vazio!")
	@Size(max = 1, message = "O campo Pai deve ter no máximo {max} caracter")
	private String pai;
	
	@NotEmpty(message = "O campo Mãe não pode estar nulo/vazio!")
	@Size(max = 1, message = "O campo Mãe deve ter no máximo {max} caracter")
	private String mae;

	@NotEmpty(message = "O campo Avôs não pode estar nulo/vazio!")
	@Size(max = 1, message = "O campo Avôs deve ter no máximo {max} caracter")
	private String avosMasculinos;

	@NotEmpty(message = "O campo Avós não pode estar nulo/vazio!")
	@Size(max = 1, message = "O campo Avós deve ter no máximo {max} caracter")
	private String avosFemininos;
	
	@NotEmpty(message = "O campo Tios não pode estar nulo/vazio!")
	@Size(max = 1, message = "O campo Tios deve ter no máximo {max} caracter")
	private String tios;
	
	@NotEmpty(message = "O campo Tias não pode estar nulo/vazio!")
	@Size(max = 1, message = "O campo Tias deve ter no máximo {max} caracter")
	private String tias;
	
	
	public HistoricoPatologiaFamiliares converterParaHistoricoPatologiaFamiliares(HistoricoPatologiaFamiliaresPorData historicoPatologiaFamiliaresPorData) {
		FamiliarTemPatologiaConversao familiarTemPatologia = new FamiliarTemPatologiaConversao();
		
		return new HistoricoPatologiaFamiliares.HistoricoPatologiaFamiliaresBuilder()
				.patologiaFamiliares(new PatologiaFamiliaresConversao().converterParaEnumComBaseNaDescricao(patologiaFamiliares))
				.pai(familiarTemPatologia.convertToEntityAttribute(pai))
				.mae(familiarTemPatologia.convertToEntityAttribute(mae))
				.avosMasculinos(familiarTemPatologia.convertToEntityAttribute(avosMasculinos))
				.avosFemininos(familiarTemPatologia.convertToEntityAttribute(avosFemininos))
				.tios(familiarTemPatologia.convertToEntityAttribute(tios))
				.tias(familiarTemPatologia.convertToEntityAttribute(tias))
				.historicoPatologiaFamiliaresPorData(historicoPatologiaFamiliaresPorData)
				.criarHistoricoPatologiaFamiliares();
	}
}