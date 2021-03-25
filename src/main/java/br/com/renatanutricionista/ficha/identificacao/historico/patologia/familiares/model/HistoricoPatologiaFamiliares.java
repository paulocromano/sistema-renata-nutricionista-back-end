package br.com.renatanutricionista.ficha.identificacao.historico.patologia.familiares.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.renatanutricionista.ficha.identificacao.historico.patologia.familiares.enums.FamiliarTemPatologia;
import br.com.renatanutricionista.ficha.identificacao.historico.patologia.familiares.enums.PatologiaFamiliares;
import br.com.renatanutricionista.ficha.identificacao.historico.patologia.familiares.por.data.model.HistoricoPatologiaFamiliaresPorData;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "historico_patologia_familiares", schema = "sistema_nutricionista")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonIgnoreProperties(value = "historicoPatologiaFamiliaresPorData")
public class HistoricoPatologiaFamiliares {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "patologia")
	@NotNull(message = "O campo Patologia dos Familiares não pode estar nulo!")
	private PatologiaFamiliares patologiaFamiliares;
	
	@NotNull(message = "O campo Pai não pode estar nulo!")
	private FamiliarTemPatologia pai;
	
	@NotNull(message = "O campo Mãe não pode estar nulo!")
	private FamiliarTemPatologia mae;
	
	@Column(name = "avos_masculinos")
	@NotNull(message = "O campo Avôs não pode estar nulo!")
	private FamiliarTemPatologia avosMasculinos;
	
	@Column(name = "avos_femininos")
	@NotNull(message = "O campo Avós não pode estar nulo!")
	private FamiliarTemPatologia avosFemininos;
	
	@NotNull(message = "O campo Tios não pode estar nulo!")
	private FamiliarTemPatologia tios;
	
	@NotNull(message = "O campo Tias não pode estar nulo!")
	private FamiliarTemPatologia tias;
	
	@ManyToOne
	@JoinColumn(name = "historico_patologia_familiares_por_data_id")
	private HistoricoPatologiaFamiliaresPorData historicoPatologiaFamiliaresPorData;


	public static class HistoricoPatologiaFamiliaresBuilder {
		
		private PatologiaFamiliares patologiaFamiliares;
		private FamiliarTemPatologia pai;
		private FamiliarTemPatologia mae;
		private FamiliarTemPatologia avosMasculinos;
		private FamiliarTemPatologia avosFemininos;
		private FamiliarTemPatologia tios;
		private FamiliarTemPatologia tias;
		private HistoricoPatologiaFamiliaresPorData historicoPatologiaFamiliaresPorData;
		
		
		public HistoricoPatologiaFamiliaresBuilder patologiaFamiliares(PatologiaFamiliares patologiaFamiliares) {
			this.patologiaFamiliares = patologiaFamiliares;
			return this;
		}
		
		public HistoricoPatologiaFamiliaresBuilder pai(FamiliarTemPatologia pai) {
			this.pai = pai;
			return this;
		}
		
		public HistoricoPatologiaFamiliaresBuilder mae(FamiliarTemPatologia mae) {
			this.mae = mae;
			return this;
		}
		
		public HistoricoPatologiaFamiliaresBuilder avosMasculinos(FamiliarTemPatologia avosMasculinos) {
			this.avosMasculinos = avosMasculinos;
			return this;
		}
		
		public HistoricoPatologiaFamiliaresBuilder avosFemininos(FamiliarTemPatologia avosFemininos) {
			this.avosFemininos = avosFemininos;
			return this;
		}
		
		public HistoricoPatologiaFamiliaresBuilder tios(FamiliarTemPatologia tios) {
			this.tios = tios;
			return this;
		}
		
		public HistoricoPatologiaFamiliaresBuilder tias(FamiliarTemPatologia tias) {
			this.tias = tias;
			return this;
		}
		
		public HistoricoPatologiaFamiliaresBuilder historicoPatologiaFamiliaresPorData(HistoricoPatologiaFamiliaresPorData historicoPatologiaFamiliaresPorData) {
			this.historicoPatologiaFamiliaresPorData = historicoPatologiaFamiliaresPorData;
			return this;
		}
		
		
		public HistoricoPatologiaFamiliares criarHistoricoPatologiaFamiliares() {
			return new HistoricoPatologiaFamiliares(null, patologiaFamiliares, pai, mae, avosMasculinos, 
					avosFemininos, tios, tias, historicoPatologiaFamiliaresPorData);
		}
	}
}
